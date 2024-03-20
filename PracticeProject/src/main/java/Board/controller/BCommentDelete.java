package Board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardServiceImpl;

@WebServlet("/bcommentDelete.do")
public class BCommentDelete extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("댓글삭제서블릿까진옴");
		int bcno = Integer.parseInt(req.getParameter("bcno"));
		int bno = Integer.parseInt(req.getParameter("bno"));
		System.out.println("BCommentDelete:"+bcno+"bno:"+bno);
		BoardServiceImpl service = BoardServiceImpl.getInstance();
		service.DeleteComment(bcno);
		resp.sendRedirect(req.getContextPath()+"/boardDetail.do?num="+bno);
	}
}
