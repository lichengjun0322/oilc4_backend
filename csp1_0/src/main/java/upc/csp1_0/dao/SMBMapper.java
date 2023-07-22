package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.MySQLer;

import java.util.List;
@Repository
public interface SMBMapper {
    @Select("select * from smb_2")
    List<MySQLer> findAll();
}
