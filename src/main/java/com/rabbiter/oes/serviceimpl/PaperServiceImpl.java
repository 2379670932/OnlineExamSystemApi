package com.rabbiter.oes.serviceimpl;

import com.rabbiter.oes.entity.FillQuestion;
import com.rabbiter.oes.entity.JudgeQuestion;
import com.rabbiter.oes.entity.MultiQuestion;
import com.rabbiter.oes.entity.PaperManage;
import com.rabbiter.oes.mapper.PaperMapper;
import com.rabbiter.oes.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    @Override
    public List<PaperManage> findAll() {
        return paperMapper.findAll();
    }

    @Override
    public List<PaperManage> findById(Integer paperId) {
        return paperMapper.findById(paperId);
    }

    @Override
    public int add(PaperManage paperManage) {
        return paperMapper.add(paperManage);
    }

    @Override
    public Integer getMaxScore(Integer paperId) {

        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);   //选择题题库 1
        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);     //填空题题库 2
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);   //判断题题库 3
        return 2 * (multiQuestionRes.size() + fillQuestionsRes.size() + judgeQuestionRes.size());
    }

    @Override
    public void delete(String paperId, String type, String questionId) {
        paperMapper.delete(paperId, type, questionId);
    }

    @Override
    public void deleteByPaperId(Integer paperId) {
        paperMapper.deleteByPaperId(paperId);
    }

}
