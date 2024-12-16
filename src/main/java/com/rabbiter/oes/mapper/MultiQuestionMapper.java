package com.rabbiter.oes.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.MultiQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface MultiQuestionMapper {
    /**
     * select * from multiquestions where questionId in (
     * 	select questionId from papermanage where questionType = 1 and paperId = 1001
     * )
     */
    @Select("select * from multi_question where questionId in (select questionId from paper_manage where questionType = 1 and paperId = #{paperId})")
    List<MultiQuestion> findByIdAndType(Integer PaperId);

    @Select("select * from multi_question")
    IPage<MultiQuestion> findAll(Page page);

    /**
     * 查询最后一条记录的questionId
     * @return MultiQuestion
     */
    @Select("select questionId from multi_question order by questionId desc limit 1")
    MultiQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty = "questionId")
    @Insert("insert into multi_question(subject,question,answerA,answerB,answerC,answerD,rightAnswer,analysis,section,level,score) " +
            "values(#{subject},#{question},#{answerA},#{answerB},#{answerC},#{answerD},#{rightAnswer},#{analysis},#{section},#{level}, #{score})")
    int add(MultiQuestion multiQuestion);

    @Select("select questionId,score from multi_question  where subject =#{subject} AND questionId not in(select questionId from paper_manage where paperId = #{paperId} and questionType = '1') order by rand() desc")
    List<MultiQuestion> findBySubject(@Param("subject") String subject,@Param("paperId") Integer paperId);

    @Update("update multi_question set subject = #{subject}, question = #{question}, answerA = #{answerA}, answerB = #{answerB}, answerC = #{answerC}, answerD = #{answerD}, rightAnswer = #{rightAnswer}, analysis = #{analysis}, section = #{section}, level = #{level}, score = #{score} where questionId = #{questionId}")
    int edit(MultiQuestion multiQuestion);
}
