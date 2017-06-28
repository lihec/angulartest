package com.spbt.service;

import com.spbt.dao.UserMapper;
import com.spbt.domain.User;
import com.spbt.domain.base.BaseListBO;
import com.spbt.domain.base.Page;
import com.spbt.domain.base.PageableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * user服务
 * @author 李贺[of253]
 * @date 2015/8/10 10:43
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(){
        return userMapper.getUserList();
    }

    public BaseListBO getUserPage(PageableVO vo){
        Page page = Page.getInstance(vo);
        List<User> list = userMapper.getUserPage(page);
        return BaseListBO.getInstance(page, list);
    }
}
