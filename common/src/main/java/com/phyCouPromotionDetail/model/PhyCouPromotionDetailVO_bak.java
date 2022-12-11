package com.phyCouPromotionDetail.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;

import com.course.model.PhyCouVO;
import com.phyCouPromotion.model.PhyCouPromotionVO;

public class PhyCouPromotionDetailVO_bak implements java.io.Serializable{
	private PhyCouPromotionVO phyCouPromotionVO;
	private PhyCouVO phyCouVO;
//	private Integer project_no;
//	private Integer course_no;
    private Integer prom_price;
		
		
//	public Integer getProject_no() {
//		return project_no;
//	}
//	public void setProject_no(Integer project_no) {
//		this.project_no = project_no;
//	}
//	public Integer getCourse_no() {
//		return course_no;
//	}
//	public void setCourse_no(Integer course_no) {
//		this.course_no = course_no;
//	}
	public PhyCouPromotionVO getPhyCouPromotionVO() {
		return phyCouPromotionVO;
	}
	public void setPhyCouPromotionVO(PhyCouPromotionVO phyCouPromotionVO) {
		this.phyCouPromotionVO = phyCouPromotionVO;
	}
	public PhyCouVO getPhyCouVO() {
		return phyCouVO;
	}
	public void setPhyCouVO(PhyCouVO phyCouVO) {
		this.phyCouVO = phyCouVO;
	}
	public Integer getProm_price() {
		return prom_price;
	}
	public void setProm_price(Integer prom_price) {
		this.prom_price = prom_price;
	}
	@Override
	public String toString() {
		return "PhyCouPromotionDetailVO [phyCouPromotionVO=" + phyCouPromotionVO.getProject_no() + ", phyCouVO=" + phyCouVO.getCourse_no()
				+ ", prom_price=" + prom_price + "]";
	}

	
	


	
}
