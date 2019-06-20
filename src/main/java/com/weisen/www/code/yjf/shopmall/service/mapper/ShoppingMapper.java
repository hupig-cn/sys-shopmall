package com.weisen.www.code.yjf.shopmall.service.mapper;

import com.weisen.www.code.yjf.shopmall.domain.*;
import com.weisen.www.code.yjf.shopmall.service.dto.ShoppingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Shopping} and its DTO {@link ShoppingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShoppingMapper extends EntityMapper<ShoppingDTO, Shopping> {



    default Shopping fromId(Long id) {
        if (id == null) {
            return null;
        }
        Shopping shopping = new Shopping();
        shopping.setId(id);
        return shopping;
    }
}
