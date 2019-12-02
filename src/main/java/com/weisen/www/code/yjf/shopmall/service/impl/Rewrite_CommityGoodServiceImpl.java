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

import java.util.*;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/29 11:31
 */
@Service
@Transactional
public class Rewrite_CommityGoodServiceImpl implements Rewrite_CommityGoodService {

    @Value("${images-path}")
    private String imagesPath;


    private Long hWidth = 0L;//全部横图的总宽度

    private Long hHeigh = 0L;//全部横图的总高度

    private Long sWidth = 0L;//全部竖图的总宽度

    private Long sHeigh = 0L;//全部竖图的总高度

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

    /**
     * hui
     * @param pageSize
     * @param pageNum
     * @param type
     * @param condition
     * @param name
     * @return
     */
    @Override
    public Result myfilesList(Integer pageSize, Integer pageNum,Integer type,Integer condition,String name) {
            List<Rewrite_Commity2DTO> bbc = new ArrayList<>();//最后返回值
            int a = pageNum * pageSize;  //分页
            List<Specifications> specificationsByOrdrerBys = new ArrayList<>();
        if (name == null || name.equals("")) { //如果没有搜索关键字，则走下面
            String ccc = "";//type = 0 全部商品  type = 1 积分精选，2 美食, 3 数码 ，4 居家
            if (type == 0 && condition == 0) {  //condition-0 全部
                specificationsByOrdrerBys = rewrite_specificationsRepository.findSpecificationsByOrdrerBys(a, pageSize);
            }else if (type == 0 && condition == 1) { //condition-1 新品发布
                specificationsByOrdrerBys = rewrite_specificationsRepository.findSpecificationsByOrdrerByc(a, pageSize);
            }else if (type == 0 && condition == 2) { //condition-2 便宜好货
                specificationsByOrdrerBys = rewrite_specificationsRepository.findSpecificationsByOrdrerByp(a, pageSize);
            }else if (type == 0 && condition == 3) { //condition-3 热卖
                specificationsByOrdrerBys = rewrite_specificationsRepository.findSpecificationsByOrdrerBysc(a, pageSize);
            } else {//上面的是根据页数跟每页大小查询全部商家
                //Long ccc = rewrite_classificationRepository.findClassificationByType(type);//查询分类表的id
                List<Long> commodityByBrandid = rewrite_commodityRepository.findCommodityByClassificationid(ccc);//用分类表的id去查商品
                for (int i = 0; i < commodityByBrandid.size(); i++) {//然后查出来的商品加入到list里面
                    Long asd = commodityByBrandid.get(i);//查出商品id
                    Specifications s = rewrite_specificationsRepository.findSpecificationsByCommodityid(asd + "");//根据id查询对应的规格id
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

    /**
     * 传入list的规格 然后就返回商品列表
     * @param specificationsByOrdrerBys
     * @return
     */
    private List<Rewrite_Commity2DTO> specificationslist(List<Specifications> specificationsByOrdrerBys ){
        List<Rewrite_Commity2DTO> abc = new ArrayList<>();
        for (int i = 0; i < specificationsByOrdrerBys.size(); i++) {
            Rewrite_Commity2DTO rewrite_commity2DTO = new Rewrite_Commity2DTO();//一个商品列表的内容

            Specifications s = specificationsByOrdrerBys.get(i);//获取必要的信息
            String price = s.getPrice();
            Long fileid = s.getFileid();
            String commodityid = s.getCommodityid();
            String specifications = s.getSpecifications();
            Integer sales = s.getSales();
            Files files = filesRepository.findByIds(fileid);//根据id查询图片的宽高
            Integer height = files.getHeight();
            Integer width = files.getWidth();
            Integer num = s.getNum();
            if (num == null){
                num = 9999;
            }

            rewrite_commity2DTO.setCommodityId(Long.valueOf(commodityid));//商品id
            rewrite_commity2DTO.setText(specifications);//描述
            rewrite_commity2DTO.setPrice(price);//价格
            rewrite_commity2DTO.setUrl(imagesPath + fileid);//图片路径
            rewrite_commity2DTO.setWidth(width);//宽
            rewrite_commity2DTO.setHeight(height);//高
            rewrite_commity2DTO.setSales(sales);
            rewrite_commity2DTO.setNum(num);
            abc.add(rewrite_commity2DTO);
        }
        return abc;
    }

    /**
     * 关键字查询的
     * @param pageSize
     * @param pageNum
     * @param name
     * @return
     */
    private List<Rewrite_Commity2DTO> like(Integer pageSize, Integer pageNum, String name) {//用于关键字查询的
        List<Rewrite_Commity2DTO> abc = new ArrayList<>();//返回的值
        Set<Long> shangpin = new HashSet<>();//为了确保id的唯一性
        List<Specifications> id = new ArrayList<>();
        //先查分类
        String name2 = "%"+name+"%";//做个处理
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
        List<Rewrite_Commity2DTO> list = specificationslist(id);//物理分页
        for (int i = 1 + (pageSize * (pageNum)); i <= (pageNum+1) * (pageSize); i++) {
            if (i > list.size()) {
                return abc;
            }
            Rewrite_Commity2DTO rewrite_commity2DTO = list.get(i-1);
            abc.add(rewrite_commity2DTO);
        }
        return abc;
    }

    /**
     * hui
     * @param commityid
     * @return
     */
    @Override
    public Result findCommodityInfo2(String commityid) {
        //商品详情
        Rewrite_GoodsCommity2DTO r = new Rewrite_GoodsCommity2DTO();

        Specifications a = rewrite_specificationsRepository.findSpecificationsByCommodityid(commityid);//根据id去查规格
        String specifications = a.getCommodityid();
        String title = a.getSpecifications();
        Integer sales = a.getSales();
        String price = a.getPrice();
        String model = a.getModel();
        Long fileid = a.getFileid();
        Integer num = a.getNum();
        if (num == null){
            num = 9999;
        }
        List<Long> Hp = new ArrayList<>();
        List<Rewrite_GoodsCommityDTO> hplist = new ArrayList<>();
        List<Rewrite_GoodsCommityDTO> splist = new ArrayList<>();
        List<Long> Sp = new ArrayList<>();
        List<Prodcutimage> slist = reweite_prodcutimageRepository.findAllBySpecificationsid(Long.valueOf(commityid));//根据id查prodcutimage的图片
        for (int i = 0; i < slist.size(); i++) {
            Prodcutimage prodcutimage = slist.get(i);
            String type = prodcutimage.getType();
            if (type.equals("1")){//做分类，分为横图和竖图
                Hp.add(Long.valueOf(prodcutimage.getFileid()));
            }else {
                Sp.add(Long.valueOf(prodcutimage.getFileid()));
            }
        }
        sHeigh = 0L;
        sWidth = 0L;
        hHeigh = 0L;
        hWidth = 0L;
        hplist = aab(Hp,0);
        splist = aab(Sp,1);
        r.setSales(sales);
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
        r.setNum(num);
        return Result.suc("查询成功",r);
    }

    @Override
    public Result findCommodityInfo3() {
        List<Specifications> all = rewrite_specificationsRepository.findAll();
        Random random = new Random();
        for (int i = 0; i < all.size(); i++) {
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            Specifications specifications = all.get(i);
            Long id = specifications.getId();
            specifications.setId(id);
            specifications.setOther(a+"");
            specifications.setSales(b);
            rewrite_specificationsRepository.save(specifications);
        }
        return null;
    }

    /**
     * 商品详情的图片处理
     * @param Sp
     * @param a
     * @return
     */
    public List<Rewrite_GoodsCommityDTO> aab (List<Long> Sp,Integer a){

        List<Rewrite_GoodsCommityDTO> splist = new ArrayList<>();
        if (a == 1){
            Sp.add(3315L);//如果是竖图就再最后加一个保证图
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
