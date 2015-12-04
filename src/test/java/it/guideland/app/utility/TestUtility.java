package it.guideland.app.utility;

import java.nio.charset.StandardCharsets;

import org.springframework.security.crypto.codec.Base64;

public class TestUtility {

	public static String createAuthorizationHeader(String username, String password) {

		String userCredentials = username + ":" + password;
		//new Base64();
		String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes(StandardCharsets.UTF_8)));
		return basicAuth;
	}
}
