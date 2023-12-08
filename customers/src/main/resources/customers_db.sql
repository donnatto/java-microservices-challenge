create table customer
(
    customer_id binary(16)                  not null
        primary key,
    address     varchar(255)                null,
    age         int                         not null,
    dni         varchar(255)                null,
    gender      enum ('MALE', 'FEMALE')     null,
    name        varchar(255)                null,
    phone       varchar(255)                null,
    password    varchar(255)                null,
    status      enum ('ACTIVE', 'INACTIVE') null
);