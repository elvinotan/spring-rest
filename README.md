# Spring-Rest
Pada bagian ini kita akan mencoba membuat suatu User Management Module yang terdiri dari : </br> 
a. User-Relative = One To Many</br>
b. Role</br>
c. Menu</br>
Hubungan antara User dan Role, serta Menu dan Role memiliki hubungan ManyToMany. 
Kemudian kita akan membuat persistenece management yang kemudian fungsionalnya akan kita expose dengan mengunakan rest technology.

Untuk mempermudah development kita menggunakan database memory yaitu H2, dgn menggunkan H2 otomatis table akan di create pada saat server up
,namun table hanya ada apabila aplikasi hidup, dan akan di re-create saat aplikasi di restart.

Bila ingin menyediakan initilize data, kita perlu menyediakan data.sql se-level dengan application.properties yang otomatis akan 
di execute pada saat server up 

# Dependencies
Actuator</br>
Spring Data</br>
Web</br>
H2</br>

# How to
0. Konfigurasi
```
server.port=8080

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

#http://localhost:8080/h2-console 
spring.h2.console.enabled=true

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type=TRACE
```
1. Buat Spring Boot application dan tambahkan dependencies sesuai di atas.
2. Buat package berdasarkan tujuan masing masing, contoh : model, repository, service, impl, controller
3. Buat persistence model, pada kasus ini membuat 3 jenis model yaitu User, Role, Menu dan Relative
```
@Entity
@Table(name="gn_user")
public class UserBean {
	private Long id;
	private String name;
	private String email;
	private String password;
	private AuditTrail auditTrail;
	private List<RelativeBean> relatives = new ArrayList<RelativeBean>();
	private List<RoleBean> roles = new ArrayList<RoleBean>();
	
	public UserBean() {
	}
	
	public UserBean(Long id) {
		this.id = id;
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	@NotNull
	@Column(name="email")
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	@NotNull
	@Column(name="name")
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@NotNull
	@Column(name="password")
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
//	Bisa juga di Object AuditTrail tidak mapping ke colum, tapi saat di implementasinya di mapping
//	@AttributeOverrides(value = {
//			@AttributeOverride(name = "addressLine1", column = @Column(name = "house_number")),
//	        @AttributeOverride(name = "addressLine2", column = @Column(name = "street"))
//	})
	@Embedded
	public AuditTrail getAuditTrail() { return auditTrail; }
	public void setAuditTrail(AuditTrail auditTrail) { this.auditTrail = auditTrail; }
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
	@OrderBy("name asc")
	public List<RelativeBean> getRelatives() { return relatives; }
	public void setRelatives(List<RelativeBean> relatives) { this.relatives = relatives; }
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public List<RoleBean> getRoles() { return roles; }
	public void setRoles(List<RoleBean> roles) { this.roles = roles; }
}
```
4. Untuk masing masing model akan memiliki signature method yang sama yaitu createdBy, createdDate, modifiedBy, modifiedDate. Untuk memenuhi
kebutuhan ini kita akan membuat suatu @Embeddable model yang akan di pasangkan ke masing masing model
```
@Embeddable
public class AuditTrail {
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	
	public AuditTrail() {}
	
	public AuditTrail(String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name="created_by")
	public String getCreatedBy() { return createdBy; }
	public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	public Date getCreatedDate() { return createdDate; }
	public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
	
	@Column(name="modified_by")
	public String getModifiedBy() { return modifiedBy; }
	public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	public Date getModifiedDate() { return modifiedDate; }
	public void setModifiedDate(Date modifiedDate) { this.modifiedDate = modifiedDate; }
}
```
5. Untuk memudahkan oprasional database CRUD, kita menggunakna framework spring data, yang akan di mapping ke masing-masing model
Spring data memiliki fiture antara lain :</br>
a. Anotation @Repository</br>
b. findBy ....</br>
c  @Param untuk inject value pada query line, dengan tanda :</br>
d. Auto build in insert, update dan delete default</br>
e. Hql    : @Query(value="select a  from UserBean a", nativeQuery=false)</br>
f. Native : @Query(value="select a.* from gn_user a", nativeQuery=true)</br>
g. Gunakan @Modifying untuk delete dan update secara manual (bukan fungsi build in)</br>

```
@Repository
public interface UserRepository extends JpaRepository<UserBean, Long>{
	
	public Optional<UserBean> findById(Long id);
	
	@Query(value="select a from UserBean a where a.name= :name and a.email = :email", nativeQuery=false)
	public List<UserBean> findUserCriteria(@Param("name") String name, @Param("email") String email);
}
```

