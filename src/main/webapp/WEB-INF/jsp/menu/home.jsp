<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center align-items-center">
	<img src="/static/img/home_img.png" alt="홈 캐릭터 이미지" class="col-5" id="bannerImage">
</div>

<script>
	$(document).ready(function () {
		
		let bannerSrc = ["/static/img/home_img.png", "/static/img/home_img1.jpg", "/static/img/home_img2.jpg"]
		let currentIndex = 0;
		
		setInterval(function() {
			$("#bannerImage").attr("src", bannerSrc[currentIndex]);
			currentIndex++;
			
			if(currentIndex > bannerSrc.length){
				currentIndex = 0;
			}
			
		}, 3000); // 3초마다 변환
		
	});
</script>