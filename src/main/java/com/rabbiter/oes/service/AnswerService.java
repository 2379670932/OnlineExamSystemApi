package com.rabbiter.oes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.vo.AnswerVO;
import com.rabbiter.oes.vo.QuestionVO;

public interface AnswerService {

    IPage<AnswerVO> findAll(Page<AnswerVO> page, String subject, String section, String question);

    /**
     * 根据类型和id获取题目
     *
     * @param type 类型
     * @param questionId 题目id
     * @return 题目信息
     */
    QuestionVO findByIdAndType(String type, Long questionId);
}
