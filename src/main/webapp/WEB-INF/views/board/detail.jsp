<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<button class="btn btn-secondary" onClick="history.back()">List</button>	
	<c:if test="${board.user.id == principal.user.id }">
		<button id="btn-update" class="btn btn-warning">Modify</button>
		<button id="btn-delete" class="btn btn-danger">Delete</button>
	</c:if>
	<br />
	<br />
	<div>
		Contents number:<span id="id"><i>${board.id }</i></span>&nbsp;&nbsp;&nbsp;&nbsp;
		Writer:<span><i>${board.user.username }</i></span>
		
	</div>
	<br/>
	<h3>${board.title }</h3>

	<hr />

	<div>${board.content }</div>

	<hr />

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>



