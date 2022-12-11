package com.course.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.meal.model.MealVO;

import hibernate.util.HibernateUtil;


public class PhyCouHibernateDAO implements PhyCouDAO_interface {

	private static final String GET_ALL_STMT = 
		"FROM PhyCouVO ORDER BY COURSE_NO";
	private static final String GET_CAN_SIGNUP_STMT = 
		"FROM PhyCouVO WHERE CURRENT_SIGN_UP_PEOPLE < MAX_SIGN_UP_PEOPLE AND COURSE_STATUS =1";
    public static final String FIND_BY_NAME_KEYWORD = 
       	"FROM PHYSICAL_COURSE WHERE :course_name ";
    
	@Override
	public void insert(PhyCouVO phyCouVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(phyCouVO);
			session.getTransaction().commit();

		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

		@Override
		public void update(PhyCouVO phyCouVO) {

			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				if ( phyCouVO.getPic() != null ) {
					NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE PHYSICAL_COURSE SET "
	                        + "COURSE_NAME = :COURSE_NAME, COURSE_HR = :COURSE_HR, "
	                        + "COURSE_PRICE = :COURSE_PRICE, COURSE_TEACHER = :COURSE_TEACHER, "
	                        + "COURSE_DATE = :COURSE_DATE, COURSE_LOCATION = :COURSE_LOCATION, "
							+ "COURSE_INFO = :COURSE_INFO, COURSE_STATUS = :COURSE_STATUS, "
							+ "SIGN_UP_START_DAY = :SIGN_UP_START_DAY, SIGN_UP_END_DAY =  :SIGN_UP_END_DAY, "
							+ "MAX_SIGN_UP_PEOPLE = :MAX_SIGN_UP_PEOPLE, "
							+ "MIN_SIGN_UP_PEOPLE = :MIN_SIGN_UP_PEOPLE, CURRENT_SIGN_UP_PEOPLE = :CURRENT_SIGN_UP_PEOPLE, "
							+ "CREATE_DATE = :CREATE_DATE, UPDATE_TIME = :UPDATE_TIME, "
							+ "PIC = :PIC WHERE COURSE_NO = :COURSE_NO")						
							.setParameter("COURSE_NO", phyCouVO.getCourse_no())
							.setParameter("COURSE_NAME", phyCouVO.getCourse_name())
							.setParameter("COURSE_HR", phyCouVO.getCourse_hr())
							.setParameter("COURSE_PRICE", phyCouVO.getCourse_price())
							.setParameter("COURSE_TEACHER", phyCouVO.getCourse_teacher() )
							.setParameter("COURSE_DATE", phyCouVO.getCourse_date() )
							.setParameter("COURSE_LOCATION", phyCouVO.getCourse_location())
							.setParameter("COURSE_INFO", phyCouVO.getCourse_info())
							.setParameter("COURSE_STATUS", phyCouVO.getCourse_status())
							.setParameter("SIGN_UP_START_DAY", phyCouVO.getSign_up_start_day() )
							.setParameter("SIGN_UP_END_DAY", phyCouVO.getSign_up_end_day())
							.setParameter("MAX_SIGN_UP_PEOPLE", phyCouVO.getMax_sign_up_people())
							.setParameter("MIN_SIGN_UP_PEOPLE", phyCouVO.getMin_sign_up_people())
							.setParameter("CURRENT_SIGN_UP_PEOPLE", phyCouVO.getCurrent_sign_up_people())
							.setParameter("CREATE_DATE", phyCouVO.getCreate_date())
							.setParameter("UPDATE_TIME", phyCouVO.getUpdate_time())
							.setParameter("PIC", phyCouVO.getPic());
							int result = nativeQuery.executeUpdate();
							session.getTransaction().commit();
				} else {
					NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE PHYSICAL_COURSE SET "
		                        + "COURSE_NAME = :COURSE_NAME, COURSE_HR = :COURSE_HR, "
		                        + "COURSE_PRICE = :COURSE_PRICE, COURSE_TEACHER = :COURSE_TEACHER, "
		                        + "COURSE_DATE = :COURSE_DATE, COURSE_LOCATION = :COURSE_LOCATION, "
								+ "COURSE_INFO = :COURSE_INFO, COURSE_STATUS = :COURSE_STATUS, "
								+ "SIGN_UP_START_DAY = :SIGN_UP_START_DAY, SIGN_UP_END_DAY =  :SIGN_UP_END_DAY, "
								+ "MAX_SIGN_UP_PEOPLE = :MAX_SIGN_UP_PEOPLE, "
								+ "MIN_SIGN_UP_PEOPLE = :MIN_SIGN_UP_PEOPLE, CURRENT_SIGN_UP_PEOPLE = :CURRENT_SIGN_UP_PEOPLE "
								+ "CREATE_DATE = :CREATE_DATE, UPDATE_TIME = :UPDATE_TIME, "
								+ "WHERE COURSE_NO = :COURSE_NO")	
							.setParameter("COURSE_NO", phyCouVO.getCourse_no())
							.setParameter("COURSE_NAME", phyCouVO.getCourse_name())
							.setParameter("COURSE_HR", phyCouVO.getCourse_hr())
							.setParameter("COURSE_PRICE", phyCouVO.getCourse_price())
							.setParameter("COURSE_TEACHER", phyCouVO.getCourse_teacher() )
							.setParameter("COURSE_DATE", phyCouVO.getCourse_date() )
							.setParameter("COURSE_LOCATION", phyCouVO.getCourse_location())
							.setParameter("COURSE_INFO", phyCouVO.getCourse_info())
							.setParameter("COURSE_STATUS", phyCouVO.getCourse_status())
							.setParameter("SIGN_UP_START_DAY", phyCouVO.getSign_up_start_day() )
							.setParameter("SIGN_UP_END_DAY", phyCouVO.getSign_up_end_day())
							.setParameter("MAX_SIGN_UP_PEOPLE", phyCouVO.getMax_sign_up_people())
							.setParameter("MIN_SIGN_UP_PEOPLE", phyCouVO.getMin_sign_up_people())
							.setParameter("CURRENT_SIGN_UP_PEOPLE", phyCouVO.getCurrent_sign_up_people())
					        .setParameter("CREATE_DATE", phyCouVO.getCreate_date())
					        .setParameter("UPDATE_TIME", phyCouVO.getUpdate_time());
							
					int result = nativeQuery.executeUpdate();
					session.getTransaction().commit();	
				}
				
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
		}

		@Override
		public void updateStatus(Integer course_no, Integer course_status) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
				session.beginTransaction();
				PhyCouVO phyCouVO = (PhyCouVO) session.get(PhyCouVO.class, course_no);
				if (course_status!=1) {
					phyCouVO.setCourse_status(1);
				} else {
					phyCouVO.setCourse_status(2);
				}
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
		}


		@Override
		public PhyCouVO findByPrimaryKey(Integer course_no) {

			PhyCouVO phyCouVO = null;
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				phyCouVO = (PhyCouVO) session.get(PhyCouVO.class, course_no);
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;			
			}
			return phyCouVO;
		}

		@Override
		public List<PhyCouVO> getAll() {
			List<PhyCouVO> list = null;
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				Query<PhyCouVO> query = session.createQuery(GET_ALL_STMT, PhyCouVO.class);
				list = query.getResultList();
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
			return list;
		}

		@Override
		public List<PhyCouVO> getCanSignUp() {
			
			List<PhyCouVO> list = null;
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				Query<PhyCouVO> query = session.createQuery(GET_CAN_SIGNUP_STMT, PhyCouVO.class);
				list = query.getResultList();
				session.getTransaction().commit();
			} catch (RuntimeException ex ) {
				session.getTransaction().rollback();
				throw ex;
			}
			return list;
		}
		

		@Override
		public List<PhyCouVO> getAll(Map<String, String[]> map) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void delete(Integer course_no) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
				session.beginTransaction();
				PhyCouVO phyCouVO = (PhyCouVO) session.get(PhyCouVO.class, course_no);
				session.delete(phyCouVO);
				session.getTransaction().commit();			
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
	 	}

		@Override
		public List<PhyCouVO> findByNameKeyword(String nameKeyword) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			List<PhyCouVO> list = null;
			
			try {
				session.beginTransaction();
				Query<PhyCouVO> query = session.createQuery(FIND_BY_NAME_KEYWORD, PhyCouVO.class)
						.setParameter("course_name", "course_name like "+"%"+nameKeyword+"%");
				list = query.getResultList();
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
			return list;
		}
