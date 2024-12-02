package com.rabbiter.oes.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.serviceimpl.AnswerServiceImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import com.rabbiter.oes.vo.AnswerVO;
import com.rabbiter.oes.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AnswerController {

    @Autowired
    private AnswerServiceImpl answerService;

    @GetMapping("/answers/{page}/{size}/{subject}/{section}/{question}")
    public ApiResult findAllQuestion(
            @PathVariable("page") Integer page, @PathVariable("size") Integer size,
            @PathVariable("subject") String subject, @PathVariable("section") String section,
            @PathVariable("question") String question){
       Page<AnswerVO> answerVOPage = new Page<>(page,size);
       IPage<AnswerVO> answerVOIPage = answerService.findAll(answerVOPage, subject, section, question);
       return ApiResultHandler.buildApiResult(200,"查询所有题库", answerVOIPage);
    }

    /**
     * 根据类型和id获取题目
     *
     * @param type 类型
     * @param questionId 题目id
     * @return 题目信息
     */
    @GetMapping("/answers/{type}/{questionId}")
    public ApiResult findByIdAndType(
            @PathVariable("type") String type, @PathVariable("questionId") Long questionId
    ) {
        QuestionVO questionVO = answerService.findByIdAndType(type, questionId);
        return ApiResultHandler.buildApiResult(200, "查询题目", questionVO);
    }

}
