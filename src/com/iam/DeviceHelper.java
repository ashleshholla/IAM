package com.iam;

import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeviceHelper {
	public String setDeviceAttributesforCreation() throws JsonProcessingException{
		DeviceAttributes device=new DeviceAttributes();
		Random rand=new Random();
		int r=rand.nextInt(100000);
		ObjectMapper mapper=new ObjectMapper();
        String uuid = UUID.randomUUID().toString();
        device.setUsername(uuid);
        device.setUid(uuid);
        device.setGid("hello"+r);
        device.setCn("tyco"+r);
        device.setDeviceContainsSecureKeyStorage("false");
        device.setDeviceManufacturer("tyco");
        device.setUserPassword("tyco@123");
        device.setSerialNumber("1243254"+r);
        device.setDeviceType("fire");
        device.setInetUserStatus("active");
        device.setDescription("hello, this is just to test");
        String data = mapper.writeValueAsString(device);
        return data;
	}
	
	public String setDeviceAttributesforUpdation(String uuid) throws JsonProcessingException{
		DeviceAttributes device=new DeviceAttributes();
		Random r=new Random();
		ObjectMapper mapper=new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		device.setUid(uuid);
        device.setGid("hi"+r);
        device.setDeviceContainsSecureKeyStorage("true");
        device.setDeviceManufacturer("tyco international");
        device.setDeviceType("fire and security");
        String data = mapper.writeValueAsString(device);
        return data;
	}

}
