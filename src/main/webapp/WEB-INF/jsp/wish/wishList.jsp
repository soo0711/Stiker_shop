<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="cart-size d-flex justify-content-center">
	<div class="col-7">
	<c:forEach items="${wishs }" var="wish">
		<div>
			<hr>
			<a href="/product/detail?productId=${wish.menuCard.product.id }" class="text-dark">
			<div class="mx-2 d-flex justify-content-between">
				<div class="d-flex">
					<img src="${wish.menuCard.productImage[0].imagePath }" id="plus" width="100" height="100" alt="상품이미지">
					<div class="ml-3 mb-2">
						<div>${wish.menuCard.product.name }</div>
						<div><fmt:formatNumber type="number" value="${wish.menuCard.product.price }"/> 원</div>
					</div>
				</div>
			</div>
			</a>
			<hr>
		</div>
	</c:forEach>
	<div class="d-flex justify-content-end mt-5">
		<a href="/cart/cart-list-view" class="btn btn-light mx-3" >장바구니 가기</a>
		<button class="btn btn-dark" id="btnOrder">주문하기</button>
	</div>
	</div>
</div>