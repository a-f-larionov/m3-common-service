create table users_points
(
    userId  bigint not null,
    pointId bigint not null,
    score   bigint not null,
    primary key (userId, pointId)
);

create index userId on users_points (userId, pointId)