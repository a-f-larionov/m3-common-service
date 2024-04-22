create table users_stuff
(
    userId       bigint not null,
    hummerQty    bigint not null default '0',
    shuffleQty   bigint not null default '0',
    goldQty      bigint not null default '0',
    lightningQty bigint not null,
    primary key (userId)
)
