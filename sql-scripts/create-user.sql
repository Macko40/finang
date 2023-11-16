-- Drop user first if they exist
DROP USER if exists 'finangportal'@'%' ;

-- Now create user with prop privileges
CREATE USER 'finangportal'@'%' IDENTIFIED BY 'finangportal';

-- GRANT ALL PRIVILEGES ON * . * TO 'finangportal'@'%';

GRANT ALL PRIVILEGES ON finangportal.* TO 'finangportal'@'localhost';
FLUSH PRIVILEGES;







