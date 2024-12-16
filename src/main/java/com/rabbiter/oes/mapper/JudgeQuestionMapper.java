package com.rabbiter.oes.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.JudgeQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

//判断题

@Mapper
public interface JudgeQuestionMapper {

    @Select("select * from judge_question where questionId in (select questionId from paper_manage where questionType = 3 and paperId = #{paperId})")
    List<JudgeQuestion> findByIdAndType(Integer paperId);

    @Select("select * from judge_question")
    IPage<JudgeQuestion> findAll(Page page);


    /**
     * 查询最后一条记录的questionId
     * @return JudgeQuestion
     */
    @Select("select questionId from judge_question order by questionId desc limit 1")
    JudgeQuestion findOnlyQuestionId();

    @Insert("insert into judge_question(subject,question,answer,analysis,level,section,score) values " +
            "(#{subject},#{question},#{answer},#{analysis},#{level},#{section}, #{score})")
    int add(JudgeQuestion judgeQuestion);

    @Select("select questionId,score from judge_question  where subject=#{subject} AND questionId not in(select questionId from paper_manage where paperId = #{paperId} and questionType = '3') order by rand() desc")
    List<JudgeQuestion> findBySubject(@Param("subject") String subject,@Param("paperId") Integer paperId);

    @Update("update judge_question set subject = #{subject}, question = #{question}, answer = #{answer}, section = #{section}, analysis = #{analysis}, level = #{level} , score = #{score} where questionId = #{questionId}")
    int edit(JudgeQuestion judgeQuestion);
}
