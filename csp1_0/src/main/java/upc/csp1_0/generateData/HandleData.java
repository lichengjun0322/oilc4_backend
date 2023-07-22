package upc.csp1_0.generateData;

import java.io.*;

public class HandleData {
    public void handler() throws IOException {
        FileInputStream file = new FileInputStream("/home/r00t/SDN/userlog/root.txt");
        InputStreamReader isr = new InputStreamReader(file);
        BufferedReader br = new BufferedReader(isr);
        String flow = "";
        String flows = "";
        String[] arrs = null;
        int numNode = 0;
        int[] nodeList = new int[10];
        while ((flow=br.readLine())!=null){
            boolean flag = true;
            arrs = flow.split(" ");
            flows = "1" + " " + arrs[1].charAt(9) +" " + 1 +" " + 1 +" " + 1 +" " + 1 +" " + 1 +" " + 1;
            for(int k:nodeList){
                if((arrs[1].charAt(9) - '0') != k){
                    continue;
                }else {
                    flag = false;
                    break;
                }
            }
            if(flag == true){
               nodeList[numNode] =  arrs[1].charAt(9) - '0';
               numNode = numNode + 1;
            }
            //System.out.println(flows);
            File newFile = new File("/home/r00t/SDN/userlog/root02.txt");
            if(!newFile.exists()){
                newFile.createNewFile();
            }
            newFile.setExecutable(true);
            newFile.setReadable(true);
            newFile.setWritable(true);
            OutputStream newOs = new FileOutputStream(newFile,true);
            try{
                newOs.write(flows.getBytes("GBK"));
                newOs.write("\n".getBytes("GBK"));
                if(newOs != null){
                    newOs.close();
                }
            }catch (Exception e){
                e.printStackTrace();
                if(newOs != null){
                    newOs.close();
                }
            }
        }
        br.close();
        isr.close();
        file.close();
        System.out.println(numNode);
        File numFile = new File("/home/r00t/SDN/userlog/root03.txt");
        if(!numFile.exists()){
            numFile.createNewFile();
        }
        numFile.setExecutable(true);
        numFile.setReadable(true);
        numFile.setWritable(true);
        OutputStream numOs = new FileOutputStream(numFile,true);
        try{
            numOs.write((numNode + "").getBytes("GBK"));
            if(numOs != null){
                numOs.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            if(numOs != null){
                numOs.close();
            }
        }
    }

}
