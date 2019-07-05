package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.ClassificationDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_submitClassification;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

public interface Rewrite_ClassificationService {

    Result insertClassification(Rewrite_submitClassification rewrite_submitClassification);

    // 创建商户分类
    void createClassification(ClassificationDTO classificationDTO);
}
