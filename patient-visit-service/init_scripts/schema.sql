create user 'patient_db'@'%' identified by '14292';

grant all privileges on *.* to 'patient_db'@'%';

flush privileges;

create database patient_db;

use patient_db;

create table if not exists holiday(holiday_date varchar(100),holiday_name varchar(100),created_by varchar(100),
modified_by varchar(100),created_date_time timestamp,modified_date_time timestamp,constraint pk_date_holiday primary key(holiday_date));

create table if not exists patient(patient_id varchar(30),patient_name varchar(120),gender varchar(5),age int,created_by varchar(120),
created_date_time timestamp,modified_date_time timestamp,constraint pk_patientId primary key(patient_id));

create table if not exists physician(reg_no varchar(25),name varchar(120),created_by varchar(100),modified_by varchar(100),
created_date_time timestamp,modified_date_time timestamp,constraint pk_reg_no primary key(reg_no));

create table if not exists visit(visited_id varchar(40),reason varchar(300),visit_date_time varchar(100),created_by varchar(100),
modified_by varchar(100),created_date_time timestamp,modified_date_time timestamp,reg_no varchar(25),patient_id varchar(30),
constraint pk_visited_id primary key(visited_id),constraint fk_reg_no foreign key(reg_no) references physician(reg_no),
constraint fk_patient_id foreign key(patient_id) references patient(patient_id));