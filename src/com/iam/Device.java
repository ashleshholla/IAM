package com.iam;

import java.util.UUID;

//import java.text.ParseException;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import com.iam.Tokens;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
//import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
import com.iam.DeviceAttributes;
import java.util.Random;

@Path("/device")
public class Device {
	  
	  ClientBuilder clientBuilder = ClientBuilder.newBuilder();
	  ClientConfig config = new ClientConfig(); 
	  Client client=clientBuilder.withConfig(config).build();
	  @GET
	  @Path("/createDevice")
	  @Produces("application/json")
	  public Response createDevice() throws JSONException, JsonProcessingException{
		 ObjectMapper mapper = new ObjectMapper();
		  /*mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);*/
		  DeviceAttributes device=new DeviceAttributes();
		  String token=null;
		  Random r=new Random();
		  //String data=null;
          String uuid = UUID.randomUUID().toString();
          device.setUsername(uuid);
          device.setUid(uuid);
          device.setGid("hello"+r);
          device.setCn("tyco123456");
          device.setDeviceContainsSecureKeyStorage("false");
          device.setDeviceManufacturer("tyco");
          device.setUserPassword("tyco@123");
          device.setSerialNumber("1243254"+r);
          device.setDeviceType("fire");
          device.setInetUserStatus("active");
          String data = mapper.writeValueAsString(device);
		  Tokens tok=new Tokens();
		  //DeviceAttributes d=new DeviceAttributes();  
		  token=tok.getTokenOnly();
		  //System.out.println(token);
		  WebTarget webtarget=client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedDevices").path("/users").queryParam("_action","create");
		  Response response=webtarget.request().header("iplanetDirectoryPro",token).header("Content-Type","application/json").post(Entity.json(data));
		  String str = response.readEntity(String.class);
		  //System.out.println(str);
		  return Response.ok().entity(device.getUid()).build();
	  }
 }
