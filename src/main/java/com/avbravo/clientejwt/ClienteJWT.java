/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.clientejwt;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import javax.ws.rs.core.Response;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author avbravo
 */
public class ClienteJWT {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void run() throws Exception {

        try {

         connect();

        } finally {
            close();
        }
    }

    private void close() throws IOException {
        httpClient.close();
    }

   public String getTokens() {
        String token = "";
        try {

            HttpPost post = new HttpPost("http://localhost:8080/jwt-provider/auth");

            // add request parameter, form parameters
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("username", "user1"));
            urlParameters.add(new BasicNameValuePair("password", "user1"));
            urlParameters.add(new BasicNameValuePair("issuer", "http://apuntesdejava.com"));
            urlParameters.add(new BasicNameValuePair("public-key", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAprklfylWG4UCFvI4TIXsHB3dZlig1zlsOZWqEqrD3T9dV+PA5XKqL3sujpAiXRZM2fR7Qc8V9VcnuRvph+ihNs77imIKAH29+gPoB4Aq48iiUPWU5B7AzmJqLVgdYMuzYPy1emfXyk2oYXoHnc+6eGJSHidb5KqnM3e662ZTDTahXAS1cQKvYXqGxExaI+DSHEwTglGN+n4suUkW4Vt0KOYkN0gFPCf4wKbXZZfiosF59cjAQ/YVE2EwXQ8KCDGpTh3Uy4vkz+wX3cmEOAzPU0SddFXr3u5Zm3xf1BCC1EqLsGqbx2vOOeBNW4lOrRX2HpgBjM+ZYS0ZjtOwC+tc/QIDAQAB"));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse response = httpClient.execute(post)) {
                token = EntityUtils.toString(response.getEntity());
                System.out.println("Tokens= ");
                System.out.println("----------------------------------------");                
                System.out.println(token);
                System.out.println("----------------------------------------");
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception ex) {
            System.out.println("getJWT() " + ex.getLocalizedMessage());
        }
        return token;
    }

    public Response connect() throws Exception {

        Client client = ClientBuilder.newClient();
        WebTarget echoEndpointTarget = ClientBuilder.newClient().target("http://localhost:8080/web-app/resources/ping/secure");

        Response response = echoEndpointTarget.request(TEXT_PLAIN).header(HttpHeaders.AUTHORIZATION, "Bearer " + getTokens()).get();

        if (response.getStatus() == HttpURLConnection.HTTP_OK) {
            String reply = response.readEntity(String.class);
            System.out.println("ok " + reply);
        } else {
            System.out.println("no se conecto");
        }

        // Must return hello, user={token upn claim}
        return response;
    }

}
