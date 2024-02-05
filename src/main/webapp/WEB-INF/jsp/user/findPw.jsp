<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center align-items-center">
	<div id="signIn">
		<h2 class="my-4 text-center">비밀번호 찾기</h2>
			<div>
				<span>이메일</span>
				<input type="text" class="form-control my-2" id="email" name="email">
			</div>
		
		<button type="button" class="btn btn-light btn-block my-3" id="btnFindPw">임시 비밀번호 보내기</button>
		
	</div>
</div>

<script>
	
	$(document).ready(function() {
		$("#btnFindPw").on("click", function() {
			// alert("비밀번호 찾기");
			
			// vaildation
			let email = $("#email").val().trim();
			
			if (!email){
				alert("이메일을 입력해주세요.");
				return false;
			}
			
			$.ajax({
				type: "POST"
				, url: "/user/find-pw"
				, data: {"email" : email}
				
				, success: function(data){
					if (data.code == 200){
						alert("이메일로 임시 비밀번호를 전송했습니다.");
						location.href="/user/sign-in-view";
					} else{
						alert(data.error_messgae);
						location.reload();
					}	
				}
				, error: function(request, status, error){
					alert("임시 비밀번호 발급에 실패했습니다. 관리자에게 문의 주세요.");
					location.reload();
				}
			}); // - ajax
			
		}); // - btn pw
	}); // - docu

</script>