package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Nsis_ayer;

import java.util.List;
@Repository
public interface Nsis_ayMapper {
    @Select("select * from nsis_ay")
    List<Nsis_ayer> findAll();
}
