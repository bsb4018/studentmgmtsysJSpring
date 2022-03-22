package com.builds.sprojects.sms.service;

import java.util.List;

import com.builds.sprojects.sms.entity.Student;

public interface StudentService {
	
	List<Student> findAll();
	
	public Student findById(int theId);
	
	public void save(Student theStudent);
	
	public void deleteById(int theId);
	
	public List<Student> searchBy(String theName);
}
