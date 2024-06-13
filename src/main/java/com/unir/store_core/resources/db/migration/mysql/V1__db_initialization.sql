CREATE TABLE IF NOT EXISTS `users` (
  `id` uuid PRIMARY KEY,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `countries` (
  `id` uuid PRIMARY KEY,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `orders` (
  `id` uuid PRIMARY KEY,
  `user_id` uuid NOT NULL,
  `shipping_carrier_id` uuid NOT NULL,
  `status` varchar(255),
  `total_price` float,
  `shipping_address` varchar(255),
  `billing_address` varchar(255),
  `payment_method` varchar(255),
  `payment_status` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `order_items` (
  `id` uuid PRIMARY KEY,
  `order_id` uuid NOT NULL,
  `product_id` uuid NOT NULL,
  `status` varchar(255),
  `product_name` varchar(255),
  `qty` int,
  `price` float,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `shipping_carriers` (
  `id` uuid PRIMARY KEY,
  `name` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `payment_transactions` (
  `id` uuid PRIMARY KEY,
  `order_id` uuid NOT NULL,
  `transaction_id` varchar(255),
  `amount` float,
  `status` varchar(255),
  `payment_method` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `categories` (
  `id` uuid PRIMARY KEY,
  `name` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `products` (
  `id` uuid PRIMARY KEY,
  `category_id` uuid NOT NULL,
  `name` varchar(255),
  `description` varchar(255),
  `brand` varchar(255),
  `color` varchar(255),
  `weight` varchar(255),
  `price` float,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `wishlists` (
  `id` uuid PRIMARY KEY,
  `user_id` uuid NOT NULL,
  `name` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `wishlist_items` (
  `id` uuid PRIMARY KEY,
  `wishlist_id` uuid NOT NULL,
  `product_id` uuid NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `carts` (
  `id` uuid PRIMARY KEY,
  `user_id` uuid NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `cart_items` (
  `id` uuid PRIMARY KEY,
  `cart_id` uuid NOT NULL,
  `product_id` uuid NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

ALTER TABLE `cart_items` ADD FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`);

ALTER TABLE `wishlist_items` ADD FOREIGN KEY (`wishlist_id`) REFERENCES `wishlists` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `order_items` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `products` ADD FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

ALTER TABLE `payment_transactions` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `wishlists` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `wishlist_items` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);
