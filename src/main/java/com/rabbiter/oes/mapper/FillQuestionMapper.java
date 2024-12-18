package com.rabbiter.oes.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.FillQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

//填空题
@Mapper
public interface FillQuestionMapper {

    @Select("select * from fill_question where questionId in (select questionId from paper_manage where questionType = 2 and paperId = #{paperId})")
    List<FillQuestion> findByIdAndType(Integer paperId);

    @Select("select * from fill_question")
    IPage<FillQuestion> findAll(Page page);

    /**
     * 查询最后一条questionId
     * @return FillQuestion
     */
    @Select("select questionId from fill_question order by questionId desc limit 1")
    FillQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
    @Insert("insert into fill_question(subject,question,answer,analysis,level,section,score) values " +
            "(#{subject},#{question},#{answer},#{analysis},#{level},#{section}, #{score})")
    int add(FillQuestion fillQuestion);

    @Select("select questionId,score from fill_question where subject = #{subject} AND questionId not in(select questionId from paper_manage where paperId = #{paperId} and questionType = '2') order by rand() desc")
    List<FillQuestion> findBySubject(@Param("subject") String subject,@Param("paperId") Integer paperId);

    @Update("update fill_question set section = #{section}, question = #{question}, answer = #{answer}, level = #{level}, analysis = #{analysis} , score = #{score} where questionId = #{questionId}")
    int edit(FillQuestion fillQuestion);
}
