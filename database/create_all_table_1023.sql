CREATE TABLE `dessert` (
  `dessert_id` varchar(2) NOT NULL,
  `dessert_desc` varchar(20) NOT NULL,
  PRIMARY KEY (`dessert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `menu_desc` varchar(30) DEFAULT NULL,
  `menu_image_path` varchar(255) DEFAULT NULL,
  `dessert_id` varchar(2) DEFAULT NULL,
  `store_id` int(11) NOT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `store_id` (`store_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) NOT NULL,CREATE TABLE `dessert` (
  `dessert_id` varchar(2) NOT NULL,
  `dessert_desc` varchar(20) NOT NULL,
  PRIMARY KEY (`dessert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `menu_desc` varchar(30) DEFAULT NULL,
  `menu_image_path` varchar(255) DEFAULT NULL,
  `dessert_id` varchar(2) DEFAULT NULL,
  `store_id` int(11) NOT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `store_id` (`store_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) NOT NULL,
  `title` varchar(20) NOT NULL,
  `posted_date` datetime NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `post_category_id` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `post_category` (
  `post_category_id` varchar(2) NOT NULL,
  `post_category_desc` varchar(20) NOT NULL,
  PRIMARY KEY (`post_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `post_comment` (
  `post_comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `post_id` int(11) NOT NULL,
  `content` varchar(100) NOT NULL,
  `posted_date` date DEFAULT NULL,
  `parent_comment` int(11) DEFAULT NULL,
  PRIMARY KEY (`post_comment_id`),
  KEY `user_id` (`user_id`),
  KEY `post_id` (`post_id`),
  KEY `parent_comment` (`parent_comment`),
  CONSTRAINT `post_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `post_comment_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `post_comment_ibfk_3` FOREIGN KEY (`parent_comment`) REFERENCES `post_comment` (`post_comment_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `post_image` (
  `post_image_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_image_path` varchar(255) DEFAULT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`post_image_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `post_image_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `recommendation` (
  `recommendation_id` int(11) NOT NULL AUTO_INCREMENT,
  `reference_type` varchar(1) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `reference_id` int(11) NOT NULL,
  `posted_date` datetime NOT NULL,
  PRIMARY KEY (`recommendation_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `recommendation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `store_id` int(11) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `posted_date` datetime NOT NULL,
  `star` int(11) NOT NULL,
  `review_image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `store_id` (`store_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `review_comment` (
  `review_comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `review_id` int(11) NOT NULL,
  `content` varchar(100) NOT NULL,
  `posted_date` date DEFAULT NULL,
  `parent_comment` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_comment_id`),
  KEY `user_id` (`user_id`),
  KEY `review_id` (`review_id`),
  KEY `parent_comment` (`parent_comment`),
  CONSTRAINT `review_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `review_comment_ibfk_2` FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`) ON DELETE CASCADE,
  CONSTRAINT `review_comment_ibfk_3` FOREIGN KEY (`parent_comment`) REFERENCES `review_comment` (`review_comment_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `store` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(20) NOT NULL,
  `address` varchar(40) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `store_desc` varchar(100) NOT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `store_image_path` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `store_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `user_id` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `user_email` varchar(40) NOT NULL,
  `user_image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

  `title` varchar(20) NOT NULL,
  `posted_date` datetime NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `post_category_id` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `post_category` (
  `post_category_id` varchar(2) NOT NULL,
  `post_category_desc` varchar(20) NOT NULL,
  PRIMARY KEY (`post_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `post_comment` (
  `post_comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `post_id` int(11) NOT NULL,
  `content` varchar(100) NOT NULL,
  `posted_date` date DEFAULT NULL,
  `parent_comment` int(11) DEFAULT NULL,
  PRIMARY KEY (`post_comment_id`),
  KEY `user_id` (`user_id`),
  KEY `post_id` (`post_id`),
  KEY `parent_comment` (`parent_comment`),
  CONSTRAINT `post_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `post_comment_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `post_comment_ibfk_3` FOREIGN KEY (`parent_comment`) REFERENCES `post_comment` (`post_comment_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `post_image` (
  `post_image_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_image_path` varchar(255) DEFAULT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`post_image_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `post_image_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `recommendation` (
  `recommendation_id` int(11) NOT NULL AUTO_INCREMENT,
  `reference_type` varchar(1) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `reference_id` int(11) NOT NULL,
  `posted_date` datetime NOT NULL,
  PRIMARY KEY (`recommendation_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `recommendation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `store_id` int(11) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `posted_date` datetime NOT NULL,
  `star` int(11) NOT NULL,
  `review_image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `store_id` (`store_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `review_comment` (
  `review_comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `review_id` int(11) NOT NULL,
  `content` varchar(100) NOT NULL,
  `posted_date` date DEFAULT NULL,
  `parent_comment` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_comment_id`),
  KEY `user_id` (`user_id`),
  KEY `review_id` (`review_id`),
  KEY `parent_comment` (`parent_comment`),
  CONSTRAINT `review_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `review_comment_ibfk_2` FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`) ON DELETE CASCADE,
  CONSTRAINT `review_comment_ibfk_3` FOREIGN KEY (`parent_comment`) REFERENCES `review_comment` (`review_comment_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `store` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(20) NOT NULL,
  `address` varchar(40) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `store_desc` varchar(100) NOT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `store_image_path` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `store_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `user_id` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `user_email` varchar(40) NOT NULL,
  `user_image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
