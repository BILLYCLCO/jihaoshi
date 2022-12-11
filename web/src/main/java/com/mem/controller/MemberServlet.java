package com.mem.controller;

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

import com.mem.model.MemService;
import com.mem.model.MemberVO;

@WebServlet("/member/MemberServlet")
public class MemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String memberacc = req.getParameter("memberAccount");
			String memberpas = req.getParameter("memberPassword");
			String membername = req.getParameter("memberName");
			String memberpho = req.getParameter("memberPhone");
			String membernick = req.getParameter("memberNickname");
			String memberadd = req.getParameter("memberAddress");
			String memberemail = req.getParameter("memberEmail");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			String mailReg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (memberacc == null || memberacc.trim().length() == 0) {
				errorMsgs.add("會員帳號請勿空白");
			}
			if (memberpas == null || memberpas.trim().length() == 0) {
				errorMsgs.add("會員密碼請勿空白");
			}
			if (membername == null || membername.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!membername.trim().matches(enameReg)) {
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			if (memberpho == null || memberpho.trim().length() == 0) {
				errorMsgs.add("會員電話請勿空白");
			}
			if (membernick == null || membernick.trim().length() == 0) {
				errorMsgs.add("會員暱稱請勿空白");
			}
			if (memberadd == null || memberadd.trim().length() == 0) {
				errorMsgs.add("會員地址請勿空白");
			}
			if (memberemail == null || memberemail.trim().length() == 0) {
				errorMsgs.add("會員email請勿空白");
			} else if (!memberemail.trim().matches(mailReg)) {
				errorMsgs.add("email格式不符合");
			}

			MemberVO memVO = new MemberVO();
			memVO.setMemberName(membername);
			memVO.setMemberAccount(memberacc);
			memVO.setMemberPassword(memberpas);
			memVO.setMemberPhone(memberpho);
			memVO.setMemberNickname(membernick);
			memVO.setMemberAddress(memberadd);
			memVO.setMemberEmail(memberemail);
			
			MemService memSvc = new MemService();
			MemberVO memVO2 = memSvc.findByAccount(memberacc);
			if (!(memVO2 == null)) {
				errorMsgs.add("帳號重複");
			}
			MemberVO memVO3 = memSvc.findByEmail(memberemail);
			if (!(memVO3 == null)) {
				errorMsgs.add("email重複");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/member/addmember.jsp");
				failureView.forward(req, res);
				return;
			}

			MemService memSvc2 = new MemService();
			memVO = memSvc2.addmember(memberacc, memberpas, membername, memberpho, membernick, memberadd, memberemail);

			req.setAttribute("MemberVO", memVO);
			String url = "signupSucces.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer memberno = Integer.valueOf(req.getParameter("memberNo").trim());
			String membername = req.getParameter("memberName");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			String memberpas = req.getParameter("memberPassword");
			String memberpho = req.getParameter("memberPhone");
			String membernick = req.getParameter("memberNickname");
			String memberadd = req.getParameter("memberAddress");
			String memberemail = req.getParameter("memberEmail");
			String memberacc = req.getParameter("memberAccount");

			if (memberacc == null || memberacc.trim().length() == 0) {
				errorMsgs.add("會員帳號請勿空白");
			}
			if (membername == null || membername.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!membername.trim().matches(enameReg)) {
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			if (memberpas == null || memberpas.trim().length() == 0) {
				errorMsgs.add("會員密碼請勿空白");
			}
			if (memberpho == null || memberpho.trim().length() == 0) {
				errorMsgs.add("會員電話請勿空白");
			}
			if (membernick == null || membernick.trim().length() == 0) {
				errorMsgs.add("會員暱稱請勿空白");
			}
			if (memberadd == null || memberadd.trim().length() == 0) {
				errorMsgs.add("會員地址請勿空白");
			}
			if (memberemail == null || memberemail.trim().length() == 0) {
				errorMsgs.add("會員email請勿空白");
			}

			MemberVO memVO = new MemberVO();
			memVO.setMemberAccount(memberacc);
			memVO.setMemberName(membername);
			memVO.setMemberPassword(memberpas);
			memVO.setMemberPhone(memberpho);
			memVO.setMemberNickname(membernick);
			memVO.setMemberAddress(memberadd);
			memVO.setMemberEmail(memberemail);
			memVO.setMemberNo(memberno);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("MemberVO", memVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/member/update_mem_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			MemService memSvc = new MemService();
			memVO = memSvc.updateMem(memberacc, memberno, memberpas, membername, memberpho, membernick, memberadd,
					memberemail);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("MemberVO", memVO);
			String url = "editSucces.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 查詢一個會員

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer memberNo = Integer.valueOf(req.getParameter("memberNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			MemService memSvc = new MemService();
			MemberVO memVO = memSvc.getOneMem(memberNo);
			if (memVO == null) {
				errorMsgs.add("查無資料");

			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/frontPage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("MemberVO", memVO);
			String url = "update_mem_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("Login".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			final HttpSession session = req.getSession();

			String memberacc = req.getParameter("memberAccount");
			String memberpas = req.getParameter("memberPassword");

			if (memberacc == null || memberacc.trim().length() == 0) {
				errorMsgs.add("會員帳號請勿空白");
			}
			if (memberpas == null || memberpas.trim().length() == 0) {
				errorMsgs.add("會員密碼請勿空白");
			}

			MemberVO member = new MemberVO();

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", member);
				RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
				failureView.forward(req, res);
				return;
			}

			MemService memSvc = new MemService();
			member = memSvc.Login(memberacc, memberpas);

			if (member == null) {
				errorMsgs.add("帳號或密碼錯誤");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", member);
				RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
				failureView.forward(req, res);
				return;
			}

			session.removeAttribute("Guest");
			session.setAttribute("member", member);
			session.setAttribute("memberNo", member.getMemberNo());


			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			}catch (Exception ignored) { }
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}

		// 登出
		if ("Logout".equals(action)) {
			final HttpSession session = req.getSession();
			session.removeAttribute("member");

			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
		// -------------------登出結束------------------------------------
	}
}
