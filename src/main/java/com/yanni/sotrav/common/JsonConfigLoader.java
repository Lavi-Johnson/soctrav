package com.yanni.sotrav.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class JsonConfigLoader {

	public static <T> T load(final InputStream inputStream, final Class<T> clazz) {
		try {
			if (inputStream != null) {
				final Gson gson = new Gson();
				final BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				return gson.fromJson(reader, clazz);
			}
		} catch (final Exception e) {
		}
		return null;
	}

}
