CREATE TABLE `draw_result` (
  `draw_num` int(10) unsigned NOT NULL PRIMARY KEY,
  `n1` int(2) unsigned NOT NULL,
  `n2` int(2) unsigned NOT NULL,
  `n3` int(2) unsigned NOT NULL,
  `n4` int(2) unsigned NOT NULL,
  `n5` int(2) unsigned NOT NULL,
  `joker` int(2) unsigned NOT NULL,
  `draw_date` date NOT NULL,
  KEY `idx_n1` (`n1`),
  KEY `idx_n2` (`n2`),
  KEY `idx_n3` (`n3`),
  KEY `idx_n4` (`n4`),
  KEY `idx_n5` (`n5`),
  KEY `idx_joker` (`joker`),
  KEY `idx_date` (`draw_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;