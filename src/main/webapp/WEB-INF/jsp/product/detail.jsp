<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center my-3 content1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<img id ="productImg" src="${menuCard.productImage[0].imagePath }" data-img-path1="${menuCard.productImage[0].imagePath }" data-img-path2="${menuCard.productImage[1].imagePath }" data-img-path3="${menuCard.productImage[2].imagePath }" class="col-4 w-100" height="605" alt="${menuCard.product.name }">
	<div class="col-4 my-2">
		<h3 class="font-weight-bold text-center">${menuCard.product.name }</h3>
		<form method="POST" action="/order/order-view">
		<div class="d-flex justify-content-center my-3 align-items-center">
			<img src="/static/img/minus.png" id="minus" width="25" height="25" alt="마이너스 아이콘">
			<input type="text" id="buyCount" class="form-control col-1 text-center mx-2" value="1" name="count">
			<img src="/static/img/plus.png" id="plus" width="25" height="25" alt="플러스 아이콘">
		</div>
		<div class="my-4">
			<h5><img src="/static/img/heart_1.png" width="25" height="25" alt="아이콘"> 상품소개</h5>
			<div>${menuCard.product.introduce }</div>
		</div>
		<div class="my-4">
			<h5><img src="/static/img/heart_1.png" width="25" height="25" alt="아이콘"> 상세설명</h5>
			<div>${menuCard.product.detail }</div>
		</div>
		<div class="my-4">
			<h5><img src="/static/img/heart_1.png" width="25" height="25" alt="아이콘"> 교환 및 환불</h5>
			<div>상품이 '배송준비' 상태의 경우 이미 택배사로 송장이 넘어간 경우로 교환/환불 시 왕복 배송비가 청구될 수 있습니다.</div>
		</div>
		<div class="my-4">
			<h5><img src="/static/img/heart_1.png" width="25" height="25" alt="아이콘"> 배송안내</h5>
			<div>주문시부터 배송기간이 2-3일 소요됩니다.</div>
		</div>
		<div class="mt-5">
			<input type="hidden" name="productId" value="${menuCard.product.id }">
			<button class="btn btn-dark btn-block" id="btnOrder" data-product-id="${menuCard.product.id }">주문하기</button>
			<a href="#" class="btn btn-light btn-block my-3" id="btnCart" data-toggle="modal" data-target="#modalCart" data-product-id="${menuCard.product.id }">장바구니 담기</a>
			<c:if test="${isWish}">
				<a href="#" class="btn btn-light btn-block btnWish" data-product-id="${menuCard.product.id }">위시리스트 <img src="/static/img/islike.png" alt="빈 하트" width="15" height="15"> </a>
			</c:if>
			<c:if test="${!isWish}">
				<a href="#" class="btn btn-light btn-block btnWish text-center" data-product-id="${menuCard.product.id }">위시리스트 <img src="/static/img/islike2.png" alt="채워진 하트" width="15" height="15"> </a>
			</c:if>
		</div>
		</form>
	</div>
</div>

<!-- Modal -->
<!-- 장바구니 담기 -->
<div class="modal fade" id="modalCart" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<%-- 
		modal-sm: 작은 모달창
		modal-dialog-centerd: 수직 기준 가운데 위치
	 --%>
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content text-center">
			<div class="py-3 border-bottom">
				<div>장바구니에 담겼습니다. <br>
				장바구니로 이동하겠습니까?</div>
			</div>
			<div class="my-3">
				<button class="btn btn-light" data-dismiss="modal">취소하기</button>
				<a href="/cart/cart-list-view" class="btn btn-secondary" id="btnCartGo">장바구니로 이동</a>
			</div>
		</div>
	</div>
</div>

<script>

	$(document).ready(function () {
		
		$("#btnCart").on("click", function(){
			let productId = $("#btnCart").data("product-id");
			let buyCount = $("#buyCount").val();
			
			$.ajax({
				url: "/cart/cart-list"
				, type: "POST"
				, data: {"productId": productId, "buyCount" : buyCount}
			
				, success: function(data){
					if (data.code == 200){
						console.log("장바구니에 담았습니다.")
					}
					else {
						alert("로그인이 필요한 서비스입니다.");
						location.href="/user/sign-in-view"
					}
				}
				
				, error: function(request, status, error) {
					alert("장바구니에 담지 못했습니다. 관리자에게 문의주세요.")
					location.reload();
				}
			});
			
		});
		
		$(".btnWish").on("click", function(){
			// alert("btnWish");
			let productId = $(this).data("product-id");
			
			$.ajax({
				url: "/wish/" + productId
			
				, success: function(data){
					if (data.code == 200){
						// 토글만 하는 곳
						location.reload(true);
					} else {
						alert("로그인이 필요한 서비스입니다.");
						location.href="/user/sign-in-view"
					}
				}
				, error: function(request, status, error){
					alert("위시리스트 담기에 실패했습니다. 관리자에게 문의해주세요.");
				}
			});
		});
		
		$("#plus").on("click", function(){
			// alert("plus");
			let buyCount = parseInt($("#buyCount").val()) + 1;
			$("#buyCount").val(buyCount);
		});
		
		$("#minus").on("click", function(){
			// alert("minus");
			if ($("#buyCount").val() == 1){
				return false;
			}
			
			let buyCount = parseInt($("#buyCount").val()) - 1;
			$("#buyCount").val(buyCount);
			
		});
		
		
		let img1 = $("#productImg").data("img-path1");
		let img2 = $("#productImg").data("img-path2");
		let img3 = $("#productImg").data("img-path3");
		let bannerSrc = [];

		if (!img2){
			bannerSrc = [img1];
		}
		
		if (img2 && !img3){
			bannerSrc = [img1, img2];
		}
		
		if (img1 && img2 && img3){
			bannerSrc = [img1, img2, img3];
		}
		
		let currentIndex = 0;
		
		
		setInterval(function() {
			$("#productImg").attr("src", bannerSrc[currentIndex]);
			currentIndex++;
			
			if(currentIndex > bannerSrc.length){
				currentIndex = 0;
			}
			
		}, 3000); // 3초마다 변환
		
	});

</script>