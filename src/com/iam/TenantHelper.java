package com.iam;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import org.forgerock.opendj.ldap.ErrorResultException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TenantHelper {
	public String SetTenantAttributesforCreation(String tenantalias) throws JsonProcessingException{
		TenantAttributes tenant=new TenantAttributes();
		ObjectMapper mapper = new ObjectMapper();
		Random random=new Random();
		int r=random.nextInt(100000);
        String tenantid = UUID.randomUUID().toString();
        String uid = UUID.randomUUID().toString();
		tenant.setCn("tenant"+r);
		tenant.setSn("tyco"+r);
		tenant.setTenantAlias(tenantalias);
		tenant.setUsername(tenantid);
		tenant.setUserpassword("tyco@123");
		tenant.setOu(tenantid);
		tenant.setTenantId(tenantid);
		tenant.setTenantAdmin(uid);
		tenant.setUid(uid);
		String data=mapper.writeValueAsString(tenant);
		return data;
	}

	public void createTenantTree(String tenantId,String uid) throws IOException, ErrorResultException 
	{
		String ldap = new TenantTreeTemplate().updateTemplate("/Resources/TenantTree.ldif", tenantId);
		LDAPConnections ldap_connections=new LDAPConnections();
		ldap_connections.LoadLDIF(ldap);
		LDAPConnections.addUserToTenant(tenantId, uid);
		LDAPConnections.addUser(tenantId, uid);
	}
}
