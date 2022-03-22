package com.builds.sprojects.sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.builds.sprojects.sms.dao.StudentRepository;
import com.builds.sprojects.sms.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository theStudentRepository) {
		studentRepository = theStudentRepository;
	}

	@Override
	public List<Student> findAll() {	
		return studentRepository.findAllByOrderByFirstNameAsc();
	}


	@Override
	public Student findById(int theId) {
		Optional<Student> result = studentRepository.findById(theId);
		Student theStudent = null;//need to do this for Optional Query
		
		if(result.isPresent()) {
			theStudent = result.get();
		}
		else {
			//no student found
			throw new RuntimeException("Did not find student id- " + theId);
		}
		
		return theStudent;
	}

	@Override
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
		
	}

	@Override
	public void deleteById(int theId) {
		studentRepository.deleteById(theId);
		
	}

	@Override
	public List<Student> searchBy(String theName) {
		
		List<Student> results = null;
		if(theName != null && (theName.trim().length() > 0)) {
			results = studentRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
		}
		else {
			results = findAll();
		}
	
		return results;
	}

}
