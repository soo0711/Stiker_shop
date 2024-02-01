<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center align-items-center">
	<div>
		<h2 class="my-4 text-center">회원가입</h2>
		<form method="post" action="/user/sign-up" id="loginForm">
			<div>
				<span>* ID</span>
				<div class="d-flex my-2">
					<input type="text" class="form-control mr-2" id="loginId" name="loginId">
					<button type="button" class="btn text-nowrap" id="loginIdCheckBtn">중복확인</button>
				</div>
			</div>
			<div>
				<span>* 비밀번호</span>
				<input type="text" class="form-control my-2" id="password" name="password">
			</div>
			<div>
				<span>* 비밀번호 확인</span>
				<input type="text" class="form-control my-2" id="passwordConfirm" name="passwordConfirm">
			</div>
			<div>
				<span>* 이름</span>
				<input type="text" class="form-control my-2" id="name" name="name">
			</div>
			<div>
				<span>* 전화번호</span>
				<div class="d-flex align-items-center">
					<div class="dropdown id="phoneStart">
						  <button class="form-control dropdown-toggle" type="button" data-toggle="dropdown">
						  010
						  </button>
						  <div class="dropdown-menu">
						    <button class="dropdown-item" type="button">011</button>
						    <button class="dropdown-item" type="button">016</button>
						    <button class="dropdown-item" type="button">017</button>
						  </div>
					</div>
					<span class="mx-2"> - </span>
					<input type="text" class="form-control my-2" id="phoneMiddle">
					<span class="mx-2"> - </span>
					<input type="text" class="form-control my-2" id="phoneEnd">
				</div>
			</div>
			<div>
				<span>* 이메일</span>
				<input type="text" class="form-control my-2" id="name" name="name">
			</div>
			
			<button type="submit" class="btn btn-light float-right my-2" id="btnSignUp">회원가입</button>
			
		</form>
	</div>
</div>

<script>

	$(document).ready(function() {
		$("#loginForm").on("submit", function(e) {
			e.preventDefault();
			// alert("click");
			
			// vaildation check
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			let passwordConfirm = $("#passwordConfirm").val();
			let name = $("#name").val().trim();
			let phoneNumber = $("#phoneStart").val() +  $("#phoneMiddle").val().trim() +  $("#phoneEnd").val().trim();
			
			alert(phoneNumber);
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
			
		}); // - btnsignup
		
	}); // - doc

</script>
