package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @description: mq工具类
 * @date: 2022/3/2 16:50
 * @author: LiHaoHan
 * @program: utils
 */
public class RabbitMQUtil {

    /**
     * 获取信道
     * @return
     * @throws Exception
     */
    public static Channel getChannel() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.15.50.129");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //获取信道
        return connection.createChannel();
    }

}
