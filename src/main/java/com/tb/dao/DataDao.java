package com.tb.dao;

import java.util.List;

import com.tb.model.Student;

public interface DataDao {
	public int add(Student student);

	public int update(Student student);

	public Student get(int id);

	public List<Student> list();

	public int delete(int id);
}
