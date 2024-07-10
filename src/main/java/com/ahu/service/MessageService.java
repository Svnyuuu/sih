package com.ahu.service;

import java.util.Date;
import java.util.List;

import com.ahu.pojo.Message;
import com.ahu.pojo.PageBean;

public interface MessageService {

        // 收件箱信息分页显示
        PageBean<Message> listAllMessages(Integer pageNum, Integer pageSize, Date beginTime, Date endTime,
                        String title, String status);

        // 删除消息，移入回收站
        void deleteMessages(List<Integer> id);

        // 收藏状态更新消息
        void updateMessageFavorite(Integer id, Integer favorite);

        // 永久删除
        void deleteForeverMessages(Integer id);

        // 收藏夹
        PageBean<Message> listFavoriteMessages(Integer pageNum, Integer pageSize, Date beginTime, Date endTime,
                        String title, Integer favorite);

        // 草稿箱
        PageBean<Message> listDraftMessages(Integer pageNum, Integer pageSize, Date beginTime, Date endTime,
                        String title, String status);

        // 发送邮件
        boolean sendMessage(/* String to, */ String receiveBoat, String from, /* String sendBoat, */String subject,
                        String content);

        // 保存邮件
        void saveMessage(Message message);

        // 已发送
        PageBean<Message> listAllSendMessages(Integer pageNum, Integer pageSize, Date beginTime, Date endTime,
                        String title, String status);

        // 回收站
        PageBean<Message> listAllRecycleMessages(Integer pageNum, Integer pageSize, Date beginTime, Date endTime,
                        String title, String status);

}