create user 'billing_db'@'%' identified by '14292';

grant all privileges on *.* to 'billing_db'@'%';

grant select,insert,update,delete on patient_db.visit to 'billing_db'@'%';

grant alter,references on patient_db.visit to 'billing_db'@'%';

flush privileges;

create table if not exists billing(id bigint not null auto_increment,billed_date_time timestamp,visited_id varchar(100),
constraint pk_id primary key(id),constraint fk_visited_id foreign key(visited_id) references patient_db.visit(visited_id))
auto_increment = 1006;


