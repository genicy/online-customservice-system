drop database IF EXISTS customservice;
create database customservice;
use customservice;

DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` int(11) PRIMARY KEY auto_increment,
  `uid` int(11) ,                          
  `cid` int(11) ,    
  `dir`  int,                      	
  `cchat` varchar(255) ,	
  `ctime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `uip` int(11) NOT NULL,
  `cip` int(11) NOT NULL
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS `customservice`;
CREATE TABLE `customservice` (
  `cid` int(11) PRIMARY KEY auto_increment ,
  `password` varchar(40) NOT NULL,   
  `images` varchar(255) NOT NULL,       
  `gender` int NOT NULL,		
  `age` int NOT NULL,
  `serivcNnum` int(11) NOT NULL default 0,
  `mailaddress` varchar(255) NOT NULL,
  `telno` varchar(15) NOT NULL,  
  `regTime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `judge` double(10,2) default 10.00,	
  `status` int(11) default 1		
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS `csStatus`;
CREATE TABLE `csStatus` (
   `cid` int(11) PRIMARY KEY auto_increment ,
  `servicestatus` int(11) NOT NULL,
  `maxNum` int(11) NOT NULL default 10,	
  `numbering` int(11) NOT NULL default 0,	
   FOREIGN KEY(cid) REFERENCES customservice(cid)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS `quickanswer`;
CREATE TABLE `quickanswer` (
  `qid` int(11) PRIMARY KEY auto_increment,
  `cid` int(11) ,
  `quickbutt` varchar(25) NOT NULL,
  `answer` varchar(255) NOT NULL,	
   FOREIGN KEY(cid) REFERENCES customservice(cid)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `csadmin`;
CREATE TABLE `csadmin` (
  `aid` int(11) PRIMARY KEY auto_increment,
  `password` varchar(40) NOT NULL,
  `mailaddress` varchar(255) NOT NULL
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `robot`;
CREATE TABLE `robot` (
  `rid` int(11) ,
  `keyword` varchar(40) NOT NULL,
  `answer` varchar(255) NOT NULL,	
  `updateTime` timestamp NOT NULL default CURRENT_TIMESTAMP  ,
 FOREIGN KEY(rid) REFERENCES company(rid)
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `cname` varchar(255) NOT NULL,
  `rid` int(11) PRIMARY KEY auto_increment,
  `rstatus` int(11) default 1
)DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `queue`;
CREATE TABLE `queue` (
  `cid` int(11) PRIMARY KEY,
  `number` int(11) ,
 FOREIGN KEY(cid) REFERENCES customservice(cid)
)DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `customerqueue`;
CREATE TABLE `customerqueue` (
  `uid` int(11) PRIMARY KEY,
  `cid` int(11),
  `number` int(11) ,
 FOREIGN KEY(cid) REFERENCES customservice(cid)
)DEFAULT CHARSET=utf8;


insert  into customservice values(100001,'123456','图片路径',1,22,5,'250738437@qq.com','13456874851','2018-01-01',8.20,0);
insert  into customservice values(100002,'123456','图片路径',1,22,5,'646377081@qq.com','13456874851','2018-01-01',9.20,0);
insert  into customservice values(100003,'123456','图片路径',1,22,5,'646377082@qq.com','13456874851','2018-01-01',7.20,0);
insert  into customservice values(100004,'123456','图片路径',1,22,5,'646377083@qq.com','13456874851','2018-01-01',7.05,0);
insert  into customservice values(100005,'123456','图片路径',1,22,5,'646377088@126.com','13456874851','2018-01-01',8.20,0);



insert  into csStatus values (100001,0,5,0);
insert  into csStatus values (100002,0,4,0);
insert  into csStatus values (100003,0,3,0);
insert  into csStatus values (100004,0,4,0);
insert  into csStatus values (100005,0,5,0);


insert  into quickanswer values (100001,'f1','您好，我是客服HH。很高兴为您服务，有什么可以为您效劳的。');
insert  into quickanswer values (100001,'f2','您的问题我已了解，我会联系相关部门尽快解决问题，给您满意的答复。');
insert  into quickanswer values (100001,'f3','不好意思，现在接入的顾客有点多，可能会回复的慢一点，请您谅解');
insert  into quickanswer values (100001,'f4','感谢您的咨询，如果可以请您给个好评。');


insert  into  csadmin values (00001,'123456','646377080@qq.com');
insert  into  csadmin values (00002,'123456','646377081@qq.com');


insert  into  company values ('XXX公司',001,1);
insert  into  company values ('XXX技术有限公司',002,1);


insert  into  robot values (001,'产品1介绍','本产品是由XX制造，集多种技术于一身性价比贼高。','2018-01-01');
insert  into  robot values (001,'产品1价格','本产品原价XXX，目前由于活动优惠价为：XXX','2018-01-01');
insert  into  robot values (002,'业务1办理','业务1办理需经过XXX授权，在XXX填写相关信息完成办理','2018-01-01');
insert  into  robot values (002,'业务1介绍','办理业务1后，您可以享用XXX套餐，套餐内软件应用不限流量，套餐外流量0.1元/兆','2018-01-01');
