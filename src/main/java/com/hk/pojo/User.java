package com.hk.pojo;

import com.hk.plugins.Sex;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Date birthday;
    private String address;
    private Sex sex;
    private List ids;
    private List order;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(Integer id, String username, String password, Sex sex){
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
    }

    public User(String username, String password, Sex sex, Date birthday){
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
    }
}
