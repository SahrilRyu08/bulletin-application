create table if not exists posts (
    id      bigserial primary key,
    title   varchar(100) not null,
    author  varchar(30) not null,
    password    varchar(255) not null,
    content     text not null,
    view_count bigint not null default 0,
    created_at timestamp not null default now(),
    updated_at timestamp,
    deleted boolean not null default false
);