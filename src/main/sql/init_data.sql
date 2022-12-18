insert into hello.user (username, password, gmt_created, gmt_modified)
VALUES ('test', 'test', now(), now()),
       ('dev', 'dev', now(), now());

insert into hello.admin_user (username, password, gmt_created, gmt_modified)
VALUES ('admin', 'admin', now(), now());