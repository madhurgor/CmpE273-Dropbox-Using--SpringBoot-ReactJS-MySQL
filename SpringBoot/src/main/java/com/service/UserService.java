package com.service;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User login(String username,String password){
        System.out.println(userRepository.findByUsernameAndPassword(username,password));
        User u1 = (User) userRepository.findByUsernameAndPassword(username,password);
        return u1;
    }

    public User about(String username){
        System.out.println(userRepository.findByUsername(username));
        User u1 = (User) userRepository.findByUsername(username);
        return u1;
    }

    public void about_change(String username,String firstname,String lastname,String phone_no,String education,String hobbies,String work,String le,String interest){
        User user = new User();
        user.setUsername(username);
        user.setHobbies(hobbies);
        user.setWork(work);
        user.setPhone_no(phone_no);
        user.setEducation(education);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setLe(le);
        user.setInterest(interest);
        user.setPassword(userRepository.findByUsername(username).getPassword());
        userRepository.save(user);
    }

    public String signup(String username,String password,String firstname,String lastname){
        System.out.println(userRepository.findByUsername(username));
        User u1 = (User) userRepository.findByUsername(username);
        if(u1!=null){
            return "already exists";
        }else{
            User n = new User();
            n.setFirstname(firstname);
            n.setPassword(password);
            n.setUsername(username);
            n.setLastname(lastname);
            userRepository.save(n);
            new File("H:/node/CmpE273-Dropbox-Using--SpringBoot-ReactJS-MySQL/"+username).mkdir();
            new File("H:/node/CmpE273-Dropbox-Using--SpringBoot-ReactJS-MySQL/"+username+"/normal").mkdir();
            new File("H:/node/CmpE273-Dropbox-Using--SpringBoot-ReactJS-MySQL/"+username+"/star").mkdir();
            return "signed up";
        }
    }
}