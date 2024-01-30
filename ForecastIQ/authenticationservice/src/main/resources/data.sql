create table user_registration (id int auto_increment primary key,

    first_name varchar(25),
    last_name varchar(25),
    email varchar(50),
    password varchar(25),
    contact_number varchar(25),
   confirm_password varchar(25));

create table wish_list (id int auto_increment primary key,

    email varchar(50),
    city varchar(50),
    weather varchar(100),
    temperature varchar(100));