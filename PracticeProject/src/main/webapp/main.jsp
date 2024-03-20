<%@page import="Board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>No</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>작성날짜</th>
        </tr>
        <% 
            List<BoardVO> blist = (List<BoardVO>) request.getAttribute("boardList");
            for(BoardVO bolist : blist){
        %>
        <tr>
            <td><%= bolist.getB_no() %></td>
            <td><a href="<%= request.getContextPath() %>/boardDetail.do?num=<%= bolist.getB_no() %>"><%= bolist.getB_title() %></a></td>
            <td><%= bolist.getB_writer() %></td>
            <td><%= bolist.getB_cnt() %></td>
            <td><%= bolist.getB_date() %></td>
        </tr>
        <%
            }
        %>
    </table>
    <input type="button" value="작성" onclick="location.href='./view/board/BoardInsert.jsp'" /><br>

    <%
        int start = Integer.parseInt(request.getParameter("start"));
        int end = Integer.parseInt(request.getParameter("end"));
        int totalCount = (Integer) request.getAttribute("boardCnt");

        int totalPages = (int) Math.ceil((double) totalCount / 10);
        for (int pagee = 1; pagee <= totalPages; pagee++) {
            int pageNum = (pagee - 1) * 10;
            String link = "./boardList.do?start=" + pageNum + "&end=" + (pageNum + 10);
    %>
            <a href="<%= link %>"><%= page %></a>
    <%
        }
    %>
</body>
</html>