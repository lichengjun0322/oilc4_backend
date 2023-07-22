package upc.csp1_0.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.csp1_0.model.Cluster;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;

@Service
@Transactional
public class RunKmeans {
    public void runner() throws IOException {
        ArrayList<float[]> dataset = new ArrayList<float[]>();

        FileInputStream file = new FileInputStream("/home/r00t/SDN/userlog/root02.txt");
        InputStreamReader isr = new InputStreamReader(file,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String[] arrs = null;
        String flow = "";
        while ((flow = br.readLine()) != null){
            arrs = flow.split(" ");
            dataset.add(new float[] {Integer.parseInt(arrs[0]),Integer.parseInt(arrs[1]),Integer.parseInt(arrs[2]),
                    Integer.parseInt(arrs[3]),Integer.parseInt(arrs[4]),Integer.parseInt(arrs[5]),
                    Integer.parseInt(arrs[6]),Integer.parseInt(arrs[7])});
        }
        br.close();
        isr.close();
        file.close();
        //dataset.add(new float[] {1,4,1,1,1,1,1,1});
        int k = readK();
        Kmeans kmeans = new Kmeans(k,dataset);
        Set<Cluster> clusterSet = kmeans.run();
        System.out.println("单次迭代运行次数："+kmeans.getIterTimes());
        for(Cluster cluster : clusterSet){
            System.out.println(cluster);
        }

        //输出簇中的个数
        File newFile = new File("/home/r00t/SDN/userlog/root04.txt");
        if(!newFile.exists()){
            newFile.createNewFile();
        }
        newFile.setExecutable(true);
        newFile.setReadable(true);
        newFile.setWritable(true);
        OutputStream os = new FileOutputStream(newFile,false);
        for(Cluster cluster : clusterSet){
            System.out.println(cluster.getPointNumber());
            String info = cluster.getId() + " " + cluster.getCenter() + " " + cluster.getPointNumber();
            try{
                os.write(info.getBytes("GBK"));
                os.write("\n".getBytes("GBK"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        os.close();

    }
    public int readK() throws IOException {
        FileInputStream fis = new FileInputStream("/home/r00t/SDN/userlog/root03.txt");
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        int num = 0;
        while ((line = br.readLine()) != null){
            num = Integer.parseInt(line);
        }
        br.close();
        isr.close();
        fis.close();
        return num;
    }
}
