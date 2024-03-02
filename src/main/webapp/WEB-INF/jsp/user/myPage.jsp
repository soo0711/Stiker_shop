<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center align-items-center">
	<div class="col-4">
		<h2 class="my-4 text-center">프로필 수정</h2>
		<form method="post" action="/user/sign-up" id="loginForm">
			<div>
				<span>이름</span>
				<input type="text" class="form-control my-2" id="name" name="name">
			</div>
			<div>
				<span>비밀번호</span>
				<input type="password" class="form-control my-2" id="password" name="password">
			</div>
			<div>
				<span>비밀번호 확인</span>
				<input type="password" class="form-control my-2" id="passwordConfirm" name="passwordConfirm">
			</div>
			<div>
				<span>전화번호</span>
				<div class="d-flex align-items-center">
					<select class="form-control" id="phoneStart">
					  <option selected>010</option>
					  <option>016</option>
					  <option>017</option>
					  <option>018</option>
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
			<div>
				<span>주소</span>
				<div class="d-flex align-items-center">
					<input type="text" class="form-control col-5 my-2" id="postcode" placeholder="우편번호">
					 <input type="button" class="btn btn-light ml-2" onclick="execDaumPostcode()" value="우편번호 찾기">
				</div>
				<input type="text" class="form-control" id="address" placeholder="주소">
				<input type="text" class="form-control my-2" id="detailAddress" placeholder="상세주소">
			</div>
			<div>
				<span>생년월일</span>
				<input type="text" class="form-control my-2" id="birth" name="birth" placeholder="ex) 19990101">
			</div>
			<div>
				<span>환불 계좌 은행</span>
				<div class="d-flex align-items-center my-2">
					<select class="form-control" id="refundBank">
					  <option selected>-- 은행 선택 --</option>
					  <option>우리</option>
					  <option>농협</option>
					  <option>국민</option>
					  <option>신한</option>
					  <option>기업</option>
					</select>
				</div>
			</div>
			<div>
				<span>환불 계좌 번호</span>
				<input type="text" class="form-control my-2" id="refundAccount" name="refundAccount">
			</div>
			
			<button type="submit" class="btn btn-secondary float-right my-3" id="btnUpdate">업데이트 하기</button>
			
		</form>
	</div>
</div>
<a href="#" class="ml-5">회원 탈퇴</a>

<script>
	
</script>