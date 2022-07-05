CREATE TABLE `appchat`.`login` (
  `username` NVARCHAR(45) NOT NULL primary key,
  `password` VARCHAR(45) NOT NULL);
SELECT * FROM appchat.login;
delete from `appchat`.`login`
where username = 'viet' and password ='d41d8cd98f00b204e9800998ecf8427e'
INSERT INTO `appchat`.`login` (`username`, `password`) VALUES ('viet', '827ccb0eea8a706c4c34a16891f84e7b');






DELETE FROM `appchat`.`login` 
WHERE (username="conan") AND (password=12345);
USE appchat;
SELECT * FROM loginlogin