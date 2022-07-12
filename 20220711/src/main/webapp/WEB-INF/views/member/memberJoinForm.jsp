<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">


		<div>
			<h1>회원가입</h1>
		</div>

		<div>

			<form id="frm" action="memberJoin.do" onsubmit="return formCheck()"
				method="post">

				<div>
					<table border=1>
						<tr>
							<th width="150">아이디</th>
							<td width="200"><input type="text" id="memberId"
								name="memberId" size="20"> &nbsp; <input type="hidden"
								id="checkId" value="No">
								<button type="button" id="btn" onclick="idCheck()">중복체크</button>
							</td>
						</tr>
						<tr>
							<th width="150">패스워드</th>
							<td width="300"><input type="password" id="memberPwd"
								name="memberPwd" size="20" required="required"> &nbsp;</td>
						</tr>
						<tr>
							<th width="150">패스워드 확인</th>
							<td width="300"><input type="password" id="pwd" size="20">
								&nbsp;</td>
						</tr>
						<tr>
							<th width="150">이름</th>
							<td width="250"><input type="text" id="memberName"
								name="memberName" size="20"> &nbsp;</td>
						</tr>


					</table>
				</div>
				<br>

				<div>
					<input type="submit" value="회원가입"> &nbsp; <input
						type="reset" value="취소">&nbsp; <input type="button"
						value="홈으로" onclick="location.href='main.do'">&nbsp;

				</div>

			</form>

		</div>

	</div>

	<script type="text/javascript">
		function formCheck() {
			if (frm.memberId.value == "") {
				alert("사용자 아이디를 입력하세요.")
				frm.memberId.focus();
				return false;
			}
			if (frm.checkId.value == "No") {
				alert("아이디 중복체크를 해주세요.")
				return false;
			}
			if (frm.memberPwd.value !== frm.pwd.value) {
				alert("패스워드가 일치하지 않습니다.")
				frm.memberPwd.value = "";
				frm.pwd.value = "";
				frm.memberPwd.focus();
				return false;
			}
			if (frm.memberName.value == "") {
				alert("사용자 이름을 입력하세요.")
				frm.memberName.focus();
				return false;
			}

			return true;

		}

		function idCheck() {
			let id = frm.memberId.value;
			if (id == "") {
				alet("아이디 입력 후 중복체크 해주세요");
				fem.memberId.focus();
			} else {
				let ajax = new XMLHttpRequest();
				ajax.open('get', 'ajaxMemberIdCheck.do?id=' + id);
				ajax.send();
				ajax.onload = function() {

					if (this.readyState == 4 && this.status == 200) {
						htmlConvertAjax(this.responseText);
					} else {
						errorAjaxCall();
					}

				};

			}

		}

		function htmlConvertAjax(str) {
			if (str == "usable") {
				alert("사용가능한 아이디입니다");
				frm.checkId.value = "Yes";
				frm.btn.disabled = true;
				frm.memberPwd.focus();
			} else {
				alert("사용할 수 없는 아이디입니다.");
				frm.memberId.value = "";
				frm.memberId.focus();
			}

		}

		function errorAjaxCall() {
			alert("잠시 후 다시 시도해주세요.");
		}
	</script>

</body>
</html>