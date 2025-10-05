create table if not exists TRANSACTIONS
(
    ID uuid  default random_uuid() primary key,
    AMOUNT int,
    CREATED_DATE timestamp default current_timestamp(),
    REFERENCE varchar(255),
    SLOGAN varchar(255)
);