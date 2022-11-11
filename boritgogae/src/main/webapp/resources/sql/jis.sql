select version();
use elrek1991;

-- fk 연결하는 구문들입니다.
-- --------------------------------------------------------------------------------------------------------------
alter table likes
add constraint like_memberId_fk foreign key(memberId) references members(memberId);

alter table likes
add constraint like_prodNo_fk foreign key(prodNo) references product(prodNo);

alter table deleteAccount
add constraint deleteAccount_deleteCode_fk foreign key(deleteCode) references deleteReason(deleteCode);

alter table orders
add constraint orders_deliveryFeeCode_fk foreign key(deliveryFeeCode) references deliveryFee(deliveryFeeCode);

alter table deleteAccount
add constraint deleteAccount_memberId_fk foreign key(memberId) references members(memberId);

alter table members
add constraint members_memberEmail_fk foreign key(memberEmail) references certify(email);

alter table exchangeImg
add constraint exchangeImg_exchangeNo_fk foreign key(exchangeNo) references exchange(exchangeNo);

alter table exchange
add constraint exchange_reasonNo_fk foreign key(reasonNo) references exchangeWhy(reasonNo);

alter table exchange
add constraint exchange_orderDetailNo_fk foreign key(orderDetailNo) references detailOrder(orderDetailNo);

alter table delivery
add constraint delivery_deliveryStatus_fk foreign key(deliveryStatus) references deliveryStatus(deliveryStatus);

alter table delivery
add constraint delivery_orderDetailNo_fk foreign key(orderDetailNo) references detailOrder(orderDetailNo);

alter table orders
add constraint orders_deliveryOption_fk foreign key(deliveryOption) references deliveryFee(deliveryOption);

alter table askBoard
add constraint askBoard_writer_fk foreign key(writer) references members(memberId);

alter table askBoard
add constraint askBoard_askCode_fk foreign key(askCode) references askClass(askCode);

alter table askImg
add constraint askImg_askBno_fk foreign key(askBno) references askBoard(askBno);

alter table askRead
add constraint askRead_askBno_fk foreign key(askBno) references askBoard(askBno);

alter table askRead
add constraint askRead_writer_fk foreign key(memberId) references members(memberId);

alter table askLike
add constraint askLike_askBno_fk foreign key(askBno) references askBoard(askBno);

alter table detailOrder
add constraint detailOrder_memberId_fk foreign key(memberId) references members(memberId);












select * from members where memberId = 'abcd';
update members set nickName = '미스터쏜', memberPwd= '1234'  where memberId = 'abcd';
select * from pointHistory where memberId = 'naver';
select * from couponUsed where memberId = 'naver';
select * from couponUsed where memberId = 'naver' and useDate = null ;

select * from members where memberId = 'naver';
select memberPoint from members where  memberId = 'naver';
select purchaseAmount from members where  memberId = 'naver';
update members set memberPoint = 50, purchaseAmount = 20000 where memberId = 'naver';

update members set grade = 'silver' where memberId = 'naver';
select * from orders where memberId = 'naver';
select * from detailOrder where orderNo = 2;



select * from couponUsed where orderNo = 4;


select * from pointHistory where orderNo = 4;

select * from orders where guestEmail = 'guest@g.com' and guestPwd = '1234' and phoneNumber = '01078941115';

select * from detailOrder where orderNo = 4;

select * from detailOrder where orderDetailNo = 3;
update detailOrder set purchaseConfirm = 'T' where orderDetailNo = 3;

update members set memberPoint =500, purchaseAmount = 200000 where memberId = 'abcd';
select * from members where memberId = 'abcd';

select * from detailOrder where orderDetailNo = 3;
update detailOrder set cancelStatus = 'F' where orderDetailNo = 3;

select prodQuantity from product where prodNo = 'cl3ga00s03c10e00';
select * from product where prodNo = 'cl3ga00s03c10e00';

update product set prodQuantity = 5 where prodNo = 'cl3ga00s03c10e00';

select reasonWhy from exchangeWhy;

