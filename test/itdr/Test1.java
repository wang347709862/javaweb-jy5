package itdr;

import com.itdr.pojo.Products;
import com.itdr.pojo.Users;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test1 {
    @Test
    public void test1() throws SQLException {
        ComboPooledDataSource c=new ComboPooledDataSource();
        QueryRunner qr=new QueryRunner(c);
//        Connection cn=c.getConnection();
//        String sql="select * from users where id=1";
        String sql="select * from products where id = 1";
        Products p1 = qr.query(sql, new BeanHandler<Products>(Products.class));
//        Users p1 = qr.query(sql, new BeanHandler<Users>(Users.class));
        System.out.println(p1.toString());
//        PreparedStatement ps=cn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()){
//            System.out.println(rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+
//                            rs.getString(5)+","+
//                    rs.getString(6)+","+rs.getString(7));
//        }
    }
}
