package com.lgcns.tct.sp.sub3.dto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lgcns.tct.sp.sub3.ValiableManager;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class ActionState extends State {

    private String url;
    private List<String> parameters;

    public ActionState(String name, String url, List<String> parameters) {
        super(name);
        this.url = url;
        this.parameters = parameters;
    }

    @Override
    public String run() throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.start();

        try {
            String query = makeQuery();
            //System.out.println(">> ActionState.run - query : " + url + query);
            ContentResponse contentResponse = httpClient.newRequest(url + query).method(HttpMethod.GET).send();
            JsonObject responseJo = new Gson().fromJson(contentResponse.getContentAsString(), JsonObject.class);
            //System.out.println(">> ActionState.run - responseJo : " + responseJo.toString());
            for (String key : responseJo.keySet()) {
                ValiableManager.put(key, responseJo.get(key).getAsString());
            }

            return responseJo.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String makeQuery() throws UnsupportedEncodingException {
        String query = "";
        for (int i = 0; i < parameters.size(); i++) {
            if (i == 0) {
                query += "?";
            }
            query += URLEncoder.encode(parameters.get(i), "UTF-8") + "="
                    + URLEncoder.encode(ValiableManager.get(parameters.get(i)), "UTF-8");
            if (i < parameters.size() - 1) {
                query += "&";
            }
        }

        return query;
    }

    @Override
    public String toString() {
        return "ActionState{" +
                "url='" + url + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
