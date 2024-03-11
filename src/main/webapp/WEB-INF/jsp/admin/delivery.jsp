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
				<c:forEach items="${orders }" var="order" varStatus="status">
					<tr>
						<td>${status.count }</td>
						<td>주문번호 ${order.id } - ${order.name}</td>
						<td class="col-3">
							<input type="text" class="count form-control" value="${order.status }">
						</td>
						<td>
							<a href="#" class="btnModify btn btn-light" data-order-id="${order.id }">수정</a>
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
		$(document).on("click", ".btnModify", function() {
			// alert("수정");
			let orderId = $(this).data("order-id");
			let status = $(this).parent().prev().children().val();
			
			$.ajax({
				url: "/admin/order-update"
				, type: "POST"
				, data: {"orderId" : orderId, "status" : status}
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
			});
			
		}); // btn modify
		
	}); // - doc
</script>