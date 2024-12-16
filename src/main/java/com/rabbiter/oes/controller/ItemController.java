package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.*;
import com.rabbiter.oes.service.PaperService;
import com.rabbiter.oes.serviceimpl.FillQuestionServiceImpl;
import com.rabbiter.oes.serviceimpl.JudgeQuestionServiceImpl;
import com.rabbiter.oes.serviceimpl.MultiQuestionServiceImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import com.rabbiter.oes.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    FillQuestionServiceImpl fillQuestionService;

    @Autowired
    JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    PaperService paperService;

    @PostMapping("/item")
    public ApiResult ItemController(@RequestBody Item item) {
        // 选择题
        Integer changeNumber = item.getChangeNumber();
        // 填空题
        Integer fillNumber = item.getFillNumber();
        // 判断题
        Integer judgeNumber = item.getJudgeNumber();
        //出卷id
        Integer paperId = item.getPaperId();

        // 数据库获取数据
        List<MultiQuestion> changeNumberList=new ArrayList<>();
        if (changeNumber!=null &&changeNumber>0){
            changeNumberList = multiQuestionService.findBySubject(item.getSubject(), changeNumber,item.getChangeTotalScore(),item.getPaperId());
            if (changeNumberList == null || changeNumberList.size() != changeNumber) {
                return ApiResultHandler.buildApiResult(400, "科目【" + item.getSubject() + "】题库【选择题】题目数量不足【" + changeNumber + "】或者该主题的题目分数不足当前总分【" + item.getChangeTotalScore() + "】，组卷失败", null);
            }
        }
        List<FillQuestion> fillList=new ArrayList<>();
        if (fillNumber!=null &&fillNumber>0){
            fillList = fillQuestionService.findBySubject(item.getSubject(), fillNumber,item.getFillTotalScore(),item.getPaperId());
            if (fillList == null || fillList.size() != fillNumber) {
                return ApiResultHandler.buildApiResult(400, "科目【" + item.getSubject() + "】题库【填空题】题目数量不足【" + fillNumber + "】或者该主题的题目分数不足当前总分【" + item.getFillTotalScore() + "】，组卷失败", null);
            }
        }
        List<JudgeQuestion> judges=new ArrayList<>();
        if (judgeNumber!=null &&judgeNumber>0){
            judges = judgeQuestionService.findBySubject(item.getSubject(), judgeNumber,item.getJudgeTotalScore(),item.getPaperId());
            if (judges == null || judges.size() != judgeNumber) {
                return ApiResultHandler.buildApiResult(400, "科目【" + item.getSubject() + "】题库【判断题】题目数量不足【" + judgeNumber + "】或者该主题的题目分数不足当前总分【" + item.getJudgeTotalScore() + "】，组卷失败", null);
            }
        }

        // 符合组题条件，执行组题
        // 选择题
        for (MultiQuestion m : changeNumberList) {
            PaperManage paperManage = new PaperManage(paperId,1,m.getQuestionId());
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"选择题组卷保存失败",null);
        }

        // 填空题
        for (FillQuestion f : fillList) {
            PaperManage paperManage = new PaperManage(paperId,2,f.getQuestionId());
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"填空题题组卷保存失败",null);
        }
        // 判断题
        for (JudgeQuestion j : judges) {
            PaperManage paperManage = new PaperManage(paperId,3,j.getQuestionId());
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"判断题题组卷保存失败",null);
        }


        return ApiResultHandler.buildApiResult(200,"试卷组卷成功",null);
    }
}
