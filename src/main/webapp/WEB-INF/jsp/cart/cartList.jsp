<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="cart-size d-flex justify-content-center">
	<div class="col-7">
	<c:forEach items="${carts }" var="cart">
		<div>
			<hr>
			<input type="checkbox" value="1">
			<div class="mx-2 d-flex justify-content-between">
				<div class="d-flex">
					<img src="${cart.menuCard.productImage[0].imagePath }" id="plus" width="100" height="100" alt="상품이미지">
					<div class="ml-3 mb-2">
						<div>${cart.menuCard.product.name }</div>
						<div class="d-flex align-items-center my-2">
							<input type="text" class="buyCount form-control col-2 text-center" value="${cart.count }">
							<img src="/static/img/plus.png" class="plusCart mx-2" width="25" height="25" alt="플러스 아이콘" data-product-id="${cart.menuCard.product.id }">
							<img src="/static/img/minus.png" class="minusCart" width="25" height="25" alt="마이너스 아이콘" data-product-id="${cart.menuCard.product.id }">
						</div>
						<div><fmt:formatNumber type="number" value="${cart.menuCard.product.price }"/> 원</div>
					</div>
				</div>
				<a href="#" class="cartDelete text-secondary" data-product-id="${cart.menuCard.product.id }">X</a>
			</div>
			<hr>
		</div>
	</c:forEach>
	<div class="d-flex justify-content-end mt-5">
		<button class="btn btn-dark" id="btnOrder">주문하기</button>
	</div>
	</div>
</div>

<script>

	$(document).ready(function () {
		
		$(".plusCart").on("click", function() {
			let buyCount = parseInt($(this).prev().val()) + 1;
			$(this).prev().val(buyCount);
			count = $(this).prev().val();
			let productId = $(this).data("product-id");

			$.ajax({
				url: "/cart/update"
				, type: "POST"
				, data: {"count" : count, "productId" : productId}
			
				, success: function(data){
					if (data.code == 200) {
						location.reload(true);
					} else {
						alert(code.error_message);
					}
				}
				
				, error: function(request, status, error) {
					alert("수량 수정에 실패했습니다. 관리자에게 문의 주세요.")
				}
			});
		});
		
		$(".minusCart").on("click", function(){
			// alert("minus");
			if ($(this).siblings(".buyCount").val() == 1){
				return false;
			}
			
			let buyCount = parseInt($(this).siblings(".buyCount").val()) - 1;
			$(this).siblings(".buyCount").val(buyCount);
			count = $(this).siblings(".buyCount").val()
			let productId = $(this).data("product-id");

			$.ajax({
				url: "/cart/update"
				, type: "POST"
				, data: {"count" : count, "productId" : productId}
			
				, success: function(data){
					if (data.code == 200) {
						location.reload(true);
					} else {
						alert(code.error_message);
					}
				}
				
				, error: function(request, status, error) {
					alert("수량 수정에 실패했습니다. 관리자에게 문의 주세요.")
				}
			});
			
		});
		
		$(".cartDelete").on("click", function(){
			// alert("삭제");
			let productId = $(this).data("product-id");
			
			$.ajax({
				url: "/cart/delete"
				, type: "POST"
				, data: {"productId": productId}
				
				, success: function(data){
					if (data.code == 200) {
						location.reload(true);
					} else {
						alert(code.error_message);
					}
				}
				
				, error: function(request, status, error) {
					alert("장바구니 물품 삭제에 실패했습니다. 관리자에게 문의 주세요.")
				}	
			});
		});
	});

</script>