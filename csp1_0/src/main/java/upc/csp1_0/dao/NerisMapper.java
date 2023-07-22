package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Neriser;

import java.util.List;
@Repository
public interface NerisMapper {
    @Select("select * from neris")
    List<Neriser> findAll();
}
