package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Shifuer;

import java.util.List;
@Repository
public interface ShifuMapper {
    @Select("select * from shifu")
    List<Shifuer> findAll();
}
