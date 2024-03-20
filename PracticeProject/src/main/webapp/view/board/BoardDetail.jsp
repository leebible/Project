<%@page import="java.util.List"%>
<%@page import="Board.vo.BoardCmtVO"%>
<%@page import="Board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
#tdc{
width :450px;
}
#bc_writer{
width : 50px;
}
textarea{
width:420px;
height:25px;
}
</style>

</script>
</head>
<body>

	<%  BoardVO bvo = (BoardVO) request.getAttribute("boardVo");
		List<BoardCmtVO> bclist = (List<BoardCmtVO>) request.getAttribute("bclist");
		
	%>
<table border="1">
	<tr>
		<th>제목 : </th>
		<th id="tdc"><%=bvo.getB_title() %></th>
	</tr>
	<tr>
		<td>내용:</td>
		<td id="tdc"><%=bvo.getB_content() %></td>
	</tr>
</table>
<input type="button" value="게시글 수정" onclick="location.href='<%=request.getContextPath()%>/boardUpdateView.do?num=<%=bvo.getB_no()%>'">
<input type="button" value="게시글 삭제" onclick="location.href='<%=request.getContextPath()%>/boardDelete.do?num=<%=bvo.getB_no()%>'"><br>

<%-- <% for(BoardCmtVO boclist : bclist){ %>

		<div class="bc"> 
			<input type="hidden" id="<%=boclist.getBc_no()%>" name="bc_no" value="<%=boclist.getBc_no()%>">
			<input type="hidden" id="bno" name="bno" value="<%=bvo.getB_no()%>">
			<input type="text" id="bc_writer" name="bc_writer" value="<%=boclist.getBc_writer() %>" readonly />
			<textarea name="bc_content_update" readonly><%=boclist.getBc_content() %></textarea>
			<button id="updateBtn">수정</button>
			<button id="deleteBtn">삭제</button>
		</div>

<%
}
%> --%>
<% for(BoardCmtVO boclist : bclist) { %>
    <div class="bc" id="bc_<%=boclist.getBc_no()%>"> 
    <hr>
        <div class="bc-info">
            <span class="writer"><%=boclist.getBc_writer() %></span>
            <span class="date"><%=boclist.getBc_date() %></span>
        </div>
        <div class="bc_content">
            <p><%=boclist.getBc_content() %></p>
        </div>
        <div class="bc-actions">
            <button class="updateBtn" data-bcno="<%=boclist.getBc_no()%>" data-bno="<%=bvo.getB_no()%> ">댓글 수정</button>
            <button class="deleteBtn" data-bcno="<%=boclist.getBc_no()%>" data-bno="<%=bvo.getB_no()%>">댓글 삭제</button>
        </div>
    </div>
<% } %>
	<br>


<hr>
<div id=resultArea></div>
<form action="<%=request.getContextPath()%>/bcommentInsert.do?num=<%=bvo.getB_no() %>" method="post" >
		<input type="text" id="bc_writer" name="bc_writer" value="익명" readonly>
		댓글내용: <textarea name="bc_content" ></textarea>
<input type="submit" value="작성 ">
</form>

