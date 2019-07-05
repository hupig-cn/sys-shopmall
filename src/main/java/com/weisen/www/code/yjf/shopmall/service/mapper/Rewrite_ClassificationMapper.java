package com.weisen.www.code.yjf.shopmall.service.mapper;

import com.weisen.www.code.yjf.shopmall.domain.Classification;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_ClassificationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface Rewrite_ClassificationMapper extends EntityMapper<Rewrite_ClassificationDTO, Classification> {



    default Classification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Classification classification = new Classification();
        classification.setId(id);
        return classification;
    }
}
