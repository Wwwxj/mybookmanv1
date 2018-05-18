package cn.edu.nyist.bookman.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import cn.edu.nyist.bookman.dao.BookDao;
import cn.edu.nyist.bookman.util.DsUtil;
import cn.edu.nyist.bookman.vo.BookVo;

public class BookDaoImpl implements BookDao {

	@Override
	public int book(BookVo bookVo) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=DsUtil.getConnection();
			String sql = "insert into t_book(tid,name,descri,photo,pubDate,price,author) values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,bookVo.getTid());
			stmt.setString(2, bookVo.getName());
			stmt.setString(3, bookVo.getDescri());
			stmt.setString(4, bookVo.getPhoto());
			stmt.setDate(5, new java.sql.Date(bookVo.getPubDate().getTime()));
			stmt.setDouble(6, bookVo.getPrice());
			stmt.setString(7, bookVo.getAuther());
			int ret=stmt.executeUpdate();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(conn, stmt);
		}
		return 0;
	}

}
