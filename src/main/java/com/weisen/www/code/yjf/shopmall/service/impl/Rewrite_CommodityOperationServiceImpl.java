package com.weisen.www.code.yjf.shopmall.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.weisen.www.code.yjf.shopmall.domain.Classification;
import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import com.weisen.www.code.yjf.shopmall.domain.Merchant;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_ClassificationRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_CommodityRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_CommodityOperationService;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_CommodityClassificationDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_CommodityClassificationDTO2;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_CommodityOperationDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_GetAllCommodityOperationDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_ModifyCommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;

/**
 * 商品的增删改查
 * 
 * @author LuoJinShui
 *
 */
@Service
@Transactional
public class Rewrite_CommodityOperationServiceImpl implements Rewrite_CommodityOperationService {

	private final Rewrite_MerchantRepository rewrite_MerchantRepository;

	private final Rewrite_SpecificationsRepository rewrite_SpecificationsRepository;

	private final Rewrite_ClassificationRepository rewrite_ClassificationRepository;

	private final Rewrite_CommodityRepository rewrite_CommodityRepository;

	public Rewrite_CommodityOperationServiceImpl(Rewrite_MerchantRepository rewrite_MerchantRepository,
			Rewrite_SpecificationsRepository rewrite_SpecificationsRepository,
			Rewrite_ClassificationRepository rewrite_ClassificationRepository,
			Rewrite_CommodityRepository rewrite_CommodityRepository) {
		this.rewrite_MerchantRepository = rewrite_MerchantRepository;
		this.rewrite_SpecificationsRepository = rewrite_SpecificationsRepository;
		this.rewrite_ClassificationRepository = rewrite_ClassificationRepository;
		this.rewrite_CommodityRepository = rewrite_CommodityRepository;
	}

	// 查询商品分类
	@Override
	public Result getCommodityClassification() {
		List<Classification> classificationsList = rewrite_ClassificationRepository.findByPid(0L);
		List<Rewrite_CommodityClassificationDTO> rewrite_CommodityClassificationDTOs = new ArrayList<Rewrite_CommodityClassificationDTO>();
		for (Classification classification : classificationsList) {
			Rewrite_CommodityClassificationDTO commodityClassificationDTO = new Rewrite_CommodityClassificationDTO();
			commodityClassificationDTO.setId(classification.getId());
			commodityClassificationDTO.setCommodityName(classification.getName());
			commodityClassificationDTO.setType(classification.getType());
			rewrite_CommodityClassificationDTOs.add(commodityClassificationDTO);
		}
		return Result.suc("查询成功!", rewrite_CommodityClassificationDTOs, rewrite_CommodityClassificationDTOs.size());
	}

	// 查询商品二级分类
	@Override
	public Result getCommoditySecondaryClassification(Long pid) {
		List<Classification> classificationsList = rewrite_ClassificationRepository.findByPid(pid);
		if (classificationsList.isEmpty()) {
			return Result.fail("没有该分类!");
		} else {
			Integer type = rewrite_ClassificationRepository.findById2(pid);
			List<Rewrite_CommodityClassificationDTO2> rewrite_CommodityClassificationDTOs = new ArrayList<Rewrite_CommodityClassificationDTO2>();
			for (Classification classification : classificationsList) {
				Rewrite_CommodityClassificationDTO2 commodityClassificationDTO = new Rewrite_CommodityClassificationDTO2();
				commodityClassificationDTO.setId(classification.getId());
				commodityClassificationDTO.setCommodityName(classification.getName());
				commodityClassificationDTO
						.setIcon("http://app.yuanscore.com:8083/services/basic/api/public/getFiles/17");
				commodityClassificationDTO.setHeight(432);
				commodityClassificationDTO.setWidth(432);
				commodityClassificationDTO.setPid(pid);
				
				commodityClassificationDTO.setType(type);
				commodityClassificationDTO.setOther(classification.getName());
				rewrite_CommodityClassificationDTOs.add(commodityClassificationDTO);
			}
			return Result.suc("查询成功!", rewrite_CommodityClassificationDTOs, rewrite_CommodityClassificationDTOs.size());
		}
	}

