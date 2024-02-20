<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${fn:contains(viewName, 'manager') eq false}">
<ul class="nav nav-fill w-100">
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold" id="best">베스트</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold" id="new">NEW</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold" id="all">전체</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold" id="keyring">키링</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold" id="stiker">스티커</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold" id="memo">메모</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold" id="acc">디지털 악세사리</a></li>
</ul>
</c:if>

<c:if test="${fn:contains(viewName, 'manager') eq true}">
<ul class="nav nav-fill w-100">
	<li class="nav-item"><a href="/manager/hukahuka-upload-view" class="nav-link text-nav-color font-weight-bold" id="upload">등록</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold" id="storage">재고</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold" id="status">배송현황</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">x</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">x</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">x</a></li>
	<li class="nav-item"><a href=# class="nav-link text-nav-color font-weight-bold">x</a></li>
</ul>
</c:if>

<script>
	
	$(document).ready(function() {
		
		$(document).on("click", "#upload", function(e) {
			e.preventDefault();
	
			$.ajax({
				url: "/manager/upload-view"
				,data: {"menu" : 1} 
				
				,success: function(data){
					$(".content1").html(data);
				}
				
			}); // - upload ajax
			
		}); // - upload
		
		$(document).on("click", "#storage", function(e) {
			e.preventDefault();
			// alert("재고");
			
			$.ajax({
				url: "/manager/upload-view"
				,data: {"menu" : 2} 
				
				,success: function(data){
					$(".content1").html(data);
				}
				
			}); // - storage ajax
		}); // - storage
		
		$(document).on("click", "#status", function(e) {
			e.preventDefault();
			// alert("배송현황");
			
			$.ajax({
				url: "/manager/upload-view"
				,data: {"menu" : 3} 
				
				,success: function(data){
					$(".content1").html(data);
				}
				
			}); // - storage ajax
		}); // - status
		
		$(document).on("click", "#all", function(e) {
			e.preventDefault();

			$.ajax({
				url: "/menu-view"
				,data: {"menu" : "all"} 
				
				,success: function(data){
					location.href="/menu-view?menu=all"
				}
				
			}); // - all ajax
		}); // - all
			
		$(document).on("click", "#best", function(e) {
			e.preventDefault();
	
			$.ajax({
				url: "/menu-view"
				,data: {"menu" : "best"} 
				
				,success: function(data){
					location.href="/menu-view?menu=best"
				}
				
			}); // - best ajax
		}); // - best
			
		$(document).on("click", "#new", function(e) {
			e.preventDefault();
	
			$.ajax({
				url: "/menu-view"
				,data: {"menu" : "new"} 
				
				,success: function(data){
					location.href="/menu-view?menu=new"
				}
				
			}); // - new ajax
		}); // - new
			
		$(document).on("click", "#keyring", function(e) {
			e.preventDefault();
			// alert("키링");
			
			$.ajax({
				url: "/menu-view"
				,data: {"menu" : "keyring"} 
				
				,success: function(data){
					location.href="/menu-view?menu=keyring"
				}
				
			}); // - keyring ajax
		}); // - keyring
			
		$(document).on("click", "#stiker", function(e) {
			e.preventDefault();
	
			$.ajax({
				url: "/menu-view"
				,data: {"menu" : "stiker"} 
				
				,success: function(data){
					location.href="/menu-view?menu=stiker"
				}
				
			}); // - stiker ajax
		}); // - stiker
		
		$(document).on("click", "#memo", function(e) {
			e.preventDefault();
	
			$.ajax({
				url: "/menu-view"
				,data: {"menu" : "memo"} 
				
				,success: function(data){
					location.href="/menu-view?menu=memo"
				}
				
			}); // - memo ajax
		}); // - memo
			
		$(document).on("click", "#acc", function(e) {
			e.preventDefault();
	
			$.ajax({
				url: "/menu-view"
				,data: {"menu" : "acc"} 
				
				,success: function(data){
					location.href="/menu-view?menu=acc"
				}
				
			}); // - acc ajax
		}); // - acc
		
	}); // - doc

</script>
