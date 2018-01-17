ALTER TABLE `am_notification`.`input_event`
  ADD COLUMN `is_valid` TINYINT(1) NULL AFTER `process_date`;

ALTER TABLE `am_notification`.`input_event`
  ADD COLUMN `validation_date` DATETIME NULL AFTER `is_valid`;

ALTER TABLE `am_notification`.`input_event`
  CHANGE COLUMN `is_processed` `is_processed` TINYINT(1) NULL ,
  CHANGE COLUMN `is_valid` `is_valid` TINYINT(1) NULL ;

ALTER TABLE `am_notification`.`input_event_destination`
  ADD COLUMN `full_name` VARCHAR(45) NULL AFTER `event_notification_id`;

ALTER TABLE `am_notification`.`notification_parameter`
  DROP FOREIGN KEY `FK_notification_parameter_notification`;

ALTER TABLE `am_notification`.`notification_parameter`
  DROP COLUMN `notification_id`,
  ADD COLUMN `event_id` INT NOT NULL AFTER `parameter_id`,
  ADD INDEX `FK_Event_Parameters_idx` (`event_id` ASC),
  DROP INDEX `FK_notification_parameter_notification` ;

ALTER TABLE `am_notification`.`notification_parameter`
  ADD CONSTRAINT `FK_Event_Parameters`
FOREIGN KEY (`event_id`)
REFERENCES `am_notification`.`event` (`event_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `am_notification`.`notification_parameter`
  RENAME TO  `am_notification`.`event_parameter` ;
