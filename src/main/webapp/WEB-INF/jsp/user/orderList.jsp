<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="cart-size d-flex justify-content-center">
	<div class="col-7">
			<div>
				<div class="d-flex justify-content-start">
					<ul class="nav nav-fill mt-3">
						<li class="nav-item"><a href=# class="nav-link font-weight-bold text-dark text-start">주문처리 현황</a></li>
						<li class="nav-item"><a href=# class="nav-link font-weight-bold text-dark text-start">입금 전</a></li>
						<li class="nav-item"><a href=# class="nav-link font-weight-bold text-dark text-start">배송 중</a></li>
						<li class="nav-item"><a href=# class="nav-link font-weight-bold text-dark text-start">배송 완료</a></li>
					</ul>
				</div>
				<hr>
				<div class="mx-2 d-flex justify-content-between">
					<div class="d-flex">
						<a href="/product/detail?productId=${cart.menuCard.product.id }" class="text-dark">
							<img src="/static/img/home_img.png" id="plus" width="100" height="100" alt="상품이미지">
						</a>
						<div class="ml-3 mb-2">
							<a href="/product/detail?productId=${cart.menuCard.product.id }" class="text-dark">
								<div>${cart.menuCard.product.name }d</div>
							</a>
							<div class="d-flex align-items-center my-2">
								<small class="buyCount text-center text-secondary">주문수량: 개</small>
							</div>
							<div> 원</div>
						</div>
					</div>
				</div>
				<hr>
			</div>
		<div class="d-flex justify-content-end mt-5">
			<button class="btn btn-dark" id="btnProfile">프로필 수정</button>
		</div>
	</div>
</div>