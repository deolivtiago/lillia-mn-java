insert into roles (id, permissions, created_at) values
('user', '{GET:auth/info}'::text[], now()),
('root', default, now());
