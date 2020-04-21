<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HJ Board</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./css/jboard.css">
</head>
<body>
	<div id="contentsArea">
	<section id="titlename">
	<h1>게시판 내용</h1>
	<p class="formSign">게시판의 글 내용입니다</p>
		<div id="joinForm">
			<input type="hidden" name="num" value="<c:out value='${boardDTO.num}'/>">
			<fieldset>
			<legend>게시판 내용</legend>
			<p>
				<label for="name">이름</label><br/>
				<c:out value="${boardDTO.name}"/>
			</p>
			<p>
				<label for="subject">제목</label><br/>
				<c:out value="${boardDTO.subject}"/>
			</p>
			<p>
				<label for="content">내용</label><br/>
				<c:out value="${boardDTO.content}"/>
			</p>
			
			<c:choose>
				<c:when test="${!empty boardDTO.attached_file}">
				<p>
				<label for="attached_file">파일 첨부</label><br/>
				<c:out value="${boardDTO.attached_file}"/>&nbsp;&nbsp;&nbsp;
				<a href="./boardUpload/<c:out value='${boardDTO.attached_file}'/>">
					파일 다운
				</a>
				<input type="hidden" name="old_file" value="<c:out value='${boardDTO.attached_file}'/>">&nbsp;&nbsp;&nbsp;
				</p>
			</c:when>
			
			<c:otherwise>
				<p>
					<label for="old_file">파일 첨부</label>
					<br/> 첨부 파일이 없습니다.
				</p>
			</c:otherwise>	
			</c:choose>
			
			<div class="btnJoinArea">
				<a href="./BoardReplyMove.do?num=<c:out value='${boardDTO.num}'/>">
					<button type="button" class="btnOk">답변</button>
				</a>
				<a href="./BoardModify.do?num=<c:out value='${boardDTO.num}'/>">
					<button type="button" class="btnOk">수정</button>
				</a>
				<a href="./BoardDelete.do?num=<c:out value='${boardDTO.num}'/>">
					<button type="button" class="btnOk">삭제</button>
				</a>
				<button type="button" value="button" onclick="location.href='./BoardList.do'" class="btnOk">
				목록
				</button>
			</div>
			
			</fieldset>
			</div>
	</section>
	</div>
</body>
</html>