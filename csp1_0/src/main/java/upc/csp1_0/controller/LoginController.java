package upc.csp1_0.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import upc.csp1_0.dao.OperationMapper;
import upc.csp1_0.dao.UserMapper;
import upc.csp1_0.model.*;
import upc.csp1_0.service.FloService;
import upc.csp1_0.service.FlowService;
import upc.csp1_0.service.UserService;
import upc.csp1_0.service.neo4jService;
import upc.csp1_0.serviceImpl.ChangeRoute;
import upc.csp1_0.serviceImpl.IsLegal;
import upc.csp1_0.serviceImpl.NetInfo;
import upc.csp1_0.serviceImpl.UserLog;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    NetInfo netInfo;
    @Autowired
    private OperationMapper operationMapper;

    @Autowired
    private FlowService flowService;


    @Autowired
    UserMapper userMapper;
    @Autowired
    UserLog userLog;
    @Autowired
    ChangeRoute changeRoute;

    @Autowired
    IsLegal isLegal;
    @Autowired
    private neo4jService neo4jService;

    @Autowired
    private FloService floService;
    @RequestMapping("/login")
    public String show() {
        return "login";
    }


    /**
     * 登录
     * @param name
     * @param password
     * @return
     */

    @RequestMapping("login1")
    @ResponseBody
    public HashMap login(String name,String password){

        HashMap map = new HashMap();
        UserBean u = userService.loginIn(name,password);
        if(u!=null){
            map.put("code",0);
            map.put("msg","success");
            map.put("data",u);
        }else{
            map.put("code",-1);
            map.put("msg","用户名或者密码错误");
        }
        //登录失败



        return map;
    }

    @RequestMapping("addFlow")
    @ResponseBody
    public HashMap addFlow(String flow_id,String node_id,String table_id,String priority,String flow_name,String source_ip,String out_port) throws IOException {

        HashMap map = new HashMap();
        FlowOfInsert flowOfInsert=new FlowOfInsert();
        flowOfInsert.setFlow_id(flow_id);
        flowOfInsert.setNode_id(node_id);
        flowOfInsert.setTable_id(table_id);
        flowOfInsert.setPriority(priority);
        flowOfInsert.setFlow_name(flow_name);
        flowOfInsert.setSource_ip(source_ip);
        flowOfInsert.setOutput_port(out_port);
        boolean flag1 = flowService.isExistInsert(flowOfInsert);
        boolean flag2 = flowService.isConflictInsert(flowOfInsert);
        userLog.writeLog((String) flowOfInsert.getFlow_id() + " " + (String) flowOfInsert.getNode_id()
                + " " + (String) flowOfInsert.getTable_id() + " " + (String) flowOfInsert.getPriority() + " " + (String) flowOfInsert.getFlow_name()
                + " " + (String) flowOfInsert.getSource_ip() + " " + (String) flowOfInsert.getOutput_port() + " " + "INSERT");
//        将信息写入txt文件中
        boolean isLegalFlow = isLegal.isLegalFlow(flowOfInsert.getFlow_id(), flowOfInsert.getFlow_name(),
                flowOfInsert.getNode_id(), flowOfInsert.getOutput_port(), flowOfInsert.getPriority(),
                flowOfInsert.getSource_ip(), flowOfInsert.getTable_id(), "INSERT");
        if (flag1 != true && flag2 != true && isLegalFlow == true) {
            //flowService.insertFlow(flowOfInsert);
            neo4jService.createFlowNode(flowOfInsert.getFlow_id(),flowOfInsert.getNode_id(),flowOfInsert.getTable_id(),
                    flowOfInsert.getPriority(),flowOfInsert.getFlow_name(),flowOfInsert.getSource_ip(),
                    flowOfInsert.getOutput_port(),"INSERT");
            neo4jService.addSwithcFlowRelationship(flowOfInsert.getNode_id(),flowOfInsert.getFlow_id(),flowOfInsert.getNode_id());
            try {
                changeRoute.reRoute(flowOfInsert.getFlow_id(), flowOfInsert.getNode_id(), flowOfInsert.getTable_id(),
                        flowOfInsert.getPriority(), flowOfInsert.getFlow_name(), flowOfInsert.getSource_ip(),
                        flowOfInsert.getOutput_port());
            } catch (UnirestException e) {
                e.printStackTrace();
            }
            //System.out.println(flowOfInsert.toString());
            map.put("code",1);
            map.put("message1", "[系统后台]:");
            map.put("message2", "流表下发成功!");
            flowService.insertFlow(flowOfInsert);
            return map;
        } else {
            if (flag1 == true && flag2 != true) {
                map.put("code",3);
                map.put("message1", "[系统后台]:");
                map.put("message2", "[状态]:流表下发失败");
                map.put("message3", "请检查flow_id是否重复，或是流表指令重复下发，影响了原有策略!");
                return map;
            }
            if ((flag1 != true && flag2 == true) || isLegalFlow == false) {
                int grand = userMapper.getUserGrade(userMapper.getLoginer());
                if (isLegalFlow == true) {

                        operationMapper.insertFlowCache(flowOfInsert.getFlow_id(), flowOfInsert.getNode_id(), flowOfInsert.getTable_id(),
                                flowOfInsert.getPriority(), flowOfInsert.getFlow_name(), flowOfInsert.getSource_ip(),
                                flowOfInsert.getOutput_port(), "INSERT");
                        map.put("code",2);
                        map.put("message1", "[系统后台]:");
                        map.put("message2", "[状态]:流表下发待审核");
                        map.put("message3", "流表指令与原有策略发生冲突，影响了原有策略!已提交至审核员审核!");
                        return map;

                } else {

                        String name = userService.getLoginer();
                        operationMapper.insertWarnningCahe(flowOfInsert.getFlow_id(), flowOfInsert.getNode_id(), flowOfInsert.getTable_id(),
                                flowOfInsert.getPriority(), flowOfInsert.getFlow_name(), flowOfInsert.getSource_ip(),
                                flowOfInsert.getOutput_port(), "INSERT",name);
                        map.put("code",2);
                        map.put("message1", "[系统后台]:");
                        map.put("message2", "[状态]:流表下发待审核");
                        map.put("message3", "流表指令与原有策略发生冲突，影响了原有策略!已提交至审核员审核!");
                        return map;

                }

                //return map;
            } else {
                map.put("code",3);
                map.put("message1", "[系统后台]:");
                map.put("message2", "[状态]:流表下发失败");
                map.put("message3", "流表下发失败，策略中已配置相同指令！");
                return map;
            }
        }
       // int flag=flowService.insertFlow(flowOfInsert);

        //map.put("code",flag);
        //boolean flag1 = flowService.isExistInsert(flowOfInsert);
       // boolean flag2 = flowService.isConflictInsert(flowOfInsert);

        //map.put("flag1",flag1);
        //map.put("flag2",flag2);

        //return map;
    }


    @RequestMapping("passFlow")
    @ResponseBody
    public HashMap passFlow(String flow_id,String node_id,String table_id,String priority,String flow_name,String source_ip,String out_port){

        HashMap map = new HashMap();
        operationMapper.passFlowCache(flow_id,node_id,table_id,priority,flow_name,source_ip,out_port,"INSERT");
        operationMapper.deleteFloaCache(flow_id);
        try {
            changeRoute.reRoute(flow_id,node_id,table_id,priority,flow_name,source_ip,out_port);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        map.put("code",1);

        return map;
    }

    @RequestMapping("delFlow")
    @ResponseBody
    public HashMap addFlow(String flow_id,String node_id,String table_id){

        HashMap map = new HashMap();
        FlowOfDel flowOfDel=new FlowOfDel();
        flowOfDel.setFlow_id(flow_id);
        flowOfDel.setNode_id(node_id);
        flowOfDel.setTable_id(table_id);

        int flag=flowService.deleteFlow(flowOfDel);
        neo4jService.deleteSwitchFlowRelationship(node_id,flow_id,node_id);
        neo4jService.deleteFlowNode(flow_id,node_id);

        map.put("code",flag);

        return map;
    }

    @RequestMapping("getTopology")
    @ResponseBody
    public HashMap getTopology(){
        HashMap map=new HashMap();
        String name = userService.getLoginer();
        map.put("name", name);
        String[] switchs = netInfo.getSwitch();
        String[] pc = netInfo.getPc();
        String[][] links = netInfo.getLinks();
        map.put("switch",switchs);
        map.put("pc",pc);
        map.put("links",links);

        return map;
    }
    @RequestMapping(value = "tupu")
    @ResponseBody
    public HashMap getGraph() {
        String name = userService.getLoginer();
        HashMap map=new HashMap();
        map.put("name", name);
        map.put("message", "pass");
        Map<String,List> map1 = neo4jService.queryAll();
        map.put("flows",map1);
        return map;

    }

    @RequestMapping("findAllFlow")
    @ResponseBody
    public HashMap findAllFlow(int curPage, int pageSize){
        HashMap map = new HashMap();

        List<FlowCache> flowCacheList=flowService.findAllFlow(curPage,pageSize);
        int totalCount = flowService.getFlowCount();
        map.put("data",flowCacheList);
        map.put("totalCount",totalCount);

        return map;
    }

    @RequestMapping("findFlowCache")
    @ResponseBody
    public HashMap findFlowCache(){
        HashMap map = new HashMap();

        List<FlowCache> flows = operationMapper.findFlowCache();
        map.put("flows", flows);
        return map;
    }

    @RequestMapping("getGraph")
    @ResponseBody
    public HashMap getTupu() {
        HashMap map=new HashMap();
        String name = userService.getLoginer();
        map.put("name", name);
        map.put("message", "pass");
        Map<String,List> map1 = neo4jService.queryAll();
        map.put("flows",map1);
        return map;

    }

    @RequestMapping("/index")
    public String index(Map<String,Object> map,Model model) {
        String[] switchs = netInfo.getSwitch();
        model.addAttribute("switchs",switchs);
        List<WarnStatistic> warnStatistics = operationMapper.findWarnStatistic();
        model.addAttribute("warnStatistics",warnStatistics);
        List<Operation> operations = operationMapper.findOperation();
        model.addAttribute("operation",operations);
        List<OperationStatis> operationStatis = operationMapper.findOperationStatis();
        model.addAttribute("operStatis",operationStatis);
        return "index";
    }

    @RequestMapping("/regist")
    public String show2() {
        return "regist";
    }

    @RequestMapping(value = "loginIn", method = RequestMethod.POST)
    public String login(String name, String password, Map<String, Object> map, Model model) {
        userService.delLoginer();

        System.out.print(password);


        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(name);
        loginInfo.setPassword(password);
        UserBean userBean = userService.loginIn(name, password);
        userService.addLoginer(name);
        map.put("name", name);
        int switchNumber = netInfo.getSwitchNumber();
        map.put("switchNumber",switchNumber);
        int PcNumber = netInfo.getPcNumber();
        map.put("PcNumber",PcNumber);
        String[] switchs = netInfo.getSwitch();
        model.addAttribute("switchs",switchs);
        List<WarnStatistic> warnStatistics = operationMapper.findWarnStatistic();
        model.addAttribute("warnStatistics",warnStatistics);
        List<Operation> operations = operationMapper.findOperation();
        model.addAttribute("operation",operations);
        List<OperationStatis> operationStatis = operationMapper.findOperationStatis();
        model.addAttribute("operStatis",operationStatis);
        if (userBean != null) {
            return "index";
        } else {
            return "Beforeerror";
        }
    }

    @RequestMapping(value = "/toRegist")
    //@ResponseBody
    public String registerUser(UserRegist userRegist) {
        userService.addUser(userRegist);
        userService.addUserInfo(userRegist);
        return "login";
    }


}
