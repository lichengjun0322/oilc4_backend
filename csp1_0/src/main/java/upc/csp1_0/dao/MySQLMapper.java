package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.MySQLer;

import java.util.List;
@Repository
public interface MySQLMapper {
    @Select("select * from mysql")
    List<MySQLer> findAll();
}
