INSERT INTO registered_application (app_id, app_name, description, username, user_pass, creation_date, quota_per_day, reached_max_quota)
  VALUES ('ASR', 'AMT-Services', 'AMT Backend System', 'AMT_Services_User', '123456', CURRENT_DATE, 5000, 0);

INSERT INTO category (category_id, app_id, description) VALUES ('COR', 'ASR', 'Course');
INSERT INTO category (category_id, app_id, description) VALUES ('USR', 'ASR', 'User');
INSERT INTO category (category_id, app_id, description) VALUES ('ART', 'ASR', 'Article');
INSERT INTO category (category_id, app_id, description) VALUES ('QUZ', 'ASR', 'Quiz');
INSERT INTO category (category_id, app_id, description) VALUES ('ASG', 'ASR', 'Assignment');
INSERT INTO category (category_id, app_id, description) VALUES ('BON', 'ASR', 'Bonus');

INSERT INTO notification_type (ntf_type, description) VALUES ('EML', 'Email');
INSERT INTO notification_type (ntf_type, description) VALUES ('SMS', 'SMS');
INSERT INTO notification_type (ntf_type, description) VALUES ('WEB', 'Web Notification');

INSERT INTO java_mail_config (mail_config_id, smtp) VALUES ('GMail', 'smtp.gmail.com');
INSERT INTO java_mail_config (mail_config_id, smtp) VALUES ('Yahoo', 'smtp.mail.yahoo.com');
INSERT INTO java_mail_config (mail_config_id, smtp) VALUES ('Outlook', 'smtp-mail.outlook.com');

INSERT INTO system_address(address_id, app_id, address, address_password, ntf_type, mail_config_id)
  VALUES (1, 'ASR', 'amt.noreply1@gmail.com', 'YW10bm9yZXBseQ==', 'EML', 'GMail');
INSERT INTO system_address(address_id, app_id, address, address_password, ntf_type, mail_config_id)
  VALUES (2, 'ASR', 'amt.noreply2@gmail.com', 'YW10bm9yZXBseQ==', 'EML', 'GMail');
INSERT INTO system_address(address_id, app_id, address, address_password, ntf_type, mail_config_id)
  VALUES (3, 'ASR', 'amt.noreply3@gmail.com', 'YW10bm9yZXBseQ==', 'EML', 'GMail');

INSERT INTO event (event_id, event_type, category_id, description)
  VALUES ('NE-CO', 'NEW', 'COR', 'Adding new Course to the System');
INSERT INTO event (event_id, event_type, category_id, description)
  VALUES ('NE-SE', 'NEW', 'COR', 'Adding new Section in a Course to the System');
INSERT INTO event (event_id, event_type, category_id, description)
  VALUES ('NE-TU', 'NEW', 'COR', 'Adding new Tutorial in a Course to the System');
INSERT INTO event (event_id, event_type, category_id, description)
  VALUES ('UP-CO', 'UPD', 'COR', 'Adding new Course to the System');

INSERT INTO event_notification (event_ntf_id, event_id, notification, description)
  VALUES ('INS-NE-CO', 'NE-CO', 'INS', 'Notification for Interested Students in the new Course');
INSERT INTO event_notification (event_ntf_id, event_id, notification, description)
  VALUES ('ITR-NE-CO', 'NE-CO', 'ITR', 'Notification for the Interested Technical Reviewers in the new Course');
INSERT INTO event_notification (event_ntf_id, event_id, notification, description)
  VALUES ('ITT-NE-CO', 'NE-CO', 'ITT', 'Notification for the Interested Technical Translators in the new Course');

INSERT INTO template(template_id, event_ntf_id, ntf_type, template_subject, file_name)
  VALUES (1, 'INS-NE-CO', 'EML', 'AMT New Course', 'NewCourse_IS_EM.txt');
INSERT INTO template(template_id, event_ntf_id, ntf_type, template_subject, file_name)
  VALUES (2, 'INS-NE-CO', 'SMS', 'AMT New Course', 'NewCourse_IS_SMS.txt');
INSERT INTO template(template_id, event_ntf_id, ntf_type, template_subject, file_name)
  VALUES (3, 'INS-NE-CO', 'WEB', 'AMT New Course', 'NewCourse_IS_WebNotification.txt');

INSERT INTO template(template_id, event_ntf_id, ntf_type, template_subject, file_name)
  VALUES (4, 'ITR-NE-CO', 'EML', 'AMT New Course', 'NewCourse_ITR_EM.txt');
INSERT INTO template(template_id, event_ntf_id, ntf_type, template_subject, file_name)
  VALUES (5, 'ITR-NE-CO', 'SMS', 'AMT New Course', 'NewCourse_ITR_SMS.txt');
INSERT INTO template(template_id, event_ntf_id, ntf_type, template_subject, file_name)
  VALUES (6, 'ITR-NE-CO', 'WEB', 'AMT New Course', 'NewCourse_ITR_WebNotification.txt');

INSERT INTO template(template_id, event_ntf_id, ntf_type, template_subject, file_name)
  VALUES (7, 'ITT-NE-CO', 'EML', 'AMT New Course', 'NewCourse_ITT_EM.txt');
INSERT INTO template(template_id, event_ntf_id, ntf_type, template_subject, file_name)
  VALUES (8, 'ITT-NE-CO', 'SMS', 'AMT New Course', 'NewCourse_ITT_SMS.txt');
INSERT INTO template(template_id, event_ntf_id, ntf_type, template_subject, file_name)
  VALUES (9, 'ITT-NE-CO', 'WEB', 'AMT New Course', 'NewCourse_ITT_WebNotification.txt');
