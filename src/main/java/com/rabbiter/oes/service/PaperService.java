package com.rabbiter.oes.service;

import com.rabbiter.oes.entity.PaperManage;

import java.util.List;

public interface PaperService {

    List<PaperManage> findAll();

    List<PaperManage> findById(Integer paperId);

    int add(PaperManage paperManage);

    // 获取试卷总分
    Integer getMaxScore(Integer paperId);

    /**
     * 删除试卷中的某条试题
     *
     * @param paperId 试卷id
     * @param type 题目类型。1选择，2填空，3判断
     * @param questionId 题目id
     */
    void delete(String paperId, String type, String questionId);

    /**
     * 根据试卷id删除题目关联
     *
     * @param paperId 试卷id
     */
    void deleteByPaperId(Integer paperId);
}
