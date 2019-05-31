package com.elvino.rest.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gn_relative")
public class RelativeBean {
	private Long id;
	private String name;
	private UserBean user;
	private AuditTrail auditTrail;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	@Column(name="name")
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	public UserBean getUser() { return user; }
	public void setUser(UserBean user) { this.user = user; }
	
	//	Bisa juga di Object AuditTrail tidak mapping ke colum, tapi saat di implementasinya di mapping
//	@AttributeOverrides(value = {
//			@AttributeOverride(name = "addressLine1", column = @Column(name = "house_number")),
//	        @AttributeOverride(name = "addressLine2", column = @Column(name = "street"))
//	})
	@Embedded
	public AuditTrail getAuditTrail() { return auditTrail; }
	public void setAuditTrail(AuditTrail auditTrail) { this.auditTrail = auditTrail; }
}
