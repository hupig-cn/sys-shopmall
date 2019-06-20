package com.weisen.www.code.yjf.shopmall.service.mapper;

import com.weisen.www.code.yjf.shopmall.domain.*;
import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Specifications} and its DTO {@link SpecificationsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SpecificationsMapper extends EntityMapper<SpecificationsDTO, Specifications> {



    default Specifications fromId(Long id) {
        if (id == null) {
            return null;
        }
        Specifications specifications = new Specifications();
        specifications.setId(id);
        return specifications;
    }
}
