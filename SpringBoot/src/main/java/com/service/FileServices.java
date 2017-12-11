package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import java.nio.channels.FileChannel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServices {
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    public List<String> getFiles(String username,String path){
        List<String> r=new ArrayList<String>();
        File folder = new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/normal/"+path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                r.add(listOfFiles[i].getName());
                System.out.println("File " + listOfFiles[i].getName());
            }
        }
        return r;
    }

    public List<String> getDirectories(String username,String path){
        List<String> r=new ArrayList<String>();
        System.out.println(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/star/");
        File folder = new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/normal/"+path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                r.add(listOfFiles[i].getName());
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return r;
    }

    public List<String> getStarFiles(String username){
        List<String> r=new ArrayList<String>();
        File folder = new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/star/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                r.add(listOfFiles[i].getName());
                System.out.println("File " + listOfFiles[i].getName());
            }
        }
        return r;
    }

    public void star(String username,String item,String path) throws IOException {
        System.out.println(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/normal/"+path+"/"+item);
        System.out.println(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/star/"+item);
        File sFile= new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/normal/"+path+"/"+item);
        File dFile= new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/star/"+item);
        System.out.println(dFile.createNewFile());
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(sFile).getChannel();
            destChannel = new FileOutputStream(dFile).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }

    public void unstar(String username,String item) {
        File file = new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+ username + "/star/" + item);
        file.delete();
    }

    public void delete(String username,String item,String path) {
        File nfile = new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+ username + "/normal/"+path+"/" + item);
        File sfile = new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username + "/star/" + item);
        nfile.delete();
        sfile.delete();
    }

    public void createfolder(String username,String folder,String path) {
        new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/normal/"+path+"/"+folder).mkdir();
    }

    public InputStreamResource download(String username,String file,String path) throws IOException{
        File file1=new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username + "/normal/"+path+"/" + file);
        return new InputStreamResource(new FileInputStream(file1));
    }

    public InputStreamResource downloadR(String username,String file) throws IOException{
        File file1=new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+ username + "/star/"+ file);
        return new InputStreamResource(new FileInputStream(file1));
    }

    public void shareS(String username,String file,String[] member) {
        List<String> s=new ArrayList<String>();
        for(int i=0;i<member.length;i++){
            if(!member[i].equals("")){
                emailServiceImpl.sendMessageWithAttachment(member[i],"no-reply@dropbox.com ✔",username+" has shared file "+file+" with you. Please see attachment to view the file.",""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+ username + "/star/"+ file,file);
            }
        }
    }

    public void share(String username,String file,String path,String[] member) {
        List<String> s=new ArrayList<String>();
        for(int i=0;i<member.length;i++){
            if(!member[i].equals("")){
                emailServiceImpl.sendMessageWithAttachment(member[i],"no-reply@dropbox.com ✔",username+" has shared file "+file+" with you. Please see attachment to view the file.",""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+ username + "/normal/"+path+"/" + file,file);
            }
        }
    }

    public void deletefolder(String username,String folder,String path) {
        removeDirectory(new File(""+new File(new File(new File(new File(".").getAbsolutePath()).getParent()).getParent())+"/"+username+"/normal/"+path+"/"+folder));
    }

    public static void removeDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }
}
