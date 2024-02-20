<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cart-size d-flex justify-content-center">
	<div class="col-7">
	<c:forEach items="${carts }" var="cart">
		<div>
			<hr>
			<div class="mx-2 d-flex justify-content-between">
				<div class="d-flex">
					<img src="${cart.menuCard.productImage[0].imagePath }" id="plus" width="100" height="100" alt="상품이미지">
					<div class="ml-3 mb-2">
						<div>${cart.menuCard.product.name }</div>
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