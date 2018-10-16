    create table bt_sequences (
        sequencename varchar(255) not null,
        sequencevalue int8,
        primary key (sequencename)
    );

    create table person (
       id int8 not null,
        first_name varchar(255),
        last_name varchar(255),
        version int4 not null,
        primary key (id)
    );

insert into bt_sequences (sequencename, sequencevalue)  values ('person',14);
insert into person (id, version, first_name, last_name)  values (1, 0, 'Bill', 'Gates');
insert into person (id, version, first_name, last_name)  values (2, 0, 'Steve', 'Jobs');
insert into person (id, version, first_name, last_name)  values (3, 0, 'Robert', 'Müller');
insert into person (id, version, first_name, last_name)  values (4, 0, 'Anna', 'Krüger');
insert into person (id, version, first_name, last_name)  values (5, 0, 'Markus', 'Albrecht');
insert into person (id, version, first_name, last_name)  values (6, 0, 'Robin', 'Schulz');
insert into person (id, version, first_name, last_name)  values (7, 0, 'Thomas', 'Courieres');
insert into person (id, version, first_name, last_name)  values (8, 0, 'Markus', 'Pöschl');
insert into person (id, version, first_name, last_name)  values (9, 0, 'Heike', 'Bisschop');
insert into person (id, version, first_name, last_name)  values (10, 0, 'Alina', 'Wettengel');
insert into person (id, version, first_name, last_name)  values (11, 0, 'Sara', 'Adams');
insert into person (id, version, first_name, last_name)  values (12, 0, 'Tatjana', 'Müller');
