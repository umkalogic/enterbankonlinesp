create database if not exists `mytestbankdb`;
use `mytestbankdb`;

create table if not exists ukraine_regions
(
    region_id           int          not null
        primary key auto_increment,
    ukraine_region_name varchar(255) not null,
    constraint UK_region
        unique (ukraine_region_name)
);

create table if not exists person_data
(
    person_id          int                      not null
        primary key auto_increment,
    birth_date         datetime                 not null,
    first_name         varchar(255)             not null,
    id_number_tax_code bigint                   not null,
    last_name          varchar(255)             not null,
    middle_name        varchar(255) default ' ' null,
    secret_word        varchar(255)             not null,
    constraint UK_tax_code
        unique (id_number_tax_code)
);


create table if not exists roles
(
    role_id int                         not null
        primary key auto_increment,
    role    varchar(255) default 'USER' null
);

create table if not exists users
(
    user_id   int               not null
        primary key auto_increment,
    email     varchar(255)      not null,
    is_active tinyint default 1 null,
    password  varchar(255)      not null,
    user_name varchar(255)      not null,
    person_id int               not null,
    role_id   int               not null,
    constraint UK_email
        unique (email),
    constraint UK_user_name
        unique (user_name),
    constraint FK_person_id_users
        foreign key (person_id) references person_data (person_id),
    constraint FK_role_id_users
        foreign key (role_id) references roles (role_id)
);

create table if not exists address
(
    address_id    int               not null
        primary key auto_increment,
    address_line1 varchar(255)      not null,
    address_line2 varchar(255)      null,
    city          varchar(255)      not null,
    is_registered tinyint default 1 null,
    region_id     int               not null,
    constraint FK_region_id
        foreign key (region_id) references ukraine_regions (region_id)
);

create table if not exists person_addresses
(
    person_id  int not null,
    address_id int not null,
    primary key (person_id, address_id),
    constraint FK_address_id_address
        foreign key (address_id) references address (address_id)
        on update cascade on delete cascade,
    constraint FK_person_id_address
        foreign key (person_id) references person_data (person_id)
);

create table if not exists phone_number
(
    phone_id           int                       not null
        primary key auto_increment,
    is_primary         bit                       not null,
    phone              varchar(9)                not null,
    phone_country_code varchar(4) default '+380' not null,
    person_id          int                       not null,
    constraint FK_person_id_phone
        foreign key (person_id) references person_data (person_id)
);

create table if not exists passport_data
(
    passport_id         int               not null
        primary key auto_increment,
    is_domestic         tinyint default 1 not null,
    passport_issue_date datetime          not null,
    passport_issued_by  varchar(255)      not null,
    passport_number     int               not null,
    passport_series     varchar(2)        not null,
    person_id           int               not null,
    constraint FK_person_id_passport
        foreign key (person_id) references person_data (person_id)
);

create table if not exists bank_accounts
(
    bank_account_id     int                                    not null
        primary key auto_increment,
    account_amount      double(16, 2) default 0.00             not null,
    account_type        varchar(255)  default 'Regular'        not null,
    bank_account_number varchar(14)   default '26320130000000' not null,
    currency            varchar(10)   default '₴'              not null,
    enable_request      tinyint       default 0                null,
    is_active           tinyint       default 1                not null,
    person_id           int                                    not null,
    constraint UK_ba_number
        unique (bank_account_number),
    constraint FK_person_id
        foreign key (person_id) references person_data (person_id)
);

create table if not exists credit_card
(
    credit_card_id  int                                       not null
        primary key auto_increment,
    card_name       varchar(45) default 'MasterCard'          not null,
    card_number     bigint                                    not null,
    cvc2            varchar(3)                                not null,
    expire_date     datetime    default '2022-01-05 23:59:59' not null,
    is_active       tinyint     default 1                     not null,
    issue_date      datetime    default '2021-01-05 00:00:00' not null,
    owner_name      varchar(255)                              not null,
    bank_account_id int                                       not null,
    constraint UK_card_number
        unique (card_number),
    constraint FK_bank_account_id
        foreign key (bank_account_id) references bank_accounts (bank_account_id)
);

create table if not exists payment
(
    payment_id      int                                 not null
        primary key auto_increment,
    is_sent         tinyint   default 0                 not null,
    payment_amount  double(16, 2)                       not null,
    payment_date    timestamp default CURRENT_TIMESTAMP not null,
    to_bank_account varchar(14)                         not null,
    bank_account_id int                                 not null,
    constraint FK_bank_account_id_payment
        foreign key (bank_account_id) references bank_accounts (bank_account_id)
);

