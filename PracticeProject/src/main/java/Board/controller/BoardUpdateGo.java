package Board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardServiceImpl;
import Board.service.IBoardService;
import Board.vo.BoardVO;

@WebServlet("/boardUpdateGo.do")
public class BoardUpdateGo extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		System.out.println("boardupdatego까진 왔음");
		int b_no = Integer.parseInt(req.getParameter("num"));
		String b_title = req.getParameter("b_title");
		String b_content = req.getParameter("b_content");
		System.out.println(b_title);
		BoardVO bvo = new BoardVO();
		bvo.setB_title(b_title);
		bvo.setB_content(b_content);
		bvo.setB_no(b_no);
		IBoardService service = BoardServiceImpl.getInstance();
		service.UpdateBoard(bvo);
		resp.sendRedirect(req.getContextPath()+"/boardList.do");
	}
}
