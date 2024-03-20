package Board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardServiceImpl;
import Board.service.IBoardService;

@WebServlet("/boardDelete.do")
public class BoardDelete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bno = Integer.parseInt(req.getParameter("num"));
		System.out.println("Delno:"+bno);
		IBoardService service = BoardServiceImpl.getInstance();
		int cnt = service.DeleteBoard(bno);
		System.out.println("삭제값:"+cnt);
		resp.sendRedirect(req.getContextPath()+"/boardList.do");
	}

}
