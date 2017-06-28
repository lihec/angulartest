package com.spbt.dao;

import com.spbt.domain.User;
import com.spbt.domain.base.Page;

import java.util.List;

/**
 *
 * @author 李贺[of253]
 * @date 2015/8/10 10:32
 */
public interface UserMapper {

    List<User> getUserList();

    List<User> getUserPage(Page page);
}
