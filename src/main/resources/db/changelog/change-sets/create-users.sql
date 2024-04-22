create table users
(
    id               bigserial not null,
    socNetUserId     bigint null,
    socNetTypeId     bigint    not null,
    create_tm        bigint             default null,
    login_tm         bigint             default null,
    logout_tm        bigint             default null,
    nextPointId      bigint    not null default '1',
    fullRecoveryTime bigint             default null,
    primary key (id)
);

create index users_socNetUniqueKey on users (socNetUserId, socNetTypeId);