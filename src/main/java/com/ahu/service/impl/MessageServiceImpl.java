package com.ahu.service.impl;

import com.ahu.pojo.Message;
import com.ahu.pojo.PageBean;
import com.ahu.service.MessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.eclipse.paho.client.mqttv3.MqttClient;
import com.ahu.mapper.MessageMapper;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Value("${mqtt.url}")
    private String brokerUrl;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.clientIdSub}")
    private String clientIdSub;

    @Autowired
    private MessageMapper messageMapper;

    private Integer getUserId() {
        // 调用用户信息接口，获取用户ID,当前写死
        @SuppressWarnings("unused")
        Integer userId;
        return userId = 1;
    }

    // 信息分页显示
    @Override
    public PageBean<Message> listAllMessages(Integer pageNum,
            Integer pageSize,
            Date beginTime,
            Date endTime,
            String title, String status) {

        // 1.创建PageBean对象
        PageBean<Message> pb = new PageBean<>();
        // 2.开启分布查询,PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 3.调用mapper,只能查询当前用户的消息
        // 调用用户信息接口，获取用户ID,当前写死
        Integer userId = this.getUserId();
        List<Message> as = messageMapper.listMessages(userId, beginTime, endTime, title, status);
        // 强转PageHelper的返回结果,Page中提供了方法，可以获取PageHelper分布查询后得到的总记录和当前页数据
        @SuppressWarnings("resource")
        Page<Message> p = (Page<Message>) as;
        // 把数据填充到PageBean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    // 删除消息
    @Override
    public void deleteMessages(List<Integer> id) {
        // 调用用户信息接口，获取用户ID,当前写死
        Integer userId = this.getUserId();
        System.out.println("id=" + id + ",userId=" + userId);
        for (Integer i : id) {
            messageMapper.deleteMessages(i, userId);
        }
    }

    // 更新收藏
    @Override
    public void updateMessageFavorite(Integer id, Integer favorite) {
        // 调用用户信息接口，获取用户ID,当前写死
        Integer userId = this.getUserId();
        messageMapper.updateMessageFavorite(id, favorite, userId);
    }

    // 已发送
    @Override
    public PageBean<Message> listAllSendMessages(Integer pageNum, Integer pageSize, Date beginTime, Date endTime,
            String title, String status) {
        // 1.创建PageBean对象
        PageBean<Message> pb = new PageBean<>();
        // 2.开启分布查询,PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 3.调用mapper,只能查询当前用户的消息
        // 调用用户信息接口，获取用户ID,当前写死
        Integer userId = this.getUserId();
        List<Message> as = messageMapper.listSendMessages(userId, beginTime, endTime, title, status);
        // 强转PageHelper的返回结果,Page中提供了方法，可以获取PageHelper分布查询后得到的总记录和当前页数据
        @SuppressWarnings("resource")
        Page<Message> p = (Page<Message>) as;
        // 把数据填充到PageBean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<Message> listAllRecycleMessages(Integer pageNum, Integer pageSize, Date beginTime, Date endTime,
            String title, String status) {
        // 1.创建PageBean对象
        PageBean<Message> pb = new PageBean<>();
        // 2.开启分布查询,PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 3.调用mapper,只能查询当前用户的消息
        // 调用用户信息接口，获取用户ID,当前写死
        Integer userId = this.getUserId();
        List<Message> as = messageMapper.listRecycleMessages(userId, beginTime, endTime, title, status);
        // 强转PageHelper的返回结果,Page中提供了方法，可以获取PageHelper分布查询后得到的总记录和当前页数据
        @SuppressWarnings("resource")
        Page<Message> p = (Page<Message>) as;
        // 把数据填充到PageBean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void deleteForeverMessages(Integer id) {
        // 调用用户信息接口，获取用户ID,当前写死
        Integer userId = this.getUserId();
        System.out.println("id=" + id + ",userId=" + userId);
        messageMapper.deleteForeverMessages(id, userId);
    }

    @Override
    public PageBean<Message> listFavoriteMessages(Integer pageNum, Integer pageSize, Date beginTime, Date endTime,
            String title, Integer favorite) {
        // 1.创建PageBean对象
        PageBean<Message> pb = new PageBean<>();
        // 2.开启分布查询,PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 3.调用mapper,只能查询当前用户的消息
        // 调用用户信息接口，获取用户ID,当前写死
        Integer userId = this.getUserId();
        List<Message> as = messageMapper.listFavoriteMessages(userId, beginTime, endTime, title, favorite);
        // 强转PageHelper的返回结果,Page中提供了方法，可以获取PageHelper分布查询后得到的总记录和当前页数据
        @SuppressWarnings("resource")
        Page<Message> p = (Page<Message>) as;
        // 把数据填充到PageBean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<Message> listDraftMessages(Integer pageNum, Integer pageSize, Date beginTime, Date endTime,
            String title, String status) {
        // 1.创建PageBean对象
        PageBean<Message> pb = new PageBean<>();
        // 2.开启分布查询,PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 3.调用mapper,只能查询当前用户的消息
        // 调用用户信息接口，获取用户ID,当前写死
        Integer userId = this.getUserId();
        List<Message> as = messageMapper.listDraftMessages(userId, beginTime, endTime, title, status);
        // 强转PageHelper的返回结果,Page中提供了方法，可以获取PageHelper分布查询后得到的总记录和当前页数据
        @SuppressWarnings("resource")
        Page<Message> p = (Page<Message>) as;
        // 把数据填充到PageBean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    // 发送消息
    @Override
    public boolean sendMessage(String receiveBoat, String from, String subject,
            String content) {
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            try (MqttClient client = new MqttClient(brokerUrl, clientIdSub, persistence)) {
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                connOpts.setUserName(username);
                connOpts.setPassword(password.toCharArray());
                client.connect(connOpts);

                // 构建邮件内容
                String emailContent = String.format("Subject: %s\nFrom: %s\nTo: %s\n\n%s",
                        subject, from, receiveBoat, content);

                MqttMessage message = new MqttMessage(emailContent.getBytes());
                message.setQos(2);
                client.publish("emailTopic", message);

                client.disconnect();
            }
            return true;
        } catch (MqttException me) {
            return false;
        }
    }

    // 保存信息
    @Override
    public void saveMessage(Message message) {
        System.out.println(message);
        messageMapper.add(message.getReceiver(), message.getReceiveBoat(), message.getSender(),
                message.getSubject(), message.getContent(), message.getStatus(), getUserId(), message.getAttachement());
        System.out.println(message);
    }
}
