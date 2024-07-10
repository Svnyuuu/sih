package com.ahu.mapper;

import java.util.Date;
import java.util.List;

import com.ahu.pojo.Message;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MessageMapper {

        // 消息分页显示,不用注解来写SQL，因为此处是动态的SQL,用映射配置文件(resources:com/ahu/mapper)mapper.xml写
        List<Message> listMessages(@Param("userId") Integer userId, @Param("beginTime") Date beginTime,
                        @Param("endTime") Date endTime, @Param("title") String title, @Param("status") String status);

        // 删除消息,将消息状态变成回收站
        @Update("update message set status='4' where messageId = #{id} and userId=#{userId}")
        void deleteMessages(@Param("id") Integer id, @Param("userId") Integer userId);

        // 收藏状态更新
        @Update("update message set favorite = 1-#{favorite} where messageId = #{id} and userId=#{userId}")
        void updateMessageFavorite(@Param("id") Integer id, @Param("favorite") Integer favorite,
                        @Param("userId") Integer userId);

        // 已发送
        List<Message> listSendMessages(@Param("userId") Integer userId, @Param("beginTime") Date beginTime,
                        @Param("endTime") Date endTime, @Param("title") String title, @Param("status") String status);

        // 移到回收站
        List<Message> listRecycleMessages(@Param("userId") Integer userId, @Param("beginTime") Date beginTime,
                        @Param("endTime") Date endTime, @Param("title") String title, @Param("status") String status);

        // 永久删除
        @Delete("delete from message where messageId = #{id} and userId=#{userId}")
        void deleteForeverMessages(@Param("id") Integer id, @Param("userId") Integer userId);

        List<Message> listFavoriteMessages(@Param("userId") Integer userId, @Param("beginTime") Date beginTime,
                        @Param("endTime") Date endTime, @Param("title") String title,
                        @Param("favorite") Integer favorite);

        List<Message> listDraftMessages(@Param("userId") Integer userId, @Param("beginTime") Date beginTime,
                        @Param("endTime") Date endTime, @Param("title") String title, @Param("status") String status);

        // 保存消息
        @Insert("insert into message(receiver, receiveBoat, sender, subject, content, status,userId, attachement) values(#{receiver}, #{receiveBoat}, #{sender}, #{subject}, #{content}, #{status},#{userId}, #{attachement})")
        void add(@Param("receiver") String receiver, @Param("receiveBoat") String receiveBoat,
                        @Param("sender") String sender,
                        @Param("subject") String subject, @Param("content") String content,
                        @Param("status") Integer status,
                        @Param("userId") Integer userId, @Param("attachement") String attachement);

        // 查询消息
        @Select("select * from message where email_id = #{id}")
        Message findMessageById(Integer id);

}