package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.FTPer;

import java.util.List;
@Repository
public interface FTPMapper {
    @Select("select * from ftp")
    List<FTPer> findAll();
}
