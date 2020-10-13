package com.example.userdetail.commentdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class DBUser implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "description")
    private String desc;

    @ColumnInfo(name = "image")
    private String userImage;

    @ColumnInfo(name = "repoUrl")
    private String repourl;

    @ColumnInfo(name = "comment")
    private String comment;

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    private byte[] image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getRepourl() {
        return repourl;
    }

    public void setRepourl(String repourl) {
        this.repourl = repourl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}