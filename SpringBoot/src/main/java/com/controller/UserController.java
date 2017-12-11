package com.controller;

import com.entity.Files;
import com.entity.Group;
import com.entity.Message;
import com.entity.User;
import com.repository.GroupRepository;
import com.repository.UserActivityRepository;
import com.service.FileServices;
import com.service.GroupServices;
import com.service.UserActivityServices;
import com.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.html.HTMLParagraphElement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller    // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileServices fileServices;
    @Autowired
    private UserActivityServices userActivityServices;
    @Autowired
    private GroupServices groupServices;

    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody String user, HttpSession session)
    {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("login "+jsonObject.getString("username")+" "+jsonObject.getString("password"));
        session.setAttribute("name",jsonObject.getString("username"));
        User u1 = (User) userService.login(jsonObject.getString("username"),jsonObject.getString("password"));
        //System.out.println(u1.get(0).getUsername());
        if(u1!=null){
            userActivityServices.saveActivity(jsonObject.getString("username"),"Logged In");
            return new ResponseEntity(u1,HttpStatus.OK);
        }else{
            Message m1=new Message();
            m1.setMessage("not found");
            return new ResponseEntity(m1,HttpStatus.OK);
        }
    }

    @PostMapping(path="/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signup(@RequestBody String user, HttpSession session)
    {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("sign up "+jsonObject.getString("username")+" "+jsonObject.getString("password"));
        String r = (String) userService.signup(jsonObject.getString("username"),jsonObject.getString("password"),jsonObject.getString("firstname"),jsonObject.getString("lastname"));
        System.out.println(r);
        Message m1=new Message();
        m1.setMessage(r);
        if(!m1.getMessage().equals("already exists")){
            userActivityServices.saveActivity(jsonObject.getString("username"),"Signed Up");
        }
        return new ResponseEntity(m1,HttpStatus.OK);
    }

    @PostMapping(path="/about",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> about(@RequestBody String user, HttpSession session)
    {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("about "+jsonObject.getString("username"));
        User u1 = (User) userService.about(jsonObject.getString("username"));
        userActivityServices.saveActivity(jsonObject.getString("username"),"Checking Personal Info");
        return new ResponseEntity(u1,HttpStatus.OK);
    }

    @PostMapping(path="/about_change",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> about_change(@RequestBody String user, HttpSession session)
    {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("about_change "+jsonObject.getString("username"));
        userService.about_change(jsonObject.getString("username"),jsonObject.getString("firstname"),jsonObject.getString("lastname"),jsonObject.getString("phone_no"),jsonObject.getString("education"),jsonObject.getString("hobbies"),jsonObject.getString("work"),jsonObject.getString("le"),jsonObject.getString("interest"));
        userActivityServices.saveActivity(jsonObject.getString("username"),"Changing Personal Info");
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path="/files_fetch",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> files_fetch(@RequestBody String user, HttpSession session)
    {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("normal files fetched "+jsonObject.getString("username")+" "+jsonObject.getString("path"));
        List<String> d=fileServices.getDirectories(jsonObject.getString("username"),jsonObject.getString("path"));
        List<String> f=fileServices.getFiles(jsonObject.getString("username"),jsonObject.getString("path"));
        userActivityServices.saveActivity(jsonObject.getString("username"),"Fetching Regular Files");
        Files f1=new Files();
        f1.setFiles(f);
        f1.setFolders(d);
        return new ResponseEntity(f1,HttpStatus.OK);
    }

    @PostMapping(path="/files_fetchR",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> files_fetchR(@RequestBody String user, HttpSession session)
    {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("star files fetched "+jsonObject.getString("username"));
        List<String> f=fileServices.getStarFiles(jsonObject.getString("username"));
        userActivityServices.saveActivity(jsonObject.getString("username"),"Fetching Starred Files");
        Files f1=new Files();
        f1.setFiles(f);
        return new ResponseEntity(f1,HttpStatus.OK);
    }

    @GetMapping(path="/star",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> star(@RequestParam("file") String item, @RequestParam("username") String username, @RequestParam("path") String path, HttpSession session) throws IOException{
        System.out.println("star file "+item+" of user "+username+" in the path of "+path);
        userActivityServices.saveActivity(username,"Starred a File "+item);
        fileServices.star(username,item,path);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/unstar",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> unstar(@RequestParam("file") String item, @RequestParam("username") String username, HttpSession session) {
        System.out.println("unstar file "+item+" of user "+username);
        fileServices.unstar(username,item);
        userActivityServices.saveActivity(username,"Unstarred a File "+item);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@RequestParam("file") String item, @RequestParam("username") String username, @RequestParam("path") String path, HttpSession session){
        System.out.println("delete file "+item+" of user "+username+" in the path of "+path);
        fileServices.delete(username,item,path);
        userActivityServices.saveActivity(username,"Deleted a File "+item);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/createfolder",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createfolder(@RequestParam("path") String path, @RequestParam("username") String username, @RequestParam("foldername") String folder, HttpSession session){
        System.out.println("create folder "+folder+" for user "+username+" in the path of "+path);
        fileServices.createfolder(username,folder,path);
        userActivityServices.saveActivity(username,"Created a Folder "+folder);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/open_folder",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> open_folder(@RequestParam("path") String path, @RequestParam("username") String username, HttpSession session){
        System.out.println("open folder for user "+username+" in the path of "+path);
        List<String> d=fileServices.getDirectories(username,path);
        List<String> f=fileServices.getFiles(username,path);
        userActivityServices.saveActivity(username,"Opened a Folder "+path);
        Files f1=new Files();
        f1.setFiles(f);
        f1.setFolders(d);
        return new ResponseEntity(f1,HttpStatus.OK);
    }

    @GetMapping(path="/delete_folder",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete_folder(@RequestParam("path") String path, @RequestParam("username") String username,@RequestParam("folder") String folder, HttpSession session){
        System.out.println("delete folder "+folder+" for user "+username+" in the path of "+path);
        fileServices.deletefolder(username,folder,path);
        userActivityServices.saveActivity(username,"Deleted a Folder "+path);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path="/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> upload(@RequestParam("f1") MultipartFile file, @RequestParam("username") String username, HttpSession session) {
        System.out.println("in upload file for "+username);
        try{
            file.transferTo(new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+ username + "/normal/"+file.getOriginalFilename()));
            userActivityServices.saveActivity(username,"Uploaded a File "+file.getOriginalFilename());
        }
        catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(path="/uploadS",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> uploadS(@RequestParam("f1") MultipartFile file, @RequestParam("username") String username, @RequestParam("path") String path, HttpSession session) {
        System.out.println("in upload file for "+username);
        try{
            file.transferTo(new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+ username + "/normal/"+path+"/"+file.getOriginalFilename()));
            userActivityServices.saveActivity(username,"Uploaded a File "+file.getOriginalFilename()+" on given path");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(path="/download",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> download(@RequestParam("path") String path, @RequestParam("username") String username,@RequestParam("file") String file, HttpSession session) throws IOException {
        System.out.println("download file "+file+" for user "+username+" in the path of "+path);
        InputStreamResource file1=fileServices.download(username,file,path);
        userActivityServices.saveActivity(username,"Downloaded a File "+file);
        return new ResponseEntity(file1,HttpStatus.OK);
    }

    @GetMapping(path="/downloadR",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> downloadR(@RequestParam("username") String username,@RequestParam("file") String file, HttpSession session) throws IOException {
        System.out.println("download star file "+file+" for user "+username);
        InputStreamResource file1=fileServices.downloadR(username,file);
        userActivityServices.saveActivity(username,"Downloaded a File "+file);
        return new ResponseEntity(file1,HttpStatus.OK);
    }

    @GetMapping(path="/shareS",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> shareS(@RequestParam("username") String username,@RequestParam("file") String file,@RequestParam("member[]") String[] member, HttpSession session) throws IOException {
        System.out.println("share star file "+file+" of user "+username);
        fileServices.shareS(username,file,member);
        userActivityServices.saveActivity(username,"Shared a Starred File "+file);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/share",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> share(@RequestParam("username") String username,@RequestParam("file") String file,@RequestParam("path") String path,@RequestParam("member[]") String[] member, HttpSession session) throws IOException {
        System.out.println("share normal file "+file+" of user "+username+" in the path of "+path);
        fileServices.share(username,file,path,member);
        userActivityServices.saveActivity(username,"Shared a Regular File "+file);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout(@RequestBody String user, HttpSession session) {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("logout "+jsonObject.getString("username"));
        System.out.println(session.getAttribute("name"));
        userActivityServices.saveActivity(jsonObject.getString("username"),"Logged Out");
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path="/group_create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> group_create(@RequestParam("username") String username,@RequestParam("grp_name") String grp_name,@RequestParam("group[]") String[] member, HttpSession session) throws IOException {
        System.out.println("creating group named "+grp_name+" for user "+username);
        fileServices.group_create(username,grp_name,member);
        for(int i=0;i<member.length;i++){
            groupServices.save(username,grp_name,member[i]);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /*@PostMapping(path="/own_groups_files",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> own_groups_files(@RequestBody String user, HttpSession session) {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("fetching groups created on own "+jsonObject.getString("username"));
        return new ResponseEntity(groupServices.findByOwnerName(jsonObject.getString("username")),HttpStatus.OK);
    }

    @PostMapping(path="/shared_groups_files",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> shared_groups_files(@RequestBody String user, HttpSession session) {
        JSONObject jsonObject = new JSONObject(user);
        System.out.println("fetching groups created on own "+jsonObject.getString("username"));
        return new ResponseEntity(groupServices.findByMemberName(jsonObject.getString("username")),HttpStatus.OK);
    }*/
}