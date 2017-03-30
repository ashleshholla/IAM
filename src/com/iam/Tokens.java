package com.iam;

//import java.text.ParseException;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/tokens")
public class Tokens {
  private Client client;
  ClientBuilder clientBuilder = ClientBuilder.newBuilder();
  ClientConfig config = new ClientConfig();
  
  public static final String USERNAME="amadmin";
  public static final String PASSWORD="TycoNET12";
  String tokenId;
  @GET
  @Path("/sso")
  @Produces("application/json")
  public Response getSSOToken() throws JSONException{
	  client = clientBuilder.withConfig(config).build();
	  WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json/authenticate");
      Response response = webTarget.request().header("X-OpenAM-Username",USERNAME).header("X-OpenAM-Password", PASSWORD)
              .header("Content-Type", "application/json").post(Entity.json(""));
      String str = response.readEntity(String.class);
      //System.out.println(str);
      JSONObject jsonObj = null;
          jsonObj = new JSONObject(str);
		  tokenId = jsonObj.get("tokenId").toString();
      return Response.ok().entity(str).build();
    		  //response.readEntity(SSOTokenResponse.class);   
  }
  public String getTokenOnly() throws JSONException{
	/*  Response resp=getSSOToken();
	  String str = resp.readEntity(String.class);
	  JSONObject json = null;
	  String token=null;
	  json = new JSONObject(str);
	  token = json.get("tokenId").toString();*/
	  Response resp=getSSOToken();
	  String tokenJsonString = resp.getEntity().toString();
	  JSONObject tokenJson = new JSONObject(tokenJsonString);
	  String token = tokenJson.get("tokenId").toString();
	  return token;
  }
}
