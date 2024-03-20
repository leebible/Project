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

@WebServlet("/boardUpdateView.do")
public class BoardUpdateView extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int b_no = Integer.parseInt(req.getParameter("num"));
		IBoardService service = BoardServiceImpl.getInstance();
		BoardVO bvo = service.GetBoardDetail(b_no);
		req.setAttribute("bvo", bvo);
		req.getRequestDispatcher("/view/board/BoardUpdate.jsp").forward(req, resp);
	}
}
