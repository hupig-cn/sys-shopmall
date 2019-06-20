package com.weisen.www.code.yjf.shopmall.service.mapper;

import com.weisen.www.code.yjf.shopmall.domain.*;
import com.weisen.www.code.yjf.shopmall.service.dto.ClassificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Classification} and its DTO {@link ClassificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClassificationMapper extends EntityMapper<ClassificationDTO, Classification> {



    default Classification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Classification classification = new Classification();
        classification.setId(id);
        return classification;
    }
}
