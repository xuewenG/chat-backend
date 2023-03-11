CREATE TABLE `message`  (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `fromId` INT(11) NOT NULL,
  `toId` INT(11) NOT NULL,
  `contactType` INT(11) NOT NULL,
  `type` INT(11) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX FROM_ID_TO_ID_CONTACT_TYPE_INDEX(`fromId`, `toId`, `contactType`)
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
