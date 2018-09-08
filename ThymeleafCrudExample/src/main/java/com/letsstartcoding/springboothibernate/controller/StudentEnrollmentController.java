package com.letsstartcoding.springboothibernate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.letsstartcoding.springboothibernate.dao.StudentDAO;
import com.letsstartcoding.springboothibernate.model.Student;



@Controller
public class StudentEnrollmentController {
	
	@Autowired
	private StudentDAO studentDao;
	
	@RequestMapping(value="/enroll",method=RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		Student student = new Student();
		model.addAttribute("student",student);
		return "enroll";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveRegistration(@Valid Student student,BindingResult result,ModelMap model,RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			System.out.println("has errors");
			return "enroll";
		}
	
		studentDao.save(student);
		
		return "redirect:/viewstudents";
	}
	
	
	@RequestMapping(value="/viewstudents")
	public ModelAndView getAll() {
		
		List<Student> list=studentDao.findAll();
		return new ModelAndView("viewstudents","list",list);
	}
	
	
	@RequestMapping(value="/editstudent/{id}")
	public String edit (@PathVariable int id,ModelMap model) {
		
		Student student=studentDao.findOne(id);
		model.addAttribute("student",student);
		return "editstudent";
	}
	
	@RequestMapping(value="/editsave",method=RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("student") Student p) {
		
		Student student=studentDao.findOne(p.getId());
		
		student.setFirstName(p.getFirstName());
		student.setLastName(p.getLastName());
		student.setCountry(p.getCountry());
		student.setEmail(p.getEmail());
		student.setSection(p.getSection());
		student.setSex(p.getSex());
		
		studentDao.save(student);
		return new ModelAndView("redirect:/viewstudents");
	}
	
	@RequestMapping(value="/deletestudent/{id}",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		Student student=studentDao.findOne(id);
		studentDao.delete(student);
		return new ModelAndView("redirect:/viewstudents");
	}
	
	

	@ModelAttribute("sections")
	public List<String> intializeSections(){
		List<String> sections = new ArrayList<String>();
		sections.add("Graduate");
		sections.add("Post Graduate");
		sections.add("Reasearch");
		return sections;
	}
	
	
	/*
	 * Method used to populate the country list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("countries")
	public List<String> initializeCountries() {

		List<String> countries = new ArrayList<String>();
		countries.add("INDIA");
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		return countries;
	}

	
	
	

}
