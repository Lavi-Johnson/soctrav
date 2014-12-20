package com.yanni.sotrav.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonConfigLoader {

	public static <T> T load(final InputStream inputStream, final Class<T> clazz) {
		try {
			if (inputStream != null) {
				final Gson gson = buildGson();
				final BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				return gson.fromJson(reader, clazz);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Gson buildGson(){
		// Creates the json object which will manage the information received 
		GsonBuilder builder = new GsonBuilder(); 

		// Register an adapter to manage the date types as long values 
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
		
		   @Override
		   public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		      return new Date(json.getAsJsonPrimitive().getAsLong()); 
		   }
		});

		Gson gson = builder.create();
		return gson;
	}

}
