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

insert into bt_sequences (sequencename, sequencevalue)  values ('person',10);
insert into person (id, version, first_name, last_name)  values (1, 0, 'Bill', 'Gates');
insert into person (id, version, first_name, last_name)  values (2, 0, 'Steve', 'Jobs');

