package com.study.webService;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.MessageElement;
import org.apache.axis.types.Schema;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WebServiceClientU {

    public Map<String, String> callWebSVDiff(String wsdUrl, String operationName, Object... params) throws Exception {
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new java.net.URL(wsdUrl));
        call.setOperationName(new QName("http://tempuri.org/", operationName));
        call.addParameter(new QName("http://tempuri.org/", "PackingCertNo"),
                Constants.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "CntrNumber"),
                Constants.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "ContainDate"),
                Constants.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "InputTime"),
                Constants.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        call.setUseSOAPAction(true);
        // 设置超时时间,单位ms
        call.setTimeout(15000);
        // 设置项目uri
        call.setSOAPActionURI("http://tempuri.org/CheckBoxing");
        call.setReturnType(Constants.XSD_SCHEMA);
        // 输出SOAP请求报文
        log.info("--SOAP Request:{}", call.getMessageContext().getRequestMessage().getSOAPPartAsString());
        Schema result = (Schema) call.invoke(params);
        MessageElement[] data = result.get_any();
        // 输出SOAP返回报文
        log.info("--SOAP Response: {}", call.getResponseMessage().getSOAPPartAsString());

        Map<String, String> map = new HashMap<>();
        for (MessageElement datum : data) {
            map.put(datum.getName(), datum.getValue().replace(":;", ""));
        }
        return map;
    }

}
