DROP TABLE IF EXISTS userdetail;
DROP TABLE IF EXISTS portfolio;

-- intentionally naming this table as customer as "user" has some issues
CREATE TABLE userdetail
(
    id      int AUTO_INCREMENT primary key,
    name    VARCHAR(50),
    balance int
);

CREATE TABLE portfolio
(
    id       int AUTO_INCREMENT primary key,
    user_id  int,
    ticker   VARCHAR(10),
    quantity int,
    foreign key (user_id) references userdetail (id)
);
