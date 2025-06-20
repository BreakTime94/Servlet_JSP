package controller.reply;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import service.ReplyService;

@WebServlet("/reply/*")
@Slf4j
public class ReplyServlet extends HttpServlet{

	public static final String ID = "/reply/";
	
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
		
		String uri = getURI(req);
		ReplyService service = new ReplyService();
		Gson gson = new Gson();
		String ret = "";
		
		
		if(uri.startsWith("list") || uri.equals("*")) {//목록조회
			log.info("{}", uri);
			String tmp = "list/";
			if(uri.contains(tmp)) {
				String[] tmps = uri.split("/");
				//reply/list/bno값/lastrno값
				//reply/list/bno값
				//reply/rno바로 접근
				if(tmps.length > 1) {
					Long bno = Long.valueOf(tmps[1]);
					Long lastRno = null;
					if(tmps.length > 2) {
						lastRno = Long.valueOf(tmps[2]);
					}
					ret = gson.toJson(service.list(bno, lastRno));
				}	
			}
		}
		else { // 단일조회
			ret = gson.toJson(service.findBy(Long.parseLong(uri)));
		}
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(ret);
		}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		Long rno = Long.valueOf(uri);
		
		new ReplyService().remove(rno);
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(Map.of("result", true)));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String ret = String.join("", req.getReader().lines().toList());	
		
		Reply reply  = new Gson().fromJson(ret, Reply.class);
//		System.out.println(reply);
		new ReplyService().register(reply);
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(Map.of("result", true)));
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		Long rno = Long.valueOf(uri);
		
		req.setCharacterEncoding("utf-8");
		String ret = String.join("", req.getReader().lines().toList());
		Reply reply = new Gson().fromJson(ret, Reply.class);
		new ReplyService().modify(reply);
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(Map.of("result", true)));
	}
	
}
