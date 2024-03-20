package Board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardServiceImpl;
import Board.vo.BoardCmtVO;

@WebServlet("/bcommentInsert.do")
public class BCommentInsert extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		int b_no = Integer.parseInt(req.getParameter("num"));
		String bc_writer = req.getParameter("bc_writer");
		String bc_content = req.getParameter("bc_content");
		BoardCmtVO bcvo = new BoardCmtVO();
		bcvo.setBc_writer("익명");
		bcvo.setBc_content(bc_content);
		bcvo.setB_no(b_no);
		BoardServiceImpl service = BoardServiceImpl.getInstance();
		service.AddComment(bcvo);
		resp.sendRedirect(req.getContextPath()+"/boardDetail.do?num="+b_no);
	}

}
