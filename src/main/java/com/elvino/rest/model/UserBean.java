package com.elvino.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
