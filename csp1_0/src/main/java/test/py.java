package test;

import java.sql.*;

public class py {
    public static void main(String[] args) {
        sql_test t1 = new sql_test();
        int [] a =new int[2];
        for (int i = 0; i < t1.sql_lianjie().length; i++) {
            a [i]=t1.sql_lianjie()[i];
            System.out.println(a[i]);
        }
        System.out.println(a[0]);
    }
}
class sql_test {
    int safe;
    int unsafe;
    int[] sql_lianjie() {
        Connection con = null;
        Statement sql;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载JDBC—MySQL数据库驱动
        } catch (Exception e) {
        }
//        String uri="jdbc:mysql://127.0.0.1:3306/students?useSSL=true";
        String uri = "jdbc:mysql://rm-m5eflj34w7ot74658ko.mysql.rds.aliyuncs.com/mydata";
        String user = "user";
        String password = "QZX331zwj21223";
        try {
            con = DriverManager.getConnection(uri, user, password);//连接数据库
            System.out.println(con);
        } catch (SQLException e) {

        }

        try {
            sql = con.createStatement();//创建SQL对象
            rs = sql.executeQuery("SELECT * FROM num");
            while (rs.next()) {
                int safe = rs.getInt("safe");
                int unsafe = rs.getInt("unsafe");
                this.safe = safe;
                this.unsafe = unsafe;
            }
            con.close();
        } catch (SQLException e) {

            System.out.println(e);

        }
        int[] num = {safe, unsafe};
        return num;
    }
}


