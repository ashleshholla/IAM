package com.iam;

public class TenantAttributes {
	private String username;
	private String userpassword;
	private String cn;
	private String sn;
	private String tenantAlias;
	private String email;
	private String ou;
	private String tenantAdmin;
	private String uid;
	private String tenantId;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantAdmin() {
		return tenantAdmin;
	}
	public void setTenantAdmin(String tenantAdmin) {
		this.tenantAdmin = tenantAdmin;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getTenantAlias() {
		return tenantAlias;
	}
	public void setTenantAlias(String tenantAlias) {
		this.tenantAlias = tenantAlias;
	}

}
