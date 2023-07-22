package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Viruter;

import java.util.List;
@Repository
public interface VirutMapper {
    @Select("select * from virut")
    List<Viruter> findAll();
}
