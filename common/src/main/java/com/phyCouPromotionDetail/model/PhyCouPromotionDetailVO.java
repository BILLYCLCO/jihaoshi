package com.phyCouPromotionDetail.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

import com.course.model.PhyCouVO;
import com.phyCouPromotion.model.PhyCouPromotionVO;

@Entity
@Table(name="PHYSICAL_COURSE_PROMOTION_DETAIL")
@IdClass(com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO.PK.class)
public class PhyCouPromotionDetailVO implements java.io.Serializable{
	@Id
	@Column
    private Integer project_no;
	@Id
	@Column
    private Integer course_no;
	@Column
	private Integer prom_price;
	@ManyToOne
	@JoinColumn(name = "project_no", 
	insertable = false, updatable = false)
	private PhyCouPromotionVO phyCouPromotionVO;
	@ManyToOne
	@JoinColumn(name = "course_no",
	insertable = false, updatable = false)
	private PhyCouVO phyCouVO;
		
		
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

	
	static class PK implements Serializable {

		private static final long serialVersionUID = 1L;
		public Integer project_no;
		public Integer course_no;
		
		PK() {
			
		}

		@Override
		public int hashCode() {
			return Objects.hash(course_no, project_no);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PK other = (PK) obj;
			return Objects.equals(course_no, other.course_no) && Objects.equals(project_no, other.project_no);
		}
		
		
	


	}

	
}
