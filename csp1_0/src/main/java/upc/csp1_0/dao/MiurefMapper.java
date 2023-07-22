package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Miurefer;

import java.util.List;
@Repository
public interface MiurefMapper {
    @Select("select * from miuref")
    List<Miurefer> findAll();
}
