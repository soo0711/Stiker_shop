<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="cart-size d-flex justify-content-center">
	<div class="col-7">
		<hr>
		<input type="checkbox" value="1">
		<div class="mx-2 d-flex">
			<img src="/static/img/home_img2.jpg" id="plus" width="100" height="100" alt="상품이미지">
			<div class="ml-3 mb-2">
				<div>엉망진창 랜덤팩</div>
				<div class="d-flex align-items-center my-2">
					<input type="text" id="buyCount" class="form-control col-2 text-center" value="1">
					<img src="/static/img/plus.png" id="plusCart" class="mx-2" width="25" height="25" alt="플러스 아이콘">
					<img src="/static/img/minus.png" id="minusCart" width="25" height="25" alt="마이너스 아이콘">
				</div>
				<div>9000원</div>
			</div>
		</div>
		<hr>
	</div>
</div>

<script>

	$(document).ready(function () {
		
		$("#plusCart").on("click", function() {
			let buyCount = parseInt($("#buyCount").val()) + 1;
			$("#buyCount").val(buyCount);
		});
		
		$("#minusCart").on("click", function(){
			// alert("minus");
			if ($("#buyCount").val() == 1){
				return false;
			}
			
			let buyCount = parseInt($("#buyCount").val()) - 1;
			$("#buyCount").val(buyCount);
			
		});
	});

</script>