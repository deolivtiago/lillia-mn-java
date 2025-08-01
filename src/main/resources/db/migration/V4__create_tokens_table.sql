create table tokens (
    id uuid primary key default gen_random_uuid(),

    is_revoked boolean not null default false,

    username text references users (username) on delete cascade on update cascade not null,

    created_by text references users (username) on delete restrict on update cascade,
    updated_by text references users (username) on delete restrict on update cascade,

    updated_at timestamptz(0) not null default now(),
    created_at timestamptz(0) not null
);

--    expires_at timestamptz(0) not null default now() + '7 days'::interval,
