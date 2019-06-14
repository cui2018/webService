package com.cui.webservice.server.service;

import com.cui.webservice.server.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @Auther: cui
 * @Date: 2019/6/6
 * @Description:
 */
@WebService
public interface UserService {
    @WebMethod(operationName = "cuiGetName")
    @WebResult(name = "resGetName", partName = "resPartName")
    String getName(@WebParam(name = "userId") String userId);

    @WebMethod(operationName = "cuiGetUser")
    @WebResult(name = "resGetUser", partName = "resGetUser")
    User getUser(@WebParam(name = "userId") String userId, @WebParam(name = "username") String username, @WebParam(name = "age") String age);
}
