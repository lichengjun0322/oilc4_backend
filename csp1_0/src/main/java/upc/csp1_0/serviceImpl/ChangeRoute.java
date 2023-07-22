package upc.csp1_0.serviceImpl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:下发流表改变路由
 */
@Service
@Transactional
public class ChangeRoute {
    public String reRoute(String flow_id,String node_id,String table_id,
                          String priority,String flow_name,String source_ip,String output_port) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        String url = "http://127.0.0.1:8080/restconf/config/opendaylight-inventory:nodes/node/"+node_id+"/table/"+table_id+"/flow/"+flow_id;
        String body="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<flow xmlns=\"urn:opendaylight:flow:inventory\">" +
                "\n<priority>"+priority+"</priority>\n<flow-name>"+flow_name+"</flow-name>\n<idle-timeout>0</idle-timeout>\n<hard-timeout>0</hard-timeout>" +
                "\n<match>\n<ethernet-match>\n<ethernet-type>\n<type>2048</type>\n</ethernet-type>\n</ethernet-match>\n" +
                "<ipv4-source>"+source_ip+"</ipv4-source>\n</match>\n<id>"+flow_id+"</id>\n<table_id>"+table_id+"</table_id>\n<instructions>\n<instruction>" +
                "\n<order>0</order>\n<apply-actions>\n<action>\n<order>0</order>\n<output-action>\n<output-node-connector>"+output_port+"</output-node-con" +
                "nector>\n</output-action>\n</action>\n</apply-actions>\n</instruction>\n</instructions>\n</flow>";
        HttpResponse<String> response = Unirest.put(url)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .header("Cookie", "JSESSIONID=18b7z8vgvsjw5m2hmuxrnopwr")
                .body(body)
                .asString();
        return response.getBody();
    }
}
