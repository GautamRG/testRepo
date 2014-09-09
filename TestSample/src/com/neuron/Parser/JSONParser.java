package com.neuron.Parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	static InputStream is = null;

	static String json = "";

	public JSONParser() {

	}

	public JSONObject getJSONFromUrlFromJSON(String url, String jsn) {
		JSONObject jObj = null;
		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// // set http params

			HttpParams params11 = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params11, 300000);
			HttpConnectionParams.setSoTimeout(params11, 300000);

			HttpPost httpPost = new HttpPost(url);

			ByteArrayEntity baEntity = new ByteArrayEntity(jsn.getBytes("UTF8"));

			baEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

			httpPost.setEntity(baEntity);

			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);

				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			} catch (Exception e5) {

				e5.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error has occurred inside encoding");
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			System.out.println("here 3");
			Log.e("JSON", json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}

	public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {
		JSONObject jObj = null;
		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// // set http params

			HttpParams params11 = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params11, 300000);
			HttpConnectionParams.setSoTimeout(params11, 300000);

			HttpPost httpPost = new HttpPost(url);

			httpPost.setEntity(new UrlEncodedFormEntity(params));

			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			} catch (Exception e5) {

				e5.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error has occurred inside encoding");
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			System.out.println("here 3");
			Log.e("JSON", json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}

}
