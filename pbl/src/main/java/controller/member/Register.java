package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import lombok.extern.slf4j.Slf4j;
import service.MemberService;
import util.ParamUtil;

@WebServlet("/member/register")
@Slf4j
public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 파라미터 수집
//		String id = req.getParameter("id");
//		String pw = req.getParameter("pw");
//		String name = req.getParameter("name");
//		String email = req.getParameter("email");
//		log.info("{} {} {} {}", id, pw, name, email);
//		//2. Member인스턴스 생성
//		Member member = Member.builder().id(id).pw(pw).name(name).email(email).build();
//		log.info("{}", member);
		Member member = ParamUtil.get(req, Member.class);
		new MemberService().register(member);
		
		//3. memberservice.register(member)호출
		
		//4. index로 리디렉션
		resp.sendRedirect("../index");
	
	}
	
}
