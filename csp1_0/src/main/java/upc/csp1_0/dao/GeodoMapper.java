package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Geodoer;

import java.util.List;
@Repository
public interface GeodoMapper {
    @Select("select * from geodo")
    List<Geodoer> findAll();
}
