use elrek1991;

-- 생일 쿠폰
-- 로그인시 회원정보 가져와서 (밑에 로그인) --------
-- 미완 shell script // 서버에서 자정이 되면 일괄적으로 처리 ??
-- 생년월일 확인해서 생일일시 
-- select * from members where birthDay = now();
-- insert into couponUsed 
-- values(memberId = #{memberId}, couponName = #{(select couponName from coupon where couponName = '생일쿠폰')}, 
-- useDate = (select date_add(now(), interval 6 month)), issueDate = now(), couponWhy = '생일쿠폰')


-- 자동 로그인
-- select * from members where sessionId = #{sessionId} and sessionLimit > now()
select * from members where sessionId = 'AaBbCc112233' and sessionLimit > now();
-- update members set sessionId = #{sessionId}, sessionLimit = #{sessionLimit} where memberId = #{memberId}
update members set sessionId = 'AaBbCc112233', sessionLimit = date_add(now(), interval 6 month) where memberId = 'abcd';


-------- 회원 로그인
-- 로그인 = 저장된 회원의 정보와일치하는지 확인 
-- select * from members where memberId = #{memberId} and memberPwd = sha1(md5(#{memberPwd}))
select * from members where memberId = 'abcd' and memberPwd = '1234';
-- 로그인성공시 = 최종로그인시간 업데이트 
update members set lastLogInDate = now() where memberId = 'abcd';
-- 로그인 시 비밀번호 60일 뒤 재설정
-- 로그인시 비밀번호최종수정 체크 
select * from members 
where lastPwdUpdate + (select date_add(now(), interval 6 month)) < now();
-- 일시 비밀번호(회원정보)수정 화면


----- 로그아웃
-- 로그아웃시간 업데이트
-- 세션아이디 만료, 삭제 
update members set logOutDate = now() where sessionId = 'AaBbCc112233';


-- 비회원 주문내역조회
-- select * from orders where name = #{name} and phoneNumber= #{phoneNumber} and guestPwd= #{guestPwd}
select * from orders
where name = '게스트' and phoneNumber= '01078941115' and guestPwd= '1234';

-- 비회원 주문비밀번호 찾기
select guestPwd from orders
where name = '게스트' and phoneNumber = '01078941115' and orderNo = 2;


-- 아이디찾기
-- 이름/이메일 확인 
-- select * from members where memberName = #{memberName} and memberEmail = #{memberEmail}
select * from members where memberName = '신태호' and memberEmail = 'shinsa0401@naver.com';
-- 이메일 인증 
insert into authentication(email, authCheck, authNumber) value('shinsa0401@naver.com', 'N', 112233);
-- 전에 인증한적이 있다면 업데이트
update authentication set authCheck = 'N', authNumber = 113355 where email = 'shinsa0401@naver.com';
-- 인증번호 확인 
select * from authentication where authNumber = 112233;
-- 확인후 인증여부 0 -> 1 
update authentication set authCheck = 'Y' where email = 'shinsa0401@naver.com' and authNumber = 112233;


-- 비밀번호찾기
-- 아이디/이메일 확인 
select * from members where memberId = 'shin' and memberEmail = 'shinsa0401@naver.com';
-- 이메일 인증 
insert into authentication(email, authCheck, authNumber) value('shinsa0401@naver.com', 'N', 113355);
-- 전에 인증한적이 있다면 업데이트
update authentication set authCheck = 'N', authNumber = 113355 where email = 'shinsa0401@naver.com';
-- 인증번호 확인 
select * from authentication where authNumber = 113355;
-- 확인후 인증여부 0 -> 1 
update authentication set authCheck = 'Y' where email = 'shinsa0401@naver.com' and authNumber = 113355;
-- 비밀번호업데이트 비밀번호수정페이지로 이동후 새로운비밀번호 설정
update members set memberPwd = '12341234' where memberId = 'shin';


---------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------  게시판 ------------------------------------------ 
---------------------------------------------------------------------------------------------------------------------------------------
-- 질문게시판 생성
CREATE TABLE `elrek1991`.`questionBoard` (
  `no` INT(11) NOT NULL AUTO_INCREMENT,
  `writer` VARCHAR(12) NOT NULL,
  `pwd` VARCHAR(100) NOT NULL,
  `writtenDate` DATETIME NULL DEFAULT now(),
  `readCount` INT(11) NULL DEFAULT '0',
  `likeCount` INT(11) NULL DEFAULT '0',
  `title` VARCHAR(50) NOT NULL,
  `content` VARCHAR(1000) NULL,
  `uploadFile` VARCHAR(200) NULL,
  `ref` INT(11) NULL DEFAULT '0',
  `step` INT NULL DEFAULT '0',
  `refOrder` INT NULL DEFAULT '0',
  `isDelete` VARCHAR(1) NULL DEFAULT 'N',
  `category` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`no`));
  
