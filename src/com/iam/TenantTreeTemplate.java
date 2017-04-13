package com.iam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TenantTreeTemplate {
	public String updateTemplate(String templateLocation, String id) throws IOException{
		String ldifString;
		InputStream inputStream=TenantTreeTemplate.class.getResourceAsStream(templateLocation);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer ldifStringBuffer = new StringBuffer();
		String line;
		while ((line=bufferedReader.readLine()) != null)
		{
			ldifStringBuffer.append(line);
			ldifStringBuffer.append("\r\n");
		}
		ldifString = ldifStringBuffer.toString().replaceAll("<ID>", id);
		return ldifString;
	}
}
