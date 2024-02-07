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
			<span>상품 설명</span>
			<input type="text" class="form-control my-2" id="detailProduct" name="detailProduct">
		</div>
		<div>
			<span>상품 소개</span>
			<input type="text" class="form-control my-2" id="introduceProduct" name="introduceProduct">
		</div>
		<div>
			<span>카테고리</span>
			<div class="d-flex align-items-center my-2">
				<select class="form-control" id="category">
				  <option selected>키링</option>
				  <option>스티커</option>
				  <option>메모</option>
				  <option>디지털 악세사리</option>
				</select>
			</div>
		</div>
		<div>
			<div>상품 이미지</div>
			<div id="file-list">
				<div class="d-flex justify-content-between">
					<input type="file" id="file" class="my-2" accept=".jpg, .png, .gif, .jpeg">
					<a href="#" class="my-2 deleteFile text-secondary">x</a>
				</div>
			</div>
			<a href="#" class="my-2 text-secondary float-right" id="fileAdd">파일 추가</a>
		</div>
		
		<button type="submit" class="btn btn-secondary btn-block my-2" id="btnUpload">등록</button>
	</div>
</div>
</c:if>

<script>
	$(document).ready(function() {
		// alert("준비");
		
		$("#btnUpload").on("click", function(){
			//alert("클릭");
			
			// vaildation
			let name = $("#name").val().trim();
			let count = $("#count").val().trim();
			let detailProduct = $("#detailProduct").val().trim();
			let introduceProduct = $("#introduceProduct").val().trim();
			let category = $("#category").val();
			let fileName = $("#file").val(); //C:\fakepath\gg.jpg
			
			if (!name){
				alert("상품 이름 입력");
				return false;
			}
			
			if (!count){
				alert("재고 입력");
				return false;
			}
			
			if (!detailProduct){
				alert("상품 상세 입력");
				return false;
			}
			
			if(!introduceProduct){
				alert("상품 소개 입력");
				return false;
			}
			
			if (fileName){
				// alert(fileName);
				// 확장자만 뽑은 후 소문자로 변경해서 검사한다.
				let extension = fileName.split(".").pop().toLowerCase();
				if ($.inArray(extension, ['jpg', 'png', 'gif', 'jpeg']) == -1){
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$("#file").val(""); // 파일을 비운다.
					return;
				}
			}
			
			// form 태그를 js에서 만든다.
			// 이미지를 업로드 할 때는 반드시 form 태그가 있어야한다.
			let formData = new FormData();
			formData.append("name", name ); // key는 name 속성과 같다. Request Parameter명
			formData.append("count", count);
			formData.append("detailProduct", detailProduct);
			formData.append("introduceProduct", introduceProduct);
			formData.append("category", category);
			formData.append("file", $("#file")[0].files[0]); // 파일 한개만 받는다.
			
			alert("이름: " + name +"\n재고: " + count + "\n상품 상세: " + detailProduct
					+"\n상품 소개: " + introduceProduct + "\n카테고리: "  + category + "\n이미지:" + fileName);
			
			// AJAX
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
			
		}); // btn
		
		
		// 파일 추가
		$("#fileAdd").on("click", function(e) {
			e.preventDefault();
			let str = "<div class='d-flex justify-content-between'><input type='file' id='file' class='my-2' accept='.jpg, .png, .gif, .jpeg'><a href='#' class='my-2 deleteFile text-secondary'>x</a></div>";
			$("#file-list").append(str);
			$(".deleteFile").on("click", function(e) {
				e.preventDefault();
				$(this).parent().remove();
			});
		}); // add file
		
		// 파일 삭제
		$(".deleteFile").on("click", function(e) {
			e.preventDefault();
			$(".deleteFile").parent().remove();
		}); // delete file
		
	}); // doc
</script>