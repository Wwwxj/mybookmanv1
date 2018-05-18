package cn.edu.nyist.studentadd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



import cn.edu.nyist.studentadd.util.DsUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public int save(String name, int age) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			System.out.println("----------------------------");
			conn=DsUtil.getConnection();
			String sql="insert into t_student(name,age) values(?,?)";
			System.out.println(sql);
			stmt=conn.prepareStatement(sql);
			
			stmt.setString(1, name);
			stmt.setInt(2, age);
			int ret=stmt.executeUpdate();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("==================");
		}finally {
			DsUtil.free(conn, stmt);
		}
		return 0;
	}

	

}
