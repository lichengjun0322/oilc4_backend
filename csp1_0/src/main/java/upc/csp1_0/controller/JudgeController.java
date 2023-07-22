package upc.csp1_0.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import upc.csp1_0.dao.*;
import upc.csp1_0.model.*;
import upc.csp1_0.service.*;
import upc.csp1_0.service.FlowService;
import upc.csp1_0.service.UserService;
import upc.csp1_0.serviceImpl.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class JudgeController {
    @Autowired
    RequestTopology requestTopology;
    @Autowired
    ChangeRoute changeRoute;
    @Autowired
    DeleteFlow deleteFlow;
    @Autowired
    AuthorityJudge authorityJudge;
    @Autowired
    UserService userService;
    @Autowired
    FlowService flowService;
    @Autowired
    OperationMapper operationMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserLog userLog;
    @Autowired
    RunKmeans runKmeans;
    @Autowired
    IsLegal isLegal;
    @Autowired
    neo4jServiceImpl neo4jService;
    @Autowired
    NetInfo netInfo;
    @Autowired
    FaultRecovery faultRecovery;

    @Resource
    LoginMapper loginMapper;

    @Resource
    BittorrentMapper bittorrentMapper;
    @Resource
    FacetimeMapper facetimeMapper;
    @Resource
    FTPMapper ftpMapper;
    @Resource
    GmailMapper gmailMapper;
    @Resource
    MySQLMapper mySQLMapper;
    @Resource
    OutlookMapper outlookMapper;
    @Resource
    SkyperMapper skyperMapper;
    @Resource
    SMBMapper smbMapper;
    @Resource
    WeiboMapper weiboMapper;
    @Resource
    WorldOfWarcraftMapper worldOfWarcraftMapper;

    @Resource
    CridexMapper cridexMapper;
    @Resource
    GeodoMapper geodoMapper;
    @Resource
    HtbotMapper htbotMapper;
    @Resource
    MiurefMapper miurefMapper;
    @Resource
    NerisMapper nerisMapper;
    @Resource
    Nsis_ayMapper nsis_ayMapper;
    @Resource
    ShifuMapper shifuMapper;
    @Resource
    TinbaMapper tinbaMapper;
    @Resource
    VirutMapper virutMapper;
    @Resource
    ZeusMapper zeusMapper;



    /**
     * @description:获取网络拓扑
     * @return:Json格式网络拓扑信息
     */

    @RequestMapping(value = "/index0")
    public String index0(Map<String, Object> map,Model model) {
        System.out.println(loginMapper.findAll().get(0).getCode());
        int code = loginMapper.findAll().get(0).getCode();
        model.addAttribute("code",code);
        return "index0";
    }

    @RequestMapping(value = "/newPage")
    public String newPage(Map<String, Object> map,Model model){
        int safe = loginMapper.findAll().get(0).getCode();
        int unsafe = loginMapper.findAll().get(0).getId();
        model.addAttribute("safe",safe);
        model.addAttribute("unsafe",unsafe);

        String name = userService.getLoginer();
        map.put("name", name);

        int [] num_Bittorrent = new int[7];
        int sum_Bittorrent=0;
        for(int i=0;i<=6;i++)
        {
            num_Bittorrent[i]=bittorrentMapper.findAll().get(i).getValue();
            sum_Bittorrent=sum_Bittorrent+num_Bittorrent[i];
        }
        model.addAttribute("num_Bittorrent",num_Bittorrent);
        model.addAttribute("sum_Bittorrent",sum_Bittorrent);

        int [] num_facetime= new int[7];
        int sum_facetime=0;
        for(int i=0;i<=6;i++)
        {
            num_facetime[i]=facetimeMapper.findAll().get(i).getValue();
            sum_facetime = sum_facetime+num_facetime[i];
        }
        model.addAttribute("num_facetime",num_facetime);
        model.addAttribute("sum_facetime",sum_facetime);

        int [] num_ftp= new int[7];
        int sum_ftp=0;
        for(int i=0;i<=6;i++)
        {
            num_ftp[i]=ftpMapper.findAll().get(i).getValue();
            sum_ftp=sum_ftp+num_ftp[i];
        }
        model.addAttribute("num_ftp",num_ftp);
        model.addAttribute("sum_ftp",sum_ftp);

        int [] num_gmail= new int[7];
        int sum_gmail=0;
        for(int i=0;i<=6;i++)
        {
            num_gmail[i]=gmailMapper.findAll().get(i).getValue();
            sum_gmail=sum_gmail+num_gmail[i];
        }
        model.addAttribute("num_gmail",num_gmail);
        model.addAttribute("sum_gmail",sum_gmail);

        int [] num_mysql= new int[7];
        int sum_mysql=0;
        for(int i=0;i<=6;i++)
        {
            num_mysql[i]=mySQLMapper.findAll().get(i).getValue();
            sum_mysql=sum_mysql+num_mysql[i];
        }
        model.addAttribute("num_mysql",num_mysql);
        model.addAttribute("sum_mysql",sum_mysql);

        int [] num_outlook= new int[7];
        int sum_outlook=0;
        for(int i=0;i<=6;i++)
        {
            num_outlook[i]=outlookMapper.findAll().get(i).getValue();
            sum_outlook=num_outlook[i]+sum_outlook;
        }
        model.addAttribute("num_outlook",num_outlook);
        model.addAttribute("sum_outlook",sum_outlook);

        int [] num_skype= new int[7];
        int sum_skype=0;
        for(int i=0;i<=6;i++)
        {
            num_skype[i]=skyperMapper.findAll().get(i).getValue();
            sum_skype=sum_skype+num_skype[i];
        }
        model.addAttribute("num_skype",num_skype);
        model.addAttribute("sum_skype",sum_skype);

        int [] num_smb= new int[7];
        int sum_smb=0;
        for(int i=0;i<=6;i++)
        {
            num_smb[i]=smbMapper.findAll().get(i).getValue();
            sum_smb=sum_smb+num_smb[i];
        }
        model.addAttribute("num_smb",num_smb);
        model.addAttribute("sum_smb",sum_smb);

        int [] num_weibo= new int[7];
        int sum_weibo = 0;
        for(int i=0;i<=6;i++)
        {
            num_weibo[i]=weiboMapper.findAll().get(i).getValue();
            sum_weibo = sum_weibo+num_weibo[i];
        }
        model.addAttribute("num_weibo",num_weibo);
        model.addAttribute("sum_weibo",sum_weibo);

        int [] num_worldof= new int[7];
        int sum_worldof = 0;
        for(int i=0;i<=6;i++)
        {
            num_worldof[i]=worldOfWarcraftMapper.findAll().get(i).getValue();
            sum_worldof=sum_worldof+num_worldof[i];
        }
        model.addAttribute("num_worldof",num_worldof);
        model.addAttribute("sum_worldof",sum_worldof);

//        hahah
        int [] num_cridex = new int[7];
        int sum_cridex = 0;
        for(int i=0;i<=6;i++)
        {
            num_cridex[i]=cridexMapper.findAll().get(i).getValue();
            sum_cridex = sum_cridex+num_cridex[i];
        }
        model.addAttribute("num_cridex",num_cridex);
        model.addAttribute("sum_cridex",sum_cridex);

        int [] num_geodo= new int[7];
        int sum_geodo = 0;
        for(int i=0;i<=6;i++)
        {
            num_geodo[i]=geodoMapper.findAll().get(i).getValue();
            sum_geodo = sum_geodo+num_geodo[i];
        }
        model.addAttribute("num_geodo",num_geodo);
        model.addAttribute("sum_geodo",sum_geodo);

        int [] num_htbot= new int[7];
        int sum_htbot = 0;
        for(int i=0;i<=6;i++)
        {
            num_htbot[i]=htbotMapper.findAll().get(i).getValue();
            sum_htbot = sum_htbot+num_htbot[i];
        }
        model.addAttribute("num_htbot",num_htbot);
        model.addAttribute("sum_htbot",sum_htbot);

        int [] num_miure= new int[7];
        int sum_miure=0;
        for(int i=0;i<=6;i++)
        {
            num_miure[i]=miurefMapper.findAll().get(i).getValue();
            sum_miure=sum_miure+num_miure[i];
        }
        model.addAttribute("num_miure",num_miure);
        model.addAttribute("sum_miure",sum_miure);

        int [] num_neris= new int[7];
        int sum_neris=0;
        for(int i=0;i<=6;i++)
        {
            num_neris[i]=nerisMapper.findAll().get(i).getValue();
            sum_neris = sum_neris + num_neris[i];
        }
        model.addAttribute("num_neris",num_neris);
        model.addAttribute("sum_neris",sum_neris);

        int [] num_ay= new int[7];
        int sum_ay = 0;
        for(int i=0;i<=6;i++)
        {
            num_ay[i]=nsis_ayMapper.findAll().get(i).getValue();
            sum_ay =sum_ay+num_ay[i];
        }
        model.addAttribute("num_ay",num_ay);
        model.addAttribute("sum_ay",sum_ay);

        int [] num_shifu= new int[7];
        int sum_shifu = 0;
        for(int i=0;i<=6;i++)
        {
            num_shifu[i]=shifuMapper.findAll().get(i).getValue();
            sum_shifu = sum_shifu + num_shifu[i];
        }
        model.addAttribute("num_shifu",num_shifu);
        model.addAttribute("sum_shifu",sum_shifu);

        int [] num_virut= new int[7];
        int sum_virut = 0;
        for(int i=0;i<=6;i++)
        {
            num_virut[i]=virutMapper.findAll().get(i).getValue();
            sum_virut = sum_virut+num_virut[i];
        }
        model.addAttribute("num_virut",num_virut);
        model.addAttribute("sum_virut",sum_virut);

        int [] num_tinba= new int[7];
        int sum_tinba = 0;
        for(int i=0;i<=6;i++)
        {
            num_tinba[i]=tinbaMapper.findAll().get(i).getValue();
            sum_tinba=sum_tinba+num_tinba[i];
        }
        model.addAttribute("num_tinba",num_tinba);
        model.addAttribute("sum_tinba",sum_tinba);

        int [] num_zeus= new int[7];
        int sum_zeus = 0;
        for(int i=0;i<=6;i++)
        {
            num_zeus[i]=zeusMapper.findAll().get(i).getValue();
            sum_zeus=sum_zeus+num_zeus[i];
        }
        model.addAttribute("num_zeus",num_zeus);
        model.addAttribute("sum_zeus",sum_zeus);

        return "newPage";
    }


    @RequestMapping(value = "/topology")
    public String topology(Map<String, Object> map,Model model) {
        boolean flag = authorityJudge.grandJudge(requestTopology);
        String name = userService.getLoginer();
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


        sql_test t1 = new sql_test();
        int [] number =new int[2];
        for (int i = 0; i < t1.sql_lianjie().length; i++) {
            number [i]=t1.sql_lianjie()[i];
            System.out.println(number[i]);
        }
        System.out.println(number[0]);
        model.addAttribute("number",number);

//        List<WarnStatistic> warnStatistics1 = operationMapper.findWarnStatistic();
//        model.addAttribute("warnStatistics1",warnStatistics1);

        int [] warnStatistics1 = {1,2,3,4,5,6,7,8,9,10,11,12};
        model.addAttribute("warnStatistics1",warnStatistics1);
        System.out.println(warnStatistics1);
        if (flag == true) {
            return "index";
        } else {
            return "Beforeerror";
        }
    }
    /**
     * @description:下发流表改变路由
     * @return:无返回值
     */
    @RequestMapping(value = "/changeRoute")
    public String changeRoute(Map<String, Object> map) {
        boolean flag = authorityJudge.grandJudge(changeRoute);
        String name = userService.getLoginer();
        map.put("name", name);
        if (flag == true) {
            return "insertFlow";
        } else {
            return "Beforeerror";
        }
    }

    /**
     * 暂时没有做查询重复流表
     *
     * @param flowOfInsert
     * @return
     * @throws UnirestException
     */
    @RequestMapping(value = "/insert-flow")
    public String insertFlow(FlowOfInsert flowOfInsert, Map<String, Object> map) throws UnirestException, IOException {
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
            flowService.insertFlow(flowOfInsert);
            neo4jService.createFlowNode(flowOfInsert.getFlow_id(),flowOfInsert.getNode_id(),flowOfInsert.getTable_id(),
                    flowOfInsert.getPriority(),flowOfInsert.getFlow_name(),flowOfInsert.getSource_ip(),
                    flowOfInsert.getOutput_port(),"INSERT");
            neo4jService.addSwithcFlowRelationship(flowOfInsert.getNode_id(),flowOfInsert.getFlow_id(),flowOfInsert.getNode_id());
            changeRoute.reRoute(flowOfInsert.getFlow_id(), flowOfInsert.getNode_id(), flowOfInsert.getTable_id(),
                    flowOfInsert.getPriority(), flowOfInsert.getFlow_name(), flowOfInsert.getSource_ip(),
                    flowOfInsert.getOutput_port());
            //System.out.println(flowOfInsert.toString());
            map.put("message1", "[系统后台]:");
            map.put("message2", "流表下发成功!");
            return "insertFlow";
        } else {
            if (flag1 == true && flag2 != true) {
                map.put("message1", "[系统后台]:");
                map.put("message2", "[状态]:流表下发失败");
                map.put("message3", "[可能原因]:请检查flow_id是否重复，或是流表指令重复下发，影响了原有策略!");
                return "insertFlow";
            }
            if ((flag1 != true && flag2 == true) || isLegalFlow == false) {
                int grand = userMapper.getUserGrade(userMapper.getLoginer());
                if (isLegalFlow == true) {
                    if (grand != 9) {
                        operationMapper.insertFlowCache(flowOfInsert.getFlow_id(), flowOfInsert.getNode_id(), flowOfInsert.getTable_id(),
                                flowOfInsert.getPriority(), flowOfInsert.getFlow_name(), flowOfInsert.getSource_ip(),
                                flowOfInsert.getOutput_port(), "INSERT");
                        map.put("message1", "[系统后台]:");
                        map.put("message2", "[状态]:流表下发待审核");
                        map.put("message3", "[可能原因]:流表指令与原有策略发生冲突，影响了原有策略!已提交至审核员审核!");
                    }
                } else {
                    if (grand != 9) {
                        String name = userService.getLoginer();
                        operationMapper.insertWarnningCahe(flowOfInsert.getFlow_id(), flowOfInsert.getNode_id(), flowOfInsert.getTable_id(),
                                flowOfInsert.getPriority(), flowOfInsert.getFlow_name(), flowOfInsert.getSource_ip(),
                                flowOfInsert.getOutput_port(), "INSERT",name);
                        map.put("message1", "[系统后台]:");
                        map.put("message2", "[状态]:流表下发待审核");
                        map.put("message3", "[可能原因]:流表指令与原有策略发生冲突，影响了原有策略!已提交至审核员审核!");
                    }
                }

                return "insertFlow";
            } else {
                map.put("message1", "[系统后台]:");
                map.put("message2", "[状态]:流表下发失败");
                map.put("message3", "[可能原因]:流表下发失败，策略中已配置相同指令！");
                return "insertFlow";
            }
        }
    }

    /**
     * @description:删除流表
     * @return:无返回值
     */
    @RequestMapping(value = "/deleteFlow")
    public String delteFlow(Map<String, Object> map) {
        boolean flag = authorityJudge.grandJudge(deleteFlow);
        String name = userService.getLoginer();
        map.put("name", name);
        if (flag == true) {
            return "deleteFlow";
        } else {
            return "Beforeerror";
        }
    }

    @RequestMapping(value = "/delete-flow")
    public String deleteFlowImpl(FlowOfDel flowOfDel) throws UnirestException, IOException {
        FlowCache findFlow = operationMapper.findFlows(flowOfDel.getFlow_id(), flowOfDel.getTable_id(),flowOfDel.getNode_id());
        userLog.writeLog((String) findFlow.getFlow_id() + " " + (String) findFlow.getNode_id()
                + " " + (String) findFlow.getTable_id() + " " + (String) findFlow.getPriority() + " " + (String) findFlow.getFlow_name()
                + " " + (String) findFlow.getSource_ip() + " " + (String) findFlow.getOut_port() + " " + "DELETE");
        //System.out.println(findFlow);
        flowService.deleteFlow(flowOfDel);
        deleteFlow.delFlow(flowOfDel.getTable_id(), flowOfDel.getFlow_id(),flowOfDel.getNode_id());
        neo4jService.deleteSwitchFlowRelationship(findFlow.getNode_id(),findFlow.getFlow_id(),findFlow.getNode_id());
        neo4jService.deleteFlowNode(findFlow.getFlow_id(),findFlow.getNode_id());

        return "deleteFlow";
    }

    @RequestMapping(value = "/flow-cache-view")
    public String flowCacheView(Map<String, Object> map, Model model) {
        int grand = userMapper.getUserGrade(userMapper.getLoginer());
        if (grand == 9) {
            String name = userService.getLoginer();
            map.put("name", name);
            map.put("message", "pass");
            List<FlowCache> flows = operationMapper.findFlowCache();
            model.addAttribute("flows", flows);
            int switchNumber = netInfo.getSwitchNumber();
            map.put("switchNumber",switchNumber);
            int PcNumber = netInfo.getPcNumber();
            map.put("PcNumber",PcNumber);
            String[] switchs = netInfo.getSwitch();
            model.addAttribute("switchs",switchs);
            return "flow-apply-alerts";
        } else {
            map.put("message", "reject");
            String[] switchs = netInfo.getSwitch();
            model.addAttribute("switchs",switchs);
            int switchNumber = netInfo.getSwitchNumber();
            map.put("switchNumber",switchNumber);
            int PcNumber = netInfo.getPcNumber();
            map.put("PcNumber",PcNumber);
            List<WarnStatistic> warnStatistics = operationMapper.findWarnStatistic();
            model.addAttribute("warnStatistics",warnStatistics);
            List<Operation> operations = operationMapper.findOperation();
            model.addAttribute("operation",operations);
            List<OperationStatis> operationStatis = operationMapper.findOperationStatis();
            model.addAttribute("operStatis",operationStatis);
            return "index";
        }

    }

    @RequestMapping(value = "reject")
    public String rejectFlow(HttpServletRequest request) {
        String flow_id = (String) request.getParameter("flow_id");
        operationMapper.deleteFloaCache(flow_id);
        System.out.println(flow_id);
        return "redirect:/flow-cache-view";
    }

    @RequestMapping(value = "pass")
    public String passFlow(HttpServletRequest request) throws UnirestException {
        operationMapper.passFlowCache((String) request.getParameter("flow_id"), (String) request.getParameter("node_id"),
                (String) request.getParameter("table_id"), (String) request.getParameter("priority"),
                (String) request.getParameter("flow_name"), (String) request.getParameter("source_ip"),
                (String) request.getParameter("out_port"), (String) request.getParameter("type"));
        operationMapper.deleteFloaCache((String) request.getParameter("flow_id"));
        changeRoute.reRoute((String) request.getParameter("flow_id"), (String) request.getParameter("node_id"),
                (String) request.getParameter("table_id"), (String) request.getParameter("priority"),
                (String) request.getParameter("flow_name"), (String) request.getParameter("source_ip"),
                (String) request.getParameter("out_port"));
        return "redirect:/flow-cache-view";
    }

    @RequestMapping(value = "/kmeans")
    public String KMeans(Map<String, Object> map,Model model) throws IOException {
        runKmeans.runner();
        String name = userService.getLoginer();
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
        return "index";
    }

    @RequestMapping(value = "/isLegal")
    public String isLegalFlow(Map<String, Object> map, Model model) {
        int grand = userMapper.getUserGrade(userMapper.getLoginer());
        if (grand == 9) {
            String name = userService.getLoginer();
            map.put("name", name);
            map.put("message", "pass");
            List<WarnningCahe> flows = operationMapper.findWarnningCache();
            model.addAttribute("flows", flows);
            return "isLegalFlow";
        } else {
            map.put("message", "reject");
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
            return "index";
        }
    }

    @RequestMapping(value = "reject-warnning-cahe")
    public String rejectWarnningCahe(HttpServletRequest request) {
        String flow_id = (String) request.getParameter("flow_id");
        operationMapper.deleteWarnningCache(flow_id);
        System.out.println(flow_id);
        return "redirect:/isLegal";
    }

    @RequestMapping(value = "pass-warnning-cahe")
    public String passWarnningCahe(HttpServletRequest request) throws UnirestException {
        operationMapper.passFlowCache((String) request.getParameter("flow_id"), (String) request.getParameter("node_id"),
                (String) request.getParameter("table_id"), (String) request.getParameter("priority"),
                (String) request.getParameter("flow_name"), (String) request.getParameter("source_ip"),
                (String) request.getParameter("out_port"), (String) request.getParameter("type"));
        operationMapper.deleteWarnningCache((String) request.getParameter("flow_id"));
        changeRoute.reRoute((String) request.getParameter("flow_id"), (String) request.getParameter("node_id"),
                (String) request.getParameter("table_id"), (String) request.getParameter("priority"),
                (String) request.getParameter("flow_name"), (String) request.getParameter("source_ip"),
                (String) request.getParameter("out_port"));
        return "redirect:/isLegal";
    }

    @RequestMapping(value = "graph")
    public String infoGraph(Map<String, Object> map, Model model) {
            String name = userService.getLoginer();
            map.put("name", name);
            map.put("message", "pass");
            Map<String,List> map1 = neo4jService.queryAll();
            model.addAttribute("flows",map1);
            return "informGraph";

    }

    @RequestMapping(value = "network-topology")
    public String netTopology(Map<String, Object> map,Model model){
        String name = userService.getLoginer();
        map.put("name", name);
        String[] switchs = netInfo.getSwitch();
        String[] pc = netInfo.getPc();
        String[][] links = netInfo.getLinks();
        model.addAttribute("switch",switchs);
        model.addAttribute("pc",pc);
        model.addAttribute("links",links);
        return "network-topology";
    }

    @RequestMapping(value = "fault-recovery")
    public String faultRecovery(Map<String, Object> map,Model model){
        final String[][] faultLink = faultRecovery.findFault();
        String name = userService.getLoginer();
        map.put("name", name);
        model.addAttribute("flink",faultLink);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String[][] faultLink2 = faultRecovery.findFault();
                System.out.println(faultLink2.length);
            }
        },20,10);
        return "fault-recovery";
    }

}



class sql_test {
    int safe;
    int unsafe;
    int[] sql_lianjie() {
        Connection con = null;
        Statement sql;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载JDBC—MySQL数据库驱动
        } catch (Exception e) {
        }
//        String uri="jdbc:mysql://127.0.0.1:3306/students?useSSL=true";
        String uri = "jdbc:mysql://rm-m5eflj34w7ot74658ko.mysql.rds.aliyuncs.com/zhz";
        String user = "user";
        String password = "QZX331zwj21223";
        try {
            con = DriverManager.getConnection(uri, user, password);//连接数据库
        } catch (SQLException e) {
        }

        try {
            sql = con.createStatement();//创建SQL对象
            rs = sql.executeQuery("SELECT * FROM traffic_num");
            while (rs.next()) {
                int safe = rs.getInt("Safe_traffic_num");
                int unsafe = rs.getInt("Unsafe_traffic_num");
                this.safe = safe;
                this.unsafe = unsafe;
            }
            con.close();
        } catch (SQLException e) {

            System.out.println(e);

        }
        int[] num = {safe, unsafe};
        return num;
    }
}
