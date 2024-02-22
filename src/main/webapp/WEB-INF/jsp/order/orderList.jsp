<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<div class="d-flex justify-content-center">
		<div class="col-7">
			<c:if>
			<c:forEach items="${orderCards}" var="orderCard">
			<hr>
			<div class="mx-2 d-flex justify-content-between">
				<div class="d-flex">
					<a href="/product/detail?productId=${orderCard.product.id }" class="text-dark">
						<img src="/static/img/home_img2.jpg" id="plus" width="100" height="100" alt="상품이미지">
					</a>
					<div class="ml-3 mb-2">
						<a href="/product/detail?productId=${orderCard.product.id }" class="text-dark">
							<div>${orderCard.product.name }</div>
						</a>
						<small type="text" class="buyCount text-center text-secondary">주문수량: ${count }개</small>
						<div>${orderCard.product.price} 원</div>
					</div>
				</div>
			</div>
			<hr>
			</c:forEach>
			</c:if>
		</div>
	</div>
	<div class=" d-flex justify-content-center">	
		<div class="col-7 d-flex justify-content-between">
			<div>
				<div>받으시는 분${orderCards}</div>
				<input type="text" class="form-control col-3 my-2" id="name">
				<div>주소</div>
				<div class="d-flex align-items-center">
					<input type="text" class="form-control col-3 my-2" id="postNumber" placeholder="우편번호">
					<a href="#" class="ml-2">우편번호 검색</a>
				</div>
				<input type="text" class="form-control col-8" id="address1" placeholder="기본 주소">
				<input type="text" class="form-control col-8 my-2" id="address2" placeholder="나머지 주소">
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
					<select class="form-control col-3" id="domain">
					  <option selected>naver.com</option>
					  <option>gmail.com</option>
					  <option>daum.net</option>
					  <option>kakao.com</option>
					  <option>직접 입력</option>
					</select> 
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
						<div class="font-weight-bold d-flex align-items-center justify-content-between">
							<div class="ml-2">주문 금액 </div>
							<div class="mr-2"> 8500원 </div>
						</div>
						<div class="mt-3 font-weight-bold d-flex align-items-center justify-content-between">
							<small class="text-secondary ml-4">상품 금액 </small>
							<small class="text-secondary mr-2"> 5500원 </small>
						</div>
						<div class="font-weight-bold d-flex align-items-center justify-content-between">
							<small class="text-secondary ml-4">배송비 </small>
							<small class="text-secondary mr-2"> + 3000원 </small>
						</div>
					</div>
				<hr>
				<div class="d-flex align-items-center justify-content-between">
					<h5 class="font-weight-bold ml-2">결제 최종액 </h5>
					<h5 class="font-weight-bold mr-2"> 8500원</h5>
				</div>
				<button class="btn btn-secondary btn-block my-4">결제하기</button>
				<!-- 비회원이면 안보이게 하기-->
				<div> 
					<input type="checkbox" id="remember" value="remember">
					<label for="remember"><small>결제수단과 입력정보를 다음에도 사용</small></label>
				</div>
			</div>
		</div> 
	</div>
</div>
