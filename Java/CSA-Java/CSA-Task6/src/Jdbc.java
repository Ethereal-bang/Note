import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/task6", "root", "theday1012");

        String sql1 = "INSERT INTO student VALUES" +
                "('s001', '老大', 20, '计算机学院')," +
                "('s002','老二', 19, '计算机学院')," +
                "('s003','老三', 18, '计算机学院')," +
                "('s004','老四', 17, '计算机学院')";
        String sql2 = "select * from student";
        String sql3 = "delete from student where sno = 's004'";
        String sql4 = "select * from student where sno = 's003'";
        String sql5 = "update student set `name` = '老大', age = 20, college = '通信学院' where sno = 's001'";

        Statement stmt = conn.createStatement();
        // 1.插入
        int count1 = stmt.executeUpdate(sql1);
        // 2.查询
        ResultSet rs2 = stmt.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println(rs2.getString(1)+','+rs2.getString(2)+','+rs2.getInt(3)+','+rs2.getString(4));
        }
        // 3.删除
        int count3 = stmt.executeUpdate(sql3);
        // 4.条件查询
        ResultSet rs4 = stmt.executeQuery(sql4);
        rs4.next();
        System.out.println(rs4.getString(1)+','+rs4.getString(2)+','+rs4.getInt(3)+','+rs4.getString(4));
        // 5.修改
        int count5 = stmt.executeUpdate(sql5);

        System.out.println("1:" + count1 + ",3:" + count3 + ",5:" + count5);
        conn.close();
        stmt.close();
    }
}