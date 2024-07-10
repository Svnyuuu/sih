package com.ahu.controller;

import java.util.*;

import com.ahu.pojo.PageBean;
import org.eclipse.paho.client.mqttv3.MqttClient;
import com.ahu.pojo.Result;
import com.ahu.service.MessageService;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.ahu.pojo.Message;

//项目拉取路径https://gitee.com/dxqllp/CommSys.git

@RestController
@RequestMapping("/sys")
@Validated
public class MessageController {

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private MessageService messageService;

    // 1. 收件箱，草稿箱，回收站，已发送，收藏夹：每项的首页，所读取信息根据status不同选择,status=1
    // 1.1 消息显示(带条件与分页)
    @SuppressWarnings("unchecked")
    @GetMapping("/receive")
    public Result<PageBean<Message>> getAllListMessages(Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date beginTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
            @RequestParam(required = false) String title,
            String status) {
        try {
            // System.out.println("pageNum:" +
            // pageNum+",pageSize:"+pageSize+",beginTime:"+beginTime+",endTime:"+endTime);
            // System.out.println("title:"+title+",status:"+status);
            PageBean<Message> data = messageService.listAllMessages(pageNum, pageSize, beginTime, endTime, title,
                    status);
            // System.out.println(data.getItems());
            return Result.success("消息列表分页成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("消息列表获取失败");
        }
    }

    // 1.2 删除消息
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/delete")
    public Result DeleteMessages(@RequestParam List<Integer> id) {
        try {
            System.out.println("id=" + id);
            messageService.deleteMessages(id);
            return Result.success("消息删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("消息删除失败");
        }
    }

    // 1.3 收藏状态更新
    @SuppressWarnings("rawtypes")
    @GetMapping("/favoriteUpdate")
    public Result updateFavorite(@RequestParam List<Integer> ids, @RequestParam List<Integer> favorites) {
        if (ids.size() != favorites.size()) {
            return Result.error("ID列表和收藏状态列表的长度不匹配");
        }
        try {
            for (int i = 0; i < ids.size(); i++) {
                Integer id = ids.get(i);
                Integer favorite = favorites.get(i);
                System.out.println("id:" + id + ", favorite:" + favorite);
                messageService.updateMessageFavorite(id, favorite);
            }
            return Result.success("信息收藏状态更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("信息收藏状态更新失败");
        }
    }

    // 2 已发送
    // 收件箱，草稿箱，回收站，已发送，收藏夹：每项的首页，所读取信息根据status不同选择,status=2
    // 2.1 消息显示(带条件与分页)
    @SuppressWarnings("unchecked")
    @GetMapping("/send")
    public Result<PageBean<Message>> getAllSendMessages(Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date beginTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
            @RequestParam(required = false) String title,
            String status) {
        try {
            System.out.println(
                    "pageNum:" + pageNum + ",pageSize:" + pageSize + ",beginTime:" + beginTime + ",endTime:" + endTime);
            PageBean<Message> data = messageService.listAllSendMessages(pageNum, pageSize, beginTime, endTime, title,
                    status);
            // System.out.println(data.getItems());
            return Result.success("消息列表分页成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("消息列表获取失败");
        }
    }

    // 3 回收站
    // 收件箱，草稿箱，回收站，已发送，收藏夹：每项的首页，所读取信息根据status不同选择,status=4
    // 3.1 消息显示(带条件与分页)
    @SuppressWarnings("unchecked")
    @GetMapping("/reback")
    public Result<PageBean<Message>> getAllRecycleMessages(Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date beginTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
            @RequestParam(required = false) String title,
            String status) {
        try {
            System.out.println(
                    "pageNum:" + pageNum + ",pageSize:" + pageSize + ",beginTime:" + beginTime + ",endTime:" + endTime);
            PageBean<Message> data = messageService.listAllRecycleMessages(pageNum, pageSize, beginTime, endTime, title,
                    status);
            // System.out.println(data.getItems());
            return Result.success("消息列表分页成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("消息列表获取失败");
        }
    }

    // 3.2 删除消息
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/deleteForever")
    public Result DeleteMessagesForever(@RequestParam Integer id) {
        try {
            System.out.println("id=" + id);
            messageService.deleteForeverMessages(id);
            return Result.success("消息删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("消息删除失败");
        }
    }

    // 4. 收藏夹
    // 4.1 消息显示(带条件与分页)
    @SuppressWarnings("unchecked")
    @GetMapping("/favorite")
    public Result<PageBean<Message>> getAllFavoriteMessages(Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date beginTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
            @RequestParam(required = false) String title,
            Integer favorite) {
        try {
            PageBean<Message> data = messageService.listFavoriteMessages(pageNum, pageSize, beginTime, endTime, title,
                    favorite);
            // System.out.println(data.getItems());
            return Result.success("消息列表分页成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("消息列表获取失败");
        }
    }

    // 5. 草稿箱
    // 5.1 消息显示(带条件与分页)
    @SuppressWarnings("unchecked")
    @GetMapping("/draft")
    public Result<PageBean<Message>> getAllDraftMessages(Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date beginTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
            @RequestParam(required = false) String title,
            String status) {
        try {
            System.out.println("pageNum:" + pageNum + ",pageSize:" + pageSize + ",beginTime:" + beginTime);
            System.out.println("status:" + status);
            PageBean<Message> data = messageService.listDraftMessages(pageNum, pageSize, beginTime, endTime, title,
                    status);
            // System.out.println(data.getItems());
            return Result.success("消息列表分页成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("消息列表获取失败");
        }
    }

    // 6.新建信息
    // 6.1存到草稿箱
    @SuppressWarnings("rawtypes")
    @PostMapping("/saveToDraft")
    public Result SaveMessage(@RequestBody @Validated Message message) {
        try {
            // 信息状态为草稿箱
            message.setStatus(3);
            // System.out.println(message);
            // 保存信息到数据库
            messageService.saveMessage(message);
            return Result.success("信息保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("信息保存失败");
        }
    }

    // 下载附件
    @SuppressWarnings("rawtypes")
    @GetMapping("/download")
    public Result downloadFile(@RequestParam String attachement) {
        try {
            // 创建RestTemplate对象
            RestTemplate restTemplate = new RestTemplate();
            // 使用RestTemplate下载文件
            byte[] fileBytes = restTemplate.getForObject(attachement, byte[].class);

            // 将文件字节数组封装为Resource对象
            Resource resource = new ByteArrayResource(fileBytes);

            return Result.success("文件下载成功", resource);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // 捕获HTTP客户端和服务器错误，并打印详细的响应状态码和错误信息
            e.printStackTrace();
            return Result.error("文件下载失败 - HTTP状态码: " + e.getStatusCode() + ", 错误信息: " + e.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
            // 捕获资源访问异常，例如连接超时
            e.printStackTrace();
            return Result.error("文件下载失败 - 资源访问异常: " + e.getMessage());
        } catch (Exception e) {
            // 捕获其他所有异常
            e.printStackTrace();
            return Result.error("文件下载失败 - 系统异常: " + e.getMessage());
        }
    }

    // 发送消息
    @SuppressWarnings("rawtypes")
    @PostMapping("/sendMessage")
    public Result SendMessage(@RequestBody @Validated Message message) {
        try {
            // 确保客户端已连接
            if (!mqttClient.isConnected()) {
                mqttClient.connect();
            }

            // 构建消息内容
            String messageContent = String.format("To: %s\nFrom: %s\nSubject: %s\n\n%s",
                    message.getReceiveBoat(), message.getSender(), message.getSubject(),
                    message.getContent());
            System.out.println("Publishing message: " + messageContent);

            // 发布消息请求到MQTT主题
            String topic = "messageTopic";
            MqttMessage m = new MqttMessage(messageContent.getBytes());
            m.setQos(2);
            mqttClient.publish(topic, m);

            // 保存消息到数据库
            // messageService.saveMessage(message.getReceiveBoat(), message.getSender(),
            // message.getSubject(), message.getContent());

            return Result.success("消息发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("消息发送失败");
        }
    }

}