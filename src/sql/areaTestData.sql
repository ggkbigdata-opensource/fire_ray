insert into user_concern_area(user_id,area_type,area_id,create_date)
values(0,0,0,sysdate());

insert into district(name,remark,images,mod_user,mod_date)
values('天河区','','',1,sysdate());

insert into street(name,remark,district_id,images,longitude,latitude,mod_user,mod_date)
values('沙河大街','',1,'',123.45,123.45,1,sysdate());

insert into street(name,remark,district_id,images,longitude,latitude,mod_user,mod_date)
values('林和西街','',1,'',125.45,125.45,1,sysdate());

INSERT INTO BLOCK (name,block_type,street_id,longitude,latitude,images,remark,mod_user,mod_time)
values('沙河市场','marcket',1,125.45,125.45,'','',1,sysdate());

INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-01',1,1,1,100,70,30,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-02',1,1,1,200,80,40,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-03',1,1,1,10,5,4,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-04',1,1,1,800,500,200,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-05',1,1,1,4,2,1,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-06',1,1,1,0,0,0,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-07',1,1,1,86,53,20,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-08',1,1,1,120,58,20,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-09',1,1,1,123,100,80,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-10',1,1,1,200,150,130,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-11',1,1,1,8,5,2,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2015','2015-12',1,1,1,400,300,200,SYSDATE(),1);

INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2016','2016-01',1,1,1,80,72,31,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2016','2016-02',1,1,1,100,80,40,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2016','2016-03',1,1,1,100,51,41,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2016','2016-04',1,1,1,400,300,200,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2016','2016-05',1,1,1,0,0,0,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2016','2016-06',1,1,1,20,10,10,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2016','2016-07',1,1,1,84,53,20,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2016','2016-08',1,1,1,120,58,20,SYSDATE(),1);
INSERT INTO FIRE_EVENT_SUM (YEAR,MONTH,district_id,street_id,block_id,original_fire_num,confirm_fire_num,smoke_fire_num,create_date,create_user) 
VALUES('2016','2016-09',1,1,1,12,10,8,SYSDATE(),1);




