create table if not exists user_agents
(
    id    bigserial       not null,
    uid   bigint       not null,
    agent varchar(512) not null default '',
    primary key (id)
);