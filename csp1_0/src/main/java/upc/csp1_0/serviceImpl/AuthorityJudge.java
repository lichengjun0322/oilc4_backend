package upc.csp1_0.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.csp1_0.dao.UserMapper;

/**
 * @description:操作权限判断
 */
@Service
@Transactional
public class AuthorityJudge {
    @Autowired
    private UserMapper userMapper;
    /**
       @description:获取当前登录用户等级
     */
    public int userInfo(){
        String user;
        user = userMapper.getLoginer();
        int grade = userMapper.getUserGrade(user);
        return grade;
    }
    public boolean grandJudge(Object object){
        int grand = userInfo();
        if((object instanceof RequestTopology) && grand >= 1){
            return true;
        }
        else if ((object instanceof ChangeRoute) && grand >= 3){
            return true;
        }
        else if((object instanceof DeleteFlow) && grand >= 5){
            return true;
        }else {
            return false;
        }
    }
}
