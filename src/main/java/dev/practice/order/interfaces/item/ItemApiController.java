package dev.practice.order.interfaces.item;

import dev.practice.order.application.item.ItemFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemApiController {
    private final ItemFacade itemFacade;
    private final ItemDtoMapper itemDtoMapper;

    @PostMapping
    public CommonResponse<ItemDto.RegisterResponse> registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request){
        String partnerToken = request.getPartnerToken();
        ItemCommand.RegisterItemRequest itemCommand = itemDtoMapper.of(request);
        String itemToken = itemFacade.registerItem(itemCommand, partnerToken);
        ItemDto.RegisterResponse response = itemDtoMapper.of(itemToken);
        return CommonResponse.success(response);
    }

    @PostMapping("/change-on-sales")
    public CommonResponse<String> changeOnSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        String itemToken = request.getItemToken();
        itemFacade.changeOnSaleItem(itemToken);
        return CommonResponse.success("OK");
    }

    @PostMapping("/change-end-of-sales")
    public CommonResponse<String> changeEndOfSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        String itemToken = request.getItemToken();
        itemFacade.changeEndOfSaleItem(itemToken);
        return CommonResponse.success("OK");
    }

    @GetMapping("/{itemToken}")
    public CommonResponse<ItemDto.Main> retrieve(@PathVariable("itemToken") String itemToken) {
        ItemInfo.Main itemInfo = itemFacade.retrieveItemInfo(itemToken);
        ItemDto.Main response = itemDtoMapper.of(itemInfo);
        return CommonResponse.success(response);
    }


}
