package com.iam;

//import java.text.ParseException;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/tokens1")
public class Tokens1 {
  private Client client;
  ClientBuilder clientBuilder = ClientBuilder.newBuilder();
  ClientConfig config = new ClientConfig();
  
  //public static final String USERNAME="amadmin";
  //public static final String PASSWORD="TycoNET12";
  
  
  @GET
  @Path("/sso1")
  @Produces("text/plain")
  public Response getSSOToken(@PathParam("username") String username,
		  @PathParam("password") String password)
  {
	  client = clientBuilder.withConfig(config).build();
	  WebTarget webTarget = client.target("http://openam.dev.project.net:8080/openam/json/authenticate");
      Response response = webTarget.request().header("X-OpenAM-Username",username).header("X-OpenAM-Password", password)
              .header("Content-Type", "application/json").post(Entity.json(""));
      String str = response.readEntity(String.class);
      System.out.println(str);
      JSONObject jsonObj = null;
      String tokenId = null;
          try {
              jsonObj = new JSONObject(str);
              tokenId = jsonObj.get("tokenId").toString();
          } catch (JSONException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      return Response.ok().entity(str).build();
    		  //response.readEntity(SSOTokenResponse.class);   
  }
}
