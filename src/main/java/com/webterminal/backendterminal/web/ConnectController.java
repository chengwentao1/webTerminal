package com.webterminal.backendterminal.web;

import com.webterminal.backendterminal.connect.ssh.model.HostData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ContectController
 * @Description
 * @Author cwt
 * @Date 2022/10/1 21:20
 */
@Controller
public class ConnectController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/info")
    public Object getInfo() {
        return "+------测试--------+";
    }
    @GetMapping("/")
    public String index() {
        return "fronted/index";
    }
    @PostMapping("getIp")
    @ResponseBody
    public Object getIp() throws SocketException {
        Map<String, String> map= new HashMap<>();
        map.put("ip", "127.0.0.1:"+port);
        return map;
    }
    @PostMapping("/connect")
    public String connect(HostData data, Model model) throws Exception {
        // 添加参数，这样thymeleaf可以解析到对象的值,注意添加的值要是Map形式的，否则前端无法解析就会出错
        model.addAttribute("data", data);
//        ConstantPool.SSH_DATA = data;
        return "fronted/terminal";
    }
@PostMapping("/testConnect")
@ResponseBody
public Object testConnect(Object data) {
        Map<String,Object> map=new HashMap();
    map.put("res","true");
    return map;
}
}
