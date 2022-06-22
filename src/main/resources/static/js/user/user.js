let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});

		$("#btn-update").on("click", () => {
			this.update();
		});

	},


	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()


		};

		//ajax호출 시 default가 비동기 호출이다.
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //데이터 타입 명시
			dataType: "json" // 응답이 왔을때의 데이터타입을 자바스크립트 오브젝트로 변환해준다.

		}).done(function(resp) {
			if (resp.status == 500) {
				alert("가입 실패");
			} else {
				alert("회원가입 완료");
				location.href = "/";
			}

		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		//ajax호출 시 default가 비동기 호출이다.
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //데이터 타입 명시
			dataType: "json" // 응답이 왔을때의 데이터타입을 자바스크립트 오브젝트로 변환해준다.

		}).done(function(resp) {
			alert("수정 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}

}

index.init();