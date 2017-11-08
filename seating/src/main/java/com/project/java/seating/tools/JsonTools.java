package com.project.java.seating.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTools<T> {
	private Class<T> type;
	
	public JsonTools(Class<T> type) {
		super();
		this.type = type;
	}

	public String createJson(T object) {
		ObjectMapper mapper = new ObjectMapper();

		ByteArrayOutputStream str = new ByteArrayOutputStream();
		;
		// Object to JSON in file
		try {
			mapper.writeValue(str, object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final byte[] data = str.toByteArray();

		// Object to JSON in String
		return new String(data);
	}

	public T createObject(String json){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