6. Buat Class Service dan Service Implementation dan terapkan Transaction Propagation
Terapkan @Service untuk menandakan ini merupakn service (@Service = Producer)
Terapkan @Autowire untuk meng-inject object (@Autowire = Consumer)</br>
Beberapa Transactional Umum yang di support oleh Spring : </br>
Propagation.SUPPORTS</br>
Propagation.REQUIRED</br>
Propagation.REQUIRES_NEW</br>
Propagation.NEVER</br>
Propagation.MANDATORY</br>
```
@Service("userService")
public class UserServiceImpl extends DaoImpl implements UserService{

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public UserBean getUser(Long id) throws Exception {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserBean saveUser(UserBean bean) throws Exception {
		AuditTrail auditTrail = new AuditTrail();
		if (bean.getId() == null) {
			auditTrail.setCreatedBy("system");
			auditTrail.setCreatedDate(new Date());
		}else {
			auditTrail.setModifiedBy("system");
			auditTrail.setModifiedDate(new Date());
		}
		bean.setAuditTrail(auditTrail);
		
		for (RelativeBean relative : bean.getRelatives()) {
			relative.setAuditTrail(bean.getAuditTrail());
		}
		
		bean = userRepo.save(bean);
		for (RelativeBean relative : bean.getRelatives()) {
			relative.setUser(new UserBean(bean.getId()));
		}
		
		return bean;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(Long id) throws Exception {
		userRepo.deleteById(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<UserBean> getUsers(String name, String email) throws Exception {
		return userRepo.findUserCriteria(name, email);
	}
```

7. Agar fungsi kita dapat di access dari luar, kita perlu membuat suatu framework Rest sebagai endpoint. 
a. Buat Suatu Class dan pasang anotation @RestController. 
b. Pasang @RequestMapping untuk url mapping. @RequestMapping dapat di pasang di class maupun method
c. Untuk rest terdapat beberapa macam HttpMethod antara lain POST, GET, DEL, PUT dan masing banyak lagi tapi, yang umumnya di pakai
adalah yang telah di sebautkan di atas</br>
Fungsi dan ciri khas HttpMethod :</br>
a. GET : Untuk mengambil data @GetMapping("/user/{id}")</br>
b. POST : Untuk menyimpan data @PostMapping("/user")</br>
c. DEL : Untuk menghapus data @DelMapping("/user/{id}")</br>
d. PUT : Untuk mengubah data @PutMapping("/user")</br>
```
@RestController
@RequestMapping("/usermanagement")
public class UserManagementController extends ServiceController{

	// User Module
	
	@GetMapping("/user/{id}")
	public Result getUser(@PathVariable("id") Long id) {
		try {
			return Result.success(userService.getUser(id));
		}catch(Exception e) { return Result.error("Gagal ambil data", e.getMessage()); }
	}
	
	@PostMapping("/user")
	public Result saveUser(@RequestBody UserBean bean) {
		try {
			return Result.success(userService.saveUser(bean), 200, "Simpan Data Berhasil", "Success to save data");
		}catch(Exception e) { return Result.error("Gagal simpan data", e.getMessage()); }
	}
	
	@PutMapping("/user/{id}")
	public Result updateUser(@PathVariable("id") Long id, @RequestBody UserBean bean) {
		try {
			return Result.success(userService.saveUser(bean), 200, "Ubah Data Berhasil", "Success to update data");
		}catch(Exception e) { return Result.error("Gagal ubah data", e.getMessage()); }
	}
	
	@DeleteMapping("/user/{id}")
	public Result deleteUser(@PathVariable("id") Long id) {
		try {
			userService.deleteUser(id);
			return Result.success("Hapus Data Berhasil", "Success to delete data");
		}catch(Exception e) { return Result.error("Gagal hapus data", e.getMessage()); }
	}
	
	@GetMapping("/users")
	public Result getUsers(@RequestParam("name") String name, @RequestParam("email") String email){
		try {
			return Result.success(userService.getUsers(name, email));
		}catch(Exception e) { return Result.error("Gagal ambil data", e.getMessage()); }
	}
```

# Note

Untuk mengirim data dari sisi client ke server, selain alamat url kita juga dapat memberikan :</br>
a. Path url => http://localhost:8080/user/45</br>
  http://localhost:8080/user/ = Url module</br>
  45 = User Id yang sifatnya dinamis(dapt di ganti ganti tergantung data yang di pilih)</br>
  Pada sisi server kita dapat menggunakan @PathVariable untuk mengambil data tersebut</br>
  ```
  @GetMapping("/user/{id}")
  public Result getUser(@PathVariable("id") Long id) {
  ```
b. Query url => http://localhost:880/user?name=Constantine&email=constantine@gmail.com</br>
   http://localhost:8080/user/ = Url module</br>
   ?name=btpn&email=constantine@gmail.com = Query</br>
   Pad sisi server kita dapat menggunakan @RequestParam("name") untuk mengambil data yang di kirim</br>
   ```
   @GetMapping("/users")
   public Result getUsers(@RequestParam("name") String name, @RequestParam("email") String email){
   ``` 	
c. Body / Payload => http://localhost:8080/user dengan body {"name":"Constantine", "email":"constantine@gmail.com"}</br>
   http://localhost:8080/user/ = Url module</br>
   {"name":"Constantine", "email":"constantine@gmail.com"} => Body atau payload</br>
   Pad sisi server kita dapat menggunakan @RequestBody untuk mengambil data body yang di kirim</br>  	
   ```
   @PostMapping("/user")
   public Result saveUser(@RequestBody UserBean bean) {
   ```
