-- 会员等级初始数据
INSERT INTO member_level (level_name, level_code, level_sort, min_points, max_points, discount, point_multiplier, icon_url, description, status)
VALUES ('普通会员', 'NORMAL', 1, 0, 999, 1.00, 1.0, '/icons/normal.png', '注册即可成为普通会员', 1);

INSERT INTO member_level (level_name, level_code, level_sort, min_points, max_points, discount, point_multiplier, icon_url, description, status)
VALUES ('银卡会员', 'SILVER', 2, 1000, 4999, 0.95, 1.5, '/icons/silver.png', '累计积分达到1000即可升级为银卡会员，享受95折优惠', 1);

INSERT INTO member_level (level_name, level_code, level_sort, min_points, max_points, discount, point_multiplier, icon_url, description, status)
VALUES ('金卡会员', 'GOLD', 3, 5000, 19999, 0.90, 2.0, '/icons/gold.png', '累计积分达到5000即可升级为金卡会员，享受9折优惠', 1);

INSERT INTO member_level (level_name, level_code, level_sort, min_points, max_points, discount, point_multiplier, icon_url, description, status)
VALUES ('铂金会员', 'PLATINUM', 4, 20000, 49999, 0.85, 3.0, '/icons/platinum.png', '累计积分达到20000即可升级为铂金会员，享受85折优惠', 1);

INSERT INTO member_level (level_name, level_code, level_sort, min_points, max_points, discount, point_multiplier, icon_url, description, status)
VALUES ('钻石会员', 'DIAMOND', 5, 50000, NULL, 0.80, 5.0, '/icons/diamond.png', '累计积分达到50000即可升级为钻石会员，享受8折优惠和全部专属权益', 1);

-- 普通会员权益
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (1, '基础积分', 'POINTS_MULTIPLIER', '1.0', '每消费1元获得1积分', 1);

-- 银卡会员权益
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (2, '95折优惠', 'DISCOUNT', '0.95', '全场商品享受95折优惠', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (2, '1.5倍积分', 'POINTS_MULTIPLIER', '1.5', '每消费1元获得1.5积分', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (2, '包邮特权', 'FREE_SHIPPING', '1', '每月3次免运费', 1);

-- 金卡会员权益
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (3, '9折优惠', 'DISCOUNT', '0.90', '全场商品享受9折优惠', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (3, '2倍积分', 'POINTS_MULTIPLIER', '2.0', '每消费1元获得2积分', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (3, '免运费', 'FREE_SHIPPING', '1', '全场免运费', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (3, '生日礼包', 'BIRTHDAY_GIFT', '1', '生日当月赠送200积分礼包', 1);

-- 铂金会员权益
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (4, '85折优惠', 'DISCOUNT', '0.85', '全场商品享受85折优惠', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (4, '3倍积分', 'POINTS_MULTIPLIER', '3.0', '每消费1元获得3积分', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (4, '免运费', 'FREE_SHIPPING', '1', '全场免运费', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (4, '生日礼包', 'BIRTHDAY_GIFT', '1', '生日当月赠送500积分礼包', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (4, '专属商品', 'EXCLUSIVE_PRODUCT', '1', '可购买铂金专属商品', 1);

-- 钻石会员权益
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (5, '8折优惠', 'DISCOUNT', '0.80', '全场商品享受8折优惠', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (5, '5倍积分', 'POINTS_MULTIPLIER', '5.0', '每消费1元获得5积分', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (5, '免运费', 'FREE_SHIPPING', '1', '全场免运费', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (5, '豪华生日礼包', 'BIRTHDAY_GIFT', '1', '生日当月赠送1000积分+专属礼品', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (5, '专属商品', 'EXCLUSIVE_PRODUCT', '1', '可购买全部专属商品', 1);
INSERT INTO member_benefit (level_id, benefit_name, benefit_type, benefit_value, description, status)
VALUES (5, '优先客服', 'PRIORITY_SERVICE', '1', '专属1对1客服通道', 1);

-- 示例会员数据
INSERT INTO member (username, nickname, phone, email, total_points, available_points, level_id)
VALUES ('zhangsan', '张三', '13800138001', 'zhangsan@example.com', 1200, 800, 2);

INSERT INTO member (username, nickname, phone, email, total_points, available_points, level_id)
VALUES ('lisi', '李四', '13800138002', 'lisi@example.com', 6500, 3200, 3);

-- 示例积分记录
INSERT INTO points_record (member_id, points, type, description)
VALUES (1, 500, 'EARN', '购物获得积分');
INSERT INTO points_record (member_id, points, type, description)
VALUES (1, 700, 'EARN', '签到活动奖励');
INSERT INTO points_record (member_id, points, type, description)
VALUES (1, -400, 'SPEND', '兑换优惠券');

INSERT INTO points_record (member_id, points, type, description)
VALUES (2, 3000, 'EARN', '购物获得积分');
INSERT INTO points_record (member_id, points, type, description)
VALUES (2, 3500, 'EARN', '充值赠送积分');
INSERT INTO points_record (member_id, points, type, description)
VALUES (2, -3300, 'SPEND', '积分兑换商品');
