<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center align-items-center">
	<div>
		<h2 class="my-4 text-center">회원가입</h2>
		<form method="post" action="/user/sign-up" id="loginForm">
			<div>
				<span>* ID</span>
				<div class="d-flex mt-2">
					<input type="text" class="form-control mr-2" id="loginId" name="loginId" placeholder="4자 이상 입력해주세요.">
					<button type="button" class="btn text-nowrap" id="loginIdCheckBtn">중복확인</button>
				</div>
				<div class="mb-2">
					<small id="checkLength" class="text-danger d-none">4자 이상 입력해주세요.</small>
					<small id="checkDuplicated" class="text-danger d-none">중복된 ID 입니다.</small>
					<small id="checkOk" class="text-success d-none">사용 가능한 ID 입니다.</small>
				</div>
			</div>
			<div>
				<span>* 비밀번호</span>
				<input type="password" class="form-control my-2" id="password" name="password">
			</div>
			<div>
				<span>* 비밀번호 확인</span>
				<input type="password" class="form-control my-2" id="passwordConfirm" name="passwordConfirm">
			</div>
			<div>
				<span>* 이름</span>
				<input type="text" class="form-control my-2" id="name" name="name">
			</div>
			<div>
				<span>* 전화번호</span>
				<div class="d-flex align-items-center">
					<select class="form-control" id="phoneStart">
					  <option selected>010</option>
					  <option value="1">016</option>
					  <option value="2">017</option>
					  <option value="3">018</option>
					</select>
					<span class="mx-2"> - </span>
					<input type="text" class="form-control my-2" id="phoneMiddle">
					<span class="mx-2"> - </span>
					<input type="text" class="form-control my-2" id="phoneEnd">
				</div>
			</div>
			<div>
				<span>* 이메일</span>
				<input type="text" class="form-control my-2" id="email" name="email">
			</div>
			
			<button type="submit" class="btn btn-light float-right my-2" id="btnSignUp">회원가입</button>
			
		</form>
	</div>
</div>

<script>

	$(document).ready(function() {
		
		// 아이디 체크
		$("#loginIdCheckBtn").on("click", function() {
			let loginId = $("#loginId").val().trim();
			
			// d-none 초기화
			$("#checkLength").addClass("d-none");
			$("#checkDuplicated").addClass("d-none");
			$("#checkOk").addClass("d-none");
			
			if (loginId.length < 4){
				$("#checkLength").removeClass("d-none");
				return;
			}
			
			 $.ajax({
				 type: "POST"
				 , url: "/user/is-duplicated-id"
				 , data: {"loginId" : loginId}
			 
			 	 , success: function(data){
			 		 if (data.is_duplicated){
			 			$("#checkDuplicated").removeClass("d-none");
			 		 } else if (!data.is_duplicated){
			 			$("#checkOk").removeClass("d-none"); 
			 		 } else{
			 			 alert(data.error_message);
			 		 }
			 	 }
			 	 , error: function(request, status, error){
			 		 alert("중복 확인에 실패했습니다. 관리자에게 문의 주세요.")
			 	 }
			 })
			
		}); // -login
		
		// 회원가입
		$("#loginForm").on("submit", function(e) {
			e.preventDefault();
			// alert("click");
			
			// vaildation check
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			let passwordConfirm = $("#passwordConfirm").val();
			let name = $("#name").val().trim();
			let phoneStart = $("#phoneStart").val();
			let phoneMiddle = $("#phoneMiddle").val().trim();
			let phoneEnd = $("#phoneEnd").val().trim();
			let phoneNumber = phoneStart + phoneMiddle + phoneEnd;
			let email = $("#email").val().trim();
			
			if (!loginId){
				alert("아이디를 입력하세요.");
				return false;
			}
			
			if (!password || !passwordConfirm){
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			if (password != passwordConfirm){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			
			if (!name){
				alert("이름을 입력해주세요.");
				return false;
			}
			
			if(!phoneMiddle || !phoneEnd){
				alert("전화번호를 입력해주세요.");
				return false;
			}
			
			if (!email){
				alert("이메일을 입력해주세요.");
				return false;
			}
			
			if (!email.includes("@")){
				alert("이메일 양식이 틀렸습니다.");
				return false;
			}
			
			if ($("#checkOk").hasClass("d-none")){
				alert("중복 확인을 해주세요.");
				return false;
			}
			
			$.ajax({
				type: "POST"
				, url: "/user/sign-up"
				, data: {"loginId": loginId, "password": password, "name": name, "phoneNumber": phoneNumber, "email": email}
				
				, success: function(data) {
					if (data.code == 200){
						alert("회원가입을 축하합니다!☆")
						location.href="/user/sign-in-view";
					}
				}
				
				, error: function(request, stauts, error) {
					alert("회원가입에 실패했습니다. 관리자에게 문의주세요.");
				}
			}); // - btn ajax
			
		}); // - btnsignup
		
	}); // - doc

</script>
