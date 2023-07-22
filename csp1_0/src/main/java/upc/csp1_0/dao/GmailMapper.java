package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Gmailer;

import java.util.List;
@Repository
public interface GmailMapper {
    @Select("select * from gmail")
    List<Gmailer> findAll();
}
