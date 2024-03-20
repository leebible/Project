package Board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardServiceImpl;
import Board.vo.BoardVO;

@WebServlet("/boardInsert.do")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("b_title");
		String content=request.getParameter("b_content");
		BoardVO bvo = new BoardVO();
		bvo.setB_title(title);
		bvo.setB_content(content);
		
		BoardServiceImpl service = BoardServiceImpl.getInstance();
		int cnt = service.AddBoard(bvo);
		response.sendRedirect(request.getContextPath()+"/boardList.do");
		
		
		
		
	}

}
