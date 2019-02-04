insert into role (id, role_name) values (1, 'ROLE_ADMIN');
insert into role (id, role_name) values (2, 'ROLE_PERSON');


insert into person (ID, EMAIL, NAME, REGISTRATION_NUMBER) values (1, 'adminUser@gmail.com', 'adminUser', '$2a$10$9vr4yrTqkMpxro3XpQu.quW5qNecErj4ILu11xnf0RHg4UNNt4TKa');
insert into person (ID, EMAIL, NAME, REGISTRATION_NUMBER) values (2, 'testUser@gmail.com' , 'testUser' , '$2a$10$9vr4yrTqkMpxro3XpQu.quW5qNecErj4ILu11xnf0RHg4UNNt4TKa');

insert into PERSON_ROLE (PERSON_ID, ROLE_ID) values (1, 1);
insert into PERSON_ROLE (PERSON_ID, ROLE_ID) values (2, 2);




insert into food_type (id, name, CREATED_AT, UPDATED_AT )
values(10001, 'fruit', now(), now() );

insert into food_type (id,  name, CREATED_AT, UPDATED_AT )
values(10002, 'meat', now(), now() );

insert into food_type (id,  name, CREATED_AT, UPDATED_AT )
values(10003, 'fish', now(), now() );

insert into food_type (id,  name, CREATED_AT, UPDATED_AT )
values(10004, 'yoghurt', now(), now() );


insert into foods ( name, quantity, food_type_id, CREATED_AT, UPDATED_AT )
values('bread', 2, 10001, now(), now() );
