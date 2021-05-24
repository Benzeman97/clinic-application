create table if not exists billing(id int not null auto_increment,billied_date_time timestamp,visited_id varchar(100),
constraint pk_id primary key(id),constraint fk_visitedId foreign key(visited_id) references patient_db.visit(visted_id)
)
auto_increment = 1006