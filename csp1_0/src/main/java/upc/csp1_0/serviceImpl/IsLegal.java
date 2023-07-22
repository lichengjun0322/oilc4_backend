package upc.csp1_0.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.csp1_0.model.Point;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Transactional
public class IsLegal {
    @Autowired
    RunKmeans runKmeans;
    DistanceCompute distanceCompute = new DistanceCompute();

    public boolean isLegalFlow(String flow_id, String flow_name, String node_id, String out_port,
                               String priority, String source_ip, String table_id, String type) throws IOException {
        Point flow = flowFormat(flow_id, flow_name, node_id, out_port, priority, source_ip, table_id, type);
        String localNode = String.valueOf(node_id.charAt(9));
        int k = runKmeans.readK();
        String[] cluster = new String[k];
        double[] distance = new double[k];
        int counter = 0;
        FileInputStream file = new FileInputStream("/home/r00t/SDN/userlog/root04.txt");
        InputStreamReader isr = new InputStreamReader(file, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String[] arrs = null;
        String flows = "";
        String minDisPoint = "";
        double minDis = 10;
        String minNumberPoint = "";
        String maxDisPoint = "";
        double maxDis = 0;
        String maxNumberPoint = "";
        int blongNet = 1;
        String localBlongPoint = "";
        double localBlongNumber = 0;
        while ((flows = br.readLine()) != null) {
            arrs = flows.split(" ");
            float[] info = {1, Float.parseFloat(arrs[4]), 1, 1, 1, 1, 1, 1};
            //int id = Integer.parseInt(arrs[0]);
            Point point = new Point(info);
            double dis = distanceCompute.getEuclideanDis(flow, point);
            if (dis <= minDis) {
                minDis = dis;
                minDisPoint = String.valueOf(arrs[4]);
                minNumberPoint = String.valueOf(arrs[15]);
            }
            if (dis >= maxDis) {
                maxDis = dis;
                maxDisPoint = String.valueOf(arrs[4]);
                maxNumberPoint = String.valueOf(arrs[15]);
            }
            if (Double.parseDouble(String.valueOf(arrs[15])) > localBlongNumber) {
                localBlongNumber = Double.parseDouble(String.valueOf(arrs[15]));
                localBlongPoint = String.valueOf(arrs[4]);
            }
            distance[counter] = dis;
            counter++;
        }
        br.close();
        isr.close();
        file.close();

        if (Double.parseDouble(localBlongPoint)> 3) {
            if(Double.parseDouble(localNode) >= 4){
                return true;
            }else {
                return false;
            }
        } else {
            if(Double.parseDouble(localNode) >= 4){
                return false;
            }else {
                return true;
            }
        }


        /*if((Double.parseDouble(maxNumberPoint) >= 5)){
            if(Double.parseDouble(maxDisPoint) > 3){
                blongNet = 4;
            }else {
                blongNet = 1;
            }

        }else {
            blongNet = 4;
        }
        if(Math.abs(((Double.parseDouble(localNode) - blongNet))) >= 3){
            return false;
        }else {
            return true;
        }*/


    }

    Point flowFormat(String flow_id, String flow_name, String node_id, String out_port,
                     String priority, String source_ip, String table_id, String type) {
        String flow = null;
        flow = "1" + " " + node_id.charAt(9) + " " + 1 + " " + 1 + " " + 1 + " " + 1 + " " + 1 + " " + 1;
        String node = new String(String.valueOf(node_id.charAt(9)));
        float[] flowList = {1, Integer.parseInt(node), 1, 1, 1, 1, 1, 1};
        Point point = new Point(flowList);
        return point;
    }

}
