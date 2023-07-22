package upc.csp1_0.serviceImpl;

import org.neo4j.driver.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.csp1_0.service.neo4jService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class neo4jServiceImpl implements neo4jService {
    private String uri="bolt://localhost:7687";  //neo4j端口
    private String username="neo4j";    //用户名
    private String password="root";

    public Driver createDriver(){
        return GraphDatabase.driver(uri, AuthTokens.basic(username,password));
    }

    @Override
    public void createSwitchNode(String node_id) {
        Driver driver = createDriver();
        try(Session session = driver.session()){
            String neoSql = "CREATE  (S : SwitchEntity{ node_id:'"+node_id+"'})";
            session.run(neoSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSwitchNode(String node_id) {
        Driver driver = createDriver();
        try(Session session = driver.session()){
            String neoSql = "MATCH (S : SwitchEntity{node_id:'"+node_id+"'})  delete S";
            session.run(neoSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void createFlowNode(String flow_id,String node_id,String table_id,String priority,
    String flow_name,String source_ip,String out_port,String type) {
        Driver driver = createDriver();
        try(Session session = driver.session()){
            String neoSql = "CREATE  (F : FlowEntity{ flow_id:'"+flow_id+"',node_id:'"+node_id+"',table_id:'"+table_id+"'," +
                    "priority:'"+priority+"',flow_name:'"+flow_name+"',source_ip:'"+source_ip+"',out_port:'"+out_port+"',type:'"+type+"'})";
            session.run(neoSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFlowNode(String flow_id,String node_id) {
        Driver driver = createDriver();
        try(Session session = driver.session()){
            String neoSql = "MATCH (F: FlowEntity) " +
                    "WHERE F.flow_id = '"+flow_id+"' AND F.node_id = '"+node_id+"' " +
                    "delete F";
            session.run(neoSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addSwitchRelationship(String node_id1,String node_id2) {
        Driver driver = createDriver();
        try (Session session = driver.session()){
            String neoSql = "MATCH (s1:SwitchEntity),(s2:SwitchEntity) WHERE s1.node_id = '"+node_id1+"' AND s2.node_id= '"+node_id2+"' CREATE (s1)-[r:connect]->(s2) RETURN r";
            session.run(neoSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSwitchRelationship(String node_id1, String node_id2) {
        Driver driver = createDriver();
        try (Session session = driver.session()){
            String neoSql = "MATCH (s1:SwitchEntity),(s2:SwitchEntity) WHERE s1.node_id = '"+node_id1+"' AND s2.node_id= '"+node_id2+"' MATCH (s1)-[r:connect]->(s2) DELETE r";
            session.run(neoSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addSwithcFlowRelationship(String node_id1, String Flow_id, String node_id2) {
        Driver driver = createDriver();
        try (Session session = driver.session()){
            String neoSql = "MATCH (s:SwitchEntity),(f:FlowEntity) WHERE s.node_id = '"+node_id1+"' AND (f.flow_id= '"+Flow_id+"' AND f.node_id = '"+node_id2+"') CREATE (f)-[r:blong]->(s) RETURN r";
            session.run(neoSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSwitchFlowRelationship(String node_id1, String flow_id, String node_id2) {
        Driver driver = createDriver();
        try (Session session = driver.session()){
            String neoSql = "MATCH (s:SwitchEntity),(f:FlowEntity) WHERE s.node_id = '"+node_id1+"' AND (f.flow_id= '"+flow_id+"' AND f.node_id = '"+node_id2+"') MATCH (f)-[r:blong]->(s) DELETE r";
            session.run(neoSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Map<String,List> queryAll() {
        Driver driver = createDriver();
        List SNodeList = new ArrayList();
        List FNodeList = new ArrayList();
        List SSRelList = new ArrayList();
        List SFRelList = new ArrayList();
        List list = new ArrayList();
        String SNode = "match(n:SwitchEntity) return n.node_id";
        String FNode = "match(n:FlowEntity) return n.flow_id,n.node_id";
        String StoS = "match(n:SwitchEntity)-[r]-(m:SwitchEntity) return n.node_id,m.node_id";
        String FtoS = "match(n:FlowEntity)-[r]-(m:SwitchEntity) return n.flow_id,m.node_id";

        HashMap<String, List> map = new HashMap<String, List>();
        try (Session session = driver.session()){
            List SNodeList2 = session.run(SNode).list(Record::asMap);
            List FNodeList2 = session.run(FNode).list(Record::asMap);
            List StoSList2 = session.run(StoS).list(Record::asMap);
            List FtoSList2 = session.run(FtoS).list(Record::asMap);

            for(Object l:SNodeList2){
                List li = new ArrayList();
                String[] temp = l.toString().split(",");
                for(String str:temp){
                    li.add(str);
                }
                SNodeList.add(li);
            }
            for(Object l:FNodeList2){
                List li = new ArrayList();
                String[] temp = l.toString().split(",");
                for(String str:temp){
                    li.add(str);
                }
                FNodeList.add(li);
            }
            for(Object l:StoSList2){
                List li = new ArrayList();
                String[] temp = l.toString().split(",");
                for(String str:temp){
                    li.add(str);
                }
                SSRelList.add(li);
            }
            for(Object l:FtoSList2){
                List li = new ArrayList();
                String[] temp = l.toString().split(",");
                for(String str:temp){
                    li.add(str);
                }
                SFRelList.add(li);
            }

            /*Result result1 = session.run(SNode);
            while ( result1.hasNext() )
            {
                Record record = result1.next();
                List temp = new ArrayList();
                temp.add(record.get("n"));
                temp.add(record.get("n.node_id"));
                SNodeList.add(temp);

            }*/

            map.put("SNodeList",SNodeList);
            map.put("FNodeList",FNodeList2);
            map.put("SSRelList",StoSList2);
            map.put("SFRelList",FtoSList2);
            session.close();
            driver.close();
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    //test
    private static String getType(Object a) {
        return a.getClass().toString();

    }



}