<script>
document.querySelectorAll('.updateBtn').forEach(button => {
	 button.addEventListener('click', function() {
	        const bcno = parseInt(this.dataset.bcno); // bcno 변수에 정수로 변환된 값 할당
	        const commentContent = document.querySelector('#bc_' + bcno + ' .bc_content p').innerText;
	        const commentInfo = document.querySelector('#bc_' + bcno + ' .bc-info');
	        const commentActions = document.querySelector('#bc_' + bcno + ' .bc-actions');
	        
	        // 댓글 정보 숨기기
	        document.querySelector('#bc_' + bcno + ' .bc_content p').style.display = 'none';
	        commentInfo.style.display = 'none';
	        commentActions.style.display = 'none';
	        
	        const inputWriterHTML = '<input type="text" id="bc_writer" name="bc_writer" value="익명" readonly>';
	        const textareaContentHTML = '<textarea id="newBcContent" name="bc_content">' + commentContent.trim() + '</textarea>';
	        const submitButtonHTML = '<input id="newUpdateBtn" type="button" value="수정 완료">';
	        document.querySelector('#bc_' + bcno + ' .bc_content').innerHTML = inputWriterHTML + textareaContentHTML + submitButtonHTML;

	        // 수정 완료 버튼에 대한 이벤트 리스너 추가
	        document.getElementById('newUpdateBtn').addEventListener('click', function() {
	            const newContent = document.getElementById('newBcContent').value;

	            // fetch를 이용하여 서버에 수정된 댓글 내용 전송
	            fetch('<%=request.getContextPath()%>/bcommentUpdate.do', {
	                method: 'POST',
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                body: JSON.stringify({
	                    bcno: bcno,
	                    newBcContent: newContent // 변수명 수정
	                })
	            })
	            .then(response => {
	                if (response.ok) {
	                    return response.json(); // JSON 형식의 응답 데이터를 파싱하여 반환
	                } else {
	                    throw new Error('댓글 수정에 실패했습니다.');
	                }
	            })
	            .then(data => {
	                if (data.success) {
	                    // 성공적으로 수정된 경우
	                    alert('댓글이 성공적으로 수정되었습니다.');
	                    
	                    // 수정된 날짜 출력
	                    const updatedDate = data.date;
	                    const writer = data.name;

	                    console.log('이름:', writer);
	                    console.log('수정된 날짜:', updatedDate);
	                    
	                    document.querySelector('#bc_' + bcno + ' .bc-info').style.display='block';
	                    const infoContainer = document.querySelector('#bc_' + bcno + ' .bc-info');
	                    
	                    infoContainer.innerHTML =  '<span class="writer">'+writer+'</span>';
	                    infoContainer.innerHTML += '<span class="date">'+updatedDate+'</span>';
	                    
	                    const contentContainer = document.querySelector('#bc_' + bcno + ' .bc_content');
	                    contentContainer.innerHTML = '<p>' + newContent + '</p>';
	                } else {
	                    alert('댓글 수정에 실패했습니다.');
	                }
	            })
	        });
	    });
	});
        
   


    // 삭제 버튼에 대한 이벤트 리스너 추가
    document.querySelectorAll('.deleteBtn').forEach(button => {
        button.addEventListener('click', function() {
            if (confirm('정말로 삭제하시겠습니까?')) {
                const bcno = this.dataset.bcno;
                const bno = this.dataset.bno;
                console.log(bcno+"댓"+bno)
                fetch('<%=request.getContextPath()%>/bcommentDelete.do', {
                    method: 'POST',
                    headers: {
                    	 'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'bcno=' + bcno + '&bno=' + bno
                })
                .then(resp =>{
                    if (resp.ok) {
                        // 성공적으로 삭제된 경우, 화면에서 해당 댓글 삭제
                        document.querySelector('#bc_' + bcno).remove();
                        alert('댓글이 성공적으로 삭제되었습니다.');
                    } else {
                        alert('댓글 삭제에 실패했습니다.');
                    }
                });
            }
        });
    });
    
    
</script>

<%-- <script>


document.addEventListener("DOMContentLoaded", function(){
/* 	const bcElement = document.querySelectorAll(".bc");
	bcElement.addEventListener("click",function(e){
		bcElement.forEach(li => {
		    console.log(li.textContent);
		});
		console.log(e.target)
	})
	 */
	
document.getElementById("updateBtn").addEventListener("click", function(){
		console.log("ㅎㅇ")
	
	})
	
	document.getElementById("deleteBtn").addEventListener("click", function(){
		var bcno = document.getElementById("<%=boclist.getBc_no()%>").value;
		var bno = document.getElementById("bno").value;
		console.log(bcno)
		
		
		data = {
			'bcno' : bcno,
			'bno' : bno
		}
		
		console.log('bcno : ' + bcno + 'bno :' + bno)
		
		fetch('/bcommentDelete.do',{
			method : 'post',
			body : data
		}).then(resp=>(){
				console.log(resp)
				if(resp.ok) console.log("삭제성공")
			})
	}) */
})
</script> --%>
</body>
</html>