CREATE TABLE employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  firstName varchar(45) COLLATE utf8_bin DEFAULT NULL,
  lastName varchar(45) COLLATE utf8_bin DEFAULT NULL,
  gender varchar(45) COLLATE utf8_bin DEFAULT NULL,
  dob varchar(45) COLLATE utf8_bin DEFAULT NULL,
  department varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;