//	}
//		public static void main(String[] args) {
//	
//			PhyCouHibernateDAO dao = new PhyCouHibernateDAO();
//			java.util.Date date = new java.util.Date();
//			long data = date.getTime();
//			java.sql.Timestamp update_time = new java.sql.Timestamp(data);
			
//			PhyCouVO phyCouVO1 = new PhyCouVO();
//			phyCouVO1.setCourse_name("peter1");
//			phyCouVO1.setCourse_hr(3);
//			phyCouVO1.setCourse_price(1999);
//			phyCouVO1.setCourse_teacher("peter");
//			phyCouVO1.setCourse_date(java.sql.Date.valueOf("2022-12-31"));
//			phyCouVO1.setCourse_location("904");
//			phyCouVO1.setCourse_info("test");
//			phyCouVO1.setCourse_status(0);
//			phyCouVO1.setCreate_date(update_time);
//			phyCouVO1.setUpdate_time(update_time);
//			phyCouVO1.setSign_up_start_day(java.sql.Date.valueOf("2022-10-01"));
//			phyCouVO1.setSign_up_end_day(java.sql.Date.valueOf("2022-12-27"));
//			phyCouVO1.setMax_sign_up_people(100);
//			phyCouVO1.setMin_sign_up_people(20);
//			phyCouVO1.setCurrent_sign_up_people(46);
//			dao.insert(phyCouVO1);

