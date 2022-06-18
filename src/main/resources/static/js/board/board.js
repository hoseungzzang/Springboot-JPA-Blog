let index = {
	init: function() {
		$("#btn-save").on("click", () => { 
			this.save();
		});
		
	},


	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
	
		};

		//ajax호출 시 default가 비동기 호출이다.
		$.ajax({
			type:"POST",
			url: "/api/board",
			data : JSON.stringify(data), //http body데이터
			contentType:"application/json; charset=utf-8", //데이터 타입 명시
			dataType : "json" // 응답이 왔을때의 데이터타입을 자바스크립트 오브젝트로 변환해준다.
			
		}).done(function(resp){
			alert("글쓰기 완료");
			location.href="/";
		}).fail(function(error){
				alert(JSON.stringify(error));
		});
	}

}

index.init();