package itdr;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test1 {
    @Test
    public void test1() throws SQLException {
        ComboPooledDataSource c=new ComboPooledDataSource();
        Connection cn=c.getConnection();
        String sql="select * from users";
        PreparedStatement ps=cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(2));
        }
    }
}
