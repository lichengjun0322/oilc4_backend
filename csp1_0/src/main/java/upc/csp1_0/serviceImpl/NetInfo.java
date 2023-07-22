package upc.csp1_0.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NetInfo {
    private RequestTopology requestTopology = new RequestTopology();
    private JsonNode str = requestTopology.topology();
    private JSONObject obj = str.getObject();
    private Object networkTopology = obj.get("network-topology");
    private JSONObject obj2 = (JSONObject) networkTopology;
    private Object topology = obj2.get("topology");
    private JSONArray array1 = (JSONArray) topology;
    private JSONObject array2 = (JSONObject) array1.get(0);
    private JSONArray node = (JSONArray) array2.get("node");
    private JSONArray link = (JSONArray) array2.get("link");

    /**
     * @description:获取交换机的数量
     * @return:交换机的数量
     */
    public int getSwitchNumber(){
        int switchNumber = 0;
        for (int i = 0;i < node.length();i++){
            JSONObject cNode = (JSONObject) node.get(i);
            String nodeId = cNode.get("node-id").toString();
            if(nodeId.length() < 11){
                switchNumber = switchNumber + 1;
            }
        }

        return switchNumber;
    }

    /**
     * @description:获取主机的数量
     * @return:主机的数量
     */
    public int getPcNumber(){
        int PcNumber = 0;
        for (int i = 0;i < node.length();i++){
            JSONObject cNode = (JSONObject) node.get(i);
            String nodeId = cNode.get("node-id").toString();
            if(nodeId.length() > 11){
                PcNumber = PcNumber + 1;
            }
        }
        return PcNumber;
    }

    /**
     * @description:获取交换机节点信息
     * @return:交换机数组
     */
    public String[] getSwitch(){
        String[] switches = new String[getSwitchNumber()];
        int counter = 0;
        for(int i = 0;i < node.length();i++){
            JSONObject cNode = (JSONObject) node.get(i);
            String nodeId = cNode.get("node-id").toString();
            if(nodeId.length() < 11){
                switches[counter] = nodeId;
                counter++;
            }
        }
        return switches;
    }

    /**
     * @description:获取主机信息
     * @return:主机数组
     */
    public String[] getPc(){
        String[] pcs = new String[getPcNumber()];
        int counter = 0;
        for(int i = 0;i < node.length();i++){
            JSONObject cNode = (JSONObject) node.get(i);
            String nodeId = cNode.get("node-id").toString();
            if(nodeId.length() > 11){
                pcs[counter] = nodeId;
                counter++;
            }
        }
        return pcs;
    }

    /**
     * @description:获取链路连接信息
     * @return:二维链路数组
     */
    public String[][] getLinks(){
        String[][] links = new String[link.length()][2];
        for(int i = 0;i < link.length();i++){
          JSONObject temp = (JSONObject) link.get(i);
          JSONObject end = (JSONObject) temp.get("destination");
          JSONObject start = (JSONObject) temp.get("source");
          String dest = (String) end.get("dest-node");
          String sour = (String) start.get("source-node");
          links[i][0] = sour;
          links[i][1] = dest;
        }

        return links;
    }

    public NetInfo() throws UnirestException, JsonProcessingException {
    }
}
