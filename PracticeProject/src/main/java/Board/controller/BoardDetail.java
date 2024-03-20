package Board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardServiceImpl;
import Board.service.IBoardService;
import Board.vo.BoardCmtVO;
import Board.vo.BoardVO;

@WebServlet("/boardDetail.do")
public class BoardDetail extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int bno = Integer.parseInt(req.getParameter("num"));
		
		IBoardService service = BoardServiceImpl.getInstance();
		BoardVO bvo = service.GetBoardDetail(bno);
		List<BoardCmtVO> bclist = service.GetCommentAllList(bno);
		
		req.setAttribute("boardVo", bvo);
		req.setAttribute("bclist", bclist);
		req.getRequestDispatcher("/view/board/BoardDetail.jsp").forward(req,resp);
		
		
		
		
	}

}
