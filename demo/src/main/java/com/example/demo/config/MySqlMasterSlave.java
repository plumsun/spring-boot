package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description:
 * @date: 2022/7/23 14:07
 * @author: LiHaoHan
 * @program: com.example.demo.config
 */
public class MySqlMasterSlave {


    @Autowired
    private DataSource dataSource;


    @PostConstruct
    public void test() throws SQLException {
        //获取数据库连接
        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();

        //执行指定sql
        ResultSet resultSet = statement.executeQuery("SHOW SLAVE STATUS");

        while (resultSet.next()) {
            //获取sql结果集中指定字段值
            String id = resultSet.getString("Master_User");
            System.out.println("id = " + id);
        }
    }
}
