use `freelance_platform_db`;

create table if not exists Users
(
    `UserId`             int     NOT NULL AUTO_INCREMENT,
    `FirstName`          varchar(255),
    `LastName`           varchar(255),
    `DateOfRegistration` timestamp,
    `UserEmail`          varchar(255) not null,
    `Login`              varchar(255) not null,
    `UserPassword`       varchar(255) not null,
    /*
     * Role of the user:
     * 1. Administrator;
     * 2. Freelancer;
     * 3. Client.
     */
    `Role`               tinyint not null,
    primary key (`UserId`)
);

create table if not exists Categories
(
    `CategoryId`   int auto_increment,
    `CategoryName` varchar(255),
    primary key (`CategoryId`)
);

create table if not exists Orders
(
    `OrderID`            int          NOT NULL AUTO_INCREMENT,
    `Name`               varchar(255) not null,
    `DateOfRegistration` timestamp,
    `DateOfDeadline`     timestamp,
    `ClientId`           int          not null,
    `Description`        varchar(255),
    check ( `DateOfDeadline` > `DateOfRegistration` ),
    primary key (`OrderID`),
    foreign key (`ClientId`) references Users (`UserId`)
);


create table if not exists OrderCategory
(
    `OrderCategoryId` int not null auto_increment,
    `NumberOfPeople`  int default 1,
    `Price`           int default 0,
    `OrderId`         int not null,
    `CategoryId`      int not null,
    primary key (`OrderCategoryId`),
    foreign key (`OrderId`) references Orders (`OrderID`),
    foreign key (`CategoryId`) references Categories (`CategoryId`)
);

create table if not exists Skills
(
    `SkillId`    int auto_increment,
    `UserId`     int not null,
    `CategoryId` int default null,
    primary key (`SkillId`),
    foreign key (`UserId`) references Users (`UserId`),
    foreign key (`CategoryId`) references Categories (`CategoryId`)
);

create table if not exists Works
(
    `WorkId`          int auto_increment,
    `UserId`          int not null,
    `OrderCategoryId` int not null,
    `Rating`          int not null default 3,
    primary key (`WorkId`),
    foreign key (`UserId`) references Users (`UserId`),
    foreign key (`OrderCategoryId`) references OrderCategory (`OrderCategoryId`)
);