package com.iam;

import java.util.UUID;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/device")
@Api(value = "/identity/devices", description = "Operations related to Device")
public class Device 
{
	  
	  ClientBuilder clientBuilder = ClientBuilder.newBuilder();
	  ClientConfig config = new ClientConfig(); 
	  Client client=clientBuilder.withConfig(config).build();
	  Tokens tok=new Tokens();
	  ObjectMapper mapper=new ObjectMapper();
	  @GET
	  @ApiOperation(value = "Create Device", notes = "Create Device")
	  @ApiResponses(value = {@ApiResponse(code = 201, message = "Device created successfully"),
	  @ApiResponse(code = 400, message = "Invalid input"),})
	  @Path("/createDevice")
	  @Produces("application/json")
	  public Response createDevice(@ApiParam(value = "JSON object having device data", required = true) DeviceAttributes device) throws JSONException, JsonProcessingException
	  {
		  //DeviceHelper dev=new DeviceHelper();
		  String token=null;  
		  //String data=dev.setDeviceAttributesforCreation();*/
		  //DeviceAttributes device=new DeviceAttributes();
		  String uuid = UUID.randomUUID().toString();
          device.setUsername(uuid);
          device.setUid(uuid);
		  token=tok.getTokenOnly();
		  String data=mapper.writeValueAsString(device);
		  WebTarget webtarget=client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedDevices").path("/users").queryParam("_action","create");
		  Response response=webtarget.request().header("iplanetDirectoryPro",token).header("Content-Type","application/json").post(Entity.json(data));
		  String str = response.readEntity(String.class);
		  JSONObject jsonObj = new JSONObject(str);
		  System.out.println(jsonObj);
		  String uid = jsonObj.get("uid").toString();
	      return Response.ok().entity(uid).build();
	  }
	  
	  @GET
	  @Path("/getDevice/{uuid}")
	  @Produces("application/json")
	  public Response getDevice(@PathParam("uuid")String uuid) throws JSONException{
		  String token=tok.getTokenOnly();
		  WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedDevices").path("/users/")
                  .path(uuid);
		  Response response = webTarget.request().header("iplanetDirectoryPro",token).header("Content-Type", "application/json").get();
		  String device = response.readEntity(String.class);
		  return Response.ok().entity(device).build();
	  }
	  
	  @GET
	  @Path("/deleteDevice/{uuid}")
	  @Produces("application/json")
	  public Response deleteDevice(@PathParam("uuid")String uuid) throws JSONException{
		  String token=tok.getTokenOnly();
		  WebTarget webTarget=client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedDevices").path("/users/").path(uuid);
		  Response response=webTarget.request().header("iplanetDirectoryPro", token).header("Content-Type", "application/json").delete();
		  String resp=response.readEntity(String.class);
		  return Response.ok().entity(resp).build();
	  }
	  
	  @GET
	  @Path("/updateDevice/{uuid}")
	  @Produces("application/json")
	  public Response UpdateDevice(@PathParam("uuid")String uuid)throws JSONException, JsonProcessingException{
		  String token=tok.getTokenOnly();
		  DeviceHelper device=new DeviceHelper();
          String data = device.setDeviceAttributesforUpdation(uuid);
          System.out.println(data);
		  WebTarget webTarget=client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedDevices").path("/users/").path(uuid);
		  Response response= webTarget.request().header("iplanetDirectoryPro",token).header("Content-Type","application/json").put(Entity.json(data));
		  String resp=response.readEntity(String.class);
		  return Response.ok().entity(resp).build();
	  }
 }