//			
//			PhyCouVO phyCouVO1 = new PhyCouVO();
//			phyCouVO1.setCourse_no(12);
//			phyCouVO1.setCourse_name("即好食");
//			phyCouVO1.setCourse_hr(3);
//			phyCouVO1.setCourse_price(1999);
//			phyCouVO1.setCourse_teacher("peter");
//			phyCouVO1.setCourse_date(java.sql.Date.valueOf("2022-12-31"));
//			phyCouVO1.setCourse_location("904");
//			phyCouVO1.setCourse_info("test");
//			phyCouVO1.setCourse_status(0);
//			phyCouVO1.setCreate_date(update_time);
//			phyCouVO1.setUpdate_time(update_time);
//			phyCouVO1.setSign_up_start_day(java.sql.Date.valueOf("2022-10-01"));
//			phyCouVO1.setSign_up_end_day(java.sql.Date.valueOf("2022-12-27"));
//			phyCouVO1.setMax_sign_up_people(100);
//			phyCouVO1.setMin_sign_up_people(20);
//			phyCouVO1.setCurrent_sign_up_people(46);
//			dao.update(phyCouVO1);
//
//
//			dao.delete(12);
//
//
//			PhyCouVO phyCouVO3 = dao.findByPrimaryKey(2);
//			System.out.print(phyCouVO3.getCourse_no() + ",");
//			System.out.print(phyCouVO3.getCourse_name() + ",");
//			System.out.print(phyCouVO3.getCourse_hr() + ",");
//			System.out.print(phyCouVO3.getCourse_price() + ",");
//			System.out.print(phyCouVO3.getCourse_teacher() + ",");
//			System.out.print(phyCouVO3.getCourse_date() + ",");
//			System.out.print(phyCouVO3.getCourse_location() + ",");
//			System.out.print(phyCouVO3.getCourse_info() + ",");
//			System.out.print(phyCouVO3.getCourse_status() + ",");
//			System.out.print(phyCouVO3.getCreate_date() + ",");
//			System.out.print(phyCouVO3.getUpdate_time() + ",");
//			System.out.print(phyCouVO3.getSign_up_start_day() + ",");
//			System.out.print(phyCouVO3.getSign_up_end_day() + ",");
//			System.out.print(phyCouVO3.getMax_sign_up_people() + ",");
//			System.out.print(phyCouVO3.getMin_sign_up_people() + ",");		
//			System.out.println(phyCouVO3.getCurrent_sign_up_people());		
//			System.out.println("---------------------");

//
//			List<PhyCouVO> list = dao.getAll();
//			for (PhyCouVO aCourse : list) {
//				System.out.print(aCourse.getCourse_no() + ",");
//				System.out.print(aCourse.getCourse_name() + ",");
//				System.out.print(aCourse.getCourse_hr() + ",");
//				System.out.print(aCourse.getCourse_price() + ",");
//				System.out.print(aCourse.getCourse_teacher() + ",");
//				System.out.print(aCourse.getCourse_date() + ",");
//				System.out.print(aCourse.getCourse_location() + ",");
//				System.out.print(aCourse.getCourse_info() + ",");
//				System.out.print(aCourse.getCourse_status() + ",");
//				System.out.print(aCourse.getCreate_date() + ",");
//				System.out.print(aCourse.getUpdate_time() + ",");
//				System.out.print(aCourse.getSign_up_start_day() + ",");
//				System.out.print(aCourse.getSign_up_end_day() + ",");
//				System.out.print(aCourse.getMax_sign_up_people() + ",");
//				System.out.print(aCourse.getMin_sign_up_people() + ",");		
//				System.out.println(aCourse.getCurrent_sign_up_people());		
//				System.out.println();
//			}
//		}
	}