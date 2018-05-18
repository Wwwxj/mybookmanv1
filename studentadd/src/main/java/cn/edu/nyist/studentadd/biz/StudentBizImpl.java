package cn.edu.nyist.studentadd.biz;

import cn.edu.nyist.studentadd.dao.StudentDao;
import cn.edu.nyist.studentadd.dao.StudentDaoImpl;

public class StudentBizImpl implements StudentBiz {

	@Override
	public int saveStudent(String name,int age) {
		//µ÷ÓÃDao²ã
		StudentDao studentDao=new StudentDaoImpl();
		return studentDao.save(name,age);
		
	}

}
