package com.course.model;

import java.util.List;
import java.util.Map;

import com.meal.model.MealVO;


public class PhyCouService {


	private PhyCouDAO_interface dao;

	public PhyCouService() {
		dao = new PhyCouHibernateDAO();
	}


	public PhyCouVO addCou( String course_name, Integer course_hr, Integer course_price, String course_teacher, java.sql.Date course_date, String course_location, String course_info, Integer	course_status,	java.sql.Timestamp create_date, java.sql.Timestamp update_time,java.sql.Date sign_up_start_day, java.sql.Date sign_up_end_day, Integer max_sign_up_people, Integer	min_sign_up_people, Integer	current_sign_up_people, byte[] pic) {
		
		PhyCouVO phyCouVO = new PhyCouVO();
		
		phyCouVO.setCourse_name(course_name);
		phyCouVO.setCourse_hr(course_hr);
		phyCouVO.setCourse_price(course_price);
		phyCouVO.setCourse_teacher(course_teacher);
		phyCouVO.setCourse_date(course_date);
		phyCouVO.setCourse_location(course_location);
		phyCouVO.setCourse_info(course_info);
		phyCouVO.setCourse_status(course_status);
		phyCouVO.setCreate_date(create_date);
		phyCouVO.setUpdate_time(update_time);
		phyCouVO.setSign_up_start_day(sign_up_start_day);
		phyCouVO.setSign_up_end_day(sign_up_end_day);
		phyCouVO.setMax_sign_up_people(max_sign_up_people);
		phyCouVO.setMin_sign_up_people(min_sign_up_people);
		phyCouVO.setCurrent_sign_up_people(current_sign_up_people);
		phyCouVO.setPic(pic);
		
		dao.insert(phyCouVO);
		
		return phyCouVO;
	}

	public PhyCouVO updateCou(Integer course_no, String course_name, Integer course_hr, Integer course_price, String course_teacher, java.sql.Date course_date, String course_location, String course_info, Integer	course_status,	java.sql.Timestamp create_date,	java.sql.Timestamp update_time, java.sql.Date sign_up_start_day, java.sql.Date sign_up_end_day, Integer max_sign_up_people, Integer	min_sign_up_people, Integer	current_sign_up_people, byte[] pic) {

		PhyCouVO phyCouVO = new PhyCouVO();
	   
		phyCouVO.setCourse_no(course_no);
		phyCouVO.setCourse_name(course_name);
		phyCouVO.setCourse_hr(course_hr);
		phyCouVO.setCourse_price(course_price);
		phyCouVO.setCourse_teacher(course_teacher);
		phyCouVO.setCourse_date(course_date);
		phyCouVO.setCourse_location(course_location);
		phyCouVO.setCourse_info(course_info);
		phyCouVO.setCourse_status(course_status);
		phyCouVO.setCreate_date(create_date);
		phyCouVO.setUpdate_time(update_time);
		phyCouVO.setSign_up_start_day(sign_up_start_day);
		phyCouVO.setSign_up_end_day(sign_up_end_day);
		phyCouVO.setMax_sign_up_people(max_sign_up_people);
		phyCouVO.setMin_sign_up_people(min_sign_up_people);
		phyCouVO.setCurrent_sign_up_people(current_sign_up_people);
		phyCouVO.setPic(pic);		
		
		dao.update(phyCouVO);
		System.out.println("++++++++ service +++++++++++++++++++++++++++++++");
		System.out.println(phyCouVO);
		System.out.println("+++++++++ service ++++++++++++++++++++++++++++++++");

		return phyCouVO;
	}

	public void deleteCou(Integer course_no) {
		dao.delete(course_no);
	}

	public void changeStatus(Integer course_no, Integer course_status) {
		dao.updateStatus(course_no, course_status);
	}
	public PhyCouVO getOneCou(Integer course_no) {
		return dao.findByPrimaryKey(course_no);
	}
	
    public List<PhyCouVO> findByNameKeyword(String nameKeyword) {
        return dao.findByNameKeyword(nameKeyword);
    }

	public List<PhyCouVO> getAll() {
		return dao.getAll();
	}
	
	public List<PhyCouVO> getCanSignUp() {
		return dao.getCanSignUp();
	}
	
	public List<PhyCouVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
