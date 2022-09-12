package com.uzju.dongtai_sql_inject_lab.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "test", catalog = "test")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "userName")
    private String userName;
    @Basic
    @Column(name = "passWord")
    private String passWord;
    @Basic
    @Column(name = "realName")
    private String realName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id && Objects.equals(userName, that.userName) && Objects.equals(passWord, that.passWord) && Objects.equals(realName, that.realName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, passWord, realName);
    }
}

