<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<button class="btn btn-secondary" onClick="history.back()">List</button>
	<button id="btn-update" class="btn btn-warning">Modify</button>
	<button id="btn-delete" class="btn btn-danger">Delete</button>
	<br />
	<br />

	<h3>${board.title }</h3>

	<hr />

	<div>${board.content }</div>

	<hr />

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>



