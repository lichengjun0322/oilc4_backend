package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Zeuser;

import java.util.List;
@Repository
public interface ZeusMapper {
    @Select("select * from zeus")
    List<Zeuser> findAll();
}
