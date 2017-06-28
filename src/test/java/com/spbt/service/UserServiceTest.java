package com.spbt.service;

import com.spbt.Application;
import com.spbt.domain.User;
import com.spbt.domain.base.BaseListBO;
import com.spbt.domain.base.PageableVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService Tester.
 *
 * @author <Authors name>
 * @since <pre>八月 24, 2015</pre>
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    //@Before
    //public void before() throws Exception {
    //}
    //
    //@After
    //public void after() throws Exception {
    //}

    /**
     *
     * Method: getUserList()
     *
     */
    @Test
    public void testGetUserList() {
        List<User> userList = userService.getUserList();
        System.out.println(userList);
        Assert.assertTrue(userList.size() == 5);
    }

    /**
     *
     * Method: getUserPage(PageableVO vo)
     *
     */
    @Test
    public void testGetUserPage() throws Exception {
        PageableVO pageableVO = new PageableVO();
        //第一页
        pageableVO.setPageNo(0);
        pageableVO.setPageSize(2);
        BaseListBO userPage = userService.getUserPage(pageableVO);
        System.out.println(userPage);
        Assert.assertTrue(userPage.getTotalcount() == 5);
        Assert.assertTrue(userPage.getDatalist() != null && userPage.getDatalist().size() == 2);
    }

} 
