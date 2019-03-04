package com.tb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tb.dao.DataDao;
import com.tb.model.Student;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private DataDao dataDao;

	@Override
	public int add(Student student) {
		return dataDao.add(student);
	}

	@Override
	public int update(Student student) {
		return dataDao.update(student);
	}

	@Override
	public Student get(int id) {
		return dataDao.get(id);
	}

	@Override
	public List<Student> list() {
		return dataDao.list();
	}

	@Override
	public int delete(int id) {
		return dataDao.delete(id);
	}

}
