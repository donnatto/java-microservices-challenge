create table account
(
    account_id      bigint                                                            not null
        primary key,
    account_status  enum ('PENDING', 'ACTIVE', 'INACTIVE')                            null,
    account_type    enum ('SAVINGS_ACCOUNT', 'CHECKING_ACCOUNT')                      null,
    creation_status enum ('ACCOUNT_CREATED', 'CREATION_COMPLETED', 'CREATION_FAILED') null,
    current_balance bigint                                                            null,
    customer_id     binary(16)                                                        null,
    customer_name   varchar(255)                                                      null,
    initial_balance bigint                                                            null
);

create table transaction
(
    transaction_id     binary(16)                     not null
        primary key,
    amount             bigint                         null,
    final_balance      bigint                         null,
    initial_balance    bigint                         null,
    timestamp          datetime(6)                    null,
    transaction_status enum ('COMPLETED', 'REVERTED') null,
    type               enum ('DEPOSIT', 'WITHDRAWAL') null,
    account_id         bigint                         not null,
    constraint FK6g20fcr3bhr6bihgy24rq1r1b
        foreign key (account_id) references account (account_id)
            on delete cascade
);