select * from detailOrder where orderDetailNo = 3;
update detailOrder set returnOrExchange = 'R' where orderDetailNo = 3;

update detailOrder set returnOrExchange = 'E' where orderDetailNo = 4;


insert into exchange(orderDetailNo, reasonNo, reasonContent) values(3,1,'뭐야이게');
insert into exchangeImg (exchangeNo, originalFile, uploadFile) values(1,'test/test','test/test');


update delivery set deliveryStatus = '반품요청' where orderDetailNo = 3;
update delivery set deliveryStatus = '반품중' where orderDetailNo = 3;

select prodQuantity from product where prodNo = 'cl3ga00s03c10e00';
update product set prodQuantity = 10 where prodNo = 'cl3ga00s03c10e00';

update delivery set deliveryStatus = '반품중' ,  returnDeliveryNo = 123142 where orderDetailNo = 3;
update delivery set deliveryStatus = '반품완료' where orderDetailNo = 3;

insert into detailOrder (orderNo, prodNo, qty, prodSubTotalPrice, initialOrderDetailNo) values(4, 'cf1sa00m02w02', 2, 60000, 2);

insert into delivery(orderDetailNo, orderNo, deliveryNo, recipient, phoneNumber, deliveryCompany, address, detailAddress,  postCode, deliveryRequest,  deliveryStatus )
 values(45, 14, 2141522, '몰루', 01012122323, '로젠', '일단구로구', '무슨동', 12412, '빨리와', '배송중');


select * from detailOrder;
insert into delivery(orderDetailNo, orderNo, deliveryNo, recipient, phoneNumber, deliveryCompany, address, detailAddress,  postCode, deliveryRequest,  deliveryStatus )
 values(3, 2, 214152, '몰루', 01012122323, '로젠', '일단구로구', '무슨동', 12412, '빨리와', '배송중');
 
 update couponUsed set useDate = null where orderNo = 2;
update pointHistory set pointSaveDate = null where orderNo = 2;

select orderDetailNo from detailOrder where orderNo = 4 and returnOrExchange = 'R';

select sum(prodSubTotalPrice) from detailOrder  where orderNo = 4;
update orders set totalPrice=200000, usedPoint = 1000, accumPoint = 1000 where orderNo = 4;



update delivery set deliveryStatus = '반품완료' where orderDetailNo = 44;

insert into detailOrder (orderNo, prodNo, qty, prodSubTotalPrice, initialOrderDetailNo) values(14, 'a', 2, 28000, 14);

insert into delivery(orderDetailNo, orderNo, deliveryNo, recipient, phoneNumber, deliveryCompany, address, detailAddress,  postCode, deliveryRequest,  deliveryStatus )
 values(45, 14, 214152, '몰루', 01012122323, '로젠', '일단구로구', '무슨동', 12412, '빨리와', '배송중');






-- ask보드의 총갯수 쿼리
select count(*) as cnt from askBoard;

-- ask 보드에서 검색어 처리
select count(*) as cnt from askBoard where writer like concat('%ab%');

-- 코드에 맞는 옵션값을 가져오는 쿼리
select askOption from askClass where askCode = 'a1';

-- 특정 글의 파일목록을 가져오는 쿼리
select * from askFile where askBno = 13;

-- 특정 글의 파일명과 썸네일 파일명을 가져오는 쿼리
select originalFile as savedOriginImageFileName, thumbNailFile as thumbnailFileName from askFile where askBno = 44;


select count(*) from askRead where askBno = 1;

select count(*) from askLike where askBno = 1 and likeIp = '0:0:0:0:0:0:0:1';


update delivery set deliveryStatus = '반품완료' where orderDetailNo = 44;

update askBoard set writtenDate = now() where askBno = 1;

select * from askRead where askBno in (select askBno from askBoard where isFAQ = 'Y') ;

select * from (select * from askRead where askBno in (select askBno from askBoard where isFAQ = 'Y')) where askBno=1;

