package com.zjcds.czt.domain.entity.jpa;


import javax.persistence.*;
import java.util.List;

/**
 * @author luokp on 2019/1/4.
 */
@Entity
@Table(name = "t_exam_module")
public class ExamModule {

    private Integer id;

    private String name;

    private List<ExamQuestion> questions;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ExamQuestion.class)
    @JoinColumn(name = "module_id", nullable = true)
    public List<ExamQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ExamQuestion> questions) {
        this.questions = questions;
    }
}
