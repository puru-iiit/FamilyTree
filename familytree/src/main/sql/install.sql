use family

CREATE TABLE person
(
id int AUTO_INCREMENT PRIMARY KEY,
first_name varchar(80) not null,
middle_name varchar(80) null,
last_name varchar(80) null,
gender varchar (10) null,
dob date not null,
dod date not null,
mobile_no varchar(10) null,
landline_no varchar(10) null,
passport_no varchar(10) null,
pan_no varchar(10) null,
created_by varchar(80) null,
last_modified_by varchar(80) null,
creation_date datetime  not null,
last_modified_date datetime not null
);

 
CREATE TABLE address
(
id int AUTO_INCREMENT PRIMARY KEY,
person_id int not null,
address_part_1 varchar(100),
address_part_2 varchar(100),
city varchar(100),
state varchar(100),
country varchar(100),
created_by varchar(80) null,
last_modified_by varchar(80) null,
creation_date datetime  not null,
last_modified_date datetime  not null
);
 
	

CREATE TABLE relation
(
first_Person_id int not null,
second_person_id int not null,
first_to_second_relation_type int not null,
second_to_first_relation_Type int null,
created_by varchar(80) null,
last_modified_by varchar(80) null,
creation_date datetime  not null,
last_modified_date datetime  not null,
CONSTRAINT pk_RelationID PRIMARY KEY (first_Person_id,second_person_id,first_to_second_relation_type)
);