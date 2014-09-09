package com.neuron.Parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

public class UserFunctions {

	private JSONParser jsonParser;

	public UserFunctions() {
		jsonParser = new JSONParser();
	}

	public JSONObject postJson(String URL, String jsn) {

		JSONObject json = jsonParser.getJSONFromUrlFromJSON(URL, jsn);

		System.out.println("Sending Json:>" + jsn);
		return json;
	}

	public JSONObject getJson(String URL) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		JSONObject json = jsonParser.getJSONFromUrl(URL, params);

		return json;
	}

}