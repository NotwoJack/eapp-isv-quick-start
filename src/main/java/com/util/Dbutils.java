package com.util;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Dbutils {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://192.168.0.125:3306/ruian_edu_warning?characterEncoding=utf-8";
    public static final String USERNAME = "nriat_dev";
    public static final String PASSWORD = "Dev530#";
    
     //public static final String URL = "jdbc:mysql://114.215.216.233:3306/jsxy_pre?characterEncoding=utf-8";
     //public static final String USERNAME = "jsxy";
    // public static final String PASSWORD = "Jsxy!@#654321";
    /*
     * 创建连接池BasicDataSource
     */
    public static BasicDataSource dataSource = new BasicDataSource();
    //静态代码块
    static {
        //对连接池对象 进行基本的配置
        dataSource.setDriverClassName(DRIVER); // 这是要连接的数据库的驱动
        dataSource.setUrl(URL); //指定要连接的数据库地址
        dataSource.setUsername(USERNAME); //指定要连接数据的用户名
        dataSource.setPassword(PASSWORD); //指定要连接数据的密码
    }
    /*
     * 返回连接池对象
     */
    public static DataSource getDataSource(){
        return dataSource;
    }
    public   static  Connection  getConn() throws SQLException{
        return   dataSource.getConnection();
    }
    public static void main(String[] args) {
    	try {
			Connection conn=Dbutils.getConn();
			System.err.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
}