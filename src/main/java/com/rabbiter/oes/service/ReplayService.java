package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.Replay;

import java.util.List;

public interface ReplayService {

    List<Replay> findAll();

    List<Replay> findAllById(Integer messageId);

    Replay findById(Integer replayId);

    int delete(Integer replayId);

    int update(Replay replay);

    int add(Replay replay);
}
