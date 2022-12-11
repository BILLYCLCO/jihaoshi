package com.course.model;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO;

@Entity
@Table(name="physical_course")
public class PhyCouVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy =  GenerationType.IDENTITY)
	@Column 
	private Integer course_no;
	@Column 
	private String course_name;
	@Column 
	private Integer course_hr;
	@Column 
	private Integer course_price;
	@Column 
	private String course_teacher ;
	@Column 
	private Date course_date;
	@Column 
	private String course_location;
	@Column 
	private String course_info;
	@Column 
	private Integer course_status;
	@Column 
	private Timestamp create_date;
	@Column 
	private Timestamp update_time;
	@Column 
	private Date sign_up_start_day;
	@Column 
	private Date sign_up_end_day;
	@Column 
	private Integer max_sign_up_people;
	@Column 
	private Integer min_sign_up_people;
	@Column 
	private Integer current_sign_up_people;
	@Column 
	private byte[] pic;
	@OneToMany(
			mappedBy = "phyCouVO",
			fetch = FetchType.EAGER)	
	private Set<PhyCouPromotionDetailVO> phyCouPromotionDetails = new HashSet<PhyCouPromotionDetailVO>();
	

	public void setCourse_no(Integer course_no) {
		this.course_no = course_no;
	}

	public Integer getCourse_no() {
		return course_no;
	}
	
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public Integer getCourse_hr() {
		return course_hr;
	}
	public void setCourse_hr(Integer course_hr) {
		this.course_hr = course_hr;
	}
	
	public Integer getCourse_price() {
		return course_price;
	}
	public void setCourse_price(Integer course_price) {
		this.course_price = course_price;
	}
	
	public String getCourse_teacher() {
		return course_teacher;
	}
	public void setCourse_teacher(String course_teacher) {
		this.course_teacher = course_teacher;
	}
	
	public Date getCourse_date() {
		return course_date;
	}
	public void setCourse_date(Date course_date) {
		this.course_date = course_date;
	}
	
	public String getCourse_location() {
		return course_location;
	}
	public void setCourse_location(String course_location) {
		this.course_location = course_location;
	}
	
	public String getCourse_info() {
		return course_info;
	}
	public void setCourse_info(String course_info) {
		this.course_info = course_info;
	}
	
	public Integer getCourse_status() {
		return course_status;
	}
	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}
	
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	
	public Date getSign_up_start_day() {
		return sign_up_start_day;
	}
	public void setSign_up_start_day(Date sign_up_start_day) {
		this.sign_up_start_day = sign_up_start_day;
	}
	
	public Date getSign_up_end_day() {
		return sign_up_end_day;
	}
	public void setSign_up_end_day(Date sign_up_end_day) {
		this.sign_up_end_day = sign_up_end_day;
	}
	
	public Integer getMax_sign_up_people() {
		return max_sign_up_people;
	}
	public void setMax_sign_up_people(Integer max_sign_up_people) {
		this.max_sign_up_people = max_sign_up_people;
	}
	
	public Integer getMin_sign_up_people() {
		return min_sign_up_people;
	}
	public void setMin_sign_up_people(Integer min_sign_up_people) {
		this.min_sign_up_people = min_sign_up_people;
	}
	
	public Integer getCurrent_sign_up_people() {
		return current_sign_up_people;
	}
	public void setCurrent_sign_up_people(Integer current_sign_up_people) {
		this.current_sign_up_people = current_sign_up_people;
	}
	
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}	

	public Set<PhyCouPromotionDetailVO> getPhyCouPromotionDetails() {
		return phyCouPromotionDetails;
	}
	public void setPhyCouPromotionDetails(Set<PhyCouPromotionDetailVO> phyCouPromotionDetails) {
		this.phyCouPromotionDetails = phyCouPromotionDetails;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(pic);
		result = prime * result + Objects.hash(course_date, course_hr, course_info, course_location, course_name,
				course_no, course_price, course_status, course_teacher, create_date, current_sign_up_people,
				max_sign_up_people, min_sign_up_people, phyCouPromotionDetails, sign_up_end_day, sign_up_start_day,
				update_time);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhyCouVO other = (PhyCouVO) obj;
		return Objects.equals(course_date, other.course_date) && Objects.equals(course_hr, other.course_hr)
				&& Objects.equals(course_info, other.course_info)
				&& Objects.equals(course_location, other.course_location)
				&& Objects.equals(course_name, other.course_name) && Objects.equals(course_no, other.course_no)
				&& Objects.equals(course_price, other.course_price)
				&& Objects.equals(course_status, other.course_status)
				&& Objects.equals(course_teacher, other.course_teacher)
				&& Objects.equals(create_date, other.create_date)
				&& Objects.equals(current_sign_up_people, other.current_sign_up_people)
				&& Objects.equals(max_sign_up_people, other.max_sign_up_people)
				&& Objects.equals(min_sign_up_people, other.min_sign_up_people)
				&& Objects.equals(phyCouPromotionDetails, other.phyCouPromotionDetails) && Arrays.equals(pic, other.pic)
				&& Objects.equals(sign_up_end_day, other.sign_up_end_day)
				&& Objects.equals(sign_up_start_day, other.sign_up_start_day)
				&& Objects.equals(update_time, other.update_time);
	}
	
	@Override
	public String toString() {
		return "PhyCouVO [course_no=" + course_no + ", course_name=" + course_name + ", course_price=" + course_price
				+ ", course_teacher=" + course_teacher + ", course_status=" + course_status
				+ ", current_sign_up_people=" + current_sign_up_people + "]";
	}
	
	
		
	
	
																

}
