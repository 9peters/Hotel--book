-- 테이블 전체 확인
select *
  from tab;

-- 시퀀스 전체 확인
select *
  from user_sequences;

-- 생성 시작
create sequence s_members nocache;

create table t_members (
      member_no number
                constraint member_no_pk primary key,
     permission number,
             id varchar2(20),
       password varchar2(20),
           name varchar2(20),
          email varchar2(20),
    phonenumber char(11),
       birthday varchar2(20),
        address varchar2(200)
);

create sequence s_room_type_no nocache;

create table t_room_type (
         room_type_no number
                      constraint room_type_no_pk primary key,
           room_type  varchar2(30),
              pyeong  number,
     max_person_count number,
           room_count number,
                price number,
         room_content varchar2(4000)
);

create sequence s_room_no nocache;

create table t_room (
         room_no number
                 constraint room_no_pk primary key,
    room_type_no number
                 constraint room_type_no_fk references t_room_type(room_type_no) on delete cascade         
);

create sequence s_amd_no nocache;

create table t_accommodation (
            amd_no number
                   constraint amd_no_pk primary key,
         member_no number
                   constraint amd_member_no_fk references t_members(member_no) on delete cascade,
              name varchar2(20),
          password varchar2(20),
       phonenumber char(11),
             email varchar2(20),
           room_no number
                   constraint amd_room_no_fk references t_room(room_no) on delete cascade,
      room_type_no number
                   constraint amd_room_type_no_fk references t_room_type(room_type_no) on delete cascade,
     check_in_date date,
    check_out_date date,
      people_count number,
          pay_date date
                   default sysdate,
        pay_method char(1),
        amd_status char(1)
                   default 'N'
);

create sequence s_inquiry_no nocache;

create table t_inquiry (
      inquiry_no number
                 constraint inquiry_no_pk primary key,
       member_no number
                 constraint inquiry_member_no_fk references t_members(member_no) on delete cascade,
            name varchar2(20),
           email varchar2(20),
     phonenumber char(11),
           title varchar2(100),
         content varchar2(4000),
inquiry_reg_date date
                 default sysdate
);

create sequence s_hotel_no nocache;

create table t_hotel_info ( 
      hotel_no number
               constraint hotel_info_hotel_no_pk primary key,
    hotel_name varchar2(20),
          info varchar2(4000),
        vision varchar2(4000),
    directions varchar2(4000)
);

create sequence s_review_no nocache;

create table t_review (
           review_no number
                     constraint review_no_pk primary key,
           member_no number
                     constraint review_member_no_fk references t_members(member_no) on delete cascade, 
                name varchar2(20),
            password varchar2(20),
         phonenumber char(11),
               email varchar2(20),
       review_rating number,
              amd_no number
                     constraint review_amd_no_fk references t_accommodation(amd_no) on delete cascade,
             room_no number
                     constraint review_room_no_fk references t_room(room_no) on delete cascade,
        room_type_no number
                     constraint review_room_type_no_fk references t_room_type(room_type_no) on delete cascade,
       check_in_date date,
      review_content varchar2(4000),
     review_reg_date date
                     default sysdate
);