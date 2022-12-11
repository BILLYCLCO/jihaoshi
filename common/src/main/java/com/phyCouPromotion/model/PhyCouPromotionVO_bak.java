package com.phyCouPromotion.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.phyCouPromotionDetail.model.*;

public class PhyCouPromotionVO_bak implements java.io.Serializable{
	private Integer project_no;
	private String project_name;
	private Date start_date;
	private Date end_date;
	private String prom_description;
	private Integer prom_status ;
	private Date update_time;
	private Set<PhyCouPromotionDetailVO> phyCouPromotionDetails = new HashSet<PhyCouPromotionDetailVO>();
		
	public Integer getProject_no() {
		return project_no;
	}
	public void setProject_no(Integer project_no) {
		this.project_no = project_no;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getProm_description() {
		return prom_description;
	}
	public void setProm_description(String prom_description) {
		this.prom_description = prom_description;
	}
	public Integer getProm_status() {
		return prom_status;
	}
	public void setProm_status(Integer prom_status) {
		this.prom_status = prom_status;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Set<PhyCouPromotionDetailVO> getPhyCouPromotionDetails() {
		return phyCouPromotionDetails;
	}
	public void setPhyCouPromotionDetails(Set<PhyCouPromotionDetailVO> phyCouPromotionDetails) {
		this.phyCouPromotionDetails = phyCouPromotionDetails;
	}
	
//	@Override
//	public String toString() {
//		return "PhyCouPromotionVO [project_no=" + project_no + ", project_name=" + project_name + ", start_date="
//				+ start_date + ", end_date=" + end_date + ", prom_description=" + prom_description + ", prom_status="
//				+ prom_status + ", update_time=" + update_time + "]";
//	}
	
	@Override
	public int hashCode() {
		return Objects.hash(project_name);
	}
	@Override
	public String toString() {
		return "PhyCouPromotionVO [project_no=" + project_no + ", project_name=" + project_name + ", start_date="
				+ start_date + ", end_date=" + end_date + ", prom_description=" + prom_description + ", prom_status="
				+ prom_status + ", update_time=" + update_time + ", phyCouPromotionDetails=" + phyCouPromotionDetails
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhyCouPromotionVO_bak other = (PhyCouPromotionVO_bak) obj;
		return Objects.equals(project_name, other.project_name);
	}
}
