package com.study.util;

import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.MessageElement;
import org.apache.axis.types.Schema;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import java.util.*;


/**
 * @ClassName WebServiceClientU
 * @Description
 * @Author <a href="lyc_1237@163.com">ycli</a>
 * @Date 2020/11/18 2:39 下午
 * @Version 1.0.0
 */
@Component
public class WebServiceClientU {
    public static Map<String, String> callWebSVDiff(String wsdUrl, String operationName, Object... params) throws Exception {
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new java.net.URL(wsdUrl));
        call.setOperationName(new QName("http://tempuri.org/",operationName));
        call.addParameter(new QName("http://tempuri.org/","PackingCertNo"), XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/","CntrNumber"), XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/","ContainDate"), XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/","InputTime"), XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.setUseSOAPAction(true);
        call.setTimeout(1);
        call.setSOAPActionURI("http://tempuri.org/CheckBoxing");
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_SCHEMA);
        Schema result = (Schema)call.invoke(params);
        MessageElement[] data = result.get_any();

        Map<String,String> map = new HashMap<>();
        for(int i=0; i<data.length; i++){
            map.put(data[i].getName(),data[i].getValue().replaceAll(":;",""));
        }

        System.out.println(map.toString());
        return map;
    }

}
