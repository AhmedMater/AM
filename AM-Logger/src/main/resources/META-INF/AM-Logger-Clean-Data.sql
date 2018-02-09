INSERT INTO bus_category (category, description) VALUES ('Us', 'User');
INSERT INTO bus_category (category, description) VALUES ('Cor', 'Course');
INSERT INTO bus_category (category, description) VALUES ('Sec', 'Section');
INSERT INTO bus_category (category, description) VALUES ('Tut', 'Tutor');
INSERT INTO bus_category (category, description) VALUES ('Art', 'Article');
INSERT INTO bus_category (category, description) VALUES ('Qu', 'Quiz');
INSERT INTO bus_category (category, description) VALUES ('Asg', 'Assignment');

INSERT INTO bus_log_type (type, description) VALUES ('N', 'New');
INSERT INTO bus_log_type (type, description) VALUES ('U', 'Update');
INSERT INTO bus_log_type (type, description) VALUES ('D', 'Delete');

INSERT INTO per_fn_status (status, description) VALUES ('S', 'Started');
INSERT INTO per_fn_status (status, description) VALUES ('C', 'Completed');
INSERT INTO per_fn_status (status, description) VALUES ('E', 'Error');

INSERT INTO per_log_type (type, description) VALUES ('SD', 'Start Debug');
INSERT INTO per_log_type (type, description) VALUES ('ED', 'End Debug');
INSERT INTO per_log_type (type, description) VALUES ('Er', 'Error Msg Only');
INSERT INTO per_log_type (type, description) VALUES ('EE', 'Error Msg With Exception');
INSERT INTO per_log_type (type, description) VALUES ('Ex', 'Exception Only');
INSERT INTO per_log_type (type, description) VALUES ('In', 'Info');
INSERT INTO per_log_type (type, description) VALUES ('Wr', 'Warn');