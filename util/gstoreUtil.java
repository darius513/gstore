package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class gstoreUtil {


    public static JSONArray query(String SPARQL) throws IOException, InterruptedException {
        var values = new HashMap<String, String>() {{
            put("action", "queryDB");
            put("accesskeyid", "b6d78722084d48a1b445288ef95c3e06");
            put ("access_secret", "469CE50AE08844214FE59522E2E72AF8");
            put ("dbName", "jinrong");
            put ("sparql", SPARQL);
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://cloud.gstore.cn/api"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        String line = JSONObject.parseObject(JSONObject.parseObject(JSONObject.parseObject(response.body())
                                .getString("data"))
                        .getString("results"))
                .getString("bindings");
        return JSONArray.parseArray(line);
    }
}
