INSERT INTO event_category (category, description) VALUES ('Co', 'Course');
INSERT INTO event_category (category, description) VALUES ('Us', 'User');
INSERT INTO event_category (category, description) VALUES ('Ar', 'Article');
INSERT INTO event_category (category, description) VALUES ('Qu', 'Quiz');
INSERT INTO event_category (category, description) VALUES ('As', 'Assignment');
INSERT INTO event_category (category, description) VALUES ('Bo', 'Bonus');

INSERT INTO notification_type (type, description) VALUES ('EM', 'Email');
INSERT INTO notification_type (type, description) VALUES ('SMS', 'SMS');
INSERT INTO notification_type (type, description) VALUES ('WN', 'Web Notification');

INSERT INTO java_mail_config (mail_config_id, smtp) VALUES ('GMail', 'smtp.gmail.com');
INSERT INTO java_mail_config (mail_config_id, smtp) VALUES ('Yahoo', 'smtp.mail.yahoo.com');
INSERT INTO java_mail_config (mail_config_id, smtp) VALUES ('Outlook', 'smtp-mail.outlook.com');

INSERT INTO system_address(address_id, address, address_password, type, mail_config_id)
  VALUES (1, 'amt.noreply1@gmail.com', 'YW10bm9yZXBseQ==', 'EM', 'GMail');
INSERT INTO system_address(address_id, address, address_password, type, mail_config_id)
  VALUES (2, 'amt.noreply2@gmail.com', 'YW10bm9yZXBseQ==', 'EM', 'GMail');
INSERT INTO system_address(address_id, address, address_password, type, mail_config_id)
  VALUES (3, 'amt.noreply3@gmail.com', 'YW10bm9yZXBseQ==', 'EM', 'GMail');

INSERT INTO event_type (type, category, description)
  VALUES ('NCor', 'Co', 'Adding new Course to the System');
INSERT INTO event_type (type, category, description)
  VALUES ('NSec', 'Co', 'Adding new Section in a Course to the System');
INSERT INTO event_type (type, category, description)
  VALUES ('NTut', 'Co', 'Adding new Tutorial in a Course to the System');
INSERT INTO event_type (type, category, description)
  VALUES ('UCor', 'Co', 'Adding new Course to the System');

INSERT INTO event_notification (event_notif_id, event_type, notification, description)
  VALUES ('NCor-IS', 'NCor', 'IS', 'Notification to all Students interested in the Course');
INSERT INTO event_notification (event_notif_id, event_type, notification, description)
  VALUES ('NCor-ITR', 'NCor', 'ITR', 'Notification to all Technical Reviewers interested in the Course');
INSERT INTO event_notification (event_notif_id, event_type, notification, description)
  VALUES ('NCor-ITT', 'NCor', 'ITT', 'Notification to all Technical Reviewers interested in the Course');

INSERT INTO notification_template(notif_temp_id, event_notif_id, notification_type, template_name, num_of_placeholders)
  VALUES ('NCor-IS-EM', 'NCor-IS', 'EM', 'NewCourse_IS_EM.txt', 5);
INSERT INTO notification_template(notif_temp_id, event_notif_id, notification_type, template_name, num_of_placeholders)
  VALUES ('NCor-IS-SMS', 'NCor-IS', 'SMS', 'NewCourse_IS_SMS.txt', 3);
INSERT INTO notification_template(notif_temp_id, event_notif_id, notification_type, template_name, num_of_placeholders)
  VALUES ('NCor-IS-WN', 'NCor-IS', 'WN', 'NewCourse_IS_WebNotification.txt', 2);
INSERT INTO notification_template(notif_temp_id, event_notif_id, notification_type, template_name, num_of_placeholders)
  VALUES ('NCor-ITR-EM', 'NCor-ITR', 'EM', 'NewCourse_ITR_EM.txt', 5);
INSERT INTO notification_template(notif_temp_id, event_notif_id, notification_type, template_name, num_of_placeholders)
  VALUES ('NCor-ITR-SMS', 'NCor-ITR', 'SMS', 'NewCourse_ITR_SMS.txt', 3);
INSERT INTO notification_template(notif_temp_id, event_notif_id, notification_type, template_name, num_of_placeholders)
  VALUES ('NCor-ITR-WN', 'NCor-ITR', 'WN', 'NewCourse_ITR_WebNotification.txt', 2);
INSERT INTO notification_template(notif_temp_id, event_notif_id, notification_type, template_name, num_of_placeholders)
  VALUES ('NCor-ITT-EM', 'NCor-ITT', 'EM', 'NewCourse_ITT_EM.txt', 5);
INSERT INTO notification_template(notif_temp_id, event_notif_id, notification_type, template_name, num_of_placeholders)
  VALUES ('NCor-ITT-SMS', 'NCor-ITT', 'SMS', 'NewCourse_ITT_SMS.txt', 3);
INSERT INTO notification_template(notif_temp_id, event_notif_id, notification_type, template_name, num_of_placeholders)
  VALUES ('NCor-ITT-WN', 'NCor-ITT', 'WN', 'NewCourse_ITT_WebNotification.txt', 2);
