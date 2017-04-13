package com.iam;

import org.forgerock.opendj.ldap.SearchScope;
import org.forgerock.opendj.ldap.requests.ModifyRequest;
import org.forgerock.opendj.ldap.requests.Requests;
import org.forgerock.opendj.ldap.requests.SearchRequest;
import org.forgerock.opendj.ldap.responses.SearchResultEntry;
import org.forgerock.opendj.ldif.ChangeRecord;
import org.forgerock.opendj.ldif.ConnectionChangeRecordWriter;
import org.forgerock.opendj.ldif.ConnectionEntryReader;
import org.forgerock.opendj.ldif.LDIFChangeRecordReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.forgerock.opendj.ldap.Connection;
import org.forgerock.opendj.ldap.ConnectionFactory;
import org.forgerock.opendj.ldap.DecodeException;
import org.forgerock.opendj.ldap.ErrorResultException;
import org.forgerock.opendj.ldap.ErrorResultIOException;
import org.forgerock.opendj.ldap.LDAPConnectionFactory;
import org.forgerock.opendj.ldap.LdapException;
import org.forgerock.opendj.ldap.LinkedHashMapEntry;
import org.forgerock.opendj.ldap.ModificationType;
import org.forgerock.opendj.ldap.SearchResultReferenceIOException;

public class LDAPConnections {
	public static Connection getLDAPConnection() throws ErrorResultException{
		ConnectionFactory conn=new LDAPConnectionFactory("http://opendj.dev.project.net",1389);
		Connection connection = conn.getConnection();
		connection.bind(Requests.newSimpleBindRequest("cn=Directory Manager", "tycoon".getBytes()));
		conn.close();
		return connection;
	}
	
	public static boolean doesEmailExists(String mail) throws ErrorResultException, ErrorResultIOException {
		boolean emailAlreadyExists = false;
		String keyValueCheckInOpenDJ = "(mail=" + mail + ")";
		SearchRequest searchRequest = Requests.newSearchRequest("ou=people,dc=tycoon,dc=io", SearchScope.WHOLE_SUBTREE, keyValueCheckInOpenDJ);
		try (Connection con = getLDAPConnection(); 
				final ConnectionEntryReader reader = con.search(searchRequest)) {
			if (reader.hasNext()) {
				emailAlreadyExists = true;
			}
		}
		return emailAlreadyExists;
	}
	public static String getUserIDbyMail(String mail) throws SearchResultReferenceIOException, ErrorResultException, ErrorResultIOException{
		String keyValueCheckInOpenDJ = "(mail=" + mail + ")";
		String userId = "Something went wrong. Please try again later";
		SearchRequest searchrequest = Requests.newSearchRequest("ou=people,dc=tycoon,dc=io", SearchScope.WHOLE_SUBTREE, keyValueCheckInOpenDJ);
		try (Connection connection = getLDAPConnection();
				final ConnectionEntryReader reader = connection.search(searchrequest)) {

			if (reader.hasNext()) {
				final SearchResultEntry searchResultEntry = reader.readEntry();
				userId = searchResultEntry.getAttribute("uid").firstValueAsString();
				return userId;
			} 
		}
		return userId;
	}	
	public void LoadLDIF(String ldif) throws DecodeException, ErrorResultIOException, IOException, ErrorResultException{
			Connection connection=getLDAPConnection();
			InputStream in = IOUtils.toInputStream(ldif,StandardCharsets.UTF_8);
			LDIFChangeRecordReader reader = new LDIFChangeRecordReader(in);
			ConnectionChangeRecordWriter changeRecordWriter = new ConnectionChangeRecordWriter(connection);
			while(reader.hasNext()){
				ChangeRecord changeRecord = reader.readChangeRecord();
				changeRecordWriter.writeChangeRecord(changeRecord);
			}
			reader.close();
			changeRecordWriter.close();
	}
	public static boolean addEntityToGroup(String groupDN, String entityDN) throws ErrorResultException  {
		boolean isMemberAdded;
		ModifyRequest modifyRequest = Requests.newModifyRequest(groupDN)
											  .addModification(ModificationType.ADD, "uniquemember", entityDN);

		Connection connection = getLDAPConnection();
			connection.modify(modifyRequest);
			isMemberAdded = true;

		return isMemberAdded;
	}
	public static boolean addUserToTenant(String tenantId, String userId) throws ErrorResultException {
		return addEntityToGroup(String.format("cn=Users,ou=%s,ou=Tenants,dc=tycoon,dc=io", tenantId),
								String.format("uid=%s,ou=people,dc=tycoon,dc=io", userId));
	}
	public static void addUser(String tenantId, String uid) throws ErrorResultException{
		Connection con=getLDAPConnection();
		con.add(new LinkedHashMapEntry(String.format("cn=%s,ou=Users,ou=OrganizationalTree,ou=%s,ou=Tenants,dc=tycoon,dc=io", uid, tenantId))
                .addAttribute("objectClass","top")
                .addAttribute("objectClass","groupOfUniqueNames"));
		
	}
}

	


