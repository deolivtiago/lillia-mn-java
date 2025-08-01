create table roles (
    id text primary key,

    permissions text[] default '{}'::text[],

    updated_at timestamptz(0) not null default now(),
    created_at timestamptz(0) not null
);
