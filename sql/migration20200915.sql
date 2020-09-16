ALTER TABLE `bl_transaction`
ADD COLUMN `fail` INT(1) NOT NULL DEFAULT 0 COMMENT '是否是失败交易' AFTER `extension1`;


CREATE TABLE `bl_tx_events` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `trx_id` VARCHAR(100) NOT NULL,
  `block_num` INT(11) NOT NULL,
  `op_num` INT NULL,
  `caller_addr` VARCHAR(100) NOT NULL,
  `contract_address` VARCHAR(100) NOT NULL,
  `event_name` VARCHAR(100) NOT NULL,
  `event_arg` TEXT NOT NULL,
  `event_seq` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tx_events_idx_txid` (`trx_id` ASC),
  INDEX `tx_events_idx_contract_address` (`contract_address` ASC, `event_name` ASC),
  INDEX `tx_events_idx_event_name` (`event_name` ASC));

CREATE TABLE `bl_tx_contract_balance_change` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `trx_id` VARCHAR(100) NOT NULL,
  `block_num` INT(11) NOT NULL,
  `change_type` VARCHAR(100) NOT NULL COMMENT '在receipt中的变化字段',
  `address` VARCHAR(100) NOT NULL,
  `asset_id` VARCHAR(45) NOT NULL,
  `amount` BIGINT NOT NULL,
  `change_seq` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tx_contract_balance_change_idx_txid` (`trx_id` ASC),
  INDEX `tx_contract_balance_change_idx_address` (`address` ASC))
COMMENT = '合约交易的原生资产变化';

CREATE TABLE `bl_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contract_address` varchar(100) NOT NULL,
  `token_symbol` varchar(100) DEFAULT NULL,
  `precision` int(11) DEFAULT NULL,
  `creator_address` varchar(100) NOT NULL,
  `token_supply` decimal(36,18) DEFAULT NULL,
  `create_trx_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_idx_contract_address` (`contract_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='合约代币';


CREATE TABLE `bl_token_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trx_id` varchar(100) NOT NULL,
  `block_id` varchar(100) NOT NULL,
  `block_num` int(11) NOT NULL,
  `from_account` varchar(100) DEFAULT NULL,
  `to_account` varchar(100) DEFAULT NULL,
  `symbol` varchar(100) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `fee` bigint(20) NOT NULL,
  `trx_time` datetime NOT NULL,
  `contract_id` varchar(100) NOT NULL,
  `memo` varchar(100) DEFAULT NULL,
  `event_seq` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_tx_unique_trx_id_event_seq` (`trx_id`,`event_seq`),
  KEY `token_tx_idx_trx_id` (`trx_id`),
  KEY `token_tx_idx_from_account` (`from_account`),
  KEY `token_tx_idx_to_account` (`to_account`),
  KEY `token_tx_idx_contract_id` (`contract_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='token转账流水';

