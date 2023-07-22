package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Cridexer;

import java.util.List;
@Repository
public interface CridexMapper {
    @Select("select * from cridex")
    List<Cridexer> findAll();
}
