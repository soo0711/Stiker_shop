<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center align-items-center">
	<div id="signIn">
		<h2 class="my-4 text-center">로그인</h2>
		<form method="post" action="/user/sign-in">
			<div>
				<span>ID</span>
				<input type="text" class="form-control my-2" id="loginId" name="loginId">
			</div>
			<div>
				<span>비밀번호</span>
				<input type="text" class="form-control my-2" id="password" name="password">
			</div>
			
			<button type="submit" class="btn btn-light float-right my-2" id="btnSignIn">로그인</button>
			
		</form>
	</div>
</div>

<script>


</script>