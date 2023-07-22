package upc.csp1_0.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityJudgeTest {

    @Autowired
    AuthorityJudge authorityJudge;

    @Test
    public void test3(){
        RequestTopology requestTopology = new RequestTopology();
        ChangeRoute changeRoute = new ChangeRoute();
        DeleteFlow deleteFlow = new DeleteFlow();
        boolean[] flag = new boolean[3];
        flag[0] = authorityJudge.grandJudge(requestTopology);
        flag[1] = authorityJudge.grandJudge(changeRoute);
        flag[2] = authorityJudge.grandJudge(deleteFlow);
        for(boolean rst:flag){
            System.out.println(rst);
        }
    }

}