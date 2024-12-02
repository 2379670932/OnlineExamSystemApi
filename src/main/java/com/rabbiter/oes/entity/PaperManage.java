package com.rabbiter.oes.entity;

public class PaperManage {
    private Integer paperId;

    private Integer questionType;

    private Integer questionId;

    public PaperManage() {
    }

    public PaperManage(Integer paperId, Integer questionType, Integer questionId) {
        this.paperId = paperId;
        this.questionType = questionType;
        this.questionId = questionId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "PaperManage{" +
                "paperId=" + paperId +
                ", questionType=" + questionType +
                ", questionId=" + questionId +
                '}';
    }
}