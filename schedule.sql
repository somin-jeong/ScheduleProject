CREATE TABLE `Users` (
     `user_id` BIGINT NOT NULL AUTO_INCREMENT,
     `email` VARCHAR(100) NOT NULL,
     `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     CONSTRAINT `PK_USERS` PRIMARY KEY (`user_id`)
);

CREATE TABLE `Schedule` (
     `schedule_id` BIGINT NOT NULL AUTO_INCREMENT,
     `title` VARCHAR(100) NOT NULL,
     `content` VARCHAR(500) NOT NULL,
     `password` VARCHAR(100) NOT NULL,
     `authorName` VARCHAR(100) NOT NULL,
     `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     `user_id` BIGINT NOT NULL,
     CONSTRAINT `PK_SCHEDULES` PRIMARY KEY (`schedule_id`),
     CONSTRAINT `FK_SCHEDULE_USER` FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`) ON DELETE CASCADE
);