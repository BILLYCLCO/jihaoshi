package com.phyCouPromotion.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.course.model.PhyCouVO;
import com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO;

public class PhyCouPromotionService {

	private PhyCouPromotionDAO_interface dao;
    java.util.Date du = new java.util.Date();
  	java.sql.Date update_time = new java.sql.Date(du.getTime());

	public PhyCouPromotionService() {
		dao = new PhyCouPromotionHibernateDAO();
	}

	public Integer addPhyCouPromotion(String project_name, java.sql.Date start_date, 
				java.sql.Date end_date, String prom_description, Integer prom_status, Integer prom_price, String[] proCous, java.sql.Timestamp update_time) {
				
		PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
		Set<PhyCouPromotionDetailVO> set = new HashSet<PhyCouPromotionDetailVO>();
		PhyCouVO phyCouVO = null;
		PhyCouPromotionDetailVO VO = null;
		for (int i = 0 ; i < proCous.length ; i++) {
			VO = new PhyCouPromotionDetailVO();
			phyCouVO = new PhyCouVO();
			Integer course_no = Integer.valueOf(proCous[i]);
			phyCouVO.setCourse_no(course_no);
			VO.setPhyCouPromotionVO(phyCouPromotionVO);
			VO.setPhyCouVO(phyCouVO);
			VO.setProm_price(prom_price);
			set.add(VO);
		}
		phyCouPromotionVO.setProject_name(project_name);
		phyCouPromotionVO.setStart_date(start_date);
		phyCouPromotionVO.setEnd_date(end_date);
		phyCouPromotionVO.setProm_description(prom_description);
		phyCouPromotionVO.setProm_status(prom_status);
		phyCouPromotionVO.setPhyCouPromotionDetails(set);
		phyCouPromotionVO.setUpdate_time(update_time);
		Integer project_no = dao.insert(phyCouPromotionVO);
		
		
		return project_no;
	}

	public PhyCouPromotionVO updatePhyCouPromotion(Integer project_no, String project_name, java.sql.Date start_date, 
			java.sql.Date end_date, String prom_description, Integer prom_status, String[] proCous, Integer prom_price, java.sql.Timestamp update_time) {

//		PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
//		Set<PhyCouPromotionDetailVO> set = new HashSet<PhyCouPromotionDetailVO>();
//		
//		PhyCouVO phyCouVO = null;
//		PhyCouPromotionDetailVO VO = null;
//		System.out.println("================== Project_no: "+ project_no +"+++++++++++++++++++++++++");
//		for (int i = 0 ; i < proCous.length ; i++) {
//			VO = new PhyCouPromotionDetailVO();
//			phyCouVO = new PhyCouVO();
//			phyCouPromotionVO = new PhyCouPromotionVO();
//			Integer course_no = Integer.valueOf(proCous[i]);
//			phyCouVO.setCourse_no(course_no);
//			phyCouPromotionVO.setProject_no(project_no);
//			VO.setPhyCouPromotionVO(phyCouPromotionVO);
//			VO.setPhyCouVO(phyCouVO);
//			VO.setProm_price(prom_price);
//			set.add(VO);
//		}
//		
//		phyCouPromotionVO.setProject_no(project_no);
//		phyCouPromotionVO.setProject_name(project_name);
//		phyCouPromotionVO.setStart_date(start_date);
//		phyCouPromotionVO.setEnd_date(end_date);
//		phyCouPromotionVO.setProm_description(prom_description);
//		phyCouPromotionVO.setProm_status(prom_status);
//		phyCouPromotionVO.setPhyCouPromotionDetails(set);
//

		  
		    PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
			Set<PhyCouPromotionDetailVO> set = new HashSet<PhyCouPromotionDetailVO>();
			PhyCouVO phyCouVO = null;
			PhyCouPromotionDetailVO VO = null;
			phyCouPromotionVO.setProject_no(project_no);
			
			for (int i = 0 ; i < proCous.length ; i++) {
				VO = new PhyCouPromotionDetailVO();
				phyCouVO = new PhyCouVO();
				Integer course_no = Integer.valueOf(proCous[i]);
				phyCouVO.setCourse_no(course_no);
				VO.setPhyCouPromotionVO(phyCouPromotionVO);
				VO.setPhyCouVO(phyCouVO);
				VO.setProm_price(prom_price);
				set.add(VO);
			}
			
			
//			phyCouPromotionVO.setProject_no(project_no);
			phyCouPromotionVO.setProject_name(project_name);
			phyCouPromotionVO.setStart_date(start_date);
			phyCouPromotionVO.setEnd_date(end_date);
			phyCouPromotionVO.setProm_description(prom_description);
			phyCouPromotionVO.setProm_status(prom_status);
			phyCouPromotionVO.setUpdate_time(update_time);
//			phyCouPromotionVO.setPhyCouPromotionDetails(set);

			dao.update(phyCouPromotionVO);
	

		return phyCouPromotionVO;
	}

	public void deletePro(Integer project_no) {
		dao.delete(project_no);
	}

	public PhyCouPromotionVO getOnePro(Integer project_no) {
		return dao.findByPrimaryKey(project_no);
	}

	public List<PhyCouPromotionVO> getAll() {
		return dao.getAll();
	}
	
//	public List<PhyCouPromotionVO> getAll(Map<String, String[]> map) {
//		return dao.getAll(map);
//	}
}
