package com.weisen.www.code.yjf.shopmall.service.mapper;

import com.weisen.www.code.yjf.shopmall.domain.*;
import com.weisen.www.code.yjf.shopmall.service.dto.ProdcutimageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Prodcutimage and its DTO ProdcutimageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdcutimageMapper extends EntityMapper<ProdcutimageDTO, Prodcutimage> {



    default Prodcutimage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prodcutimage prodcutimage = new Prodcutimage();
        prodcutimage.setId(id);
        return prodcutimage;
    }
}
