<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div class="d-flex justify-content-center">
		<div class="col-7">
			<hr>
			<div class="mx-2 d-flex justify-content-between">
				<div class="d-flex">
					<a href="/product/detail?productId=${cart.menuCard.product.id }" class="text-dark">
						<img src="/static/img/home_img2.jpg" id="plus" width="100" height="100" alt="상품이미지">
					</a>
					<div class="ml-3 mb-2">
						<a href="/product/detail?productId=${cart.menuCard.product.id }" class="text-dark">
							<div>엉망진창</div>
						</a>
						<small type="text" class="buyCount text-center text-secondary">주문수량: 1개</small>
						<div>1000 원</div>
					</div>
				</div>
			</div>
			<hr>
		</div>
	</div>
	<div class="col-7 d-flex justify-content-center">	
		<div>
			<h5>받으시는 분</h5>
		</div>
		<div></div>
	</div>
</div>