CREATE TABLE `contact`  (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `userId` INT(11) NOT NULL,
  `contactId` INT(11) NOT NULL,
  `contactType` INT(11) NOT NULL,
  `state` INT(11) NOT NULL,
  `lastMessageId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX USER_ID_INDEX(`userId`)
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
