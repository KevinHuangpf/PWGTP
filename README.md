#PublicWelfareGroupTravelPlatform(PWGTP): 公益拼团出行平台
## 产品介绍
    公益拼团出行平台是一个基于SpringBoot的web应用，用于用户发布拼团出行活动，用户可以查看活动详情，用户可以加入活动，用户可以查看活动列表，用户可以查看活动详情，用户可以查看活动列表，用户可以查看活动详情，用户可以查看活动列表，用户可以查看活动详情，用户可以查看活动列表，用户可以查看活动详情，用户可以查看活动列表，用户可以查看活动详情，用户可以查看活动列表，用户可以查看活动详情，用户可以查看活动列表，用户可以查看活动详情，用户可以查看活动列表，用户可以查看活动详情，用户可以查看活动列表，用户可以查看

## 产品功能
### 行程管理
    - 新建行程活动
    - 修改行程活动
    - 删除行程活动
    - 发布/下架行程
    - 行程列表
    - 加入行程
    - 评价行程
### 用户管理
    - 用户注册
    - 用户登录
    - 用户管理
    - 用户注销
### 我的收藏`
    - 收藏列表
    - 行程收藏
    - 行程取消
### 评价
    - 评价列表
    - 评价删除
### 话题（BBS/论坛）
    - 发布话题
    - 评论话题
    - 管理话题

## 项目注意
    部署后访问：
    (1)api 接口：http://localhost:8080/doc.html#/home
    (2)调用前需登录：http://localhost:8080/login.html
    (3)登录后自动跳转到首页：http://localhost:8080/index.html



建表语句：
-- pwgtp.travel definition

CREATE TABLE `travel` (
`deleted` int NOT NULL DEFAULT '0',
`id` bigint NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL,
`gmt_modified` datetime NOT NULL,
`creator_uid` bigint NOT NULL,
`modifier_uid` bigint NOT NULL,
`name` varchar(100) NOT NULL,
`description` varchar(100) DEFAULT NULL,
`travel_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
`status` varchar(10) NOT NULL,
`travel_way` varchar(100) NOT NULL,
`travel_start_time` datetime NOT NULL DEFAULT '2025-01-01 00:00:00',
`travel_end_time` datetime NOT NULL DEFAULT '2025-01-01 00:00:00',
`plan_recruit_member_number` int NOT NULL,
`has_recruited_member_list` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
`travel_plan` text,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- pwgtp.`user` definition

CREATE TABLE `user` (
`id` bigint NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL,
`gmt_modified` datetime NOT NULL,
`name` varchar(100) NOT NULL,
`description` varchar(100) DEFAULT NULL,
`mobile_phone` varchar(100) NOT NULL,
`password` varchar(100) NOT NULL,
`deleted` bigint NOT NULL DEFAULT '0',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- pwgtp.favorite definition

CREATE TABLE `favorite` (
`deleted` int NOT NULL DEFAULT '0',
`id` bigint NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL,
`gmt_modified` datetime NOT NULL,
`creator_uid` bigint NOT NULL,
`modifier_uid` bigint NOT NULL,
`favorite_type` varchar(100) NOT NULL,
`associate_id` bigint NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- pwgtp.evaluation definition

CREATE TABLE `evaluation` (
`deleted` int NOT NULL DEFAULT '0',
`id` bigint NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL,
`gmt_modified` datetime NOT NULL,
`creator_uid` bigint NOT NULL,
`modifier_uid` bigint NOT NULL,
`travel_id` bigint NOT NULL,
`evaluation_content` text NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;