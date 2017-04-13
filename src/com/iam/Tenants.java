package com.iam;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.forgerock.opendj.ldap.ErrorResultException;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/tenant")
public class Tenants {
	ClientBuilder clientBuilder = ClientBuilder.newBuilder();
	ClientConfig config = new ClientConfig();
	Client client = clientBuilder.withConfig(config).build();
	Tokens tok=new Tokens();
	ObjectMapper mapper=new ObjectMapper();
	@GET
	@Path("/createTenant/{tenantalias}")
	@Produces("application/json")
	public Response createTenant(@PathParam("tenantalias")String tenantalias) throws JSONException, ErrorResultException, IOException
	{
		TenantHelper tenant=new TenantHelper();
		String data=tenant.SetTenantAttributesforCreation(tenantalias);
		String token=tok.getTokenOnly();
		WebTarget webTarget=client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedTenants").path("/users").queryParam("_action","create");
		Response response=webTarget.request().header("iplanetDirectoryPro", token).header("Content-Type", "application/json").post(Entity.json(data));
		String str = response.readEntity(String.class);
		  JSONObject jsonObj = new JSONObject(data);
		  String tenantId = jsonObj.getString("tenantId").toString();
		  String uid = jsonObj.getString("uid").toString();
		  tenant.createTenantTree(tenantId,uid);
		  /*CreatedTenantResponse re=new CreatedTenantResponse();
		  re.setTenantId(tenantId);
		  re.setUid(uid);*/
		  //mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		  //String tenantJson = mapper.writeValueAsString(re);
	      return Response.ok().entity(str).build();
	}
	
	@GET
	@Path("/getTenant/{tenantid}")
	@Produces("application/json")
	public Response getTenantAttributes(@PathParam("tenantid")String tenantid) throws JSONException{
		Tokens tok=new Tokens();
		String token=tok.getTokenOnly();
		WebTarget webTarget=client.target("http://openam.dev.project.net:8080/openam/json").path("/ManagedTenants").path("/users/").path(tenantid);
		Response response=webTarget.request().header("iplanetDirectoryPro", token).header("Content-Type","application/json").get();
		  String str = response.readEntity(String.class);
	      return Response.ok().entity(str).build();	
	}
}
