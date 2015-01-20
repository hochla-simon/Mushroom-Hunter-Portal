insert into Hunter (description, firstName, nick, password, surname) values ('testAdmin', 'AdminFirstName', 'admin', 'admin', 'AdminSurname');
insert into Hunter (description, firstName, nick, password, surname) values ('testUser', 'UserFirstName', 'user', 'user', 'UserSurname');
insert into HunterRole (hunter_id, role) values (1,'ROLE_ADMIN');
insert into HunterRole (hunter_id, role) values (2,'ROLE_USER');