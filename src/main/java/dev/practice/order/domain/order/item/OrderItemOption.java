package dev.practice.order.domain.order.item;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "order_item_options")
public class OrderItemOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_item_option_group_id")
    private OrderItemOptionGroup orderItemOptionGroup;
    private Integer ordering;
    private String itemOptionName;
    private Long itemOptionPrice;

    @Builder
    public OrderItemOption(
            OrderItemOptionGroup orderItemOptionGroup,
            Integer ordering,
            String itemOptionName,
            Long itemOptionPrice
    ) {
        if (orderItemOptionGroup == null) throw new InvalidParamException("OrderItemOption.orderItemOptionGroup");
        if (ordering == null) throw new InvalidParamException("OrderItemOption.ordering");
        if (StringUtils.isEmpty(itemOptionName)) throw new InvalidParamException("OrderItemOption.itemOptionName");
        if (itemOptionPrice == null) throw new InvalidParamException("OrderItemOption.itemOptionPrice");

        this.orderItemOptionGroup = orderItemOptionGroup;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }
}
