use freelance_platform_db;
create table if not exists Users (
                                     UserId         int     not null auto_increment,
                                     FirstName      varchar(250)    default '',
                                     LastName       varchar(250)    default '',
                                     RegDate        timestamp,
                                     UserLogin      varchar(250)    not null unique,
                                     UserEmail      varchar(250)    not null unique,
                                     UserPassword   varchar(250)    not null,
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
    foreign key (UserId) references Users(UserId),
    foreign key (CategoryId) references Categories(CategoryId)
);

create index idx_usersskillid
    on Skills(UserId);

create index idx_categoryskillid
    on Skills(CategoryId);

create table if not exists ComMethods (
                                          ComMethodId     int             not null auto_increment,
                                          ComPic          blob,
                                          ComMethodName   varchar(250)    not null unique,
                                          primary key (ComMethodId)
);

create index idx_commethodname
    on ComMethods(ComMethodName);


create table if not exists UserAddresses (
                                             UserId      int,
                                             ComMethodId int,
                                             AddressName varchar(255),
                                             primary key (UserId, ComMethodId),
                                             foreign key (UserId) references Users(UserId),
                                             foreign key (ComMethodId) references ComMethods(ComMethodId)
);

create index idx_addressname
    on UserAddresses(AddressName);

create table if not exists Orders (
                                      OrderId         int             not null auto_increment,
                                      OrderName       varchar(250)    not null,
                                      OrderRegDate    timestamp,
                                      OrderDeadLine   timestamp,
                                      OrderDesc       text,
                                      ClientId        int,
                                      primary key (OrderId),
                                      foreign key (ClientId) references Users(UserId)
);

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
                                               foreign key (OrderId) references Orders(OrderId),
                                               foreign key (CategoryId) references Categories(CategoryId)
);

create index idx_proporderid
    on OrderProperties(OrderId);

create index idx_catorderpropid
    on OrderProperties(CategoryId);

create table Works (
    WorkId              int not null auto_increment,
    OrderPropertyId     int,
    UserId              int,
    Grade               int,
    primary key (WorkId),
    foreign key (UserId) references Users(UserId),
    foreign key (OrderPropertyId) references OrderProperties(OrderPropertyId),
    check (Grade between 0 and 10)
);

create index idx_workuserid
    on Works(UserId);

create index idx_workorderproperty
    on Works(OrderPropertyId);