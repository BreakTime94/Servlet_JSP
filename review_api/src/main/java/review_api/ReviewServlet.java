package review_api;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Review;
import service.ReviewService;

@WebServlet("/review/*")
public class ReviewServlet extends HttpServlet{

	public static final String ID = "/review/";
	
	private String getURI(HttpServletRequest req) {			
		String uri = req.getRequestURI();
		uri = uri.substring(uri.indexOf(ID) + ID.length());			
		return uri;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("health check");
		//uri
		// /review/list 목록조회
//		System.out.println(req.getContextPath()); 
//		System.out.println(req.getRequestURI());
//		System.out.println(uri.indexOf("/review/")); // 처음 /review/ 문자열이 처음 나오는 인덱스 즉 앞에 /  
		
		ReviewService service = new ReviewService();
		String uri = getURI(req);
		Gson gson = new Gson();
		String ret = "";
		
		
		if(uri.startsWith("list") || uri.equals("*")) {//목록조회
			ret = gson.toJson(service.list());
		}
		else { // 단일조회
			 ret = gson.toJson(service.findBy(Long.parseLong(uri)));
		}
		System.out.println(ret);
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(ret);
		//
		
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		Long rno = Long.valueOf(uri);
		boolean result = new ReviewService().remove(rno) > 0;
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(Map.of("result", result)));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String ret = String.join("", req.getReader().lines().toList());	
		
		Review review = new Gson().fromJson(ret, Review.class);
		System.out.println(review);
		new ReviewService().register(review);
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(Map.of("result", true)));
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		Long rno = Long.valueOf(uri);
		
		req.setCharacterEncoding("utf-8");
		String ret = String.join("", req.getReader().lines().toList());
		Review review = new Gson().fromJson(ret, Review.class);
		boolean result = new ReviewService().modify(review) > 0;
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(Map.of("result", result)));
	}
	
}
