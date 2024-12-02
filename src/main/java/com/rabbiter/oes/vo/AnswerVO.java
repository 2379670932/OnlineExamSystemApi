package com.rabbiter.oes.vo;

public class AnswerVO {
    private Long questionId;
    private String question;
    private String subject;
    private String score;
    private String section;
    private String level;
    private String type;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AnswerVO{" +
                "question='" + question + '\'' +
                ", subject='" + subject + '\'' +
                ", score='" + score + '\'' +
                ", section='" + section + '\'' +
                ", level='" + level + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
