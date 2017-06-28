package com.spbt.web;

import com.spbt.domain.User;
import com.spbt.domain.base.BaseListBO;
import com.spbt.domain.base.PageableVO;
import com.spbt.exception.ServiceException;
import com.spbt.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExampleController extends BaseController {
    @Value("${app.name}")
    private String appName;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public @ResponseBody String hello() {
        return "Hello World!";
    }

    @RequestMapping("/jn")
    @ResponseBody
    public Map<String, Object> jn(String id) {
        if (StringUtils.isNotEmpty(id)) {
            throw new ServiceException("我去啊");
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "张菲");
        data.put("city", "北京");
        data.put("time", new Date());
        return getSuccessResult(data);
    }

    @RequestMapping(value = "/ex", method = RequestMethod.GET)
    public String test(String id, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(id)) {
            request.setAttribute("forward", "/");
            throw new ServiceException("我去啊");
        }
        System.out.println(appName);
        List<User> userList = userService.getUserList();
        System.out.println("userList==" + userList);

        PageableVO pageableVO = new PageableVO();
        //第一页
        pageableVO.setPageNo(0);
        pageableVO.setPageSize(2);
        BaseListBO userPage = userService.getUserPage(pageableVO);
        System.out.println(userPage);
        return "ex-index";
    }

}