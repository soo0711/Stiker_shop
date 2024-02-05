<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center align-items-center">
	<div id="signIn">
		<h2 class="my-4 text-center">아이디 찾기</h2>
		<div>
			<span>이름</span>
			<input type="text" class="form-control my-2" id="name" name="name">
		</div>
		<div>
				<span>전화번호</span>
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
				<span>이메일</span>
				<input type="text" class="form-control my-2" id="email" name="email">
			</div>
		
		<button type="button" class="btn btn-light btn-block my-3" id="btnFindId">아이디 찾기</button>
		
	</div>
</div>

<script>

	$(document).ready(function() {
		$("#btnFindId").on("click", function() {
			// alert("아이디 찾기");
			
			// vaildation check
			let name = $("#name").val().trim();
			let phoneStart = $("#phoneStart").val();
			let phoneMiddle = $("#phoneMiddle").val().trim();
			let phoneEnd = $("#phoneEnd").val().trim();
			let phoneNumber = phoneStart + phoneMiddle + phoneEnd;
			let email = $("#email").val().trim();
			
			if (!name){
				alert("이름을 입력해주세요.");
				return false;
			}
			
			if (!phoneMiddle || !phoneEnd){
				alert("전화번호를 입력해주세요.")
				return false;
			}
			
			if (!email){
				alert("이메일을 입력해주세요.")
				return false;
			}
			
			$.ajax({
				type: "POST"
				, url: "/user/find-id"
				, data: {"name": name, "phoneNumber" : phoneNumber, "email" : email}
			
				, success: function(data){
					if (data.code == 200){
						alert("아이디: " + data.userLoginId);
						location.href="/user/sign-in-view"
					} else {
						alert(data.error_message);
						location.reload(true);
					}
				}
				, error: function(request, status, error){
					alert("아이디를 찾을 수 없습니다. 관리자에게 문의해주세요.")
					location.reload(true);
				}
			}); // - ajax
			
		}); // - btn
	}); // - docu

</script>