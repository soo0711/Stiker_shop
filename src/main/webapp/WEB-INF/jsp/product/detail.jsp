<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center my-3">
	<img src="${menuCard.productImage[0].imagePath }" class="col-4 w-100" alt="${menuCard.product.name }">
	<div class="col-4 my-2">
		<h3 class="font-weight-bold">${menuCard.product.name }</h3>
		<div class="my-4">
			<h5><img src="/static/img/heart_icon.png" width="30"> 상품 소개</h5>
			<div>${menuCard.product.introduce }</div>
		</div>
		<div></div>
	</div>
</div>