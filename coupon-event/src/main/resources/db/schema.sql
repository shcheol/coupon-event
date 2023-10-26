CREATE TABLE IF NOT EXISTS coupon_event (
  coupon_event_id VARCHAR(255) NOT NULL PRIMARY KEY,
  quality INT(4),
  start_date DATE,
  end_date DATE,
  discount_policy VARCHAR(255)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS coupon (
  coupon_id VARCHAR(255) NOT NULL PRIMARY KEY,
  coupon_event_id VARCHAR(255),
  discount_policy INT(4),
  during_date DATE,
  member_id VARCHAR(255),
  state VARCHAR(100)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS member (
 member_id VARCHAR(255) NOT NULL PRIMARY KEY
) engine=InnoDB;
