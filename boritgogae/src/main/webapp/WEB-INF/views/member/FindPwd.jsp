<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
</head>
<body>

아이디 찾기 | 비밀번호 재설정

아이디 확인 후 비밀번호를 재설정 할 수 있습니다.
<input type="text" placeholder="아이디" />
<button>확인</button>
------------------------------------------

    아이디 출력

    <input type="text" placeholder="이름" /> 이름을 입력해주세요

    <input type="email" placeholder="이메일" /> 이메일 주소를 정확히 입력해주세요

    캡챠자리

    <hidden 인증번호 적는 곳>

    <button1>인증번호 받기</button>

    <button>1이후 비번 재설정</button>

    <에러> 받기버튼 ajax 로 회원정보 select
    입력한 정보와 일치하는 회원정보가 없습니다. 
    이름/이메일 주소를 확인해주세요.

    <받기되면>
    인증번호 발송 요청이 완료되었습니다.
    인증번호가 오지 않는 경우, 입력한 이름/이메일주소를 
    확인 후 다시 요청해주세요.

    <비밀번호 재설정 버튼 누른후>
    shinsa0402님 새 비밀번호를 입력해 주세요.
    <input type="password" placeholder="새 비밀번호 (8~15)" /> 비밀번호가 적합하지 않습니다. (비밀번호표시)
    <input type="password" placeholder="새 비밀번호 확인" /> 비밀번호가 서로 일치하지 않습니다. (비밀번호표시)

    일치하지않으면 확인버튼 안눌림
    

    8~15자의 영문 대/소문자, 숫자, 특수문자 중 2개 이상 조합
    사용가능 특수문자: ! “ $ % & ‘ () * + , - . / : ; < > = ? @ # [] ₩ ^ _`{} ~ |
    동일하거나 연속된 4자리 이상의 숫자/영문 반복 사용불가
    아이디, 생일, 전화번호 등 개인정보 사용불가

</body>
</html>