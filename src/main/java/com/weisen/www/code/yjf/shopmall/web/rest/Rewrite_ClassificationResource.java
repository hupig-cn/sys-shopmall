package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.service.Rewrite_ClassificationService;
import com.weisen.www.code.yjf.shopmall.service.dto.ClassificationDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/weisen/classification")
@Api(tags = "商品分类")
public class Rewrite_ClassificationResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ClassificationResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_ClassificationService rewrite_ClassificationService;

    public Rewrite_ClassificationResource(Rewrite_ClassificationService rewrite_ClassificationService) {
        this.rewrite_ClassificationService = rewrite_ClassificationService;
    }

    @PostMapping("/createClassification")
    @ApiOperation(value = "创建商户分类")
    @Timed
    public ResponseEntity<Result> createClassification(@RequestBody ClassificationDTO classificationDTO) throws URISyntaxException {
        log.debug("REST request to save Commodity : {}", classificationDTO);
//        if (commodityDTO.getId() != null) {
//            throw new BadRequestAlertException("A new commodity cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        rewrite_ClassificationService.createClassification(classificationDTO);
        return  ResponseEntity.ok(Result.suc("成功"));


    }
}
