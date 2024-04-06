-- 회원
drop table market_member;

create table market_member(
    member_id varchar2(20) primary key,
    member_pw varchar2(100) not null,
    member_name varchar2(20) not null,
    phone varchar2(20) not null,
    enabled char(1) default 1 check(enabled in ('1','0')),
    rolename varchar2(20) default 'ROLE_USER'
);

select * from market_member;


-- 게시글 
drop table market_board;
drop sequence market_board_seq;

create table market_board(
    board_num number primary key,
    member_id varchar2(20) references market_member(member_id) on delete cascade not null,
    title varchar2(200) not null,
    contents varchar2(2000) not null,
    input_date date default sysdate,
    category varchar2(50) check(category in ('clothes','shoes','bag')),
    soldout char(1) default 'N' check(soldout in ('N','Y')),
    buyer_id varchar2(20)
);
create sequence market_board_seq;

-- contents의 타입을 varchar2-> CLOB으로 변경
-- 1. 새로운 CLOB 타입의 컬럼 추가
ALTER TABLE market_board ADD (new_contents CLOB);

-- 2. 기존 컬럼의 데이터를 새로운 컬럼으로 복사
UPDATE market_board SET new_contents = contents;

-- 3. 기존 컬럼 삭제 또는 비활성화
ALTER TABLE market_board DROP COLUMN contents;

-- 3-1. 기존 컬럼 비활성화
ALTER TABLE market_board RENAME COLUMN new_contents TO contents;


select * from market_board;


-- 게시글 코멘트
drop table market_comment ;
drop sequence market_comment_seq;

create table market_comment(
    comment_num number primary key,
    board_num number references market_board(board_num) on delete cascade,
    member_id varchar2(20) references market_member(member_id) on delete cascade,
    comment_text varchar2(500),
    input_date date default sysdate
);
create sequence market_comment_seq;

select * from market_comment;

