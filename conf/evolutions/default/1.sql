# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table passwords (
  id                        bigint not null,
  askdatetime               timestamp,
  enddatetime               timestamp,
  passphrase                varchar(255),
  isvalid                   boolean,
  timestamp                 timestamp,
  constraint pk_passwords primary key (id))
;

create table roles (
  id                        bigint not null,
  role                      varchar(255),
  timestamp                 timestamp,
  constraint pk_roles primary key (id))
;

create table sessions (
  id                        bigint not null,
  startdatetime             timestamp,
  maximumtimeelapse         bigint,
  macaddress                varchar(255),
  isactive                  boolean,
  time_stamp                timestamp,
  constraint pk_sessions primary key (id))
;

create table users (
  id                        bigint not null,
  firstname                 varchar(255),
  lastname                  varchar(255),
  email                     varchar(255),
  isactive                  boolean,
  timestamp                 timestamp,
  constraint pk_users primary key (id))
;

create sequence passwords_seq;

create sequence roles_seq;

create sequence sessions_seq;

create sequence users_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists passwords;

drop table if exists roles;

drop table if exists sessions;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists passwords_seq;

drop sequence if exists roles_seq;

drop sequence if exists sessions_seq;

drop sequence if exists users_seq;

