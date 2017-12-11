package com.service;

import com.entity.Group;
import com.entity.UserActivity;
import com.repository.GroupRepository;
import com.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GroupServices{
    @Autowired
    private GroupRepository groupRepository;

    /*public List<Group> findByOwnerName(String o){
        return groupRepository.findByowener_name(o);
    }

    public List<Group> findByMemberName(String o){
        return groupRepository.findBymember_name(o);
    }*/

    public void save(String user,String grp, String member){
        new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+grp+" "+user).mkdir();
        Group n=new Group();
        n.setGroup_name(grp);
        n.setMember_name(member);
        n.setOwner_name(user);
        groupRepository.save(n);
    }
}