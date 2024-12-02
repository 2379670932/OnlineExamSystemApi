package com.rabbiter.oes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Message {
    private Integer id;
    private Integer temp_id;//解决id为null创建的一个临时id

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date time;

    List<Replay> replays;   //一对多关系，评论信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(Integer temp_id) {
        this.temp_id = temp_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Replay> getReplays() {
        return replays;
    }

    public void setReplays(List<Replay> replays) {
        this.replays = replays;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", temp_id=" + temp_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", replays=" + replays +
                '}';
    }
}