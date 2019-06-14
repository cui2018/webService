package com.cui.webservice.client.config;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.xml.namespace.QName;

/**
 * @Auther: cui
 * @Date: 2019/6/13
 * @Description:
 */
public class ClientUtil {

    public static Client getClient(){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8082/webServiceDemo/webservice?wsdl");
        //设置超时单位为毫秒
        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(3000);  //连接超时
        httpClientPolicy.setAllowChunking(false);    //取消块编码
        httpClientPolicy.setReceiveTimeout(3000);     //响应超时
        http.setClient(httpClientPolicy);
        return client;
    }

    public static QName getQName(String methodName){
        QName operationName = new QName("http://service.server.webservice.cui.com/", methodName);
        return operationName;
    }
}
