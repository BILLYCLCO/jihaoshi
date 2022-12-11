package com.manager.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

@WebServlet("/manager/ManagerServlet")
public class ManagerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("managerNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入管理員編號");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/listAllManager.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer managerNo = null;
			try {
				managerNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("管理員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/listAllManager.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ManagerService mgrSvc = new ManagerService();
			ManagerVO mgrVO = mgrSvc.getOneMem(managerNo);
			if (mgrVO == null) {
				errorMsgs.add("查無資料");

			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/listAllMember.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			List<Integer> authorityNo= mgrSvc.getAuthority(mgrVO.getManagerNo());
			mgrVO.setAuthorityNo(authorityNo);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ManagerVO", mgrVO);
			String url = "/manager/listOneManager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer managerNo = Integer.valueOf(req.getParameter("managerNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			ManagerService mgrSvc = new ManagerService();
			ManagerVO mgrVO = mgrSvc.getOneMem(managerNo);
			if (mgrVO == null) {
				errorMsgs.add("查無資料");

			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/listAllMember.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("mgrVO", mgrVO);
			String url = "/manager/update_mgr_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer managerno = Integer.valueOf(req.getParameter("managerNo").trim());
			String manageracc = req.getParameter("managerAccount");
			String managerpwd = req.getParameter("managerPassword");
			String managername = req.getParameter("managerName");
			String managerip = req.getParameter("managerIp");
			Integer managerstate = Integer.valueOf(req.getParameter("managerStatus").trim());

			if (manageracc == null || manageracc.trim().length() == 0) {
				errorMsgs.add("員工帳號: 請勿空白");
			}
			if (managerpwd == null || managerpwd.trim().length() == 0) {
				errorMsgs.add("員工密碼: 請勿空白");
			}
			if (managername == null || managername.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			}
			if (managerip == null || managerip.trim().length() == 0) {
				errorMsgs.add("員工IP: 請勿空白");
			}
			if (managerstate == null)  {
				errorMsgs.add("員工狀態請勿空白");
			}else if(managerstate >=2) {
				errorMsgs.add("員工狀態只能是0或1");
			}

			ManagerVO mgrVO = new ManagerVO();
			mgrVO.setManagerNo(managerno);
			mgrVO.setManagerAccount(manageracc);
			mgrVO.setManagerPassword(managerpwd);
			mgrVO.setManagerName(managername);
			mgrVO.setManagerIp(managerip);
			mgrVO.setManagerStatus(managerstate);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("mgrVO", mgrVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/update_mgr_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ManagerService mgrSvc = new ManagerService();
			mgrVO = mgrSvc.updateEmp( managerno,manageracc,managerpwd,managername,managerip,managerstate);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("mgrVO", mgrVO);
			String url = "/manager/listAllManager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String manageracc = req.getParameter("managerAccount");
			String managerpas = req.getParameter("managerPassword");
			String managername = req.getParameter("managerName");
			String managerIp=req.getParameter("managerIp");
			
			if (manageracc == null || manageracc.trim().length() == 0) {
				errorMsgs.add("會員帳號請勿空白");
			}
			if (managerpas == null || managerpas.trim().length() == 0) {
				errorMsgs.add("會員密碼請勿空白");
			}
			if (managername == null || managername.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			}

			ManagerVO mgrVO = new ManagerVO();
			mgrVO.setManagerAccount(manageracc);
			mgrVO.setManagerPassword(managerpas);
			mgrVO.setManagerName(managername);
			mgrVO.setManagerIp(managerIp);
			

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", mgrVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/addManager.jsp");
				failureView.forward(req, res);
				return;
			}

			ManagerService memSvc = new ManagerService();
			mgrVO = memSvc.addmember( manageracc, managerpas,managername,managerIp);
			
			
			req.setAttribute("mgrVO", mgrVO);
			String url = "/manager/listAllManager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}
		if ("Login".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			final HttpSession session = req.getSession();

			String manageracc = req.getParameter("managerAccount");
			String managerpas = req.getParameter("managerPassword");

			if (manageracc == null || manageracc.trim().length() == 0) {
				errorMsgs.add("請輸入管理員帳號");
			}
			if (managerpas == null || managerpas.trim().length() == 0) {
				errorMsgs.add("請輸入管理員密碼");
			}

			ManagerVO manager = new ManagerVO();
//			List<ManagerVO> mgrVO2 = (List<ManagerVO>) new ManagerVO();

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ManagerVO", manager);
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/login.jsp");
				failureView.forward(req, res);
				return;
			}

			ManagerService mgrSvc = new ManagerService();
			manager = mgrSvc.Login(manageracc, managerpas);
			if (manager == null) {
				errorMsgs.add("帳號或密碼錯誤");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", manager);
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/login.jsp");
				failureView.forward(req, res);
				return;
			}
			List<Integer> authorityNo= mgrSvc.getAuthority(manager.getManagerNo());
			manager.setAuthorityNo(authorityNo);
			
			session.setAttribute("manager", manager);
			
//			String location = (String) session.getAttribute("location");
//			if (location != null) {
//				session.removeAttribute("location");
//				res.sendRedirect(location);
//			}
			res.sendRedirect(req.getContextPath() + "/index.jsp");

		}

		// 登出
		if ("Logout".equals(action)) {
			final HttpSession session = req.getSession();
			session.removeAttribute("manager");
			
			res.sendRedirect(req.getContextPath() + "/manager/login.jsp");
		}
	}
}
