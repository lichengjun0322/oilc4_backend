package upc.csp1_0.dao;

import org.apache.ibatis.annotations.*;
import upc.csp1_0.model.*;

import java.util.List;

@Mapper
public interface OperationMapper {

     int insertFlow(FlowOfInsert flowOfInsert);
    int addFlow(String flow_id, String node_id, String table_id, String priority, String flow_name, String source_ip, String out_port);

    int deleteFlow(FlowOfDel flowOfDel);
    List<FlowCache> findAllFlow(@Param("start")int start, @Param("pageSize") int pageSize);
    public int getFlowCount();
    FlowOfInsert isExistInsert(FlowOfInsert flowOfInsert);

    FlowOfInsert isConflictInsert(FlowOfInsert flowOfInsert);

    @Select("select * from flowcache")
    List<FlowCache> findFlowCache();

    @Insert("INSERT INTO `flowcache`" +
            "(`flow_id`, `node_id`, `table_id`, `priority`, `flow_name`, `source_ip`, `out_port`, `type`) " +
            "VALUES (#{flow_id},#{node_id},#{table_id},#{priority},#{flow_name},#{source_ip},#{out_port},#{type})")
    int insertFlowCache(@Param("flow_id") String flow_id,@Param("node_id") String node_id,
                        @Param("table_id") String table_id,@Param("priority") String priority,
                        @Param("flow_name") String flow_name,@Param("source_ip") String source_ip,
                        @Param("out_port") String out_port,@Param("type") String type);
    @Delete("DELETE FROM `flowcache` WHERE flow_id = #{flow_id}")
    int deleteFloaCache(@Param("flow_id") String flow_id);

    @Insert("INSERT INTO `flow`(`flow_id`, `node_id`, `table_id`, `priority`, `flow_name`, `source_ip`, `out_port`, `type`) " +
            "VALUES (#{flow_id},#{node_id},#{table_id},#{priority},#{flow_name},#{source_ip},#{out_port},#{type})")
    int passFlowCache(@Param("flow_id") String flow_id,@Param("node_id") String node_id,
                      @Param("table_id") String table_id,@Param("priority") String priority,
                      @Param("flow_name") String flow_name,@Param("source_ip") String source_ip,
                      @Param("out_port") String out_port,@Param("type") String type);

    @Select("SELECT * FROM `flow` WHERE (`flow_id`=#{flow_id} AND `table_id`=#{table_id} AND `node_id`=#{node_id})")
    FlowCache findFlows(@Param("flow_id") String flow_id,@Param("table_id") String table_id,@Param("node_id") String node_id);

    @Insert("INSERT INTO `warnningcahe`" +
            "(`flow_id`, `node_id`, `table_id`, `priority`, `flow_name`, `source_ip`, `out_port`, `type`, `user`) " +
            "VALUES (#{flow_id},#{node_id},#{table_id},#{priority},#{flow_name},#{source_ip},#{out_port},#{type},#{user})")
    int insertWarnningCahe(@Param("flow_id") String flow_id,@Param("node_id") String node_id,
                           @Param("table_id") String table_id,@Param("priority") String priority,
                           @Param("flow_name") String flow_name,@Param("source_ip") String source_ip,
                           @Param("out_port") String out_port,@Param("type") String type,
                           @Param("user") String user);

    @Select("select * from warnningcahe")
    List<WarnningCahe> findWarnningCache();

    @Delete("DELETE FROM `warnningcahe` WHERE flow_id = #{flow_id}")
    int deleteWarnningCache(@Param("flow_id") String flow_id);

    @Select("select * from warnstatistic")
    List<WarnStatistic> findWarnStatistic();

    @Select("select * from operation")
    List<Operation> findOperation();

    @Select("select * from operationstatis")
    List<OperationStatis> findOperationStatis();

    @Select("select * from network")
    List<netWork> findFaultLink();

    @Insert("INSERT INTO `network2`(`snode`, `dnode`) VALUES (#{sNode},#{dNode})")
    int insertNet(@Param("sNode") String sNode,@Param("dNode") String dNode);
}
