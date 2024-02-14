<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${userLoginId eq 'suuuu'}">
<div class="d-flex justify-content-center align-items-center content1 w-100">
	<div class="col-7">
		<h2 class="my-4 text-center">재고</h2>
		<table class="table text-center">
			<thead class="bg-light">
				<tr>
					<th>No.</th>
					<th>이름</th>
					<th>재고</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products }" var="product" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>${product.name }</td>
						<td>
							<input type="text" class="count form-control" value=${product.count }>
						</td>
						<td>
							<a href="#" class="btnModify btn btn-light" data-product-name="${product.name }">수정</a>
						</td>
						<td>
							<a href="#" class="btnDelete btn btn-light" data-product-name="${product.id }">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</c:if>

<script>
$(document).ready(function() {
	$(document).on("click", ".btnModify", function(e) {
		e.preventDefault();
		// alert("수정");
		let count = $(this).parent().prev().children().val();
		let name = $(this).data("product-name");
		
		$.ajax({
			type: "POST"
			,url: "/manager/update"
			, data: {"count" : count, "name" : name}
		
			, success: function(data){
				if (data.code == 200){
					alert("수정 완료!");
					location.reload();
				} else {
					alert(data.error_message);
				}
			}
			, error: function(request, status, error){
				alert("수정에 실패했습니다. 관리자에게 문의주세요.");
			}
		}); // modify ajax
		
	}); // btn Modify
	
	$(document).on("click", ".btnDelete", function(e) {
		e.preventDefault();
		// alert("삭제");
		
		let name = $(this).data("product-id");
		// console.log(name);
		
		$.ajax({
			type: "POST"
			,url: "/manager/delete"
			, data: {"id" : id}
		
			, success: function(data){
				if (data.code == 200){
					alert("삭제 완료!");
					location.reload();
				} else {
					alert(data.error_message);
				}
			}
			, error: function(request, status, error){
				alert("삭제에 실패했습니다. 관리자에게 문의주세요.");
			}
		}); // delete ajax
		
	}); // delete delete
	
}); // - doc
</script>