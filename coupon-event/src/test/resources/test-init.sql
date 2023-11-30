truncate table member;
truncate table promotion;
truncate table coupon;

insert into promotion values ('promotion1', 'title1', 'context1', 2, '2023-10-28','2023-10-29','TEN_PERCENTAGE');
insert into promotion values ('promotion2', 'title2', 'context2', 2, '2023-10-30','2024-11-30','TEN_PERCENTAGE');
insert into promotion values ('promotion3', 'title3', 'context3', 2, '2023-10-31','2024-11-30','TEN_PERCENTAGE');
insert into coupon values ('coupon1', 'promotion1', null, 'TEN_PERCENTAGE', '2023-12-31', null, 'CREATED');
insert into coupon values ('coupon2', 'promotion1', null, 'TEN_PERCENTAGE', '2023-12-31', null, 'CREATED');
insert into coupon values ('coupon3', 'promotion2', null, 'TEN_PERCENTAGE', '2023-12-31', null, 'CREATED');
insert into coupon values ('coupon4', 'promotion2', null, 'TEN_PERCENTAGE', '2023-12-31', null, 'CREATED');
insert into coupon values ('coupon5', 'promotion3', null, 'TEN_PERCENTAGE', '2023-12-31', null, 'CREATED');
insert into coupon values ('coupon6', 'promotion3', '2023-10-31', 'TEN_PERCENTAGE', '2023-12-31', '1', 'CREATED');
insert into member values ('1');
insert into member values ('2');
insert into member values ('3');