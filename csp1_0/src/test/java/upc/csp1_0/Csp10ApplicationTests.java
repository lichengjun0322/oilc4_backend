package upc.csp1_0;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import upc.csp1_0.model.UserBean;
import upc.csp1_0.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Csp10ApplicationTests {
    @Autowired
    UserService userService;
    @Test
    public void contextLoads() {
        UserBean userBean = userService.loginIn("a","a");
        System.out.println("该用户ID为：");
        System.out.println(userBean.getId());
    }

}
