package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.User;

import java.util.List;
@Repository
public interface LoginMapper {
    @Select("select * from login")
    List<User> findAll();
}
