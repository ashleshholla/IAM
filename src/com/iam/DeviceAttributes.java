package com.iam;

public class DeviceAttributes {
	private String serialNumber;
	private String uid;
	private String userPassword;
	private String DeviceContainsSecureKeyStorage;
	private String gid;
	private String DeviceManufacturer;
	private String DeviceType;
	private String cn;
	private String inetUserStatus;
	private String username;
	private String description;
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getDeviceContainsSecureKeyStorage() {
		return DeviceContainsSecureKeyStorage;
	}

	public void setDeviceContainsSecureKeyStorage(String deviceContainsSecureKeyStorage) {
		DeviceContainsSecureKeyStorage = deviceContainsSecureKeyStorage;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getDeviceManufacturer() {
		return DeviceManufacturer;
	}

	public void setDeviceManufacturer(String deviceManufacturer) {
		DeviceManufacturer = deviceManufacturer;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getInetUserStatus() {
		return inetUserStatus;
	}

	public void setInetUserStatus(String inetUserStatus) {
		this.inetUserStatus = inetUserStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}

