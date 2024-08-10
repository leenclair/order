package dev.practice.order.domain.partner;

public interface PartnerService {
    // Command, Criteria (요청) ----- Info (응답)
    PartnerInfo registerPartner(PartnerCommand command);
    PartnerInfo getPartnerInfo(String partnerToken);
    PartnerInfo enablePartner(String partnerToken);
    PartnerInfo disablePartner(String partnerToken);


}
