let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});
	},


	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()

		};

		//ajax호출 시 default가 비동기 호출이다.
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //데이터 타입 명시
			dataType: "json" // 응답이 왔을때의 데이터타입을 자바스크립트 오브젝트로 변환해준다.

		}).done(function(resp) {
			alert("글쓰기 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	deleteById: function() {
		//ajax호출 시 default가 비동기 호출이다.

		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json" // 응답이 왔을때의 데이터타입을 자바스크립트 오브젝트로 변환해준다.

		}).done(function(resp) {
			alert("삭제 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	update: function() {
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val()

		};

		//ajax호출 시 default가 비동기 호출이다.
		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //데이터 타입 명시
			dataType: "json" // 응답이 왔을때의 데이터타입을 자바스크립트 오브젝트로 변환해준다.

		}).done(function(resp) {
			alert("수정 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};
	
		//ajax호출 시 default가 비동기 호출이다.
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //데이터 타입 명시
			dataType: "json" // 응답이 왔을때의 데이터타입을 자바스크립트 오브젝트로 변환해준다.

		}).done(function(resp) {
			alert("댓글작성 완료");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	replyDelete: function(boardId, replyId) {
		//ajax호출 시 default가 비동기 호출이다.
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json" // 응답이 왔을때의 데이터타입을 자바스크립트 오브젝트로 변환해준다.

		}).done(function(resp) {
			alert("댓글 삭제 완료");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}


}

index.init();