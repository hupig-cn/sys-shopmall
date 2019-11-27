package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Classification;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private final Rewrite_ClassificationRepository rewrite_classificationRepository;

    public Rewrite_CommityGoodServiceImpl(Rewrite_SpecificationsRepository rewrite_specificationsRepository, Rewrite_CommodityRepository rewrite_commodityRepository, Reweite_ProdcutimageRepository reweite_prodcutimageRepository, FilesRepository filesRepository, Rewrite_ClassificationRepository rewrite_classificationRepository) {
        this.rewrite_specificationsRepository = rewrite_specificationsRepository;
        this.rewrite_commodityRepository = rewrite_commodityRepository;
        this.reweite_prodcutimageRepository = reweite_prodcutimageRepository;
        this.filesRepository = filesRepository;
        this.rewrite_classificationRepository = rewrite_classificationRepository;
    }

    @Override
    public Result myfilesList(Integer pageSize, Integer pageNum,Integer type,Integer condition,String name) {
            List<Rewrite_Commity2DTO> bbc = new ArrayList<>();//最后返回值
            int a = pageNum * pageSize;  //分页
            List<Specifications> specificationsByOrdrerBys = new ArrayList<>();
        if (name == null || name.equals("")) { //如果没有搜索关键字，则走下面
            String ccc = "";
            if (type == 0) { //type = 0 全部商品  type = 1 积分精选，2 美食, 3 数码 ，4 居家
                specificationsByOrdrerBys = rewrite_specificationsRepository.findSpecificationsByOrdrerBys(a, pageSize);
            } else {
                if (type == 2) {
                    ccc = "13";
                } else if (type == 3) {
                    ccc = "15";
                } else if (type == 4) {
                    ccc = "14";
                }
                //Long ccc = rewrite_classificationRepository.findClassificationByType(type);//查询分类表的id
                List<Long> commodityByBrandid = rewrite_commodityRepository.findCommodityByClassificationid(ccc);//用分类表的id去查商品
                for (int i = 0; i < commodityByBrandid.size(); i++) {//然后查出来的商品加入到list里面
                    Long asd = commodityByBrandid.get(i);
                    Specifications s = rewrite_specificationsRepository.findSpecificationsByCommodityid(asd + "");
                    specificationsByOrdrerBys.add(s);
                }

            }
            bbc = specificationslist(specificationsByOrdrerBys);//能一步到胃
            return Result.suc("查询成功", bbc, specificationsByOrdrerBys.size());
        }else {
            //搜索 --name搜索分类，
            bbc = like(pageSize,pageNum,name);//有关键字查询就上他
            if (name.equals("积分精选")){
                specificationsByOrdrerBys = rewrite_specificationsRepository.findSpecificationsByOrdrerBys(a, pageSize);
                bbc = specificationslist(specificationsByOrdrerBys);//能一步到胃
            }
            if (bbc.size()<= 0){
                return Result.suc("客官！后面没数据了");
            }
            return Result.suc("查询成功", bbc);
        }
    }

    private List<Rewrite_Commity2DTO> specificationslist(List<Specifications> specificationsByOrdrerBys ){
        List<Rewrite_Commity2DTO> abc = new ArrayList<>();
        for (int i = 0; i < specificationsByOrdrerBys.size(); i++) {
            Rewrite_Commity2DTO rewrite_commity2DTO = new Rewrite_Commity2DTO();

            Specifications s = specificationsByOrdrerBys.get(i);
            String price = s.getPrice();
            Long fileid = s.getFileid();
            String commodityid = s.getCommodityid();
            String specifications = s.getSpecifications();
            Files files = filesRepository.findByIds(fileid);//根据id查询图片的宽高
            Integer height = files.getHeight();
            Integer width = files.getWidth();

            rewrite_commity2DTO.setCommodityId(Long.valueOf(commodityid));//商品id
            rewrite_commity2DTO.setText(specifications);//描述
            rewrite_commity2DTO.setPrice(price);//价格
            rewrite_commity2DTO.setUrl(imagesPath + fileid);//图片路径
            rewrite_commity2DTO.setWidth(width);//宽
            rewrite_commity2DTO.setHeight(height);//高
            abc.add(rewrite_commity2DTO);
        }
        return abc;
    }

    private List<Rewrite_Commity2DTO> like(Integer pageSize, Integer pageNum, String name) {
        List<Rewrite_Commity2DTO> abc = new ArrayList<>();
        Set<Long> shangpin = new HashSet<>();
        List<Specifications> id = new ArrayList<>();
        //先查分类
        String name2 = "%"+name+"%";
        List<Integer> clist = rewrite_classificationRepository.findClassificationBynameLike(name2);//查询分类
        if (clist.size()>0) {
            for (int i = 0; i < clist.size(); i++) {
                Integer c = clist.get(i);
                List<Long> commodityByBrandid = rewrite_commodityRepository.findCommodityByClassificationid(c+"");//用分类id去查询对应的商品
                for (int j = 0; j < commodityByBrandid.size(); j++) {
                    shangpin.add(commodityByBrandid.get(j));//将id放进Set集合中
                }
            }
        }
        //分类中的商品id在这里了
        //再查个规格表中的字段
        List<Integer> specificationsByModels = rewrite_specificationsRepository.findSpecificationsByModels(name2);//查询型号
        if (specificationsByModels.size()>0) {
            for (int i = 0; i < specificationsByModels.size(); i++) {
                Integer c = specificationsByModels.get(i);
                shangpin.add(Long.valueOf(c));
            }
        }
        //查规格表中的specifcations
        List<Integer> sp2 = rewrite_specificationsRepository.findSpecificationsBySpecificationss(name2);//查询内容
        if (sp2.size()>0) {
            for (int i = 0; i < sp2.size(); i++) {
                Integer c = sp2.get(i);
                shangpin.add(Long.valueOf(c));
            }
        }
        List<Long> cctv = new ArrayList<>(shangpin);
        for (int i = 0; i < cctv.size(); i++) {
            Long aLong = cctv.get(i);
            Specifications bb = rewrite_specificationsRepository.findByCommodityid(aLong + "");//将id转为商品实体类
            id.add(bb);
        }
        List<Rewrite_Commity2DTO> list = specificationslist(id);
        for (int i = 1 + (pageSize * (pageNum)); i <= (pageNum+1) * (pageSize); i++) {
            if (i > list.size()) {
                return abc;
            }
            Rewrite_Commity2DTO rewrite_commity2DTO = list.get(i-1);
            abc.add(rewrite_commity2DTO);
        }
        return abc;
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
