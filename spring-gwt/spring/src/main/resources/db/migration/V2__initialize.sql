CREATE TABLE users (id  integer, username varchar(255), password varchar(255), primary key (id));
CREATE TABLE roles (id  integer, rolename varchar(255), primary key (id));
CREATE TABLE user_roles (user_id  integer, role_id integer, primary key (user_id,role_id), foreign key (user_id) references users (id), foreign key (role_id) references roles (id));