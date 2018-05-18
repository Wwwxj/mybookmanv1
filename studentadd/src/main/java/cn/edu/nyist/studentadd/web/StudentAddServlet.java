package cn.edu.nyist.studentadd.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nyist.studentadd.biz.StudentBiz;
import cn.edu.nyist.studentadd.biz.StudentBizImpl;


@WebServlet("/studentAdd")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StudentAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("1122222222111");
		
		System.out.println("11111111111111111111111111111111111");
		String name=request.getParameter("name");
		String strAge=request.getParameter("age");
		int age=Integer.parseInt(strAge);
	
		StudentBiz studentBiz=new StudentBizImpl();
		int ret=studentBiz.saveStudent(name,age);
		
		response.setContentType("text/html;charset=utf-8");
		if(ret>0) {
			response.getWriter().write("学生信息添加成功");
		}else {
			request.getRequestDispatcher("studentAdd.html").forward(request, response);
		}
		
	}

}
