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


select * from members where memberId =  'test';
update members set memberPwd = sha1(md5('1234')) where memberId = 'test';
update members set memberPwd = sha1(md5('1234')) where memberId = 'test';

-- 주소지 추가하는 쿼리문
insert into deliveryInfo(memberId, address, detailAddress, recipient, recipientPhoneNumber, postCode)  values('test', '울릉도 동남쪽', '뱃길따라이백리', '몰루', 01012122323, '12345');
        