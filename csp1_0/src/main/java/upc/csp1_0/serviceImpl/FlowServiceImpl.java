package upc.csp1_0.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.csp1_0.dao.OperationMapper;
import upc.csp1_0.model.FlowCache;
import upc.csp1_0.model.FlowOfDel;
import upc.csp1_0.model.FlowOfInsert;
import upc.csp1_0.service.FlowService;

import java.util.List;

@Service
@Transactional
public class FlowServiceImpl implements FlowService {
    @Autowired
    OperationMapper operationMapper;

    @Override
    public int insertFlow(FlowOfInsert flowOfInsert){
        return operationMapper.insertFlow(flowOfInsert);
    }

    @Override
    public int addFlow(String flow_id, String node_id, String table_id, String priority, String flow_name, String source_ip, String out_port) {
        return operationMapper.addFlow(flow_id,node_id,table_id,priority,flow_name,source_ip,out_port);
    }

    @Override
    public int deleteFlow(FlowOfDel flowOfDel){
        return operationMapper.deleteFlow(flowOfDel);
    }

    @Override
    public boolean isExistInsert(FlowOfInsert flowOfInsert){
        if(operationMapper.isExistInsert(flowOfInsert) != null){
            return  true;
        }else{
            return false;
        }

    }

    @Override
    public boolean isConflictInsert(FlowOfInsert flowOfInsert){
        if(operationMapper.isConflictInsert(flowOfInsert) != null){
            return true;
        }else
            return false;
    }

    @Override
    public List<FlowCache> findAllFlow(int curPage, int pageSize) {
        return operationMapper.findAllFlow((curPage-1)*pageSize,pageSize);
    }

    @Override
    public int getFlowCount() {
        return operationMapper.getFlowCount();
    }


}
