# Spring-Rest
Pada bagian ini kita akan mencoba membuat suatu User Management Module yang terdiri dari 
a. User-Relative = One To Many
b. Role
c. Menu
Hubungan antara User dan Role, serta Menu dan Role memiliki hubungan ManyToMany. 
kemudian kita akan membuat persistenece management yang kemudian fungsionalnya akan kita expose dengan mengunakan rest technology.

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
1. Buat Spring Boot application dan tambahkan dependencies sesuai di atas.
2. Buat package berdasarkan tujuan masing masing, contoh : model, repository, service, impl, controller
3. Buat persistence model, pada kasus ini membuat 3 jenis model yaitu User, Role, Menu dan Relative
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
```
@Repository
public interface UserRepository extends JpaRepository<UserBean, Long>{
	
	public Optional<UserBean> findById(Long id);
	
	@Query(value="select a from UserBean a where a.name= :name and a.email = :email", nativeQuery=false)
	public List<UserBean> findUserCriteria(@Param("name") String name, @Param("email") String email);
}
```
Spring data memiliki fiture antara lain :
a. Anotation @Repository
b. findBy ....
c  @Param untuk inject value pada query line, dengan tanda :
d. Auto build in insert, update dan delete default
e. Hql    : @Query(value="select a  from UserBean a", nativeQuery=false)
f. Native : @Query(value="select a.* from gn_user a", nativeQuery=true)
g. Gunakan @Modifying untuk delete dan update secara manual (bukan fungsi build in)

6. Buat Class Service dan Service Implementation dan terapkan Transaction Propagation
Terapkan @Service untuk menandakan ini merupakn service (@Service = Producer)
Terapkan @Autowire untuk meng-inject object (@Autowire = Consumer)

Beberapa Transactional Umum yang di support oleh Spring
Propagation.SUPPORTS
Propagation.REQUIRED
Propagation.REQUIRES_NEW
Propagation.NEVER
Propagation.MANDATORY

7. Agar fungsi kita dapat di access dari luar, kita perlu membuat suatu framework Rest sebagai endpoint. 
a. Buat Suatu Class dan pasang anotation @RestController. 
b. Pasang @RequestMapping untuk url mapping. @RequestMapping dapat di pasang di class maupun method
c. Untuk rest terdapat beberapa macam HttpMethod antara lain POST, GET, DEL, PUT dan masing banyak lagi tapi, yang umumnya di pakai
adalah yang telah di sebautkan di atas
Fungsi dan ciri khas HttpMethod :
a. GET : Untuk mengambil data @GetMapping("/user/{id}")
b. POST : Untuk menyimpan data @PostMapping("/user")
c. DEL : Untuk menghapus data @DelMapping("/user/{id}")
d. PUT : Untuk mengubah data @PutMapping("/user")

Untuk mengirim data dari sisi client ke server, selain alamat url kita juga dapat memberikan :
a. Path url => http://localhost:8080/user/45
  http://localhost:8080/user/ = Url module
  45 = User Id yang sifatnya dinamis(dapt di ganti ganti tergantung data yang di pilih)
  Pada sisi server kita dapat menggunakan @PathVariable untuk mengambil data tersebut
  ```
  @GetMapping("/user/{id}")
  public Result getUser(@PathVariable("id") Long id) {
  ```
b. Query url => http://localhost:880/user?name=Constantine&email=constantine@gmail.com
   http://localhost:8080/user/ = Url module
   ?name=btpn&email=constantine@gmail.com = Query
   Pad sisi server kita dapat menggunakan @RequestParam("name") untuk mengambil data yang di kirim
   ```
   @GetMapping("/users")
   public Result getUsers(@RequestParam("name") String name, @RequestParam("email") String email){
   ``` 	
c. Body / Payload => http://localhost:8080/user dengan body {"name":"Constantine", "email":"constantine@gmail.com"}
   http://localhost:8080/user/ = Url module
   {"name":"Constantine", "email":"constantine@gmail.com"} => Body atau payload
   Pad sisi server kita dapat menggunakan @RequestBody untuk mengambil data body yang di kirim    	
   ```
   @PostMapping("/user")
   public Result saveUser(@RequestBody UserBean bean) {
   ```
