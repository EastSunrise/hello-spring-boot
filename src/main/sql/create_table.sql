drop schema if exists hello;
create schema if not exists hello;

create table if not exists hello.admin_user
(
    id           int unsigned auto_increment,
    username     varchar(8)  not null,
    `password`   varchar(64) not null,
    gmt_created  datetime    not null,
    gmt_modified datetime    not null,
    primary key (id),
    unique key ui_admin_username (username)
);

create table if not exists hello.user
(
    id           int unsigned auto_increment,
    username     varchar(8)  not null,
    `password`   varchar(64) not null,
    gmt_created  datetime    not null,
    gmt_modified datetime    not null,
    primary key (id),
    unique key ui_username (username)
);

create table if not exists hello.user_record
(
    id           int unsigned auto_increment,
    user_id      int unsigned not null,
    record_name  varchar(16)  not null,
    record_type  tinyint      not null,
    record_time  datetime     not null,
    gmt_created  datetime     not null,
    gmt_modified datetime     not null,
    primary key (id),
    unique key ui_record_name (record_name)
)