package cn.edu.nyist.bookman.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nyist.bookman.dao.TypeDao;
import cn.edu.nyist.bookman.util.DsUtil;
import cn.edu.nyist.bookman.vo.TypeVo;

public class TypeDaoJdbcImpl implements TypeDao {

	@Override
	public List<TypeVo> findAll() {
		//查询定义3个变量
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DsUtil.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from t_type");
			List<TypeVo> ls=new ArrayList<>();
			while(rs.next()) {
				TypeVo typeVo=new TypeVo();
				typeVo.setId(rs.getInt("id"));
				typeVo.setName(rs.getString("name"));
				ls.add(typeVo);
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(conn, stmt, rs);
		}
		return null;
	}

}