update askBoard set readCount = askBoard.readCount + 1 where askBno = 1;
        
        
-- 유저가 쓴 글 조회
select writer, writtenDate, title, readCount, '문의' as board from askBoard	where writer= 'test'
union all select memberId, writtenDate, title, readCount, '공지' as board from noticeBoard where memberId = 'test'
union all select memberId, createDate, title, readCount, '팁' as board from 	tipBoard where memberId = 'test'
union all select writer, writtenDate, title, readCount, '질문' as board from questionBoard where writer = 'test'
union all select writer, writtenDate, title, readCount, '장터' as board from marketBoard where writer = 'test'
union all select writer, createDate, title, readCount, '자유' as board from freeboard where writer = 'test' order by writtenDate desc;        
        
-- 유저가 쓴 댓글 조회
select memberId, writtenTime, contents, '질문' as board from askReplyBoard where memberId = 'test'
union all select memberId, writtenDate, content, '공지' as board from noticeReplyBoard where memberId = 'test'
union all select memberId, createDate, content, '팁' as board from tipReply 	where memberId = 'test'
union all select replyWriter, replyWrittenDate, replyContent, '질문' as board from questionReply where replyWriter = 'test'
union all select replyer, replyWrittenDate, replyContent, '장터' as board from marketReply where replyer = 'test'
union all select replyer, replyWittenDate, replyContent, '자유' as board from freeReply where replyer = 'test' order by writtenTime desc;      

select * from review where writer = 'test';
select * from detailOrder where memberId = 'test' and reviewStatus = 'N' and purchaseConfirm = 'Y';
select * from detailOrder where memberId = 'test' and reviewStatus = 'Y';

select * from members;
select * from members where memberId = 'test';
select memberImg, memberId, memberName, birthDay, joinDate, nickName, memberEmail, phoneNumber, isAdmin from members where memberId = 'test';

update delivery set deliveryStatus = '반품완료' where orderDetailNo = 44;
update members set memberPwd = sha1(md5('1234')) where memberId = 'test';

select * from members where memberId =  'test' and memberPwd = sha1(md5('1234'));




update members set nickName = "미스터쏜" where memberId = 'abcd';




select * from members where memberId =  'test';
update members set memberPwd = sha1(md5('1234')) where memberId = 'test';
update members set memberPwd = sha1(md5('1234')) where memberId = 'test';

-- 주소지 추가하는 쿼리문
insert into deliveryInfo(memberId, address, detailAddress, recipient, recipientPhoneNumber, postCode)  values('test', '울릉도 동남쪽', '뱃길따라이백리', '몰루', 01012122323, '12345');

--
show grants for event@'%';

show VARIABLES like 'event%';

-- 탈퇴테스트용 회원 추가하는 쿼리문
INSERT INTO `elrek1991`.`members` (`memberId`, `memberName`, `memberPwd`, `nickName`, `birthDay`, `phoneNumber`, `memberEmail`, `memberImg`) VALUES ('test99', '탈퇴테스트', '63982e54a7aeb0d89910475ba6dbd3ca6dd4e5a1', '탈테', '2022-10-25 12:05:15', '01031232421', 'kaebi1995@gmail.com', 'img/noImg.jpg');

-- 임의의 주문내역 생성
INSERT INTO `elrek1991`.`orders` (`orderNo`, `memberId`, `prodTotalPrice`, `deliveryOption`, `totalPrice`, `orderDate`, `isMember`, `phoneNumber`, `name`, `usedPoint`, `accumPoint`) VALUES ('78', 'test', '41000', '기본', '44000', '2022-10-27 13:01:36', 'Y', '01041103923', '테슷트', '0', '410');

-- 임의의 주문상세내역 생성
INSERT INTO `elrek1991`.`detailOrder` (`orderDetailNo`, `orderNo`, `prodNo`, `qty`, `prodSubTotalPrice`, `cancelStatus`, `returnOrExchange`, `purchaseConfirm`, `returnOrExchangeConfirm`, `reviewStatus`, `memberId`) VALUES ('', '77', 'DT1RX37W00M00S00C00E00', '4', '6000', 'N', 'N', 'N', 'N', 'N', 'test');
INSERT INTO `elrek1991`.`detailOrder` (`orderNo`, `prodNo`, `qty`, `prodSubTotalPrice`, `cancelStatus`, `returnOrExchange`, `purchaseConfirm`, `returnOrExchangeConfirm`, `reviewStatus`, `memberId`) VALUES ('77', 'DT1RX37W00M00S00C00E00', '1', '35000', 'N', 'N', 'N', 'N', 'N', 'test');

