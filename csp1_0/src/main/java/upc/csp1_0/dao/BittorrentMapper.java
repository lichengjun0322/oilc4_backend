package upc.csp1_0.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import upc.csp1_0.model.Bittorrenter;

import java.util.List;
@Repository
public interface BittorrentMapper {
    @Select("select * from bittorrent")
    List<Bittorrenter> findAll();
}
