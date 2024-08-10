package dev.practice.order.infrastructure.item;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemOptionSeriesFactory;
import dev.practice.order.domain.item.option.ItemOption;
import dev.practice.order.domain.item.option.ItemOptionStore;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory {
    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;


    @Override
    public List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item) {
        List<ItemCommand.RegisterItemOptionGroupRequest> itemOptionsGroupRequestList = command.getItemOptionsGroupRequestList();
        if(CollectionUtils.isEmpty(itemOptionsGroupRequestList)) return Collections.emptyList();

        return itemOptionsGroupRequestList.stream()
                .map(requestItemOptionGroup -> {
                    //itemOptionGroup
                    ItemOptionGroup initItemOptionGroup = requestItemOptionGroup.toEntity(item);
                    ItemOptionGroup itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

                    //itemOption
                    requestItemOptionGroup.getItemOptionRequestList().forEach(requestItemOption -> {
                        ItemOption initItemOption = requestItemOption.toEntity(itemOptionGroup);
                        itemOptionStore.store(initItemOption);
                    });

                    return itemOptionGroup;
                }).collect(Collectors.toList());
    }
}
