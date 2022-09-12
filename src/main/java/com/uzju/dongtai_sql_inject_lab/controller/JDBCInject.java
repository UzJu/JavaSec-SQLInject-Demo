package com.uzju.dongtai_sql_inject_lab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@RequestMapping("/jdbc")
public class JDBCInject {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final Logger logger = LoggerFactory.getLogger(JDBCInject.class);
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * http://192.168.31.140:8821/jdbc/statement_and_inject?id=1'+and+extractvalue(1,concat(0x7e,user()))--+
     * */
    @RequestMapping("/statement_and_inject")
    public String statement_and_inject(@RequestParam("id") String id) {
        StringBuilder result = new StringBuilder();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);

            if (!con.isClosed())
                System.out.println("Connect to database successfully.");

            // sqli vuln code
            Statement statement = con.createStatement();
            String sql = "select * from user  where id = '" + id + "'";
            logger.info(sql);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String res_name = rs.getString("username");
                String res_pwd = rs.getString("password");
                String info = String.format("%s: %s\n", res_name, res_pwd);
                result.append(info);
                logger.info(info);
            }
            rs.close();
            con.close();


        } catch (ClassNotFoundException e) {
            logger.error("Sorry,can`t find the Driver!");
        } catch (SQLException e) {
            return e.toString();
        }
        return result.toString();
    }

    /**
     * http://192.168.31.140:8821/jdbc/preparestatement_and_inject?id=1'+and+extractvalue(1,concat(0x7e,user()))--+
     * */
    @RequestMapping("/preparestatement_and_inject")
    public String preparestatement_and_inject(@RequestParam("id") String id) {

        StringBuilder result = new StringBuilder();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);

            if (!con.isClosed())
                System.out.println("Connecting to Database successfully.");

            // fix code
            String sql = "select * from user where id ='" + id + "'";
            logger.info(sql);
            PreparedStatement st = con.prepareStatement(sql);

            logger.info(st.toString());  // sql after prepare statement
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String res_name = rs.getString("userName");
                System.out.println(res_name);
                String res_pwd = rs.getString("passWord");
                String info = String.format("%s: %s\n", res_name, res_pwd);
                result.append(info);
                logger.info(info);
            }

            rs.close();
            con.close();

        } catch (ClassNotFoundException e) {
            logger.error("Sorry, can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            return e.toString();
        }
        return result.toString();
    }

    /**
     * http://192.168.31.140:8821/jdbc/preparestatement_orderby_inject?id=1&orderby=id+and+extractvalue(1,concat(0x7e,user()))
     * */
    @RequestMapping("/preparestatement_orderby_inject")
    public String preparestatement_orderby_inject(@RequestParam("id") String id, @RequestParam("orderby") String orderby) {

        StringBuilder result = new StringBuilder();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);

            if (!con.isClosed())
                System.out.println("Connecting to Database successfully.");

            // fix code
            String sql = "select * from user where id = ? order by" + " " + orderby;
            logger.info(sql);
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);

            logger.info(st.toString());  // sql after prepare statement
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String res_name = rs.getString("userName");
                System.out.println(res_name);
                String res_pwd = rs.getString("passWord");
                String info = String.format("%s: %s\n", res_name, res_pwd);
                result.append(info);
                logger.info(info);
            }

            rs.close();
            con.close();

        } catch (ClassNotFoundException e) {
            logger.error("Sorry, can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            return e.toString();
        }
        return result.toString();
    }
    /**
     * http://192.168.31.140:8821/jdbc/preparestatement_like_inject?id=1%25'+and+extractvalue(1,concat(0x7e,user()))--+
     * */
    @RequestMapping("/preparestatement_like_inject")
    public String preparestatement_like_inject(@RequestParam("id") String id) {

        StringBuilder result = new StringBuilder();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);

            if (!con.isClosed())
                System.out.println("Connecting to Database successfully.");

            // fix code
            String sql = "select * from user where id like '%" + id +"%'";
            logger.info(sql);
            PreparedStatement st = con.prepareStatement(sql);

            logger.info(st.toString());  // sql after prepare statement
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String res_name = rs.getString("userName");
                System.out.println(res_name);
                String res_pwd = rs.getString("passWord");
                String info = String.format("%s: %s\n", res_name, res_pwd);
                result.append(info);
                logger.info(info);
            }

            rs.close();
            con.close();

        } catch (ClassNotFoundException e) {
            logger.error("Sorry, can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            return e.toString();
        }
        return result.toString();
    }

    /**
     * http://192.168.31.140:8821/jdbc/preparestatement_in_inject?id=1)+and+extractvalue(1,concat(0x7e,user()))--+
     * */
    @RequestMapping("/preparestatement_in_inject")
    public String preparestatement_in_inject(@RequestParam("id") String id) {

        StringBuilder result = new StringBuilder();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);

            if (!con.isClosed())
                System.out.println("Connecting to Database successfully.");

            // fix code
            String sql = "select * from user where id in (" + id +")";
            logger.info(sql);
            PreparedStatement st = con.prepareStatement(sql);

            logger.info(st.toString());  // sql after prepare statement
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String res_name = rs.getString("userName");
                System.out.println(res_name);
                String res_pwd = rs.getString("passWord");
                String info = String.format("%s: %s\n", res_name, res_pwd);
                result.append(info);
                logger.info(info);
            }

            rs.close();
            con.close();

        } catch (ClassNotFoundException e) {
            logger.error("Sorry, can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            return e.toString();
        }
        return result.toString();
    }
}
