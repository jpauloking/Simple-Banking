package com.simplebanking.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email_address", unique = true)
	private String emailAddress;
	@Column(name = "username", unique = true)
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "token")
	private String token;
	@Column(name = "code")
	private String code;
	@Column(name = "verified")
	private Boolean verified;
	@Column(name = "verified_at")
	private Date verifiedAt;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Account> accounts = new HashSet<>();
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public Date getVerifiedAt() {
		return verifiedAt;
	}
	public void setVerifiedAt(Date verifiedAt) {
		this.verifiedAt = verifiedAt;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
}
