create table payments
(
    id        bigserial not null,
    time      bigint not null default '0',
    userId    bigint not null default '0',
    orderId   bigint not null default '0',
    itemPrice bigint not null default '0',
    primary key (id)
);

create index payments_orderId on payments (orderId);