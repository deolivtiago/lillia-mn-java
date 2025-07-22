create table tokens (
    id uuid primary key default gen_random_uuid(),

    access text not null unique,
    access_expires_at timestamptz(0) not null default now() + '1 day'::interval,

    refresh text not null unique,
    refresh_expires_at timestamptz(0) not null default now() + '7 days'::interval,

    user_id uuid references users (id) on delete cascade on update cascade not null,

    created_at timestamptz(0) not null
);
