/*/**
 * 댓글 비동기로 입력
 */
/*
let forms = document.forms;
form[0].addEventListener("submit",e=>{
	e.preventDefault();
	let form = e.target;
	
	let url = form.action;
	let method = form.method;
	
	let headers = {
		"content-type" : form.enctype,
		"accept" : "text/html"
	};
	
	let formData = new FormData(form)
	let options = {
		method : method,
		headers : headers,
		body: new URLSearchParams({ 
        	bc_writer: 'bc_writer',
      		bc_content: 'bc_content'
    	})
	}
	
	fetch(url,options)
		.then(resp=>{
			if(resp.ok){
				return resp.text();
			}else{
				throw new Error(`에러발생, 상태코드 : ${resp.status}`);
			}
		}).then(html=>(
			
			
		))
	
	
	
})*/