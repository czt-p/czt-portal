package com.zjcds.czt.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Iterators;
import com.zjcds.czt.dao.jpa.ExamModuleDao;
import com.zjcds.czt.dao.jpa.ExamQuestionDao;
import com.zjcds.czt.dao.jpa.ExamRecordDao;
import com.zjcds.czt.domain.dto.ExamQuestionForm;
import com.zjcds.czt.domain.dto.ExamResultForm;
import com.zjcds.czt.domain.dto.OptionForm;
import com.zjcds.czt.domain.entity.jpa.ExamModule;
import com.zjcds.czt.domain.entity.jpa.ExamQuestion;
import com.zjcds.czt.domain.entity.jpa.ExamRecord;
import com.zjcds.czt.service.HighExamService;
import com.zjcds.czt.service.SmsSendService;
import com.zjcds.czt.utils.ExamQuestionUtils;
import com.zjcds.czt.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luokp on 2019/1/5.
 */
@Service
@Transactional(readOnly = true)
public class HighExamServiceImpl implements HighExamService {

    public static String LXDH = "400-878-0703";

    @Autowired
    private ExamModuleDao examModuleDao;

    @Autowired
    private ExamQuestionDao examQuestionDao;

    @Autowired
    private ExamRecordDao examRecordDao;

    @Autowired
    private SmsSendService smsSendService;

    @Override
    public List<ExamQuestionForm.Owner> queryBasicModuleQuestions() {
        ExamModule examModule = examModuleDao.findOneForFetchQuestions(1);
        try {
            return ExamQuestionUtils.tranExamQuestions(examModule.getQuestions());
        } catch (Exception e) {
            throw new IllegalStateException("查询基础评测题目异常");
        }
    }

    @Override
    public List<ExamQuestionForm.Owner> queryHighModuleQuestions() {
        ExamModule examModule = examModuleDao.findOneForFetchQuestions(2);
        try {
            return ExamQuestionUtils.tranExamQuestions(examModule.getQuestions());
        } catch (Exception e) {
            throw new IllegalStateException("查询基础评测题目异常");
        }
    }

    @Override
    public ExamResultForm.BasicExamResult answerBasicExam(List<ExamQuestionForm.Answer> answers) {
        // 将答案存到session中
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("BasicExamAnswers", answers);
        ExamModule examModule = examModuleDao.findOneForFetchQuestions(1);
        Double score = 0D;
        for (ExamQuestion examQuestion : examModule.getQuestions()) {
            score += calcScore(examQuestion, answers);
        }
        if (score == 7) {
            session.setAttribute("BasicExamResult", true);
            return new ExamResultForm.BasicExamResult(true);
        }
        return new ExamResultForm.BasicExamResult(false);
    }

