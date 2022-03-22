package com.builds.sprojects.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.builds.sprojects.sms.entity.Student;
import com.builds.sprojects.sms.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	private StudentService studentService;

	@Autowired
	public StudentController(StudentService theStudentService) {
		studentService = theStudentService;
	}
	
	//handler method to handle list students and return model and view
	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		//get the students from database
		List<Student> theStudents = studentService.findAll();
		
		//take the data and add to the spring model to be used by template
		theModel.addAttribute("students", theStudents);
		
		return "/students/list-students";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		//create model attribute to bind form data
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "/students/student-form";
	}
	
	//to save the added student
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("student") Student theStudent){
		
		//save the employee
		studentService.save(theStudent);
		
		//use a redirect to prevent duplicate submission
		return "redirect:/students/list";
	
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,
	                                 Model theModel){
	           
	  //get the employee from the service
	  Student theStudent = studentService.findById(theId);
                        	 
	  //set employee as a model attribute to pre-populate the form
	  theModel.addAttribute("student", theStudent);      
	                                	 	                                	 
	  //send over to our form
	  return "/students/student-form";                              	 
	                                	                          	 
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		
		//delete the employee
		studentService.deleteById(theId);
		
		//redirect to /employees/list
		return "redirect:/students/list";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("studentName") String theName,
						 Model theModel) {
		
		// delete the employee
		List<Student> theStudents = studentService.searchBy(theName);
		
		// add to the spring model
		theModel.addAttribute("students", theStudents);
		
		// send to /employees/list
		return "/students/list-students";
		
	}
	

}
