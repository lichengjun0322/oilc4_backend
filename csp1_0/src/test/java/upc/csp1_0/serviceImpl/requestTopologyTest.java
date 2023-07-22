package upc.csp1_0.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class requestTopologyTest {

    @Test
    public void topology() throws UnirestException, JsonProcessingException {
        RequestTopology requestTopology = new RequestTopology();
        JsonNode str = requestTopology.topology();
        JSONObject obj = str.getObject();
        Object networkTopology = obj.get("network-topology");
        JSONObject obj2 = (JSONObject) networkTopology;
        Object topology = obj2.get("topology");
        JSONArray array1 = (JSONArray) topology;
        JSONObject array2 = (JSONObject) array1.get(0);
        JSONArray node = (JSONArray) array2.get("node");
        JSONArray link = (JSONArray) array2.get("link");
        String[][] links = new String[link.length()][2];
        for(int i = 0;i < link.length();i++){
            JSONObject temp = (JSONObject) link.get(i);
            JSONObject end = (JSONObject) temp.get("destination");
            JSONObject start = (JSONObject) temp.get("source");
            String dest = (String) end.get("dest-node");
            String sour = (String) start.get("source-node");
            links[i][0] = sour;
            links[i][1] = dest;
            //System.out.println(dest + ":"+sour);
        }
        for(int i = 0;i < links.length;i++){
            System.out.println(links[i][0] + "--" + links[i][1]);
        }

    }
}