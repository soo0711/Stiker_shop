<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<div>
	<div class="d-flex justify-content-center">
		<div class="col-7">
			<c:set var="total" value="0" />
			<c:forEach items="${cartOrderCard }" var="cartOrder" varStatus="status">
			<c:set value="${total + cartOrder.product.price * cartOrder.cart.count}" var="total" />
				<hr>
				<div class="mx-2 d-flex justify-content-between">
					<div class="d-flex">
						<a href="/product/detail?productId=${cartOrder.product.id }" class="text-dark">
							<img src="${cartOrder.productImage[0].imagePath }" id="plus" width="100" height="100" alt="상품이미지">
						</a>
						<div class="ml-3 mb-2">
							<a href="/product/detail?productId=${cartOrder.product.id }" class="text-dark">
								<input type="hidden" class="productList" value="${cartOrder.product.id }">
								<div>${cartOrder.product.name }</div>
							</a>
							<c:if test="${empty cartOrder.cart.count }">
								<c:set value="${total + cartOrder.product.price * count}" var="total" />
								<input type="hidden" class="countList" value="${count }">
								<small class="buyCount text-center text-secondary" id="count" data-count="${count }">주문수량: ${count } 개</small>
							</c:if>
							<c:if test="${not empty cartOrder.cart.count }">
								<input type="hidden" class="countList" value="${cartOrder.cart.count }">
								<small class="buyCount text-center text-secondary">주문수량: ${cartOrder.cart.count } 개</small>
							</c:if>
							<div><fmt:formatNumber type="number" value="${cartOrder.product.price }"/> 원</div>
						</div>
					</div>
				</div>
				<hr>
				</c:forEach>
		</div>
	</div>
	<div class=" d-flex justify-content-center">	
		<div class="col-7 d-flex justify-content-between">
			<div>
				<div>주문자 명</div>
				<input type="text" class="form-control col-3 my-2" id="name">
				<div>주소</div>
				<div class="d-flex align-items-center">
					<input type="text" class="form-control col-3 my-2" id="postcode" placeholder="우편번호">
					 <input type="button" class="btn btn-light ml-2" onclick="execDaumPostcode()" value="우편번호 찾기">
				</div>
				<input type="text" class="form-control col-8" id="address" placeholder="주소">
				<input type="text" class="form-control col-8 my-2" id="detailAddress" placeholder="상세주소">
				<div>휴대전화</div>
				<div class="d-flex align-items-center">
					<select class="form-control col-2" id="phoneStart">
					  <option selected>010</option>
					  <option>016</option>
					  <option>017</option>
					  <option>018</option>
					</select>
					<span class="mx-2"> - </span>
					<input type="text" class="form-control my-2 col-2" id="phoneMiddle">
					<span class="mx-2"> - </span>
					<input type="text" class="form-control my-2 col-2" id="phoneEnd">
				</div>
				<div>이메일</div>
				<div class="d-flex align-items-center">
					<input type="text" class="form-control my-2 col-3" id="emailId">
					<span class="mx-2"> @ </span>
					<select class="form-control col-3" id="domain1">
					  <option selected>naver.com</option>
					  <option>gmail.com</option>
					  <option>daum.net</option>
					  <option>kakao.com</option>
					  <option>직접 입력</option>
					</select> 
					<input type="text" class="form-control my-2 col-3 d-none" id="domain2">
				</div>
				<div>배송 메시지</div>
				<textarea class="form-control my-2 col-8" rows="4"></textarea>
				<hr>
				<div>결제 수단</div>
				<div class="my-2 d-flex align-items-center">
					<div>
						<input type="radio" id="card" value="card" name="orderPay">
						<label for="card"><small>카드 결제</small></label>
					</div>
					<div class="mx-2">
						<input type="radio" id="now" value="now" name="orderPay">
						<label for="now"><small>실시간 계좌이체</small></label>
					</div>
					<div>
						<input type="radio" id="input" value="input" name="orderPay">
						<label for="input"><small>무통장 입금</small></label>
					</div>
				</div>
			</div>
			<div class="col-5">
				<hr>
					<div class="p-3">
						<div class="font-weight-bold d-flex align-items-center justify-content-between mb-3">
							<div class="ml-2">주문 금액 </div>
							<div class="mr-2"><fmt:formatNumber type="number" value="${total }"/> 원</div>
						</div>
						<div>
							<c:forEach items="${cartOrderCard }" var="cartOrder" varStatus="status">
								<c:if test="${empty cartOrder.cart.count }">
								<div class="d-flex font-weight-bold align-items-center justify-content-between">
									<small class="text-secondary ml-4">상품 금액 </small>
									<small name="product-price" class="text-secondary mr-2"><fmt:formatNumber type="number" value="${total }"/> 원</small>
								</div>
								</c:if>
								<c:if test="${not empty cartOrder.cart.count }">
								<div class="d-flex font-weight-bold align-items-center justify-content-between">
									<small class="text-secondary ml-4">상품 금액 </small>
									<small name="product-price" class="text-secondary mr-2"><fmt:formatNumber type="number" value="${cartOrder.product.price * cartOrder.cart.count}"/> 원</small>
								</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="font-weight-bold d-flex align-items-center justify-content-between mt-2">
							<small class="text-secondary ml-4">배송비 </small>
							<small class="text-secondary mr-2"> + 3,000 원 </small>
						</div>
					</div>
				<hr>
				<div class="d-flex align-items-center justify-content-between">
					<h5 class="font-weight-bold ml-2">결제 최종액 </h5>
					<h5 class="font-weight-bold mr-2 total" data-total-price="${total + 3000}"><fmt:formatNumber type="number" value="${total + 3000}"/> 원</h5>
				</div>

				<input type="hidden" value="${cartOrderCard }${orderCard}">
				<button type="submit" class="btn btn-secondary btn-block my-4" onclick="requestPay()"id="payButton">결제하기</button>
				<!-- 비회원이면 안보이게 하기-->
				<div> 
					<input type="checkbox" id="remember" value="remember">
					<label for="remember"><small>결제수단과 입력정보를 다음에도 사용</small></label>
				</div>
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
	
	// 결제 API
	function requestPay() {
		let name = $("#name").val().trim();
		let postcode = $("#postcode").val().trim();
		let address = $("#address").val().trim();
		let detailAddress = $("#detailAddress").val().trim();
		let totalAddress = address + " " + detailAddress;
		let phoneStart = $("#phoneStart").val();
		let phoneMiddle = $("#phoneMiddle").val().trim();
		let phoneEnd = $("#phoneEnd").val().trim();
		let phoneNumber = phoneStart + phoneMiddle + phoneEnd;
		let emailId = $("#emailId").val().trim();
		let domain1 = $("#domain1").val();
		let domain2 = $("#domain2").val().trim();
		let email = emailId + "@" + domain1;
		let deilverMessage = $("deilverMessage").val();
		let payMethod = $('input[name="orderPay"]:checked').val();
		let total = $(".total").data("total-price");
		let productList = document.getElementsByClassName("productList");
		let productId = [];
		let countList = document.getElementsByClassName("countList");
		let count = [];
		for (let i = 0; i < productList.length; i++){
			productId.push(productList[i].value);
		}
		for (let i = 0; i < countList.length; i++){
			count.push(countList[i].value);
		}
		
		if (!name){
			alert("주문자명을 입력해주세요.");
			return false;
		}
		
		if (!postcode){
			alert("우편번호를 입력해주세요.");
			return false;
		}
		
		if (!address || !detailAddress){
			alert("주소를 입력해주세요.");
			return false;
		}
		
		if(!phoneMiddle || !phoneEnd){
			alert("전화번호를 입력해주세요.");
			return false;
		}
		
		if (!emailId){
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		if (domain1 == "직접 입력"){
			if (!domain2){
				alert("이메일을 입력해주세요.");
				return false;
			}
			email = emailId + "@" + domain2;
		}
		
		if (!payMethod){
			alert("결제수단을 선택해주세요.");
			return false;
		}
	
		//가맹점 식별코드
		IMP.init('imp34871301');
		IMP.request_pay({
		    pg: 'html5_inicis',
		    pay_method: 'card',
		    merchant_uid: 'merchant_' + new Date().getTime(),
		    name: name , //결제창에서 보여질 이름
		    amount: total, //실제 결제되는 가격
		    buyer_email: email,
		    buyer_name: name,
		    buyer_tel: phoneNumber,
		    buyer_addr: totalAddress,
		    buyer_postcode: postcode
		}, function(rsp) {
			if (rsp.success) {
				var msg = ' 결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;
       			/*
				$.ajax({
					url: "/order/order-list"
					, type: "POST"
					, data: {"name" : name, "postcode" : postcode, "totalAddress" : totalAddress, "phoneNumber" : phoneNumber
						, "email" : email, "deilverMessage" : deilverMessage, "payMethod" : payMethod, "total" : total, "productId" : productId, "count" : count}
					, dataType: "json"
					, traditional: true
					, success: function(data){
						if (data.code == 200){
							alert("성공");
						} else {
							alert(data.error_message);
						}
					}
					, error: function(request, status, error){
						alert("결제에 실패했습니다. 관리자에게 문의주세요.")
					}
				});
       			*/
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += ' 에러내용 : ' + rsp.error_msg;
				alert(msg);
			}
			$.ajax({
				url: "/order/order-list"
				, type: "POST"
				, data: {"name" : name, "postcode" : postcode, "totalAddress" : totalAddress, "phoneNumber" : phoneNumber
					, "email" : email, "deilverMessage" : deilverMessage, "payMethod" : payMethod, "total" : total, "productId" : productId, "count" : count}
				, dataType: "json"
				, traditional: true
				, success: function(data){
					if (data.code == 200){
						alert("성공");
					} else {
						alert(data.error_message);
					}
				}
				, error: function(request, status, error){
					alert("결제에 실패했습니다. 관리자에게 문의주세요.")
				}
			});
		});
	}
	
	$(document).ready(function() {
		$("#domain1").on("change", function() {
			let domain = $("#domain1").val();
			if (domain == '직접 입력'){
				$("#domain2").removeClass("d-none");
				$("#domain1").addClass("d-none");
			};
		});
	});
</script>