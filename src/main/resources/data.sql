insert into gn_user(id, name, email, password, created_by, created_date) values (1, 'one', 'one@gmail.com', 'passOne', 'system', {ts '2019-06-01 18:47:52.69'})
insert into gn_user(id, name, email, password, created_by, created_date) values (2, 'two', 'two@gmail.com', 'passTwo', 'system', {ts '2019-06-02 18:47:52.69'})
insert into gn_user(id, name, email, password, created_by, created_date) values (3, 'three', 'three@gmail.com', 'passThree', 'system', {ts '2019-06-03 18:47:52.69'})

insert into gn_relative(id, name, created_by, created_date, user_id) values (1, 'eleven', 'system', {ts '2019-06-03 18:47:52.69'}, 1)

insert into gn_menu(id, code, name, created_by, created_date) values (1, 'CREATOR', 'Pembuat Data', 'system', {ts '2019-06-03 18:47:52.69'})
insert into gn_menu(id, code, name, created_by, created_date) values (2, 'APPROVER', 'Pengyetujuh Data', 'system', {ts '2019-06-03 18:47:52.69'})

insert into gn_role(id, code, name, created_by, created_date) values (1, 'OPR', 'Operator', 'system', {ts '2019-06-03 18:47:52.69'})
insert into gn_role(id, code, name, created_by, created_date) values (2, 'SPV', 'Supervisor', 'system', {ts '2019-06-03 18:47:52.69'})

insert into gn_role_menu(role_id, menu_id) values (1, 1)
insert into gn_role_menu(role_id, menu_id) values (2, 2)

insert into gn_role_user(role_id, user_id) values (1, 1)
insert into gn_role_user(role_id, user_id) values (1, 2)
insert into gn_role_user(role_id, user_id) values (2, 3)

