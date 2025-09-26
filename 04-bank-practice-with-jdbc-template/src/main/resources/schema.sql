create table if not exists transactions
(
    id varchar(255) primary key,
    amount int,
    createdDate varchar(255),
    reference varchar(255),
    slogan varchar(255)
);