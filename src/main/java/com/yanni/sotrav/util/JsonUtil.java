package com.yanni.sotrav.util;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mongodb.DBObject;

public class JsonUtil {
	
	
//	public static <T> T deserialize(DBObject dbObject, Class<T> entityClass) {
//		String str = dbObject.toString();
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		try {
//			return unmarshalFromJSON(objectMapper, str, entityClass);
//		}
//		catch(Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public static <T> T unmarshalFromJSON(ObjectMapper objectMapper, String json, Class<T> objectClass) throws IOException {
		return objectMapper.readValue(json, objectClass);
	}
	
	static String serialize(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return marshalToJSON(objectMapper, object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String marshalToJSON(ObjectMapper objectMapper, Object object) throws IOException {
		StringWriter writer = new StringWriter();
		objectMapper.writeValue(writer, object);
		writer.close();
		return writer.toString();
	}
	
	public static String marshalToJSON(Object object) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return marshalToJSON(objectMapper, object);
	}


	public static <T> T unmarshalFromJSON(String json, Class<T> objectClass) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return unmarshalFromJSON(objectMapper, json, objectClass);
	}

}
