

start transaction;


use `acme-chorbi`;

revoke all privileges on `acme-chorbi`.* from 'acme-user'@'%';

revoke all privileges on `acme-chorbi`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';

drop user 'acme-manager'@'%';

drop database `acme-chorbi`;

commit;
