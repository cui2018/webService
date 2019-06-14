package com.cui.webservice.server.impl;

import com.cui.webservice.server.entity.User;
import com.cui.webservice.server.service.UserService;

import javax.jws.WebService;

/**
 * @Auther: cui
 * @Date: 2019/6/6
 * @Description:
 */
@WebService(name="cuiWebService", targetNamespace="http://service.server.webservice.cui.com/", endpointInterface = "com.cui.webservice.server.service.UserService")
public class UserServiceImpl implements UserService {
    @Override
    public String getName(String userId) {
        return userId;
    }

    @Override
    public User getUser(String userId, String username, String age) {
        return new User(userId, username, age);
    }
}
