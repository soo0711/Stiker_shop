<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${fn:contains(viewName, 'manager') eq false}">
<ul class="nav nav-fill w-100">
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">베스트</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">NEW</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">전체</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">키링</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">스티커</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">메모</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">디지털 악세사리</a></li>
</ul>
</c:if>

<c:if test="${fn:contains(viewName, 'manager') eq true}">
<ul class="nav nav-fill w-100">
	<li class="nav-item"><a href="/manager/hukahuka-upload-view" class="nav-link text-nav-color font-weight-bold">등록</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">재고</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">배송현황</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">x</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">x</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">x</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">x</a></li>
</ul>
</c:if>
