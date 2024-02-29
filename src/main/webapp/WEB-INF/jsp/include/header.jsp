<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="new-color text-center header1">
	<small><a href="/menu-view?menu=new" class="text-white font-weight-bold text-decoration-none">NEW!! 스티커</a></small>
</div>
<div class="d-flex header2">
	<div class="col-4"></div>
	<div class="d-flex justify-content-center align-items-center col-4">
		<div class="display-4"><a href="/home-view" class="text-dark text-decoration-none">후카후카</a></div>
	</div>
	<div class="d-flex justify-content-end align-items-top header2 col-4">
		<c:if test="${fn:contains(viewName, 'manager') eq false}">
			<c:if test="${empty userName}">
				<a href="/user/sign-in-view" class="text-dark m-3">로그인</a>
				<a href="/user/sign-up-view" class="text-dark my-3">회원가입</a>
			</c:if>
			<c:if test="${not empty userName}">
				<div class="text-center my-3">
					<span class="text-dark mx-3">${userName }님</span>
					<a href="/user/sign-out" class="text-dark my-3">로그아웃</a>
					<div>
						<a href="/order/list-view" class="text-dark"><small>마이페이지</small></a>
						<a href="/cart/cart-list-view" class="text-dark mx-3"><small>장바구니</small></a>
						<a href="/wish/wish-list-view" class="text-dark"><small>위시리스트</small></a>
					</div>
				</div>
			</c:if>
		</c:if>
		<c:if test="${fn:contains(viewName, 'manager') eq true}">
			<span class="text-dark m-3">관리자 페이지</span>
			<c:if test="${userLoginId ne 'suuuu'}">
				<a href="/user/sign-in-view" class="text-dark my-3">로그인</a>
			</c:if>
		</c:if>
	</div>
</div>