    @Override
    public ExamResultForm.HighExamResult answerHighExam(List<ExamQuestionForm.Answer> answers) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Object basicExamResult = session.getAttribute("BasicExamResult");
        if (basicExamResult == null) {
            throw new IllegalStateException("高新评分失败，请先进行基础评估！");
        }
        if (!(boolean) basicExamResult) {
            throw new IllegalStateException("贵公司暂不满足国家高新申报的基本条件，无法进行高新评分。");
        }
        List<ExamQuestionForm.Answer> allAnswers = (List<ExamQuestionForm.Answer>) session.getAttribute("BasicExamAnswers");
        allAnswers.addAll(answers);
        ExamResultForm.ExamResult examResult = calcExamResult(answers);
        examResult.setAnswers(allAnswers);
        // 将结果存session
        session.setAttribute("ExamResult", examResult);
        return new ExamResultForm.HighExamResult(examResult.getScore());
    }

    private ExamResultForm.ExamResult calcExamResult(List<ExamQuestionForm.Answer> answers) {
        Double ipScore = calcIPScore(answers);
        Double saScore = calcSAScore(answers);
        Double ramScore = calcRAMScore(answers);
        Double fgScore = calcFGScore(answers);
        Double score = ipScore + saScore + ramScore + fgScore;
        ExamResultForm.ExamResult examResult = new ExamResultForm.ExamResult();
        examResult.setScore(score);
        examResult.setIpSituation(calcIPSituation(ipScore));
        examResult.setSaSituation(calcSASituation(saScore));
        examResult.setRamSituation(calcRAMSituation(ramScore));
        examResult.setFgSituation(calcFGSituation(fgScore));
        return examResult;
    }

    /**
     * 计算知识产权情况分数
     *
     * @param answers
     * @return
     */
    private Double calcIPScore(List<ExamQuestionForm.Answer> answers) {
        Double score;
        ExamQuestion question8 = examQuestionDao.findOne(8);
        ExamQuestion question9 = examQuestionDao.findOne(9);
        ExamQuestion question10 = examQuestionDao.findOne(10);
        score = calcScore(question8, answers) + calcScore(question9, answers) + calcScore(question10, answers);
        return score;
    }

    /**
     * 计算知识产权情况
     *
     * @param score
     * @return
     */
    private String calcIPSituation(Double score) {
        if (score <= 17) {
            return "较差";
        } else if (score <= 20) {
            return "差";
        } else if (score <= 23) {
            return "好";
        } else if (score <= 27) {
            return "优秀";
        }
        return "优秀";
    }

    /**
     * 计算科技成果转换情况分数
     *
     * @param answers
     * @return
     */
    private Double calcSAScore(List<ExamQuestionForm.Answer> answers) {
        Double score;
        ExamQuestion question11 = examQuestionDao.findOne(11);
        score = calcScore(question11, answers);
        return score;
    }

    /**
     * 计算科技成果转换情况
     *
     * @param score
     * @return
     */
    private String calcSASituation(Double score) {
        if (score == 0) {
            return "差";
        } else if (score <= 3) {
            return "一般";
        } else if (score <= 10) {
            return "良好";
        } else if (score <= 16) {
            return "好";
        } else if (score <= 28) {
            return "优秀";
        }
        return "优秀";
    }

    /**
     * 计算企业研发组织管理水平分数
     *
     * @param answers
     * @return
     */
    private Double calcRAMScore(List<ExamQuestionForm.Answer> answers) {
        Double score;
        ExamQuestion question12 = examQuestionDao.findOne(12);
        ExamQuestion question13 = examQuestionDao.findOne(13);
        score = calcScore(question12, answers) + calcScore(question13, answers);
        return score;
    }

    /**
     * 计算企业研发组织管理水平
     *
     * @param score
     * @return
     */
    private String calcRAMSituation(Double score) {
        if (score == 0) {
            return "较差";
        } else if (score <= 3) {
            return "差";
        } else if (score <= 15) {
            return "好";
        } else if (score <= 18) {
            return "优秀";
        }
        return "优秀";
    }

    /**
     * 计算财务增长情况分数
     *
     * @param answers
     * @return
     */
    private Double calcFGScore(List<ExamQuestionForm.Answer> answers) {
        Double score;
        ExamQuestion question14 = examQuestionDao.findOne(14);
        ExamQuestion question15 = examQuestionDao.findOne(15);
        score = calcScore(question14, answers) + calcScore(question15, answers);
        return score;
    }

    /**
     * 计算财务增长情况
     *
     * @param score
     * @return
     */
    private String calcFGSituation(Double score) {
        if (score <= 4) {
            return "差";
        } else if (score <= 6) {
            return "一般";
        } else if (score <= 9) {
            return "良好";
        } else if (score <= 19) {
            return "优秀";
        }
        return "优秀";
    }

    /**
     * 计算某题的分数
     *
     * @param examQuestion
     * @param answers
     * @return
     */
    private Double calcScore(ExamQuestion examQuestion, List<ExamQuestionForm.Answer> answers) {
        ExamQuestionForm.Answer found = Iterators.find(answers.iterator(), answer -> {
            if (answer.getId().equals(examQuestion.getId())) {
                return true;
            } else {
                return false;
            }
        }, null);
        if (found == null) {
            return 0D;
        }
        return calcScore(examQuestion, found);
    }

    /**
     * 计算某题的分数
     *
     * @param examQuestion
     * @param answer
     * @return
     */
    private Double calcScore(ExamQuestion examQuestion, ExamQuestionForm.Answer answer) {
        try {
            List<OptionForm.Owner> options = JsonUtils.toObject(examQuestion.getDetails(), new TypeReference<List<OptionForm.Owner>>() {
            });
            OptionForm.Owner found = Iterators.find(options.iterator(), option -> {
                if (answer.getAnswer().equals(option.getId())) {
                    return true;
                } else {
                    return false;
                }
            }, null);
            if (found != null) {
                return found.getScore();
            }
            return 0D;
        } catch (IOException e) {
            return 0D;
        }
    }

    @Override
    @Transactional
    public void sendDetailResult(String companyName, String telephone) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        ExamResultForm.ExamResult examResult = (ExamResultForm.ExamResult) session.getAttribute("ExamResult");
        ExamRecord examRecord = new ExamRecord();
        examRecord.setCompanyName(companyName);
        examRecord.setTelephone(telephone);
        examRecord.setScore(examResult.getScore());
        examRecord.setResult("公司知识产权情况：" + examResult.getIpSituation() + "；科技成果转化情况：" + examResult.getSaSituation() + "；企业研发组织管理水平：" + examResult.getRamSituation() + "；财务增长情况：" + examResult.getFgSituation());
        examRecord.setDetails(JsonUtils.toJson(examResult.getAnswers()));
        examRecord.setExamTime(new Date());
        // 发送短信
        Boolean smsResult = sendResultSms(telephone, examResult);
        if (!smsResult) {
            throw new IllegalStateException("发送评测报告短信失败！");
        }
        // 保存评测记录
        examRecordDao.save(examRecord);
    }

    private Boolean sendResultSms(String telephone, ExamResultForm.ExamResult examResult) {
        Map<String, Object> variates = new HashMap<>();
        variates.put("pczf", examResult.getScore());
        variates.put("zzcq", examResult.getIpSituation());
        variates.put("hjcg", examResult.getSaSituation());
        variates.put("glsp", examResult.getRamSituation());
        variates.put("cwzz", examResult.getFgSituation());
        variates.put("lxdh", LXDH);
        return smsSendService.singleSend(telephone, "gxpc", variates);
    }
}
