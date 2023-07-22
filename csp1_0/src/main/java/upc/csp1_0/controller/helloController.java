package upc.csp1_0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import upc.csp1_0.service.UserService;
import upc.csp1_0.service.neo4jService;
import upc.csp1_0.serviceImpl.UserLog;

import java.io.IOException;

@Controller
public class helloController {

    @Autowired
    UserLog userLog;
    @Autowired
    neo4jService neo4jService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "log")
    public void log() throws IOException {
       /* SwitchEntity switchEntity = new SwitchEntity("openflow");
        switchEntityRepository.save(switchEntity);*/

    }



}
