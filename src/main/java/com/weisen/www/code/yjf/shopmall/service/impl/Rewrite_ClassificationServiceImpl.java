package com.weisen.www.code.yjf.shopmall.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.shopmall.domain.Classification;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_ClassificationRepository;
import com.weisen.www.code.yjf.shopmall.security.SecurityUtils;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_ClassificationService;
import com.weisen.www.code.yjf.shopmall.service.dto.ClassificationDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_submitClassification;
import com.weisen.www.code.yjf.shopmall.service.util.CheckUtils;
import com.weisen.www.code.yjf.shopmall.service.util.DateUtils;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;

@Service
@Transactional
public class Rewrite_ClassificationServiceImpl implements Rewrite_ClassificationService {

    private final Rewrite_ClassificationRepository rewrite_classificationRepository;

    public Rewrite_ClassificationServiceImpl(Rewrite_ClassificationRepository rewrite_classificationRepository ) {
        this.rewrite_classificationRepository = rewrite_classificationRepository;
    }

    public Result insertClassification(Rewrite_submitClassification rewrite_submitClassification) {
        if (!CheckUtils.checkObj(rewrite_submitClassification))
            return Result.fail("创建信息异常");
            //如果可以直接获取到用户信息则不需要传入
            //创建者
//        else if(!CheckUtils.checkString(rewrite_submitClassification.getCreator()))
//            return Result.fail();
            //修改者
//        else if(!CheckUtils.checkString(rewrite_submitClassification.getModifier()))
//            return Result.fail();
            //分类名称
        else if (!CheckUtils.checkString(rewrite_submitClassification.getName()))
            return Result.fail("分类名称不能为空");
            //上级分类id
        else if (!CheckUtils.checkString(rewrite_submitClassification.getSuperior()))
            return Result.fail("上级分类id不能为空");
        else {
            //创建分类
            String creator = SecurityUtils.getCurrentUserLogin().get();
            if (!CheckUtils.checkString(creator))
                return Result.fail("用户信息异常");
            Classification classification = new Classification();
            String dateForNow = DateUtils.getDateForNow();
            classification.setCreatedate(dateForNow);
            classification.setCreator(creator);
            classification.setName(rewrite_submitClassification.getName());
            if (!CheckUtils.checkString(rewrite_submitClassification.getOrder()))
                classification.setOrder(rewrite_submitClassification.getOrder());
            if (!CheckUtils.checkString(rewrite_submitClassification.getOther()))
                classification.setOther(rewrite_submitClassification.getOther());
            if (!CheckUtils.checkString(rewrite_submitClassification.getSuperior()))
                classification.setSuperior(rewrite_submitClassification.getSuperior());
            Classification save = rewrite_classificationRepository.save(classification);
            if(null == save)
                return Result.fail("创建分类失败");
            return Result.suc("创建成功");
        }
    }

    // 创建商户分类
    @Override
    public void createClassification(ClassificationDTO classificationDTO) {
        Classification classification = new Classification();
        classification.setName(classificationDTO.getName());
        classification.setSuperior(classificationDTO.getSuperior());
        classification.setOrder(classificationDTO.getOrder());
        classification.setCreator("");
        classification.setCreatedate(TimeUtil.getDate());
        classification.setLogicdelete(false);
        classification.setOther("");
        rewrite_classificationRepository.save(classification);
    }

}