-- 질문게시판 댓글 테이블 생성
CREATE TABLE `elrek1991`.`questionReply` (
  `rno` INT NOT NULL AUTO_INCREMENT,
  `bno` INT NOT NULL,
  `replyWriter` VARCHAR(12) NOT NULL,
  `replyPwd` VARCHAR(100) NOT NULL,
  `replyContent` VARCHAR(1000) NOT NULL,
  `replyWrittenDate` DATETIME NULL DEFAULT now(),
  `replyUpdateDate` DATETIME NULL,
  PRIMARY KEY (`rno`));


-- 질문게시판 첨부파일 테이블 생성
CREATE TABLE `elrek1991`.`questionUploadFile` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `bno` INT NOT NULL,
  `originalFile` VARCHAR(200) NOT NULL,
  `thumbnailFile` VARCHAR(200) NULL,
  PRIMARY KEY (`no`));


-- 질문게시판 조회수 테이블 생성
CREATE TABLE `elrek1991`.`questionReadCount` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `ipAddr` VARCHAR(50) NOT NULL,
  `bno` INT NOT NULL,
  `readDate` DATETIME NULL DEFAULT now(),
  PRIMARY KEY (`no`));


-- 질문게시판 카테고리 테이블 생성
CREATE TABLE `elrek1991`.`questionCategories` (
  `category` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`category`));
  
-- 카테고리 목록
INSERT INTO `elrek1991`.`questionCategories` (`category`) VALUES ('강아지');
INSERT INTO `elrek1991`.`questionCategories` (`category`) VALUES ('고양이');
INSERT INTO `elrek1991`.`questionCategories` (`category`) VALUES ('상품');
INSERT INTO `elrek1991`.`questionCategories` (`category`) VALUES ('기타');

