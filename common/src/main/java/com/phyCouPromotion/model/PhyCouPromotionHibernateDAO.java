package com.phyCouPromotion.model;

import org.hibernate.*;
import org.hibernate.query.Query;

import com.course.model.PhyCouVO;
import com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO;
import hibernate.util.HibernateUtil;
import java.util.*;

public class PhyCouPromotionHibernateDAO implements PhyCouPromotionDAO_interface {

	private static final String GET_ALL_STMT = "from PhyCouPromotionVO order by project_no";

	@Override
	public Integer insert(PhyCouPromotionVO phyCouPromotionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(phyCouPromotionVO);
		    session.getTransaction().commit();
		    Integer project_no = phyCouPromotionVO.getProject_no();
		    return project_no;
		   
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(PhyCouPromotionVO phyCouPromotionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			session.saveOrUpdate(phyCouPromotionVO);
//			PhyCouPromotionVO fVO = null;
			PhyCouPromotionVO DBVO = session.get(PhyCouPromotionVO.class, phyCouPromotionVO.getProject_no());
			if (phyCouPromotionVO.getProject_name() !=  DBVO.getProject_name()) {
				DBVO.setProject_name(phyCouPromotionVO.getProject_name());
			}
			if (phyCouPromotionVO.getStart_date() !=  DBVO.getStart_date()) {
				DBVO.setStart_date(phyCouPromotionVO.getStart_date());
			}
			if (phyCouPromotionVO.getEnd_date() !=  DBVO.getEnd_date()) {
				DBVO.setEnd_date(phyCouPromotionVO.getEnd_date());
			}
			if (phyCouPromotionVO.getProm_description() !=  DBVO.getProm_description()) {
				DBVO.setProm_description(phyCouPromotionVO.getProm_description());
			}
			if (phyCouPromotionVO.getProm_status() !=  DBVO.getProm_status()) {
				DBVO.setProm_status(phyCouPromotionVO.getProm_status());
			}
			DBVO.setUpdate_time(phyCouPromotionVO.getUpdate_time());
					
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer project_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
			phyCouPromotionVO.setProject_no(project_no);
			session.delete(phyCouPromotionVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public PhyCouPromotionVO findByPrimaryKey(Integer project_no) {
		PhyCouPromotionVO phyCouPromotionVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			phyCouPromotionVO = (PhyCouPromotionVO) session.get(PhyCouPromotionVO.class, project_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return phyCouPromotionVO;
	}

	@Override
	public List<PhyCouPromotionVO> getAll() {
		List<PhyCouPromotionVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<PhyCouPromotionVO> query = session.createQuery(GET_ALL_STMT, PhyCouPromotionVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public Set<PhyCouPromotionDetailVO> getCousByProject_no(Integer project_no) {		
		Set<PhyCouPromotionDetailVO> set = findByPrimaryKey(project_no).getPhyCouPromotionDetails();
		return set;
	}
//}
//	@Override
//	public List<PhyCouPromotionVO> getAll(Map<String, String[]> map) {
//		List<PhyCouPromotionVO> list = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			list = HibernateUtil_CompositeQuery_Emp2.getAllC(map);
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return list;
//	}

	public static void main(String[] args) {

		PhyCouPromotionHibernateDAO dao = new PhyCouPromotionHibernateDAO();

		//● 新增-1(一方dept2.hbm.xml必須有cascade="save-update" 或cascade="all"的設定)(雖然強大,不過實務上並不常用)(但,可用在訂單主檔與明細檔一次新增成功)
//		DeptVO deptVO = new DeptVO(); // 部門POJO
//		Set<EmpVO> set = new LinkedHashSet<EmpVO>();// 準備置入員工數人,以便cascade="save-update"的測試

		
		PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
		Set<PhyCouPromotionDetailVO> set = new HashSet<PhyCouPromotionDetailVO>();
		PhyCouVO phyCouVO1 = new PhyCouVO();
		PhyCouVO phyCouVO2 = new PhyCouVO();
		
		PhyCouPromotionDetailVO phyCouPromotionDetailVO1 = new PhyCouPromotionDetailVO();
		phyCouPromotionDetailVO1.setPhyCouPromotionVO(phyCouPromotionVO);
		phyCouVO1.setCourse_no(1);
		phyCouPromotionDetailVO1.setPhyCouVO(phyCouVO1);
		phyCouPromotionDetailVO1.setProm_price(50);
		
		PhyCouPromotionDetailVO phyCouPromotionDetailVO2=  new PhyCouPromotionDetailVO();
		phyCouPromotionDetailVO2.setPhyCouPromotionVO(phyCouPromotionVO);
		phyCouVO2.setCourse_no(2);
		phyCouPromotionDetailVO2.setPhyCouVO(phyCouVO2);
		phyCouPromotionDetailVO2.setProm_price(40);
		
		set.add(phyCouPromotionDetailVO1);
		set.add(phyCouPromotionDetailVO2);
		
		phyCouPromotionVO.setProject_name("test");
		phyCouPromotionVO.setStart_date(java.sql.Date.valueOf("2001-01-16"));
		phyCouPromotionVO.setEnd_date(java.sql.Date.valueOf("2001-02-10"));
		phyCouPromotionVO.setProm_description ("test");
		phyCouPromotionVO.setProm_status(0);
	//	phyCouPromotionVO.setUpdate_time(java.sql.Date.valueOf("2022-01-16"));
		phyCouPromotionVO.setPhyCouPromotionDetails(set);
		
		dao.insert(phyCouPromotionVO);
		
		
		
		
	}
	}
		
//		EmpVO empXX = new EmpVO();   // 員工POJO1
//		empXX.setEname("吳15");
//		empXX.setJob("MANAGER15");
//		empXX.setHiredate(java.sql.Date.valueOf("2001-01-15"));
//		empXX.setSal(new Double(15000));
//		empXX.setComm(new Double(150));
//		empXX.setDeptVO(deptVO);
//
//		EmpVO empYY = new EmpVO();   // 員工POJO2
//		empYY.setEname("吳16");
//		empYY.setJob("MANAGER16");
//		empYY.setHiredate(java.sql.Date.valueOf("2001-01-16"));
//		empYY.setSal(new Double(16000));
//		empYY.setComm(new Double(160));
//		empYY.setDeptVO(deptVO);
//
//		set.add(empXX);
//		set.add(empYY);
//
//		deptVO.setDname("製造部");
//		deptVO.setLoc("中國江西");
//		deptVO.setEmps(set);
//		dao.insert(deptVO);



		//● 修改-1(一方dept2.hbm.xml必須有cascade="save-update" 或 cascade="all"的設定)(雖然強大,不過實務上並不常用)(但,可視情況使用之)
//		DeptVO deptVO = new DeptVO(); // 部門POJO
//		Set<EmpVO> set = new LinkedHashSet<EmpVO>(); // 準備置入員工數人,以便cascade="save-update"的測試
//
//		EmpVO empXX = new EmpVO(); // 員工POJO1
//		empXX.setEmpno(7015); // 【如果增加 empXX.setEmpno(7015); 則變成update】
//		empXX.setEname("吳永15");
//		empXX.setJob("MANAGER15");
//		empXX.setHiredate(java.sql.Date.valueOf("2001-01-15"));
//		empXX.setSal(new Double(15555));
//		empXX.setComm(new Double(155));
//		empXX.setDeptVO(deptVO);
//
//		EmpVO empYY = new EmpVO(); // 員工POJO2
//		empYY.setEmpno(7016); // 【如果增加 empXX.setEmpno(7016); 則變成update】
//		empYY.setEname("吳永16");
//		empYY.setJob("MANAGER16");
//		empYY.setHiredate(java.sql.Date.valueOf("2001-01-16"));
//		empYY.setSal(new Double(16666));
//		empYY.setComm(new Double(166));
//		empYY.setDeptVO(deptVO);
//
//		set.add(empXX);
//		set.add(empYY);
//
//		deptVO.setDeptno(50); // 【如果增加deptVO.setDeptno(50); 則變成update】
//		deptVO.setDname("製造部1");
//		deptVO.setLoc("中國江西1");
//		deptVO.setEmps(set);
//		dao.update(deptVO);



		//● 修改-2(不需設定cascade="save-update" 或 cascade="all")(這是經常要用到的一般修改)
//		DeptVO deptVO2 = new DeptVO(); // 部門POJO
//		deptVO2.setDeptno(50); // 【如果增加deptVO.setDeptno(50); 則變成update】
//		deptVO2.setDname("製造部2");
//		deptVO2.setLoc("中國江西2");
//		dao.update(deptVO2);



		//●刪除 (超級強大!小心使用!)(一方dept2.hbm.xml必須有cascade="delete" 或 cascade="all"的設定, 再加上inverse="true"設定)
//		dao.delete(50);



		//● 新增-2(不需要cascade="save-update" 或 cascade="all"的設定)(這是經常要用到的一般新增)
//		DeptVO deptVO = new DeptVO(); // 部門POJO
//		deptVO.setDname("製造部s");
//		deptVO.setLoc("中國江西s");
//		dao.insert(deptVO);



		//● 查詢-findByPrimaryKey (優秀!) (一方dept2.hbm.xml必須設為lazy="false")
//		DeptVO deptVO3 = dao.findByPrimaryKey(30);
//		System.out.print(deptVO3.getDeptno() + ",");
//		System.out.print(deptVO3.getDname() + ",");
//		System.out.print(deptVO3.getLoc());
//		System.out.println("\n-----------------");
//		Set<EmpVO> set3 = deptVO3.getEmps();
//		for (EmpVO aEmp : set3) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}



//		● 查詢-getAll-1 (一方dept2.hbm.xml不用設為lazy="false",因為沒用到多方的物件)
//		List<PhyCouPromotionVO> list1 = dao.getAll();
//		for (PhyCouPromotionVO aPro : list1) {
//			System.out.print(aPro.getProject_no() + ",");
//			System.out.print(aPro.getProject_name() + ",");
//			System.out.print(aPro.getStart_date() + ",");
//			System.out.print(aPro.getEnd_date() + ",");
//			System.out.print(aPro.getProm_description() + ",");
//			System.out.print(aPro.getProm_status() + ",");
//			System.out.print(aPro.getUpdate_time());
//			System.out.println();
//		}



		//● 查詢-getAll-2 (優秀!!!) (一方dept2.hbm.xml必須設為lazy="false")
//		List<PhyCouPromotionVO> list2 = dao.getAll();
//		for (DeptVO aDept : list2) {
//			System.out.print(aDept.getDeptno() + ",");
//			System.out.print(aDept.getDname() + ",");
//			System.out.print(aDept.getLoc());
//			System.out.println("\n-----------------");
//			Set<EmpVO> set2 = aDept.getEmps();
//			for (EmpVO aEmp : set2) {
//				System.out.print(aEmp.getEmpno() + ",");
//				System.out.print(aEmp.getEname() + ",");
//				System.out.print(aEmp.getJob() + ",");
//				System.out.print(aEmp.getHiredate() + ",");
//				System.out.print(aEmp.getSal() + ",");
//				System.out.print(aEmp.getComm() + ",");
//				System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//				System.out.print(aEmp.getDeptVO().getDname() + ",");
//				System.out.print(aEmp.getDeptVO().getLoc());
//				System.out.println();
//			}
//			System.out.println();
//		}

//}