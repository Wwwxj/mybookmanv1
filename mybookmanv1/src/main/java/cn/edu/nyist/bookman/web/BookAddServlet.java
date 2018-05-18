package cn.edu.nyist.bookman.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cn.edu.nyist.bookman.biz.BookBiz;
import cn.edu.nyist.bookman.biz.impl.BookBizImpl;
import cn.edu.nyist.bookman.util.MyBeanUtils;
import cn.edu.nyist.bookman.vo.BookVo;


@WebServlet("/bookAdd")
@MultipartConfig
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookAddServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//解决文件上传
		Part part = request.getPart("photo");
		String fileName = part.getHeader("Content-Disposition").split(";")[2].split("=")[1].replace("\"", "");
		fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
		String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		String newFileName = UUID.randomUUID().toString() + "." + ext;
		part.write(request.getServletContext().getRealPath("upload/" + newFileName));
		//获取用户信息
		/*String strTid=request.getParameter("tid");
		int tid=Integer.parseInt(strTid);
		String name = request.getParameter("name");
		String descri = request.getParameter("descri");
		String strPubDate=request.getParameter("pubDate");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date pubDate=null;
		try {
			pubDate=sdf.parse(strPubDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   
		String strPrice = request.getParameter("price");
		double price = Double.parseDouble(strPrice);
		String author = request.getParameter("author");*/
		BookVo bookVo=new BookVo();
		/*try {
			BeanUtils.populate(bookVo, request.getParameterMap());
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}*/
		MyBeanUtils.populate(bookVo, request.getParameterMap(),"yyyy-MM-dd");
		bookVo.setPhoto(newFileName);
		//调用业务层
		BookBiz bookBiz = new BookBizImpl();
		int ret = bookBiz.saveBook(bookVo);
        //给用户一个界面
		response.setContentType("text/html;charset=utf-8");
		if (ret > 0) {
			response.getWriter().write("书籍添加成功");
		} else {
			request.getRequestDispatcher("bookAdd").forward(request, response);
		}
	}

}
