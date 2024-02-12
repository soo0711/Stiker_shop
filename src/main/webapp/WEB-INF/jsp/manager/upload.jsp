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

<script>
	$(document).ready(function() {
		// alert("준비");
		// 업로드 
		
		var inputFileList = new Array(); // 이미지 파일 넣을 배열
		
		// 파일 선택
		$("input[name=images]").on("change", function(e) {
			// alert("파일 선택");
			var files = e.target.files;
			var fileArray = Array.prototype.slice.call(files);
			
			if (fileArray.length > 3){
				alert("이미지는 최대 3개까지 업로드 가능합니다.");
				$("input[name=images]").val("");
				return;
			}
			
			fileArray.forEach(function(f) {
				inputFileList.push(f);
			});
			
		}); // - btn select
		
		$("#btnUpload").on("click", function() {
			// alert("업로드");
			
			// vaildation
			let name = $("#name").val();
			let count = $("#count").val();
			let introduce= $("#introduce").val();
			let detail = $("#detail").val();
			let category = $("#category").val();
			let inputFile = $("input[name=images]");
			
			// 확장자 검사
			inputFileList.forEach(function(f) {
				let extension = f.name.split(".").pop().toLowerCase();
				console.log(f.name + " "+ extension);
				if ($.inArray(extension, ['jpg', 'png', 'gif', 'jpeg']) == -1){
					alert("이미지 파일만 업로드 할 수 있습니다.");
					inputFileList.pop();
					inputFileList.pop();
					inputFileList.pop();
					$("input[name=images]").val("");
					return;
				}
			});
			
			
			if (!name) {
				alert("상품 이름을 입력해주세요.");
				return false;
			}
			
			if (!count) {
				alert("개수를 입력해주세요.");
				return false;
			}
			
			if (!introduce) {
				alert("상품 소개를 입력해주세요.");
				return false;
			}
			
			if (!detail) {
				alert("상세 설명을 입력해주세요.");
				return false;
			}
			
			if (category == "--선택--"){
				alert("카테고리를 선택해주세요.");
				return false;
			}
			

		}); // -  btn upload
		
		/*
		$.ajax({
			// request
			type: "POST"
			, url: "/manager/upload"
			, data: formData
			, enctype: "multipart/form-data" // 파일 업로드를 위한 필수 설정 - 이미지가 들어가는걸 알려준다.
			, processData: false // 파일 업로드를 위한 필수 설정 - String이 아니라 객체로 보내고 있다는걸 알려준다.
			, contentType: false // 파일 업로드를 위한 필수 설정
			
			// response
			, success: function(data){
				if (data.code == 200){
					alert("상품이 저장되었습니다.");
					location.href = "/manager/hukahuka-storage-view";
				} else {
					alert(data.error_message);
				}
			}
			, error: function(request, status, error){
				alert("상품 등록하는데 실패했습니다. 관리자에게 문의 주세요.")
			}
			
		}); // - ajax
		*/
		
	}); // doc
</script>