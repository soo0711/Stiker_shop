<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<div class="d-flex justify-content-center">${orderCardList }
		<div class="col-7">
			<c:forEach items="${orderCardList }" var="orderCard" varStatus="status">
				<hr>
				<div class="mx-2 d-flex justify-content-between">
					${orderCard }
				</div>
				<hr>
			</c:forEach>
		</div>
	</div>
</div>