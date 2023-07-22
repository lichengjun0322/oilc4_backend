package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Htboter;

import java.util.List;
@Repository
public interface HtbotMapper {
    @Select("select * from htbot")
    List<Htboter> findAll();
}
