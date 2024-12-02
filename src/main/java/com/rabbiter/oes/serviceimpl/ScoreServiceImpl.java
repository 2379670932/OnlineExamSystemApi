package com.rabbiter.oes.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.Score;
import com.rabbiter.oes.mapper.ScoreMapper;
import com.rabbiter.oes.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public int add(Score score) {
        return scoreMapper.add(score);
    }

    @Override
    public List<Score> findAll() {
        return scoreMapper.findAll();
    }

    @Override
    public IPage<Score> findById(Page page, Integer studentId) {
        return scoreMapper.findById(page, studentId);
    }

    @Override
    public List<Score> findById(Integer studentId) {
        Page<Score> scorePage = new Page<>(0, 9999);
        return scoreMapper.findById(scorePage, studentId).getRecords();
    }

    @Override
    public List<Score> findByExamCode(Integer examCode) {
        return scoreMapper.findByExamCode(examCode);
    }
}
