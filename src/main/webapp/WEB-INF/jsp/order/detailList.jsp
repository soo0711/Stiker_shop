<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<div class="d-flex justify-content-center">
		<div class="col-7">
			<c:forEach items="${orderCardList.product }" var="product" varStatus="status">
				<hr>
				<div class="mx-2 d-flex justify-content-between">
					<div class="d-flex">
						<a href="/product/detail?productId=${product.id }" class="text-dark">
							<img src="${orderCardList.productImage[status.index ][0].imagePath}" id="plus" width="100" height="100" alt="상품이미지">
						</a>
						<div class="ml-3 mb-2">
							<a href="/product/detail?productId=${product.id }" class="text-dark">
								<div>${product.name } </div>
							</a>
							<div><small class="text-secondary">주문수량: ${orderCardList.orderProduct[0].count}개</small></div>
							<div><fmt:formatNumber type="number" value="${orderCardList.orderProduct[0].count *  product.price}"/> 원</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<hr>
		</div>
	</div>
</div>