package upc.csp1_0.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.csp1_0.dao.UserMapper;
import upc.csp1_0.model.UserBean;
import upc.csp1_0.model.UserRegist;
import upc.csp1_0.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserBean loginIn(String name,String password){
        return userMapper.getInfo(name,password);
    }

    @Override
    public void addUser(UserRegist userRegist){
        int rows = userMapper.addUser(userRegist);
    }

    @Override
    public void addUserInfo(UserRegist userRegist) {int rows = userMapper.addUserInfo(userRegist);}

    @Override
    public void addLoginer(String name){userMapper.addLoginer(name);}

    @Override
    public void delLoginer(){userMapper.delLoginer();}

    @Override
    public String getLoginer(){return userMapper.getLoginer();}
}
