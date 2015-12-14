package it.guideland.app.prove;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

public class HtppClientTest {

	public static void main(){
		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("/api/login");
		request.setHeader("X-Username", "");
	}
}
