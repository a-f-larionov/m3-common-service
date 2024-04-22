create table cache_top_score
(
    userId    serial not null,
    pointId   bigint not null default '0',
    place1Uid bigint not null default '0',
    place2Uid bigint not null default '0',
    place3Uid bigint not null default '0',
    pos       bigint not null default '0',
    primary key (userId, pointId)
)