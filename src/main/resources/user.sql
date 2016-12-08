CREATE TABLE user
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(20),
    password VARCHAR(20),
    gender VARCHAR(10),
    email VARCHAR(50),
    telephone VARCHAR(20),
    introduce VARCHAR(100),
    activecode VARCHAR(50),
    state INT(11),
    role VARCHAR(10) DEFAULT '普通用户',
    registerTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);