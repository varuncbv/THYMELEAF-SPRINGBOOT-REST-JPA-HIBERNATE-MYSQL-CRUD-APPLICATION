package com.letsstartcoding.springboothibernate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsstartcoding.springboothibernate.model.Student;
import com.letsstartcoding.springboothibernate.repository.StudentRepository;

@Service
public class StudentDAO {
	@Autowired
	StudentRepository studentRepository;
	
	/*to save an employee*/
	
	public Student save(Student std) {
		return studentRepository.save(std);
	}
	
	
	/* search all employees*/
	
	public List<Student> findAll(){
		return studentRepository.findAll();
	}
	
	
	/*get an employee by id*/
	public Student findOne(Integer id) {
		return studentRepository.findOne(id);
	}
	
	
	/*delete an employee*/
	
	public void delete(Student std) {
		studentRepository.delete(std);
	}
	

}
