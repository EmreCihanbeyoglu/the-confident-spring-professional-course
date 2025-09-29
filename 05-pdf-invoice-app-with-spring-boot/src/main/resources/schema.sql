create table if not exists invoices
(
    id      uuid  default random_uuid() primary key,
    pdfUrl varchar(255),
    userId varchar(255),
    amount  int
    );