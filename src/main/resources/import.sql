--Student Hardcoded data
INSERT INTO users (id,active,password,roles,user_name) VALUES (500,true,'$2a$12$G58.q0KVfATzsD.foNJTD.KlFJYjVOOJQ8ubsfoIKqYuF2cYM/BSm','ROLE_STUDENT','john'); --password : papi
INSERT INTO users (id,active,password,roles,user_name) VALUES (502,true,'$2a$12$G58.q0KVfATzsD.foNJTD.KlFJYjVOOJQ8ubsfoIKqYuF2cYM/BSm','ROLE_STUDENT','xristos'); --password : papi
INSERT INTO users (id,active,password,roles,user_name) VALUES (503,true,'$2a$12$G58.q0KVfATzsD.foNJTD.KlFJYjVOOJQ8ubsfoIKqYuF2cYM/BSm','ROLE_STUDENT','nikos'); --password : papi
INSERT INTO users (id,active,password,roles,user_name) VALUES (504,true,'$2a$12$G58.q0KVfATzsD.foNJTD.KlFJYjVOOJQ8ubsfoIKqYuF2cYM/BSm','ROLE_STUDENT','dimitra'); --password : papi
INSERT INTO users (id,active,password,roles,user_name) VALUES (505,true,'$2a$12$G58.q0KVfATzsD.foNJTD.KlFJYjVOOJQ8ubsfoIKqYuF2cYM/BSm','ROLE_STUDENT','maria'); --password : papi
INSERT INTO users (id,active,password,roles,user_name) VALUES (501,true,'$2a$12$OJJ1z4bx5ArPZvEBi1RdE.htMf7.zEEk9YXl9Pf99.i8jbex7GtfO','ROLE_STUDENT','kostas'); --password : papaki
INSERT INTO users (id,active,password,roles,user_name) VALUES (509,true,'$2a$12$OJJ1z4bx5ArPZvEBi1RdE.htMf7.zEEk9YXl9Pf99.i8jbex7GtfO','ROLE_STUDENT','eleni'); --password : papi
--Teacher Hardcoded data
INSERT INTO users (id,active,password,roles,user_name) VALUES (506,true,'$2a$12$a9UeVEbTJoETWLd3LCBRHeC1ep6AMRWZP0NX/qwd/meWnIskLMKiC','ROLE_PROFESSOR','vaggelis'); --password : pass
INSERT INTO users (id,active,password,roles,user_name) VALUES (507,true,'$2a$12$a9UeVEbTJoETWLd3LCBRHeC1ep6AMRWZP0NX/qwd/meWnIskLMKiC','ROLE_PROFESSOR','xristina'); --password : pass
INSERT INTO users (id,active,password,roles,user_name) VALUES (508,true,'$2a$12$a9UeVEbTJoETWLd3LCBRHeC1ep6AMRWZP0NX/qwd/meWnIskLMKiC','ROLE_PROFESSOR','adonis'); --password : pass

--Request Hardcoded data
INSERT INTO requests (id,name,mark,email,Dest,status,Uid, text, mail) VALUES (100,'Γιάννης', 8, 'john@gmail.com','vaggelis', 'Pending', 'john', 'Συστατική επιστολή για δουλειά', 'company4@gmail.com'); --password: papi
INSERT INTO requests (id,name,mark,email,Dest,status,Uid, text, mail) VALUES (200,'Γιάννης', 7, 'john@gmail.com','vaggelis', 'Pending', 'john','Συστατική επιστολή για μεταπτυχιακό', 'university@gmail.com'); --password: papi
INSERT INTO requests (id,name,mark,email,Dest,status,Uid, text, mail) VALUES (300,'Ελένη', 7, 'eleni@gmail.com','vaggelis', 'Pending', 'eleni','Συστατική επιστολή για εταιρεία', 'company5@hotmail.gr'); --password: papi

--Administrator Hardcoded data
INSERT INTO users (id,active,password,roles,user_name) VALUES (100,true,'$2a$12$a9UeVEbTJoETWLd3LCBRHeC1ep6AMRWZP0NX/qwd/meWnIskLMKiC','ROLE_SECRETARY','secretary'); --password : pass