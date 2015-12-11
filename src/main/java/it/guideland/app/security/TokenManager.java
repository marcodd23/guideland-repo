package it.guideland.app.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.guideland.app.model.User;

@Service
public class TokenManager {
	
	Logger logger = LoggerFactory.getLogger(TokenManager.class);

	private static final String HMAC_ALGO = "HmacSHA256";
	private static final String SEPARATOR = ".";
	private static final String SEPARATOR_SPLITTER = "\\.";
	private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

	private final Mac hmac;

	//Ho definito @Value sul parametro del costruttore, per obbligare a iniettarlo prima che 
	//venga eseguito il costruttore, altrimenti sarebbe stato null all' interno del costruttore
	// e occorre anche mettere @Autowired
	@Autowired
	public TokenManager(@Value("${token.secretkey}") String secretKey) {
		byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
		try {
			hmac = Mac.getInstance(HMAC_ALGO);
			hmac.init(new SecretKeySpec(secretKeyBytes, HMAC_ALGO));
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException("Failed to initialize HMAC: " + e.getMessage());
		}
	}

	public String createTokenForUser(User user) {
		TokenData userTokenData = new TokenData();
		userTokenData.setId(user.getUserId());
		userTokenData.setUsername(user.getUsername());
		userTokenData.setPassword(user.getAccount().getPassword());
		userTokenData.setExpirationTime(System.currentTimeMillis() + TEN_DAYS);
		userTokenData.setRole(user.getRole());
		byte[] userTokenDataBytes = toJSON(userTokenData);
		byte[] hash = createHmac(userTokenDataBytes);
		StringBuilder sb = new StringBuilder();
		sb.append(toBase64(userTokenDataBytes));
		sb.append(SEPARATOR);
		sb.append(toBase64(hash));
		return sb.toString();
	}

	public TokenData validateUserFromToken(String token) {
		logger.debug(">>>>>>>>>>>>>>>>>>> TokenManger.validateUserFromToken <<<<<<<<<<<<<<<<<<");
		String[] parts = token.split(SEPARATOR_SPLITTER);
		if (parts.length == 2 && parts[0].length() > 0 && parts[1].length() > 0) {

			byte[] userBytes = fromBase64(parts[0]);
			byte[] hashBytes = fromBase64(parts[1]);

			boolean validHash = Arrays.equals(createHmac(userBytes), hashBytes);
			if (validHash) {
				TokenData tokenData = (TokenData) fromJSON(userBytes, TokenData.class);
				if (new Date().getTime() < tokenData.getExpirationTime()) {
					return tokenData;
				}

			}
		}
		return null;
	}

	private Object toBase64(byte[] content) {
		return DatatypeConverter.printBase64Binary(content);
	}

	private byte[] fromBase64(String content) {
		return DatatypeConverter.parseBase64Binary(content);
	}

	private synchronized byte[] createHmac(byte[] userTokenDataBytes) {
		return hmac.doFinal(userTokenDataBytes);
	}

	private byte[] toJSON(TokenData tokenData) {
		try {
			return new ObjectMapper().writeValueAsBytes(tokenData);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

	private Object fromJSON(byte[] userBytes, Class<?> type) {
		try {
			return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes), type);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
