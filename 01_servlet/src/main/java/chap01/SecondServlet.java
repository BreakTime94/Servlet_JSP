package chap01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/SecondServlet", "/Servlet2"})  //������̼� �� �������� ����Ѱ�
public class SecondServlet extends HttpServlet{

    // text/html, text/plain, text/xml, application/json
    // image/png 
    // mine-type

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // �⺻ ���� ���� text/plain
        resp.setContentType("text/html; charset=euc-kr");
        PrintWriter out = resp.getWriter();
        out.write("�̷��͵� ��");
        System.out.println("���� �ֿܼ� ���");
        out.println("ȭ�鿡 ��� abcd");
    }



}