create table IF NOT EXISTS users
(
    username varchar(75)  not null primary key,
    password varchar(50) not null,
    enabled  boolean      not null
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table IF NOT EXISTS authorities
(
    username  varchar(75) not null,
    authority varchar(50) not null,
    constraint `fk_authorities_users` foreign key (username) references users (username),
    UNIQUE KEY `fk_authorities_uniq_1` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;