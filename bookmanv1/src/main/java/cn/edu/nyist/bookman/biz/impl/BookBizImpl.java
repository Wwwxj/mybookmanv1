package cn.edu.nyist.bookman.biz.impl;

import cn.edu.nyist.bookman.biz.BookBiz;
import cn.edu.nyist.bookman.dao.BookDao;
import cn.edu.nyist.bookman.dao.impl.BookDaoImpl;
import cn.edu.nyist.bookman.vo.BookVo;


public class BookBizImpl implements BookBiz {
@Override
	public int saveBook(BookVo bookVo){
		//调用Dao层
		BookDao bookDao=new BookDaoImpl();
		return bookDao.book(bookVo);
	
	}

}
