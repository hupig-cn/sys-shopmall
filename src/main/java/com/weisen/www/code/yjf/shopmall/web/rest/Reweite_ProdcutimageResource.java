package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.service.Rewrite_ProdcutimageService;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_ShoppingService;
import com.weisen.www.code.yjf.shopmall.service.dto.ProdcutimageDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.ShoppingDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weisen/prodcutimage")
@Api(tags = "商城图片")
public class Reweite_ProdcutimageResource {

    private final Logger log = LoggerFactory.getLogger(Reweite_ProdcutimageResource.class);

    private final Rewrite_ProdcutimageService rewrite_ProdcutimageService;

    public Reweite_ProdcutimageResource(Rewrite_ProdcutimageService rewrite_ProdcutimageService) {
        this.rewrite_ProdcutimageService = rewrite_ProdcutimageService;
    }

    @PostMapping("/createImage")
    @ApiOperation(value = "创建商品规格图片")
    @Timed
    public ResponseEntity<Result> createImage(@RequestBody ProdcutimageDTO prodcutimageDTO) {
        log.debug("createImage : {}");
        Result result = rewrite_ProdcutimageService.createImage(prodcutimageDTO);
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/findAllBySpecifications/{specificationsid}")
    @ApiOperation(value = "查询商品规格的图片")
    @Timed
    public ResponseEntity<Result> findAllBySpecifications(@PathVariable Long specificationsid) {
        log.debug("findAllBySpecifications : {}");
        Result result = rewrite_ProdcutimageService.findAllBySpecifications(specificationsid);
        return  ResponseEntity.ok(result);
    }

    @PostMapping("/updateImage")
    @ApiOperation(value = "修改商品规格的图片")
    @Timed
    public ResponseEntity<Result> updateImage(@RequestBody ProdcutimageDTO prodcutimageDTO) {
        log.debug("updateImage : {}");
        Result result = rewrite_ProdcutimageService.updateImage(prodcutimageDTO);
        return  ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteImage/{imageId}")
    @ApiOperation(value = "删除商品规格的图片")
    @Timed
    public ResponseEntity<Result> deleteImage(@PathVariable Long imageId) {
        log.debug("deleteImage : {}");
        Result result = rewrite_ProdcutimageService.deleteImage(imageId);
        return  ResponseEntity.ok(result);
    }

}
