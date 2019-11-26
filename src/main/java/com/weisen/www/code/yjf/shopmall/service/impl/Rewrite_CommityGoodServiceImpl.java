package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Files;
import com.weisen.www.code.yjf.shopmall.domain.Prodcutimage;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.*;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_CommityGoodService;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_Commity2DTO;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_GoodsCommity2DTO;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_GoodsCommityDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/29 11:31
 */
@Service
@Transactional
public class Rewrite_CommityGoodServiceImpl implements Rewrite_CommityGoodService {

    @Value("${images-path}")
    private String imagesPath;


    private Long hWidth = 0L;

    private Long hHeigh = 0L;

    private Long sWidth = 0L;

    private Long sHeigh = 0L;

    private final Rewrite_SpecificationsRepository rewrite_specificationsRepository;

    private final Rewrite_CommodityRepository rewrite_commodityRepository;

    private final Reweite_ProdcutimageRepository reweite_prodcutimageRepository;

    private final FilesRepository filesRepository;

    public Rewrite_CommityGoodServiceImpl(Rewrite_SpecificationsRepository rewrite_specificationsRepository, Rewrite_CommodityRepository rewrite_commodityRepository, Reweite_ProdcutimageRepository reweite_prodcutimageRepository, FilesRepository filesRepository) {
        this.rewrite_specificationsRepository = rewrite_specificationsRepository;
        this.rewrite_commodityRepository = rewrite_commodityRepository;
        this.reweite_prodcutimageRepository = reweite_prodcutimageRepository;
        this.filesRepository = filesRepository;
    }

    @Override
    public Result myfilesList(Integer pageSize, Integer pageNum,Integer type,Integer condition) {
        List<Specifications> specificationsByOrdrerBys = new ArrayList<>();
        int a = pageNum * pageSize;
        String ccc = "";
        if (type == 0){ //type = 0 全部商品  type = 1 积分精选，2 美食, 3 数码 ，4 居家
            specificationsByOrdrerBys = rewrite_specificationsRepository.findSpecificationsByOrdrerBys(a, pageSize);
        }else {
            if (type == 2) {
                ccc = "13";
            }else if (type == 3){
                ccc = "15";
            }else if (type == 4){
                ccc = "14";
            }
            List<Long> commodityByBrandid = rewrite_commodityRepository.findCommodityByBrandid(ccc);
            for (int i = 0; i < commodityByBrandid.size(); i++) {
                Long asd = commodityByBrandid.get(i);
                Specifications s = rewrite_specificationsRepository.findSpecificationsByCommodityid(asd + "");
                specificationsByOrdrerBys.add(s);
            }

        }
        List<Rewrite_Commity2DTO> bbc = new ArrayList<>();

        for (int i = 0; i < specificationsByOrdrerBys.size(); i++) {
            Rewrite_Commity2DTO rewrite_commity2DTO = new Rewrite_Commity2DTO();

            Specifications s = specificationsByOrdrerBys.get(i);
            String price = s.getPrice();
            Long fileid = s.getFileid();
            String commodityid = s.getCommodityid();
            String specifications = s.getSpecifications();
            Files files = filesRepository.findByIds(fileid);
            Integer height = files.getHeight();
            Integer width = files.getWidth();

            rewrite_commity2DTO.setCommodityId(Long.valueOf(commodityid));
            rewrite_commity2DTO.setText(specifications);
            rewrite_commity2DTO.setPrice(price);
            rewrite_commity2DTO.setUrl(imagesPath+fileid);
            rewrite_commity2DTO.setWidth(width);
            rewrite_commity2DTO.setHeight(height);
            bbc.add(rewrite_commity2DTO);
        }

        return Result.suc("查询成功",bbc,specificationsByOrdrerBys.size());
    }

    @Override
    public Result findCommodityInfo2(String commityid) {
        //商品详情
        Rewrite_GoodsCommity2DTO r = new Rewrite_GoodsCommity2DTO();

        Specifications a = rewrite_specificationsRepository.findSpecificationsByCommodityid(commityid);
        String specifications = a.getCommodityid();
        String title = a.getSpecifications();
        String price = a.getPrice();
        String model = a.getModel();
        Long fileid = a.getFileid();
        List<Long> Hp = new ArrayList<>();
        List<Rewrite_GoodsCommityDTO> hplist = new ArrayList<>();
        List<Rewrite_GoodsCommityDTO> splist = new ArrayList<>();
        List<Long> Sp = new ArrayList<>();
        List<Prodcutimage> slist = reweite_prodcutimageRepository.findAllBySpecificationsid(Long.valueOf(commityid));
        for (int i = 0; i < slist.size(); i++) {
            Prodcutimage prodcutimage = slist.get(i);
            String type = prodcutimage.getType();
            if (type.equals("1")){
                Hp.add(Long.valueOf(prodcutimage.getFileid()));
            }else {
                Sp.add(Long.valueOf(prodcutimage.getFileid()));
            }
        }
        hplist = aab(Hp,0);
        splist = aab(Sp,1);
        r.setSmallUrl(imagesPath+fileid);
        r.sethWidth(hWidth);
        r.sethHeigh(hHeigh);
        r.setsWidth(sWidth);
        r.setsHeigh(sHeigh);
        r.setCommodityId(specifications);
        r.setTitle(title);
        r.setPrice(price);
        r.setHplist(hplist);
        r.setSplist(splist);
        r.setModel(model);
        return Result.suc("查询成功",r);
    }

    public List<Rewrite_GoodsCommityDTO> aab (List<Long> Sp,Integer a){
        List<Rewrite_GoodsCommityDTO> splist = new ArrayList<>();
        if (a == 1){
            Sp.add(3315L);
        }
        for (int i = 0; i < Sp.size(); i++) {
            Long aa = Sp.get(i);
            //图片id
            Rewrite_GoodsCommityDTO rewrite_goodsCommityDTO = new Rewrite_GoodsCommityDTO();

            Files filesById = filesRepository.findByIds(aa);
            Integer width = filesById.getWidth();
            Integer height = filesById.getHeight();
            String fileContentType = filesById.getFileContentType();
            Integer size = filesById.getSize();
            rewrite_goodsCommityDTO.setUrl(imagesPath+aa);
            rewrite_goodsCommityDTO.setWidth(width);
            rewrite_goodsCommityDTO.setHeight(height);
            rewrite_goodsCommityDTO.setType(fileContentType);
            rewrite_goodsCommityDTO.setSize(size);
            splist.add(rewrite_goodsCommityDTO);
            if (a == 1){
                sHeigh = sHeigh + height;
                sWidth = sWidth + width;
            }else {
                hHeigh = hHeigh + height;
                hWidth = hWidth + width;
            }
        }
        return splist;
    }

}
