package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.io.InputStream;

public class JdbcConnectionTest {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // 加载jdbc.properties配置文件
            Properties props = new Properties();
            InputStream input = JdbcConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            props.load(input);
            
            // 加载驱动
            Class.forName(props.getProperty("jdbc.driver"));
            
            // 获取连接
            connection = DriverManager.getConnection(
                props.getProperty("jdbc.url"),
                props.getProperty("jdbc.username"),
                props.getProperty("jdbc.password")
            );
            
            System.out.println("数据库连接成功！");
            
            // 执行简单查询
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM udata");
            
            while(resultSet.next()) {
                //System.out.println("查询测试成功，返回结果: " + resultSet.toString());
                resultSet.getString("username");
                resultSet.getString("password");
                System.out.println("用户名: " + resultSet.getString("username"));
                System.out.println("密码: " + resultSet.getString("password"));
                System.out.println("-----------");
                System.out.println("-----------");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}