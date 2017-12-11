package com.service;

import com.AbstractTest;
import com.entity.User;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Transactional
public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;


    @Test
    public void login1() {
        String email = "m@g.c1";
        String password = "mmmmmm";
        User users = userService.login(email, password);
        Assert.assertEquals("m@g.c1",users.getUsername());
    }

    @Test
    public void login2() {
        String email = "m@g.com1";
        String password = "mmmmmm";
        User users = userService.login(email, password);
        Assert.assertEquals(null,users);
    }

    @Test
    public void signup1() {
        Assert.assertEquals("already exists",userService.signup("m@g.c1","mmmmmm","M1","G1"));
    }

    @Test
    public void signup2() {
        Assert.assertEquals("signed up",userService.signup("m@g.com1","mmmmmm","M1","G1"));
    }

}
