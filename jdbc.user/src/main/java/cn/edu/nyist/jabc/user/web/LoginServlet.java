package cn.edu.nyist.jabc.user.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/01login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户输入
				String name=request.getParameter("name");
				String pwd=request.getParameter("pwd");
				//根据用户输入到数据库查找比对
				Connection conn=null;
				//用statement的子接口
						//Statement stmt=null;
						PreparedStatement stmt=null;
						ResultSet rs=null;
						boolean ret=false;
						try {
							conn=DsUtil.getConn();
				//用占位符写法
							//String sql="select * from t_user where name='"+name+"' and pwd='"+pwd+"'";
							String sql="select * from t_user where name=? and pwd=?";
							System.out.println(sql);
							stmt=conn.prepareStatement(sql);
				//?跑不了，要转义
							stmt.setString(1, name);
							stmt.setString(2, pwd);
				//sql已经传了
							//rs=stmt.executeQuery(sql);
							rs=stmt.executeQuery();
							if(rs.next()) {
								ret=true;
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}finally {
							DsUtil.free(rs, stmt, conn);
						}
				//给用户反馈
						if(ret) {
							response.sendRedirect("01main.jsp");
						}else {
							
						}
			}

		}
