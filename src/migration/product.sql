USE product;
DROP TABLE IF EXISTS product;
CREATE TABLE IF NOT EXISTS product (
  productId BIGINT(20) PRIMARY KEY  AUTO_INCREMENT
  COMMENT 'product PK',
  name      VARCHAR(64) NOT NULL
  COMMENT 'product name',
  createdAt DATETIME(6) NOT NULL    DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'created at',
  updatedAt DATETIME(6) NOT NULL    DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'updatedAt at',
  KEY product_idx99 (updatedAt)
)
  ENGINE = innodb
  CHARSET = utf8
  COMMENT 'product table';

DROP TABLE IF EXISTS product_item;
CREATE TABLE IF NOT EXISTS product_item (
  productItemId BIGINT(20) PRIMARY KEY AUTO_INCREMENT
  COMMENT 'product item PK',
  productId     BIGINT(20)  NOT NULL
  COMMENT 'product PK',
  name          VARCHAR(64) NOT NULL
  COMMENT 'product item name',
  createdAt     DATETIME(6) NOT NULL   DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'created at',
  updatedAt     DATETIME(6) NOT NULL   DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'updatedAt at',
  KEY product_item_idx01 (productId),
  KEY product_item_idx99 (updatedAt)
)
  ENGINE = innodb
  CHARSET = utf8
  COMMENT 'product item table';

DROP TABLE IF EXISTS product_item_attribute;
CREATE TABLE IF NOT EXISTS product_item_attribute (
  productItemAttributeId BIGINT(20) PRIMARY KEY AUTO_INCREMENT
  COMMENT 'product item attribute PK',
  productItemId          BIGINT(20)  NOT NULL
  COMMENT 'product PK',
  name                   VARCHAR(64) NOT NULL
  COMMENT 'product item attribute name',
  value                  VARCHAR(64) NOT NULL
  COMMENT 'product item attribute value',
  createdAt              DATETIME(6) NOT NULL   DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'created at',
  updatedAt              DATETIME(6) NOT NULL   DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'updatedAt at',
  KEY product_item_attribute_idx01 (productItemId),
  KEY product_item_attribute_idx99 (updatedAt)
)
  ENGINE = innodb
  CHARSET = utf8
  COMMENT 'product item attribute table';

DROP TABLE IF EXISTS product_property;
CREATE TABLE IF NOT EXISTS product_property (
  productPropertyId BIGINT(20) PRIMARY KEY AUTO_INCREMENT
  COMMENT 'product property PK',
  productId         BIGINT(20)  NOT NULL
  COMMENT 'product PK',
  name              VARCHAR(64) NOT NULL
  COMMENT 'product property name',
  createdAt         DATETIME(6) NOT NULL   DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'created at',
  updatedAt         DATETIME(6) NOT NULL   DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'updatedAt at',
  KEY product_property_idx01 (productId),
  KEY product_property_idx99 (updatedAt)
)
  ENGINE = innodb
  CHARSET = utf8
  COMMENT 'product property table';

DROP TABLE IF EXISTS product_property_value;
CREATE TABLE IF NOT EXISTS product_property_value (
  productPropertyValueId BIGINT(20) PRIMARY KEY AUTO_INCREMENT
  COMMENT 'product property value PK',
  productPropertyId      BIGINT(20)  NOT NULL
  COMMENT 'product property PK',
  value                  VARCHAR(64) NOT NULL
  COMMENT 'product property value',
  createdAt              DATETIME(6) NOT NULL   DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'created at',
  updatedAt              DATETIME(6) NOT NULL   DEFAULT CURRENT_TIMESTAMP(6)
  COMMENT 'updatedAt at',
  KEY product_property_value_idx01 (productPropertyId),
  KEY product_property_value_idx99 (updatedAt)
)
  ENGINE = innodb
  CHARSET = utf8
  COMMENT 'product property value table';

