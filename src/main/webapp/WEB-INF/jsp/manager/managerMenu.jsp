<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${userLoginId eq 'suuuu'}">
<div class="d-flex justify-content-center align-items-center">
	<div class="col-3">
		<h2 class="my-4 text-center">등록</h2>
		<div>
			<span>상품 이름</span>
			<input type="text" class="form-control my-2" id="name" name="name">
		</div>
		<div>
			<span>재고</span>
			<input type="text" class="form-control my-2" id="count" name="count">
		</div>
		<div>
			<span>상품 소개</span>
			<input type="text" class="form-control my-2" id="introduce" name="introduce">
		</div>
		<div>
			<span>상세 설명</span>
			<input type="text" class="form-control my-2" id="detail" name="detail">
		</div>
		<div>
			<span>카테고리</span>
			<div class="d-flex align-items-center my-2">
				<select class="form-control" id="category">
					<option selected>--선택--</option>
					<option>키링</option>
					<option>스티커</option>
					<option>메모</option>
					<option>디지털 악세사리</option>
				</select>
			</div>
		</div>
		<div>
			<div>상품 이미지</div>
				<input type="file" class="my-2" name="images" multiple="multiple" accept=".png, .jpg, .jpeg">
		</div>
	
		<button type="submit" class="btn btn-secondary btn-block my-2" id="btnUpload">등록</button>

	</div>
</div>
</c:if>