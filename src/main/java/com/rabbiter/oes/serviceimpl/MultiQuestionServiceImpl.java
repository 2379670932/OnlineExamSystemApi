package com.rabbiter.oes.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.MultiQuestion;
import com.rabbiter.oes.mapper.MultiQuestionMapper;
import com.rabbiter.oes.service.MultiQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MultiQuestionServiceImpl implements MultiQuestionService {

    @Autowired
    private MultiQuestionMapper multiQuestionMapper;
    @Override
    public List<MultiQuestion> findByIdAndType(Integer PaperId) {
        return multiQuestionMapper.findByIdAndType(PaperId);
    }

    @Override
    public IPage<MultiQuestion> findAll(Page<MultiQuestion> page) {
        return multiQuestionMapper.findAll(page);
    }

    @Override
    public MultiQuestion findOnlyQuestionId() {
        return multiQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(MultiQuestion multiQuestion) {
        return multiQuestionMapper.add(multiQuestion);
    }


    @Override
    public List<MultiQuestion> findBySubject(String subject, Integer pageNo, Integer totalScore,Integer paperId) {
        List<MultiQuestion> judgeQuestionList = multiQuestionMapper.findBySubject(subject,paperId);
        return findQuestionsWithTotalScore(judgeQuestionList, pageNo, totalScore, new ArrayList<>(), 0);
    }

    private List<MultiQuestion> findQuestionsWithTotalScore(
            List<MultiQuestion> questions,
            Integer limit,
            Integer targetScore,
            List<MultiQuestion> currentSelection,
            int startIndex) {

        // 如果当前选择的问题数达到限制且总分等于目标分数，则返回当前选择
        if (currentSelection.size() == limit && getScoreSum(currentSelection) == targetScore) {
            return new ArrayList<>(currentSelection);
        }

        // 如果已经选择了足够数量的问题或总分超过了目标分数，则停止继续探索
        if (currentSelection.size() >= limit || getScoreSum(currentSelection) > targetScore) {
            return null;
        }

        for (int i = startIndex; i < questions.size(); i++) {
            MultiQuestion question = questions.get(i);

            // 尝试添加一个问题到当前选择中
            currentSelection.add(question);

            // 递归调用，继续尝试添加更多问题
            List<MultiQuestion> result = findQuestionsWithTotalScore(questions, limit, targetScore, currentSelection, i + 1);

            // 如果找到了满足条件的子集，则返回结果
            if (result != null) {
                return result;
            }

            // 否则回溯，移除最后添加的问题
            currentSelection.remove(currentSelection.size() - 1);
        }

        // 如果没有找到满足条件的子集，则返回null
        return null;
    }

    private Integer getScoreSum(List<MultiQuestion> questions) {
        Integer scoreSum = 0;
        for (MultiQuestion question : questions) {
            scoreSum+=question.getScore();
        }
        return scoreSum;
    }

    @Override
    public int edit(MultiQuestion multiQuestion) {
        return multiQuestionMapper.edit(multiQuestion);
    }
}
