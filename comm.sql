/*
Navicat MySQL Data Transfer

Source Server         : ahu
Source Server Version : 80036
Source Host           : 127.0.0.1:3306
Source Database       : comm

Target Server Type    : MYSQL
Target Server Version : 80036
File Encoding         : 65001

Date: 2024-06-30 05:31:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageId` int NOT NULL AUTO_INCREMENT,
  `receiver` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `receiveBoat` varchar(10) NOT NULL,
  `sender` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subject` varchar(20) NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `sendTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `receiveTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` set('4','3','2','1') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `userId` int NOT NULL,
  `favorite` int DEFAULT NULL,
  `recycle` int DEFAULT NULL,
  `send` int DEFAULT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '李三', 'B', '李四', 'vue3传参', 'vue2写法\n\n1.加入传递的参数值\n\n<router-link :to=\"{name:\'generate\', query:{ couponId: record.id }}\">绑定账号</router-link>\n2. 页面接收参数\n\nconst couponId = this.$route.query.couponId\n\nvue3中与vue2不同:\r\n————————————————\r\n\n                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。\n                        \r\n原文链接：https://blog.csdn.net/weixin_53035812/article/details/119539321', '2024-06-06 01:45:36', '2024-06-07 01:45:36', '1', '1', '0', '1', '1', null);
INSERT INTO `message` VALUES ('3', '李五', 'A', '小张', '太极转运鼠', '厅', '2024-06-29 19:13:56', '2024-06-29 19:13:56', '2', '2', '0', '0', '1', null);
INSERT INTO `message` VALUES ('4', '李二', 'B', '李四', '中华民国', '当我们需要在不同页面之间传递对象数据时，通常会使用 Vue Router 的 this.$router.push 方法进行导航，并通过 query 参数传递数据。然而，直接传递对象会导致数据丢失或不正确解析的问题，因为对象不能直接被编码成 URL 查询参数形式。\n\n为了解决对象数据传递和解析的问题，我们可以将对象先转换为字符串，并对字符串进行 URL 编码。在发送端，可以使用 JSON.stringify 方法将对象转换为字符串，然后使用 encodeURIComponent 函数对字符串进行编码。在接收端，使用 decodeURIComponent 函数对 URL 查询参数进行解码，并使用 JSON.parse 方法将字符串解析为对象。\r\n————————————————\r\n\n                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。\n                        \r\n当我们需要在不同页面之间传递对象数据时，通常会使用 Vue Router 的 this.$router.push 方法进行导航，并通过 query 参数传递数据。然而，直接传递对象会导致数据丢失或不正确解析的问题，因为对象不能直接被编码成 URL 查询参数形式。\n\n为了解决对象数据传递和解析的问题，我们可以将对象先转换为字符串，并对字符串进行 URL 编码。在发送端，可以使用 JSON.stringify 方法将对象转换为字符串，然后使用 encodeURIComponent 函数对字符串进行编码。在接收端，使用 decodeURIComponent 函数对 URL 查询参数进行解码，并使用 JSON.parse 方法将字符串解析为对象。\r\n————————————————\r\n\n                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。\n                        \r\n原文链接：https://blog.csdn.net/weixin_45968070/article/details/138124069', '2024-06-10 00:45:36', '2024-06-05 01:45:36', '1', '1', '0', '1', '0', null);
INSERT INTO `message` VALUES ('5', '小张', 'A', '王五', 'Test message5', 'This is a test message5.', '2024-06-05 02:45:36', '2024-06-06 01:45:36', '4', '1', '1', '0', '0', null);
INSERT INTO `message` VALUES ('6', '李五', 'A', '小张', '太极转运鼠', '厅', '2024-06-29 19:13:56', '2024-06-29 19:13:56', '2', '2', '1', '0', '0', null);
INSERT INTO `message` VALUES ('7', '和张', 'C', '五五', '楞', '太极转运鼠', '2024-06-29 19:14:16', '2024-06-29 19:14:16', '1', '3', '1', '0', '0', null);
INSERT INTO `message` VALUES ('8', '小张', 'B', '厅', '要', '递四方速递', '2024-06-29 19:14:39', '2024-06-29 19:14:39', '4', '1', '1', '1', '0', null);
INSERT INTO `message` VALUES ('9', '和张', 'C', '五五', '楞', '太极转运鼠', '2024-06-29 19:14:16', '2024-06-29 19:14:16', '3', '1', '0', '1', '0', null);
INSERT INTO `message` VALUES ('10', '小张', 'B', '厅', '要', '递四方速递', '2024-06-29 19:14:39', '2024-06-29 19:14:39', '3', '1', '0', '0', '0', null);
INSERT INTO `message` VALUES ('11', '李四', 'B', ' 小张', '美国', '总结：\r\n\r\n1.传参的是this.$router,接收参数是this.$route,易混淆。前者是VueRouter的实例对象，而后者则是一个跳转的路由对象，每一个路由都会有一个route对象，是一个局部的对象。\r\n\r\n2.params是通过name属性传递数据给指定的组件，query是通过path的配置将数据传递到指定path的地址。\r\n\r\n3.通过query传递的数据在刷新页面的时候不会消失，而params传递的数据刷新页面得时候会消失，可以通过本地存储解决。 \r\n\r\n以上为个人经验，希望能给大家一个参考，也希望大家多多支持脚本之家。', '2024-06-10 01:45:36', '2024-06-12 01:45:36', '1', '1', '0', '0', '0', null);
INSERT INTO `message` VALUES ('12', '小张', 'A', '王五', 'Test message5', 'This is a test message5.', '2024-06-05 01:45:36', '2024-06-06 01:45:36', '2', '1', '0', '1', '1', null);
