create table users (
    id uuid primary key default gen_random_uuid(),

    email text not null unique,
    password text not null,

    full_name text not null,

    avatar_url text default '',
    is_verified boolean default false,

    role_id text references roles (id) on delete restrict on update cascade not null default 'user',

    updated_at timestamptz(0) not null default now(),
    created_at timestamptz(0) not null
);
