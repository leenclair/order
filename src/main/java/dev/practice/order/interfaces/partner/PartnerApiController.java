package dev.practice.order.interfaces.partner;

import dev.practice.order.application.partner.PartnerFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.partner.PartnerCommand;
import dev.practice.order.domain.partner.PartnerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerApiController {
    private final PartnerFacade partnerFacade;
    private final PartnerDtoMapper partnerDtoMapper;

    @PostMapping
    public CommonResponse<PartnerDto.RegisterResponse> registerPartner(@RequestBody PartnerDto.RegisterRequest request){
//        pre mapstruct
        //PartnerCommand command = request.toCommand();
//        post mapstruct
        PartnerCommand command = partnerDtoMapper.of(request);
        PartnerInfo partnerInfo = partnerFacade.registerPartner(command);
        PartnerDto.RegisterResponse response = new PartnerDto.RegisterResponse(partnerInfo);

        return CommonResponse.success(response);
    }


}