	// 商家新增商品
	@Override
	public Result newCommodity(Rewrite_CommodityOperationDTO rewrite_CommodityOperationDTO) {
		String userId = rewrite_CommodityOperationDTO.getUserId();
		// 判断用户是否是商家
		Merchant merchant = rewrite_MerchantRepository.findByUseridAndState(userId);
		if (merchant == null) {
			return Result.fail("您不是商家!无法进行此操作!");
		} else {
			Classification classificationsList = rewrite_ClassificationRepository
					.findClassificationByIdAndPid(rewrite_CommodityOperationDTO.getBrandid(), 0L);
			if (classificationsList == null) {
				return Result.fail("没有该分类!请重新选择!");
			} else {
				Commodity commodity = new Commodity();
				Specifications specifications = new Specifications();
				commodity.setName(classificationsList.getName());
				commodity.setClassificationid(classificationsList.getId().toString());
				commodity.setCommoditystate("1");
				commodity.setCreator(userId);
				commodity.setCreatedate(TimeUtil.getDate());
				commodity.setPostage("0");
				specifications.setModel(classificationsList.getName());
				specifications.setFileid(rewrite_CommodityOperationDTO.getFileid());
				specifications.setSpecifications(rewrite_CommodityOperationDTO.getSpecifications());
				specifications.setIntegral(rewrite_CommodityOperationDTO.getIntegral());
				specifications.setNum(rewrite_CommodityOperationDTO.getNum());
				specifications.setPrice(rewrite_CommodityOperationDTO.getPrice());
				specifications.setDiscount(rewrite_CommodityOperationDTO.getDiscount());
				specifications.setCreator(userId);
				specifications.setCreatedate(TimeUtil.getDate());
				specifications.setLogicdelete(true);
				specifications.setOther(rewrite_CommodityOperationDTO.getOther());
				rewrite_CommodityRepository.save(commodity);
				rewrite_SpecificationsRepository.save(specifications);
				specifications.setCommodityid("" + specifications.getId());
				rewrite_SpecificationsRepository.saveAndFlush(specifications);
				return Result.suc("新增商品成功!");
			}
		}
	}

	// 商家修改商品
	@Override
	public Result modifyCommodity(Rewrite_ModifyCommodityDTO rewrite_ModifyCommodityDTO) {
		Merchant merchant = rewrite_MerchantRepository.findByUseridAndState(rewrite_ModifyCommodityDTO.getUserId());
		if (merchant == null) {
			return Result.fail("您不是商家!无法进行此操作!");
		} else {
			Specifications specifications = rewrite_SpecificationsRepository
					.findByCommodityid(rewrite_ModifyCommodityDTO.getCommodityId());
			if (specifications == null) {
				return Result.fail("您没有该商品!请重新输入查找!");
			} else {
				specifications.setFileid(rewrite_ModifyCommodityDTO.getFileid());
				specifications.setSpecifications(rewrite_ModifyCommodityDTO.getSpecifications());
				specifications.setIntegral(rewrite_ModifyCommodityDTO.getIntegral());
				specifications.setNum(rewrite_ModifyCommodityDTO.getNum());
				specifications.setPrice(rewrite_ModifyCommodityDTO.getPrice());
				specifications.setDiscount(rewrite_ModifyCommodityDTO.getDiscount());
				specifications.setModifier(rewrite_ModifyCommodityDTO.getUserId());
				specifications.setModifierdate(TimeUtil.getDate());
				specifications.setLogicdelete(true);
				specifications.setOther(rewrite_ModifyCommodityDTO.getOther());
				rewrite_SpecificationsRepository.saveAndFlush(specifications);
				return Result.suc("修改商品成功!");
			}
		}
	}

	// 商家删除下架商品
	@Override
	public Result deleteCommodity(String userId, String commodityid) {
		Merchant merchant = rewrite_MerchantRepository.findByUseridAndState(userId);
		if (merchant == null) {
			return Result.fail("您不是商家!无法进行此操作!");
		} else {
			Specifications specifications = rewrite_SpecificationsRepository.findByCommodityid(commodityid);
			if (specifications == null) {
				return Result.fail("您没有该商品!请重新输入查找!");
			} else {
				specifications.setLogicdelete(false);
				rewrite_SpecificationsRepository.saveAndFlush(specifications);
				return Result.suc("下架商品成功!");
			}
		}
	}

	// 商家查询用户所有商品
	@Override
	public Result selectAllCommodity(String userId, Integer pageNum, Integer pageSize) {
		Merchant merchant = rewrite_MerchantRepository.findByUseridAndState(userId);
		if (merchant == null) {
			return Result.fail("您不是商家!无法进行此操作!");
		} else {
			List<Rewrite_GetAllCommodityOperationDTO> rewrite_CommodityOperationDTOs = new ArrayList<Rewrite_GetAllCommodityOperationDTO>();
			List<Specifications> specifications = rewrite_SpecificationsRepository.findBySpecifications(userId,
					pageNum * pageSize, pageSize);
			for (Specifications specificationsList : specifications) {
				Rewrite_GetAllCommodityOperationDTO commodityOperationDTO = new Rewrite_GetAllCommodityOperationDTO();
				commodityOperationDTO.setCommodityId(specificationsList.getCommodityid());
				commodityOperationDTO.setModel(specificationsList.getModel());
				commodityOperationDTO.setFileid(specificationsList.getFileid());
				commodityOperationDTO.setSpecifications(specificationsList.getSpecifications());
				commodityOperationDTO.setIntegral(specificationsList.getIntegral());
				commodityOperationDTO.setNum(specificationsList.getNum());
				commodityOperationDTO.setPrice(specificationsList.getPrice());
				commodityOperationDTO.setDiscount(specificationsList.getDiscount());
				commodityOperationDTO.setOther(specificationsList.getOther());
				rewrite_CommodityOperationDTOs.add(commodityOperationDTO);
			}
			return Result.suc("查询成功!", rewrite_CommodityOperationDTOs, rewrite_CommodityOperationDTOs.size());
		}
	}
}
