package com.oracle;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 0:50 2021/4/12
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
import oracle.jdbc.driver.OracleDriver;
import java.sql.*;
import java.util.Properties;
/**
 * Created by 10412 on 2016/12/27.
 * JDBC的六大步骤
 * JAVA连接Oracle的三种方式
 */
public class JdbcTest
{
    public static void main(String[] args) {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //第一步：注册驱动
            //第一种方式：类加载(常用)
            Class.forName("oracle.jdbc.OracleDriver");

            //第二种方式：利用Driver对象
//            Driver driver = new OracleDriver();
//            DriverManager.deregisterDriver(driver);


            //第二步：获取连接
            //第一种方式：利用DriverManager（常用）
            connect = DriverManager.getConnection(" jdbc:oracle:thin:@127.0.0.1:1521:XE", "briup", "briup");
           //orcl
            //第二种方式：直接使用Driver
//            Properties pro = new Properties();
//            pro.put("user", "你的oracle数据库用户名");
//            pro.put("password", "用户名密码");
//            connect = driver.connect("jdbc:oracle:thin:@localhost:1521:XE", pro);

            //测试connect正确与否
            System.out.println(connect);


            //第三步：获取执行sql语句对象
            //第一种方式:statement
            //statement = connect.createStatement();

            //第二种方式：PreStatement
            PreparedStatement preState = connect.prepareStatement("select  * from tb1_dept where id = ?");


            //第四步：执行sql语句
            //第一种方式：
            //resultSet = statement.executeQuery("select  * from tb1_dept");

            //第二种方式：
            preState.setInt(1, 2);//1是指sql语句中第一个？,  2是指第一个？的values值
            //resultSet = preState.executeQuery();        //执行查询语句
            //查询任何语句，如果有结果集，返回true，没有的话返回false,注意如果是插入一条数据的话，虽然是没有结果集，返回false，但是却能成功的插入一条数据
            boolean execute = preState.execute();
            System.out.println(execute);

            //第五步：处理结果集
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                System.out.println(id+"   "+name+"   "+city);  //打印输出结果集
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //第六步：关闭资源
            try {
                if (resultSet!=null) resultSet.close();
                if (statement!=null) statement.close();
                if (connect!=null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}