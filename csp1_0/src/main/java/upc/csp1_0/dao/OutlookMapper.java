package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Outlooker;

import java.util.List;
@Repository
public interface OutlookMapper {
    @Select("select * from outlook")
    List<Outlooker> findAll();
}
