package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Weiboer;

import java.util.List;
@Repository
public interface WeiboMapper {
    @Select("select * from weibo_2")
    List<Weiboer> findAll();
}
