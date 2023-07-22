package upc.csp1_0.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.csp1_0.dao.OperationMapper;
import upc.csp1_0.model.netWork;

import java.util.List;

@Service
@Transactional
public class FaultRecovery {
    @Autowired
    NetInfo netInfo;
    @Autowired
    ChangeRoute changeRoute;
    @Autowired
    DeleteFlow deleteFlow;
    @Autowired
    OperationMapper operationMapper;

    public String[][] getLinks(){
        String[][] links = netInfo.getLinks();
        String[][] result = new String[links.length/2][2];
        int counter = 0;
        for(int i = 0;i < links.length-1;i++){
            for(int k = i+1;k < links.length;k++){
                if((links[k][0].equals(links[i][0]) == true && links[k][1].equals(links[i][1]))||
                        (links[k][0].equals(links[i][1]) == true && links[k][1].equals(links[i][0]) == true)){
                    links[k][0] = "repeat";
                    links[k][1] = "repeat";
                }

            }
        }
       for(int i = 0;i < links.length;i++){
           if(links[i][0].length() > 11 || links[i][1].length() > 11){
               links[i][0] = "repeat";
               links[i][1] = "repeat";
           }
       }
       for(int i = 0;i < links.length;i++){
            if(links[i][0].equals("repeat") == false){
                result[counter][0] = links[i][0];
                result[counter][1] = links[i][1];
                counter++;
            }
        }
        return result;
    }
    public String[][] findFault(){
        String[][] link1 = getInitialNet();
        String[][] link2 = getLinks();
        String[][] result = new String[Math.abs(link1.length + 8 - link2.length)][2];
        int counter = 0;
        int flag = 0;
        for(int i = 0;i < link1.length;i++){
            flag = 0;
            for(int k = 0;k < link2.length;k++){
                if((link1[i][0].equals(link2[k][0]) == true && link1[i][1].equals(link2[k][1]) == true)
                || (link1[i][0].equals(link2[k][1]) == true && link1[i][1].equals(link2[k][0]) == true)){
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                result[counter][0] = link1[i][0];
                result[counter][1] = link1[i][1];
                counter++;
            }
        }
        return result;
    }

    public String[][] getInitialNet(){
        List<netWork> list1 = operationMapper.findFaultLink();
        String[][] result = new String[list1.size()][2];
        int counter = 0;
        for (netWork nw:list1
             ) {
            result[counter][0] = nw.getsNode();
            result[counter][1] = nw.getdNode();
            counter++;
        }
        return result;
    }
}
