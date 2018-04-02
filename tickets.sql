create table member(
  email varchar(255) PRIMARY KEY,
  name varchar(255),
  password varchar(255),
  sex int,
  age int,
  address varchar(255),
  consumption double,
  score int,
  level int,
  active int
 );

create table `order`(
  orderid int PRIMARY KEY,
  booktime datetime,
  status int
);

create table activity(
  activityid int PRIMARY KEY,
  name varchar(255),
  time datetime,
  type int,
  description varchar(255),
  status int
);

create table venue(
  venueid int PRIMARY KEY,
  place varchar(255),
  description varchar(255),
  password varchar(255),
  seatnum int,
  status int,
  bala double
);

create table bank(
  accountid int PRIMARY KEY,
  paypassword varchar(255),
  balance double
);

create table ticket(
  ticketid int PRIMARY KEY AUTO_INCREMENT,
  activityid int,
  seattype int,
  row int,
  col int,
  price double,
  status int,
  locktime datetime,
  lockperson varchar(255),
  FOREIGN KEY (activityid) REFERENCES activity(activityid)
);

create table seat(
  seatid int PRIMARY KEY AUTO_INCREMENT,
  type int,
  price double,
  num int
);

create table own_card(
  email varchar(255),
  accountid int,
  PRIMARY KEY (email, accountid),
  FOREIGN KEY (email) REFERENCES member(email),
  FOREIGN KEY (accountid) REFERENCES bank(accountid)
);

create table own_ticket(
  orderid int,
  ticketid int,
  PRIMARY KEY (orderid, ticketid),
  FOREIGN KEY (orderid) REFERENCES `order`(orderid),
  FOREIGN KEY (ticketid) REFERENCES ticket(ticketid)
);

create table has_seat(
  activityid int,
  seatid int,
  PRIMARY KEY (activityid, seatid),
  FOREIGN KEY (activityid) REFERENCES activity(activityid),
  FOREIGN KEY (seatid) REFERENCES seat(seatid)
);

create table book(
  email varchar(255),
  orderid int,
  PRIMARY KEY (email, orderid),
  FOREIGN KEY (email) REFERENCES member(email),
  FOREIGN KEY (orderid) REFERENCES `order`(orderid)
);

create table plan(
  venueid int,
  activityid int,
  PRIMARY KEY (venueid, activityid),
  FOREIGN KEY (venueid) REFERENCES venue(venueid),
  FOREIGN KEY (activityid) REFERENCES activity(activityid)
);

create table presale(
  email varchar(255),
  orderid int,
  activityid int,
  type varchar(255),
  quantity varchar(255),
  price varchar(255),
  PRIMARY KEY (orderid, price)
);

create table record(
  activityid int,
  activityname varchar(255),
  orderid int,
  price double,
  operate int,
  PRIMARY KEY (orderid, price, operate)
);

create table manager(
  name varchar(255) PRIMARY KEY,
  password varchar(255),
  bala double
);

create table coupon(
  email varchar(255),
  price double,
  quantity int,
  PRIMARY KEY (email, price, quantity)
);