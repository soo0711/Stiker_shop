<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<div class="d-flex justify-content-center align-items-center">
	<div class="col-4">
		<h2 class="my-4 text-center">프로필 수정</h2>
		<div>
			<span>이름</span>
			<input type="text" class="form-control my-2" id="name" name="name" value="${user.name }">
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
				<input type="text" class="form-control my-2" id="phoneMiddle" data-phone-start="${start }" value="${middle }">
				<span class="mx-2"> - </span>
				<input type="text" class="form-control my-2" id="phoneEnd" value="${end }">
			</div>
		</div>
		<div>
			<span>이메일</span>
			<input type="text" class="form-control my-2" id="email" name="email" value="${user.email }">
		</div>
		<div>
			<span>주소</span>
			<div class="d-flex align-items-center">
				<input type="text" class="form-control col-5 my-2" id="postcode" placeholder="우편번호" value="${user.postcode }">
				 <input type="button" class="btn btn-light ml-2" onclick="execDaumPostcode()" value="우편번호 찾기">
			</div>
			<input type="text" class="form-control" id="address" placeholder="주소" value="${user.address }">
			<input type="text" class="form-control my-2" id="detailAddress" placeholder="상세주소" value="${user.detailAddress }">
		</div>
		<div>
			<span>생년월일</span>
			<input type="text" class="form-control my-2" id="birth" name="birth" placeholder="ex) 19990101" value="${user.birth }">
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
			<input type="text" class="form-control my-2" id="refundAccount" name="refundAccount" data-refund="${user.refundBank }" value="${user.refundAccount }">
		</div>
		
		<button type="submit" class="btn btn-secondary float-right my-3" id="btnUpdate">업데이트 하기</button>
	</div>
</div>
<a href="#" class="ml-5" data-toggle="modal" data-target="#modalDelete">회원 탈퇴</a>


<!-- Modal -->
<!-- 회원 탈퇴 -->
<div class="modal fade" id="modalDelete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<%-- 
		modal-sm: 작은 모달창
		modal-dialog-centerd: 수직 기준 가운데 위치
	 --%>
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content text-center">
			<div class="py-3 border-bottom">
				<div>정말로 탈퇴하시겠습니까?</div>
			</div>
			<div class="my-3">
				<button class="btn btn-light" data-dismiss="modal">취소하기</button>
				<a href="#" class="btn btn-secondary" id="btnDelete">탈퇴하기</a>
			</div>
		</div>
	</div>
</div>

<script>

// 우편번호 API
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                //document.getElementById("extraAddress").value = extraAddr;
            
            } else {
                //document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
	        }
	    }).open();
	}
	
	$(document).ready(function() {
		let start = $("#phoneMiddle").data("phone-start");
		let phoneStart = $("#phoneStart").val(start);
		let bank = $("#refundAccount").data("refund");
		if (bank != ""){
			let refundBank = $("#refundBank").val(bank);
		}
		
		$("#btnUpdate").on("click", function(){
			
			// vaildation check
			let name = $("#name").val().trim();
			let password = $("#password").val();
			let passwordConfirm = $("#passwordConfirm").val();
			let phoneStart = $("#phoneStart").val();
			let phoneMiddle = $("#phoneMiddle").val().trim();
			let phoneEnd = $("#phoneEnd").val().trim();
			let phoneNumber = phoneStart + phoneMiddle + phoneEnd;
			let postcode = $("#postcode").val().trim();
			let address = $("#address").val().trim();
			let detailAddress = $("#detailAddress").val().trim();
			let email = $("#email").val().trim();
			let birth = $("#birth").val().trim();
			let refundBank = $("#refundBank").val();
			let refundAccount = $("#refundAccount").val().trim();

			if (!name){
				alert("이름을 입력해주세요.");
				return false;
			}
			
			if (password != passwordConfirm){
				alert("비밀번호가 일치하지 않습니다.");
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
			
			if (postcode || address || detailAddress){
				if(!address || !detailAddress || ! postcode){
					alert("나머지 주소를 입력해주세요.");
					return false;
				}
			}
			
			if (refundBank != "-- 은행 선택 --" || refundAccount){
				if(!refundAccount || (refundBank == "-- 은행 선택 --")){
					alert("은행 계좌를 입력해주세요.");
					return false;
				}
			}

			
			$.ajax({
				type: "POST"
				, url: "/user/update"
				, data: {"password": password, "name": name, "phoneNumber" : phoneNumber, "email": email, "birth" : birth,
					"postcode" : postcode, "address" : address, "detailAddress" : detailAddress, "refundBank" : refundBank, "refundAccount" : refundAccount}
				
				, success: function(data) {
					if (data.code == 200){
						alert("프로필 수정 완료!")
						location.reload();
					}
				}
				
				, error: function(request, stauts, error) {
					alert("프로필 수정에 실패했습니다. 관리자에게 문의주세요.");
				}
			}); // - btn ajax
		});
		
		$("#btnDelete").on("click", function() {
			// alert("탈퇴");
			
			$.ajax({
				type: "POST"
				, url: "/user/delete"

				, success: function(data) {
					if (data.code == 200){
						alert("탈퇴 되었습니다.")
						location.href="/home-view"
					}
				}
				
				, error: function(request, stauts, error) {
					alert("회원 탈퇴에 실패했습니다. 관리자에게 문의주세요.");
				}
			}); // - btn ajax
		});
	});
</script>