package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apidto.TripInfo;
import apiservice.TripInfoService;

@WebServlet("/tripinfo")
public class TripInfoController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TripInfoService service = new TripInfoService();
		List<TripInfo> list = service.getList();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/tripinfo.jsp").forward(req, resp);
	}
	
}
