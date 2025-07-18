package controller.board;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import domain.Attach;
import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;
import util.ParamUtil;

@Slf4j
@WebServlet("/board/write")
public class Write extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// session 내의 member attr 조회후 null일 경우 
//		Object o = req.getSession().getAttribute("member");
//		log.info("{}", o);
		Criteria cri = Criteria.init(req);
		if( req.getSession().getAttribute("member") == null) {
//			AlertUtil.alert("로그인 후 글을 작성해주세요",String.format("%s%s%s", req.getContextPath(), "/member/login?url=", URLEncoder.encode(req.getRequestURL().toString(), "utf-8")) , req, resp);
			AlertUtil.alert("로그인 후 작성하여주세요", "/member/login?" + cri.getQs2(), req, resp, true);
//			resp.setContentType("text/html; charset=utf-8");
//			PrintWriter pw = resp.getWriter();
//			pw.print("<script>");
//			pw.print("alert('로그인 후 글 작성해주세요.'); ");
////			pw.print("location.href = '" + req.getContextPath() + "/member/login'");
//			pw.print(String.format("location.href = '%s%s%s'", req.getContextPath(), "/member/login?url=", URLEncoder.encode(req.getRequestURL().toString(), "utf-8")));
//			pw.print("</script>");
			return;
		}
		req.setAttribute("cri", cri);
		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//session 체크
		Criteria cri = Criteria.init(req);
		if( req.getSession().getAttribute("member") == null) {
			AlertUtil.alert("로그인 후 작성하여주세요", "/member/login?" + cri.getQs2(), req, resp, true);
		}
		//파라미터 수집
//		Board b = ParamUtils.get(req, Board.class);
//		
//		String title = req.getParameter("title");
//		String content = req.getParameter("content");
//		String id = req.getParameter("id");
//		Integer cno = Integer.valueOf(req.getParameter("cno")); 
		
		String encodedStr = req.getParameter("encodedStr");
		Type type = new TypeToken<List<Attach>>() {}.getType(); 
		List<Attach> list = new Gson().fromJson(encodedStr, type);
		log.info("{}", list);
		Board board = ParamUtil.get(req, Board.class);
		board.setAttachs(list);
		//board 인스턴스 생성
//		Board board = Board.builder().title(title).content(content).id(id).cno(cno).attachs(list).build();
		log.info("{}", board);
		
		//보드서비스 호출
		BoardService boardService = new BoardService();
		boardService.write(board);
		
		//리디렉션(board/list)
		AlertUtil.alert("글이 성공적으로 등록되었습니다.", "/board/list?cno=" + cri.getQs() + "&amount=" + cri.getAmount(), req, resp);
	}
	
}
