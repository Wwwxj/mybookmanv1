package cn.edu.nyist.studentadd.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DsUtil {
	private static ComboPooledDataSource cds = new ComboPooledDataSource();

	public static Connection getConnection() throws SQLException {
		return cds.getConnection();
	}
    public static void free(Connection conn, Statement stmt) {
    	if (conn != null) {
            try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
          try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
	public static void free(Connection conn, Statement stmt, ResultSet rs) {
		if (conn != null) {
              try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
            try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
            try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
