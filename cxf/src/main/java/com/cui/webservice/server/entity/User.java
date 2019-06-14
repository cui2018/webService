package com.cui.webservice.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: cui
 * @Date: 2019/6/6
 * @Description:
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -5939599230753662529L;
    private String userId;
    private String username;
    private String age;
    public User(){}
    public User(String userId, String username, String age){
        this.userId = userId;
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
