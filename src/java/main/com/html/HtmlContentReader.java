package com.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

public class HtmlContentReader {

	private String urlString;

	public HtmlContentReader(String urlString) {
		this.urlString = urlString;
	}

	public BufferedReader readHtmlContent() {
		BufferedReader br = null;
		URL url;

		try {
			// System.setProperty("http.proxyHost", "proxy.eng.it");
			// System.setProperty("http.proxyPort", "3128");
			url = new URL(urlString);

			// SocketAddress sa = new InetSocketAddress("proxy.eng.it", 3128);
			// Type type = Proxy.Type.HTTP;

			// Proxy proxy = new Proxy(type, sa);
			// HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					String proxyPassword = "Crossfire667";
					return new PasswordAuthentication("alepegor", proxyPassword.toCharArray());
				}
			});

			conn.setConnectTimeout(0);
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			// br.close();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return br;
	}

}
