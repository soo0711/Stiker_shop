<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="cart-size d-flex justify-content-center" id="listContent">
	<div class="col-7">
			<div>
				<div class="d-flex justify-content-start">
					<ul class="nav nav-fill mt-3">
						<li class="nav-item"><a href=# class="nav-link font-weight-bold text-dark text-start" id="allOrder">주문처리 현황</a></li>
						<li class="nav-item"><a href=# class="nav-link font-weight-bold text-dark text-start" id="before">입금 전</a></li>
						<li class="nav-item"><a href=# class="nav-link font-weight-bold text-dark text-start" id="ing">배송 중</a></li>
						<li class="nav-item"><a href=# class="nav-link font-weight-bold text-dark text-start" id="done">배송 완료</a></li>
					</ul>
				</div>
				
				<div class="mx-2"><small>무통장 입금: 농협은행 111-0000-0000-00 (스티커샵)</small></div>

				<table class="table mt-3 text-center">
					<thead class="bg-secondary text-light">
						<tr>
							<th>No.</th>
							<th>주문번호</th>
							<th>주문자</th>
							<th>전화번호</th>
							<th>주소</th>
							<th>주문금액</th>
							<th>배송현황</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderList }" var="order" varStatus="index">
						<tr>
							<td><small>${index.count }</small></td>
							<td><small><a href="javascript:listView('${order.id }')" class="text-dark">☆${order.id }</a></small></td>
							<td><small>${order.name }</small></td>
							<td><small>${order.phoneNumber }</small></td>
							<td><small>${order.address } ${order.detailAddress }</small></td>
							<td><small>${order.totalPay }</small></td>
							<td><small>${order.status }</small></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		<div class="d-flex justify-content-end mt-5">
			<a href="/user/update" class="btn btn-dark" id="btnProfile">프로필 수정</a>
		</div>
	</div>
</div>

<script>

	function listView(orderId){
		    let f = document.createElement('form');
		    
		    let input;
		    input = document.createElement('input');
		    input.setAttribute('type', 'hidden');
		    input.setAttribute('name', 'orderId');
		    input.setAttribute('value', orderId);
		    
		    f.appendChild(input);
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', '/order/detail-list-view');
		    document.body.appendChild(f);
		    f.submit();
		}
	
	$(document).ready(function(){
		$("#before").on("click", function() {
			$.ajax({
				url: "/order/list-view"
				, data: {"status" : "입금 대기"}
				, success: function(data){
					$("#listContent").html(data);
					console.log(data);
				}
			});
		});
		$("#ing").on("click", function() {
			$.ajax({
				url: "/order/list-view"
				, data: {"status" : "배송 중"}
				, success: function(data){
					$("#listContent").html(data);
					console.log(data);
				}
			});
		});
		$("#done").on("click", function() {
			$.ajax({
				url: "/order/list-view"
				, data: {"status" : "배송 완료"}
				, success: function(data){
					$("#listContent").html(data);
					console.log(data);
				}
			});
		});
		$("#allOrder").on("click", function() {
			$.ajax({
				url: "/order/list-view"
				, data: {"status" : "all"}
				, success: function(data){
					
					$("#listContent").html(data);
					console.log(data);
				}
			});
		});
	}); 

</script>