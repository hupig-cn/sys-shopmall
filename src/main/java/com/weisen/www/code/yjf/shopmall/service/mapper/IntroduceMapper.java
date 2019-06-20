package com.weisen.www.code.yjf.shopmall.service.mapper;

import com.weisen.www.code.yjf.shopmall.domain.*;
import com.weisen.www.code.yjf.shopmall.service.dto.IntroduceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Introduce} and its DTO {@link IntroduceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IntroduceMapper extends EntityMapper<IntroduceDTO, Introduce> {



    default Introduce fromId(Long id) {
        if (id == null) {
            return null;
        }
        Introduce introduce = new Introduce();
        introduce.setId(id);
        return introduce;
    }
}
