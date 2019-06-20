package com.weisen.www.code.yjf.shopmall.service.mapper;

import com.weisen.www.code.yjf.shopmall.domain.*;
import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Commodity} and its DTO {@link CommodityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommodityMapper extends EntityMapper<CommodityDTO, Commodity> {



    default Commodity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commodity commodity = new Commodity();
        commodity.setId(id);
        return commodity;
    }
}
