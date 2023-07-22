package upc.csp1_0.generateData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class GenerateData {
    public int generateLog() throws IOException {
        String filePahth = "/home/r00t/SDN/userlog/root.txt";
        Random r = new Random();
        File file = new File(filePahth);
        file.setWritable(true,false);
        if(!file.exists()){
            file.createNewFile();
        }
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
        OutputStream os = new FileOutputStream(file,true);
        String flow_id = r.nextInt(500) + "";
        String node_id = "openflow:" + (r.nextInt(3)+1);
        String table_id = flow_id;
        String priority = r.nextInt(500) + "";
        String flow_name = "flow:" + r.nextInt(200);
        String source_ip = "10.0.0." + (r.nextInt(3) + 1) + "/32";
        String out_port = (r.nextInt(2) + 1) + "";
        int seed = r.nextInt(2);
        String type = new String();
        if(seed == 1){
            type = "INSERT";
        } else {
            type = "DELETE";
        }
        String flows = flow_id + " " + node_id + " " + table_id + " " + priority + " " + flow_name + " " + source_ip + " " + out_port + " " + type;
        try {
            os.write(flows.getBytes("GBK"));
            os.write("\n".getBytes("GBK"));
            if(os != null){
                os.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            if(os != null){
                os.close();
            }
        }
        return 0;
    }
}
