
package ru.hospital.utils.web;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;


public class RequestService {

	private RequestService(){

	}
//------------------------------------------//
	public static Response executeGET(String targetURL, String cookie){
	    URL url;
	    HttpURLConnection connection = null;
	    try {

			url = new URL(targetURL);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			connection.setUseCaches (false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			StringBuilder response = new StringBuilder();
			while((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return new Response(response.toString(), cookie);
	    } catch (IOException e) {
			return new Response(e.toString(), cookie);
	    } finally {
			if(connection != null)
				connection.disconnect();
	    }
	}
//------------------------------------------//
	public static Response executePOST(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
			bw.write(urlParameters);
			bw.flush();
			bw.close();

			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			String cookie = connection.getHeaderField("Set-Cookie");
			return new Response(response.toString(), cookie);
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
//------------------------------------------//
	public static Response executeDELETE(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
			bw.write(urlParameters);
			bw.flush();
			bw.close();

			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			String cookie = connection.getHeaderField("Set-Cookie");
			return new Response(response.toString(), cookie);
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
//------------------------------------------//
	public static Response executePUT(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
			bw.write(urlParameters);
			bw.flush();
			bw.close();

			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			String cookie = connection.getHeaderField("Set-Cookie");
			return new Response(response.toString(), cookie);
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
//------------------------------------------//
	public static byte[] executeGETImage(String targetURL, String urlParameters, String cookie) {
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Cookie", cookie);
			InputStream is = connection.getInputStream();
			byte[] imageInByte = new byte[1024000];
			byte[] buf = new byte[1024000];
			int overall = 0;
			int resp = 0;
			int cur = 0;
			while ((resp = is.read(buf)) != -1) {
				overall += resp;
				for (int k = cur; k < cur + resp; k++) {
					imageInByte[k] = buf[k - cur];
				}
				cur = overall;
			}

			byte[] result = new byte[overall];
			System.arraycopy(imageInByte, 0, result, 0, overall);
			return result;

		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
//------------------------------------------//
	public static Response executePUTImage(String targetURL, File file, String cookie) {
		URL url;
		HttpURLConnection connection = null;
		try {
			//Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Cookie", cookie);
			connection.setRequestProperty("URIEncoding", "UTF-8");

			BufferedImage bim = ImageIO.read(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(bim, "png", out);
			byte[] imageBytes = out.toByteArray();
			OutputStream osw = connection.getOutputStream();
			osw.write(imageBytes);

			//Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			System.out.println("Response post request" + response.toString());
			String cookie2 = connection.getHeaderField("Set-Cookie");
			Response result = new Response(response.toString(), cookie2);
			return result;

		} catch (IOException e) {
			return null;
		} finally {


			if (connection != null) {
				connection.disconnect();
			}
		}
 }

}



