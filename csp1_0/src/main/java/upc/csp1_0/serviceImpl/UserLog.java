package upc.csp1_0.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.csp1_0.dao.UserMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
@Transactional
public class UserLog {
    @Autowired
    UserMapper userMapper;
    public int writeLog(String context) throws IOException {
        String user = userMapper.getLoginer();
        String filePahth = "/home/r00t/SDN/userlog/"+user+".txt";
        File file = new File(filePahth);
        file.setWritable(true,false);
        if(!file.exists()){
            file.createNewFile();
        }
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
        OutputStream os = new FileOutputStream(file,true);
        try {
            os.write(context.getBytes("GBK"));
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
