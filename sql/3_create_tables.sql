use freelance_platform_db;

create table if not exists Users (
                                     UserId      int     not null auto_increment,
                                     FirstName   varchar(250)    default '',
                                     LastName    varchar(250)    default '',
                                     RegDate     timestamp,
                                     UserLogin   varchar(250)    unique,
                                     UserEmail   varchar(250)    unique,
                                     UserPassword varchar(250),
    /*
     Roles:
     1 - Administrator;
     2 - Freelancer;
     3 - Client
     */
                                     UserRole   tinyint,
                                     primary key (UserId),
                                     check ( UserEmail like '_%@_%._%')
);

create index idx_fullname
    on Users (FirstName, LastName);

create index idx_email
    on Users (UserEmail);

create index idx_login
    on Users (UserLogin);

create index idx_regdate
    on Users (RegDate);

create table if not exists Categories (
                                          CategoryId      int             not null auto_increment,
                                          CategoryName    varchar(250)    not null unique,
                                          primary key (CategoryId)
);

create index idx_catname
    on Categories (CategoryName);

create trigger User_Before_Insert before insert on Users
    FOR EACH ROW
BEGIN
    set new.RegDate = NOW();
END;

create table if not exists Skills (
                                      SkillId     int not null auto_increment,
                                      UserId      int,
                                      CategoryId  int,
                                      primary key (SkillId),
                                      foreign key (UserId) references Users(UserId) on delete cascade on update cascade ,
                                      foreign key (CategoryId) references Categories(CategoryId)  on delete cascade on update cascade
);

create index idx_usersskillid
    on Skills(UserId);

create index idx_categoryskillid
    on Skills(CategoryId);

create table if not exists Orders (
                                      OrderId         int             not null auto_increment,
                                      OrderName       varchar(250)    not null,
                                      OrderRegDate    timestamp,
                                      OrderDeadLine   timestamp,
                                      OrderDesc       text,
                                      ClientId        int,
                                      primary key (OrderId),
                                      foreign key (ClientId) references Users(UserId) on delete cascade on update cascade
);
create trigger Orders_Before_Insert before insert on Orders
    for each row
begin
    set new.OrderRegDate = NOW();
end;

create index idx_ordername
    on Orders(OrderName);

create index idx_orderregdate
    on Orders(OrderRegDate);

create index idx_orderdeaddate
    on Orders(OrderDeadLine);

create index idx_clientid
    on Orders(ClientId);

create table if not exists OrderProperties (
                                               OrderPropertyId     int     not null auto_increment,
                                               OrderId             int,
                                               CategoryId          int,
                                               primary key (OrderPropertyId),
                                               foreign key (OrderId) references Orders(OrderId) on delete cascade on update cascade,
                                               foreign key (CategoryId) references Categories(CategoryId) on delete cascade on update cascade
);

create index idx_proporderid
    on OrderProperties(OrderId);

create index idx_catorderpropid
    on OrderProperties(CategoryId);
create table Works (
                       WorkId              int not null auto_increment,
                       OrderId             int,
                       UserId              int,
                       Grade               int,
    /*
     1 - not confirmed
     2 - confirmed
     3 - performed
     */
                       Status              tinyint default 1,
                       primary key (WorkId),
                       foreign key (UserId) references Users(UserId) on delete cascade on update cascade,
                       foreign key (OrderId) references orders(OrderId) on delete cascade on update cascade,
                       check ( Grade between 1 and 10)
);

create index idx_workuserid
    on Works(UserId);

create index idx_workorderproperty
    on Works(OrderId);

create unique index unq_orderproperties
    on orderproperties (OrderId, CategoryId);

create unique index unq_works
    on works (OrderId, UserId);

create unique index unq_skills
    on skills (UserId, CategoryId);

alter table users add Status tinyint default 1;