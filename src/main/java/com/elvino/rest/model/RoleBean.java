package com.elvino.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="gn_role")
public class RoleBean {
	private Long id;
	private String code;
	private String name;
	private AuditTrail auditTrail;
	private List<MenuBean> menus = new ArrayList<MenuBean>();
	private List<UserBean> users = new ArrayList<UserBean>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	@Column(name="code")
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
	
	@Column(name="name")
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
//	Bisa juga di Object AuditTrail tidak mapping ke colum, tapi saat di implementasinya di mapping
//	@AttributeOverrides(value = {
//			@AttributeOverride(name = "addressLine1", column = @Column(name = "house_number")),
//	        @AttributeOverride(name = "addressLine2", column = @Column(name = "street"))
//	})
	@Embedded
	public AuditTrail getAuditTrail() { return auditTrail; }
	public void setAuditTrail(AuditTrail auditTrail) { this.auditTrail = auditTrail; }
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "gn_role_menu", 
		joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "menu_id", nullable = false, updatable = false) }
	)
	public List<MenuBean> getMenus() { return menus; }
	public void setMenus(List<MenuBean> menus) { this.menus = menus; }

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "gn_role_user", 
		joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }
	)
	public List<UserBean> getUsers() { return users; }
	public void setUsers(List<UserBean> users) { this.users = users; }
}
