<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center align-items-center">
	<div id="signIn">
		<h2 class="my-4 text-center">로그인</h2>
		<div>
			<span>ID</span>
			<input type="text" class="form-control my-2" id="loginId" name="loginId">
		</div>
		<div>
			<span>비밀번호</span>
			<input type="password" class="form-control my-2" id="password" name="password">
		</div>
		
		<button type="button" class="btn btn-light btn-block my-3" id="btnSignIn">로그인</button>
		
		<div class="d-flex justify-content-around">
			<small><a href="#" class="text-secondary">아이디 찾기</a></small>
			<small><a href="#" class="text-secondary">비밀번호 찾기</a></small>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#btnSignIn").on("click", function() {
			// alert("로그인");
			
			// vaildation
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			
			if (!loginId){
				alert("아이디를 입력하세요.");
				return false;
			}
			
			if (!password){
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			$.ajax({
				type: "POST"
				, url: "/user/sign-in"
				, data: {"loginId" : loginId, "password" : password}
			
				, success: function(data){
					if (data.code == 200){
						alert(data.userName + "님 환영합니다!")
						location.href = "/home-view"
					} else {
						alert(data.error_message);
					}
				}
				
				, error: function(request, status, error){
					alert("로그인에 실패했습니다. 관리자에게 문의 주세요.")
				}
			});
			
		});
	}); // - docu

</script>