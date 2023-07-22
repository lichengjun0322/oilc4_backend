package upc.csp1_0.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.csp1_0.dao.FlowDao;
import upc.csp1_0.model.FlowCache;
import upc.csp1_0.service.FloService;

@Service
@Transactional
public class FloServiceImpl implements FloService
{
    @Autowired
    private FlowDao flowDao;
    @Override
    public int addFlow(String flow_id, String node_id, String table_id, String priority, String flow_name, String source_ip, String out_port) {
        return flowDao.addFlow(flow_id,node_id,table_id,priority,flow_name,source_ip,out_port);
    }

    @Override
    public FlowCache find(String flow_id) {
        return flowDao.find(flow_id);
    }
}
