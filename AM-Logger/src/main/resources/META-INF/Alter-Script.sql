ALTER TABLE `am_logger`.`per_thread`
  ADD COLUMN `server_thread_id` VARCHAR(50) NOT NULL AFTER `thread_name`;
