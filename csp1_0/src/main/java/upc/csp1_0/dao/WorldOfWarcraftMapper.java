package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.WorldOfWarcrafter;

import java.util.List;
@Repository
public interface WorldOfWarcraftMapper {
    @Select("select * from worldofwarcraft")
    List<WorldOfWarcrafter> findAll();
}
