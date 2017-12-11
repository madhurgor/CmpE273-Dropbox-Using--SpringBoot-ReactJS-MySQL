package com.service;

import com.AbstractTest;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Paths;

@Transactional
public class FileServiceTest extends AbstractTest {

    @Autowired
    private FileServices fileServices;

    @Test
    public void star() throws Exception{
        Assert.assertEquals("starred",fileServices.star("m@g.c1","272.txt","/"));
    }

    @Test
    public void unstar() throws Exception{
        Assert.assertEquals("unstarred",fileServices.unstar("m@g.c1","202 questions and node  js.txt"));
    }

    @Test
    public void createfolder() throws Exception{
        Assert.assertEquals("folder created",fileServices.createfolder("m@g.c1","M1","/"));
    }

    @Test
    public void share() throws Exception{
        String[] s=new String[2];
        s[0]="madhur.gor@sjsu.edu";
        s[1]="madhurgor@gmail.com";
        Assert.assertEquals("shared",fileServices.share("m@g.c1","shimon shim 3rd lect.txt","/",s));
    }

    @Test
    public void shareS() throws Exception{
        String[] s=new String[2];
        s[0]="madhur.gor@sjsu.edu";
        s[1]="madhurgor@gmail.com";
        Assert.assertEquals("shared",fileServices.shareS("m@g.c1","shimon shim 3rd lect.txt",s));
    }

    @Test
    public void deletefolder() throws Exception{
        Assert.assertEquals("deleted a folder",fileServices.deletefolder("m@g.c1","m","/"));
    }

    @Test
    public void delete() throws Exception{
        Assert.assertEquals("file deleted",fileServices.delete("m@g.c1","twitter.txt","/"));
    }
}
