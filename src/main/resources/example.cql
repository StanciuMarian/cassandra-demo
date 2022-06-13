create keyspace if not exists testKeyspace with replication = {'class':'SimpleStrategy', 'replication_factor':'3'};

use testKeyspace;

select * from system_schema.keyspaces;

-- drop table testKeyspace.users_by_country;
create table if not exists users_by_country (
                                                name text,
                                                email text,
                                                age int,
                                                countryCode text,
                                                PRIMARY KEY ((countryCode), name, email)
    );

select * from users_by_country;

consistency one;
consistency;
consistency quorum;
consistency;

insert into users_by_country(countrycode, name, email, age) VALUES ('RO', 'Gigel', 'gigel@test.com', 25);
insert into users_by_country(countrycode, name, email, age) VALUES ('UK', 'John', 'john@test.com', 25);
insert into users_by_country(countrycode, name, email, age) VALUES ('RO', 'Gigel2', 'gigel2@test.com', 25);
insert into users_by_country(countrycode, name, email, age) VALUES ('UK', 'John2', 'john2@test.com', 25);

update users_by_country set age=30 where countryCode='RO' and name='Gigel' and email='gigel@test.com';
update users_by_country set age=30 where countryCode='RO' and name='Gigel3' and email='gigel3@test.com';

update testKeyspace.users_by_country set age=35 where countryCode='RO' and name='Ionel' and email='ionel@test.com';

select * from users_by_country where countrycode='RO';

delete from users_by_country where countryCode = 'RO' and name='Gigel3';

drop keyspace testKeyspace;