-- 질문게시판 테이블 카테고리 -> 카테고리스 테이블 카테고리 FK 참조
ALTER TABLE `elrek1991`.`questionBoard` 
ADD CONSTRAINT `questionBoard_category_fk`
  FOREIGN KEY (`category`)
  REFERENCES `elrek1991`.`questionCategories` (`category`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- 질문게시판 테이블 글쓴이 -> 회원 테이블 아이디 FK 참조
ALTER TABLE `elrek1991`.`questionBoard` 
ADD CONSTRAINT `questionBoard_writer_fk`
  FOREIGN KEY (`writer`)
  REFERENCES `elrek1991`.`members` (`memberId`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
-- 첨부파일 테이블 글번호 -> 질문게시판 글번호 FK 참조
ALTER TABLE `elrek1991`.`questionUploadFile` 
ADD CONSTRAINT `questionUploadFile_bno_fk`
  FOREIGN KEY (`bno`)
  REFERENCES `elrek1991`.`questionBoard` (`no`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

-- 조회수 테이블 조회글번호 -> 질문게시판 글번호 FK 참조
ALTER TABLE `elrek1991`.`questionReadCount` 
ADD CONSTRAINT `questionReadCount_bno_fk`
  FOREIGN KEY (`bno`)
  REFERENCES `elrek1991`.`questionBoard` (`no`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;

-- 댓글 테이블 글번호 -> 질문게시판 글번호 FK 참조
ALTER TABLE `elrek1991`.`questionReply` 
ADD CONSTRAINT `questionReply_bno_fk`
  FOREIGN KEY (`bno`)
  REFERENCES `elrek1991`.`questionBoard` (`no`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
-- 댓글 테이블 댓글작성자 -> 회원 테이블 아이디 FK 참조
ALTER TABLE `elrek1991`.`questionReply` 
ADD CONSTRAINT `questionReply_replyWriter_fk`
  FOREIGN KEY (`replyWriter`)
  REFERENCES `elrek1991`.`members` (`memberId`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;



-----------------------------------------------------------------------------------------
-- 페이징 처리하며 게시판 전체목록보기
-- select * from board order by ref desc, reforder asc limit #{startNum}, #{postPerPage}


-- 게시판에 글등록
-- insert into board(writer, title, content, pwd) values(#{writer}, #{title}, #{content}, sha1(md5(#{pwd})))
insert into questionBoard(writer, title, content, pwd) 
values('shin', '페이징 테스트', '페이징 테스트를 위한 글 생성', sha1(md5('1234')));

-- 게시글 등록시 업로드된 파일이 이미지인경우 (사진5장까지..?)
-- insert into uploadfile(bno, originalFile, thumbnailFile) 
-- values(#{lastNo}, #{savedOriginalImgFile}, #{thumbnailFile})

-- 게시글 등록시 업로드된 파일이 이미지가 아닌경우 (5개까지..?)
-- insert into uploadfile(bno, originalFile) 
-- values(#{lastNo}, #{savedOriginalImgFile})


-- 수정/삭제하기위해 얻어온 n번 게시글의 번호
-- select * from board where no = #{no}

select count(*) as cnt from questionBoard where no = 6 and pwd = sha1(md5('1234'));

-- 게시판 n번 게시글 수정하기
-- update board set title = #{title}, content = #{content} where no = #{no} and pwd = sha1(md5(#{pwd}))
-- update questionBoard set title = '수정테스트', content = '수정되나' where no = 6 and pwd = sha1(md5('1234'));

-- 게시판 n번 게시글 첨부파일 수정하기(기존첨부파일 삭제하고)
-- delete 
-- update uploadFile set originalFile = #{}, thumbnailFile = #{} where no = #{bno}


-- 게시판 n번 게시글 삭제하기(삭제여부만업데이트)
-- update board set isDelete = 'Y' where no = #{no} and pwd = sha1(md5(#{pwd}))


-- 최근등록된글의 번호 얻어오기
select max(no) as lastNo from questionBoard;

-- 게시글이 insert 된 후 ref를 update 하는 메서드
update questionBoard set ref = 6 where no = 6;


-- 게시글 상세페이지 보기(no = n번글)
-- select * from board where no = #{no}
-- 게시글 첨부파일 조회
select * from questionUploadFile where bno = 26;


-- 작성자가 쓴글 보기
-- select * from board where writer = #{writer} order by no desc;


-- 게시글 n번글 조회수증가(읽은 뒤 24시간후 증가)
-- 조회수 처리를 위한 ip주소, 글번호 얻어오는 메서드
select * from questionReadCount where bno = 1 and ipAddr = '211.21.31.43';
-- select * from readCount where bno = #{bno} and ipAddr = #{ipAddr}
-- 조회수 처리를 위한 ip주소, 글번호, 현재시간을 insert하는 메서드
-- insert into readCount(ipAddr, bno) values(#{ipAddr}, #{bno})
-- 조회수 처리를 위한 ip주소, 글번호로 현재시간을 update 메서드
-- update readCount set readDate = now() where bno = #{bno} and ipAddr = #{ipAddr}
-- 조회수 증가
-- update board set readCount = readCount + 1 where no = #{no}




-- 전체 글의 개수 얻어오기
-- select count(*) as cnt from board

-- 검색된 글의 개수 얻어오기
select count(*) as cnt from questionBoard where content like '%세종%';
-- select count(*) as cnt from board where
--   <if test="searchType == 'writer'">
--    writer like concat('%', #{searchWord}, '%')
--   </if>
--   <if test="searchType == 'title'">
--    title like concat('%', #{searchWord}, '%')
--   </if>
--   <if test="searchType == 'content'">
--    content like concat('%', #{searchWord}, '%')
--   </if>


-- 검색어가 있을 때 페이징하며 검색결과를 가져오기
-- select * from board where
--   <if test="searchType == 'writer'">
--      writer like concat('%', #{searchWord}, '%')
--   </if>
--   <if test="searchType == 'title'">
--      title like concat('%', #{searchWord}, '%')
--   </if>
--   <if test="searchType == 'content'">
--      content like concat('%', #{searchWord}, '%')
--   </if>
-- order by ref desc, reforder asc limit #{startNum}, #{postPerPage}



-- 댓글 등록하기
-- insert into questionReply(bno, replyWriter, replyContent) values(#{bno}, #{replyWriter}, #{replyContent})
insert into questionReply(rno, bno, replyWriter, replyContent) values(4, 1, 'shin', '크앙');

-- 특정번호 글의 모든 댓글을 가져오기
-- select * from questionReply where bno = #{bno} order by rno desc

-- 댓글 개수검색
select replycount from questionBoard where (select count(*) from questionReply where bno = 1);
select count(*) from questionReply where bno = 1;

-- 댓글 수정
-- update questionReply set replyWriter = #{replyWriter}, replyContent = #{replyContent}, replyWrittenDate = now() where bno = #{bno} and rno = #{rno};
update questionReply
set replyWriter = 'shin', replyContent = '댓글 수정테스트', replyWrittenDate = now() where rno = 1 and bno =1;

-- 댓글 번호로 글번호 검색 (필요없음)
select bno from questionReply where rno = 1;

-- 해당 글의 최근 등록된 댓글 번호 얻어오기
select max(rno) as lastRno from questionReply;

-- reply ref 업데이트
update questionReply set ref = 7 where rno = 7;

-- refOrder 업데이트
update questionReply set refOrder = refOrder + 1 where ref = 68 and refOrder > 3;

-- rno로 부모댓글의 정보 얻어오기
select * from questionReply where rno = 7;

-- 댓글의 max(refOrder)값 구하기
select max(refOrder) as maxRefOrder from questionReply where bno = 1;

-- 댓글의 댓글
insert into questionReply(bno, replyWriter, replyContent, ref, step, refOrder) 
values(1, 'shin', '대댓글', 8, 1, 3);

-- 부모댓글그룹의 자식댓글수의 합 검색
select count(*) as cntSum from questionReply where ref = 42 and step != 0;

-- 부모댓글의 최대 step값 검색
select max(step) as maxStep from questionReply where ref = 42;

-- 부모댓글의 자식댓글 개수
select count(*) as cnt from questionReply where ref = 42 and step = 1; -- step = step + 1