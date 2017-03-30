package com.iam;
import java.util.Scanner;
import org.json.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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


/*	public void SetAttributes(){
		Scanner sn=new Scanner(System.in);
		System.out.println("Enter serialNumber:");
		serialNumber=sn.nextLine();
		System.out.println("Enter password:");
		userPassword=sn.nextLine();
		System.out.println("Enter DeviceContainsSecureKeyStorage or not:");
		DeviceContainsSecureKeyStorage=sn.nextLine();
		System.out.println("Enter DeviceManufacturer:");
		DeviceManufacturer=sn.nextLine();
		System.out.println("Enter DeviceType:");
		DeviceType=sn.nextLine();
		System.out.println("Enter gid:");
		gid=sn.nextLine();
		System.out.println("Enter username:");
		username=sn.nextLine();
		System.out.println("Enter cn:");
		cn=sn.nextLine();
		System.out.println("Enter inetUserStatus(active or inactive):");
		inetUserStatus=sn.nextLine();
	}*/
	
/*	public JSONObject DeviceAttributesJson(){
		SetAttributes();
		JSONObject data=null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try{
			String str="{serialNumber:"+serialNumber+",password:"+userPassword+",DeviceContainsSecureKeyStorage:"+DeviceContainsSecureKeyStorage+",DeviceManufacturer:"+DeviceManufacturer+",DeviceType:"+DeviceType+",gid:"+gid+",username:"+username+",cn:"+cn+",inetUserStatus:"+inetUserStatus+"}";
			 data= new JSONObject(str);
			 System.out.println(data);
		
		}
		catch(JSONException e){
			e.printStackTrace();
		}
		return data;
	}
	
	public String DeviceAttributesJsonString(){
		SetAttributes();
		String str="{serialNumber:"+serialNumber+",password:"+userPassword+",DeviceContainsSecureKeyStorage:"+DeviceContainsSecureKeyStorage+",DeviceManufacturer:"+DeviceManufacturer+",DeviceType:"+DeviceType+",gid:"+gid+",username:"+username+",cn:"+cn+",inetUserStatus:"+inetUserStatus+"}";
	    System.out.println(str);
		return str;
	}*/
}

