################payment_schemes table
create table payment_schemes (
	id int primary key auto_increment, 
    doc_id int not null,
    hospital_id int not null,
    doc_charge double not null,
    hosp_charge double not null,
    tax double
);