-- 임의의 배송내역 생성


-- 특정 회원의 주문 목록을 가져오는 쿼리
select orderNo from orders where memberId = 'test' order by orderNo desc;

-- 주문 번호에 따른 주문번호 컬럼내용을 가져오는 쿼리
select * from orders where orderNo = 77;

select memberId from orders where orderNo = 77;

-- 특정 회원의 주문에 따른 주문상세내역과 배송내역을 같이 보여주는 쿼리 (equi조인)
select d.*, v.deliveryNo, v.recipient, v.phoneNumber, v.address, v.detailAddress, v.postCode, v.deliveryRequest, v.deliveryStatus, v.returnDeliveryNo, v.deliveryCmplDate, p.prodName, p.prodPrice, i.originalFile
from detailOrder d inner JOIN delivery v inner join product p inner join prodImg i
on d.orderDetailNo = v.orderDetailNo and d.prodNo = p.prodNo and d.prodNo = i.prodN
where d.orderNo = 77
order by d.orderNo desc;

-- 특정 회원의 주문에 따른 주문상세내역과 배송내역을 같이 보여주는 쿼리 (equi조인) + 다중행 서브쿼리까지
select d.*, v.deliveryNo, v.recipient, v.phoneNumber, v.address, v.detailAddress, v.postCode, v.deliveryRequest, v.deliveryStatus, v.returnDeliveryNo, v.deliveryCmplDate
from detailOrder d inner JOIN delivery v
on d.orderDetailNo = v.orderDetailNo 
where d.orderNo = any(select orderNo from orders where memberId = 'test')
order by d.orderNo desc;

-- 주문취소시 주문상세내역 업데이트.
update detailOrder set cancelStatus = 'Y' where orderNo = '69';

-- 주문취소시 배송 업데이트.
update delivery set deliveryStatus = '취소' where orderNo = '14' and deliveryStatus = '결제완료';

-- 주문취소시 쿠폰 업데이트.
update couponUsed set useDate = null, orderNo = null where orderNo = '18';

-- 주문취소시 사용했던 포인트 가져오기
select usedPoint from orders where orderNo = 3;

-- 주문취소시 사용했던 포인트 재적립
insert into pointHistory(memberId, pointNo, pointHistory, orderNo) values('test', 5, 300, 5);

-- 주문취소시 적립되었던 포인트 가져오기
select accumPoint from orders where orderNo = 3;

-- 주문취소시 적립되었던 포인트 차감
insert into pointHistory(memberId, pointNo, pointHistory, orderNo) values('test', 5, -300, 5);

-- 주문취소시 재적립된 포인트 업데이트


-- 교환반품 테이블 인서트
insert into exchange(orderDetailNo, exchangeType, reasonNo, reasonContent) values('107', '교환' , '1', '없음');

-- 주문 중 교환요청과 반품요청 상태인 상세주문번호를 찾는다. --
select * from detailOrder where orderNo = 77 and returnOrExchange != 'N';

select * from delivery;

-- 배송테이블에서 현재 배송상태를 가져온다. 
select deliveryStatus from delivery where orderDetailNo = 108;


-- 주문상세 정보 가져오기
select * from detailOrder where orderDetailNo = 107;

select max(orderDetailNo) from detailOrder;

select * from delivery where orderDetailNo = 107;



-- 주문취소시 쿠폰 업데이트.
-- update orders set prodTotalPrice = #{prodTotalPrice} where orderNo = #{orderNo};

select * from detailOrder where orderNo = 77;
select orderDetailNo from detailOrder where orderNo = 77;
select sum(prodSubTotalPrice) from detailOrder where orderNo = 77;



