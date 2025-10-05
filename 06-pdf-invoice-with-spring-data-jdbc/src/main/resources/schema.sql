create table if not exists "invoices"
(
    ID      uuid  default random_uuid() primary key,
    PDF_URL varchar(255),
    USER_ID varchar(255),
    amount  int
);