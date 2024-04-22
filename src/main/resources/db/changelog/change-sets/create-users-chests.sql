create table users_chests
(
    userId  bigserial not null,
    chestId bigint not null,
    primary key (userId)
);