package com.builds.sprojects.sms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.builds.sprojects.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	//JpaRepository already has @Repository annotated hence no need to 
	//add that same notation here
	
	//JpaRepository already has @RTransaction annotated hence no need to 
	//add that same notation here
	
	//method to sort by first name in ascending order
	public List<Student> findAllByOrderByFirstNameAsc();
	
	// search by name
	public List<Student> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);

}
