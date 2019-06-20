package com.weisen.www.code.yjf.shopmall.service.mapper;

import com.weisen.www.code.yjf.shopmall.domain.*;
import com.weisen.www.code.yjf.shopmall.service.dto.OrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {



    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }
}
