package Board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Board.service.BoardServiceImpl;
import Board.vo.BoardCmtVO;

@WebServlet("/bcommentUpdate.do")
public class BCommentUpdate extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//JSON Parsing
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {/* report an error */ }
						
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jb.toString());
		int bcno = element.getAsJsonObject().get("bcno").getAsInt();
		String newContent = element.getAsJsonObject().get("newBcContent").getAsString();

		BoardCmtVO bcvo = new BoardCmtVO();
		bcvo.setBc_no(bcno);
		bcvo.setBc_content(newContent);
		// 댓글 서비스를 이용하여 댓글 수정
		BoardServiceImpl service = BoardServiceImpl.getInstance();
		int cnt = service.UpdateComment(bcvo);
		
		BoardCmtVO bcvo2 = service.getOneComment(bcno);
		String name = bcvo2.getBc_writer();
		String date = bcvo2.getBc_date();
		// 응답 데이터 설정
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();

		if (cnt > 0) {
			// 성공적으로 수정된 경우
			 out.println("{ \"success\": true, \"date\": \"" + date + "\" , \"name\": \""+name+"\"}");
		} else {
			// 수정에 실패한 경우
			out.println("{ \"success\": false }");
		}
	}
}
