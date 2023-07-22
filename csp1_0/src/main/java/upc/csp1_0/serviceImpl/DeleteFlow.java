package upc.csp1_0.serviceImpl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:删除流表
 */
@Service
@Transactional
public class DeleteFlow {
    public String delFlow(String table_id,String flow_id,String node_id) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.delete("http://127.0.0.1:8080/restconf/config/opendaylight-inventory:nodes/node/"+node_id+"/table/"+table_id+"/flow/"+flow_id)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .header("Cookie", "JSESSIONID=18emu0i7b8kzv11euznwuvkmbm")
                .asString();
        return response.getBody();
    }
}
