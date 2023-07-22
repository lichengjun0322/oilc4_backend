package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Skyper;

import java.util.List;
@Repository
public interface SkyperMapper {
    @Select("select * from skype")
    List<Skyper> findAll();
}
