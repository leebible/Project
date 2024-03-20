package Board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.service.BoardServiceImpl;
import Board.vo.BoardVO;

@WebServlet("/boardList.do")
public class BoardList extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		BoardServiceImpl service = BoardServiceImpl.getInstance();
		List<BoardVO> blist = service.GetBoardAllList();
		int total = service.getBoardCnt(); //게시글 전체 갯수
		
		int start = Integer.parseInt(req.getParameter("start")); // 시작 인덱스
        int end = Integer.parseInt(req.getParameter("end")); // 끝 인덱스
		
        Map<String, Integer> pMap = new HashMap<>();
        pMap.put("start", start);
        pMap.put("end", end);
        
		req.setAttribute("boardList", blist);
		req.setAttribute("total", total);
		System.out.println(blist);
		req.getRequestDispatcher("/main.jsp").forward(req, resp);

        service.pagingList(pMap);

        

		
	}
}
