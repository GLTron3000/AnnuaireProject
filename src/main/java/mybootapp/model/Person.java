package mybootapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "AZ_PERSONS")
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(nullable = false)
	private String name;
	
	@Basic
	@Column(nullable = false)
	private String firstname;
	
	@Basic
	@Column
	private String email;
	
	@Basic
	@Column
	private String website;
	
	@Basic
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column
	private String birthdate;
	
	@Basic
	@Column(nullable = false)
	private String password;
	
	@Basic
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Group currentGroup;
	
	
	public Person() {
		super();
	}

	public Person(String name, String firstname, String email, String website, String birthdate,
			String password, Group currentGroup) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.website = website;
		this.birthdate = birthdate;
		this.password = password;
		this.currentGroup = currentGroup;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Group getGroup() {
		return currentGroup;
	}

	public void setGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}
	
}
