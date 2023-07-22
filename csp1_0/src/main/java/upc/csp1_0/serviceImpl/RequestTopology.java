package upc.csp1_0.serviceImpl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:请求网络拓扑信息
 */
@Service
@Transactional
public class RequestTopology {
    public JsonNode topology() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = (HttpResponse<JsonNode>) Unirest.get("http://127.0.0.1:8080/restconf/operational/network-topology:network-topology")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .header("Cookie", "JSESSIONID=19vw4uuye1mns1k33o54j4x91l")
                .asJson();
        return response.getBody();
    }
}
