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
		//��ȡ�û�����
				String name=request.getParameter("name");
				String pwd=request.getParameter("pwd");
				//�����û����뵽���ݿ���ұȶ�
				Connection conn=null;
				//��statement���ӽӿ�
						//Statement stmt=null;
						PreparedStatement stmt=null;
						ResultSet rs=null;
						boolean ret=false;
						try {
							conn=DsUtil.getConn();
				//��ռλ��д��
							//String sql="select * from t_user where name='"+name+"' and pwd='"+pwd+"'";
							String sql="select * from t_user where name=? and pwd=?";
							System.out.println(sql);
							stmt=conn.prepareStatement(sql);
				//?�ܲ��ˣ�Ҫת��
							stmt.setString(1, name);
							stmt.setString(2, pwd);
				//sql�Ѿ�����
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
				//���û�����
						if(ret) {
							response.sendRedirect("01main.jsp");
						}else {
							
						}
			}

		}
