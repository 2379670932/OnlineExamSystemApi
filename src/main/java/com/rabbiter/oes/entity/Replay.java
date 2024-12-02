package com.rabbiter.oes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Replay {
    private Integer messageId;

    private Integer replayId;

    private String replay;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date replayTime;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getReplayId() {
        return replayId;
    }

    public void setReplayId(Integer replayId) {
        this.replayId = replayId;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public Date getReplayTime() {
        return replayTime;
    }

    public void setReplayTime(Date replayTime) {
        this.replayTime = replayTime;
    }

    @Override
    public String toString() {
        return "Replay{" +
                "messageId=" + messageId +
                ", replayId=" + replayId +
                ", replay='" + replay + '\'' +
                ", replayTime=" + replayTime +
                '}';
    }
}