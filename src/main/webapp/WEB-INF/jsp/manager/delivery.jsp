<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${userLoginId eq 'suuuu'}">
<div class="d-flex justify-content-center align-items-center content1 w-100">
	<div class="col-6">
		<h2 class="my-4 text-center">배송현황</h2>
		<table class="table text-center">
			<thead class="bg-light">
				<tr>
					<th>No.</th>
					<th>주문자</th>
					<th>배송현황</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>1</td>
						<td>수현-데일리키링</td>
						<td class="col-3">
							<input type="text" class="count form-control" value="배송 준비 중">
						</td>
						<td>
							<a href="#" class="btnModify btn btn-light" data-product-name="${product.name }">수정</a>
						</td>
						<td>
							<a href="#" class="btnDelete btn btn-light" data-product-id="${product.id }">삭제</a>
						</td>
					</tr>
			</tbody>
		</table>
	</div>
</div>
</c:if>

<script>
	$(document).ready(function() {
		$(document).on("click", ".btnModify", function() {
			// alert("수정");
			
		}); // btn modify
		
		$(document).on("click", ".btnDelete", function() {
			// alert("삭제");
			
		}); // btn delete
	}); // - doc
</script>