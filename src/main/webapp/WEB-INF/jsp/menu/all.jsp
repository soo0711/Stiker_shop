<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex content1 flex-wrap productList-size content1">
	<c:forEach items="${menuCard }" var="menuCard">
		<c:if test="${menuCard.product.count ne 0}">
		<a href="/product/detail?productId=${menuCard.product.id }" class="text-dark">
			<div class="m-4 product-size">
				<img src="${menuCard.productImage[0].imagePath }" width="250" height="250" alt="${menuCard.product.name }">
				<div class="mt-2"><fmt:formatNumber type="number" value="${menuCard.product.price }"/> 원</div>
				<div>${menuCard.product.name }</div>
			</div>
		</a>
		</c:if>
	</c:forEach>
</div>
