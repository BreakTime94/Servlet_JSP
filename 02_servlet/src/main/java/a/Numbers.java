package a;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/number")
public class Numbers extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Integer> list = List.of(10, 30, 50, 60);
		req.setAttribute("num", list);
		req.getRequestDispatcher("number.jsp").forward(req, resp);
		//응답 바꿔치기 
		//서블릿으로 처리하고 jsp로 보여줄때 자주 사용하는 방식
		//forward는 내 응답을 쓰지 않는다. include는 다 쓴다.
	}
	
}
