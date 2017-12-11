package com.service;

import com.entity.UserActivity;
import com.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserActivityServices {
    @Autowired
    private UserActivityRepository userActivityRepository;

    public void saveActivity(String user,String activity){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        UserActivity n=new UserActivity();
        n.setUser_id(user);
        n.setTimestamp(""+dateFormat.format(date));
        n.setActivity(activity);
        userActivityRepository.save(n);
    }
}
