package com.demo.service.impl;

import com.demo.dto.DealDTO;
import com.demo.dto.DealRequest;
import com.demo.dto.DealResponse;
import com.demo.entity.Deal;
import com.demo.repository.DealRepository;
import com.demo.service.DealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
public class DealServiceImpl  implements DealService {

    private final DealRepository dealRepository;

    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    @Transactional(noRollbackFor = Exception.class)
    public DealResponse saveDeal(DealRequest dealRequest) {

        try {
            // Convert DealRequest to Deal entity
            Deal deal = convertToEntity(dealRequest);

            // Check if deal with the same uniqueId already exists
            if (dealRepository.existsByUniqueId(deal.getUniqueId())) {
                DealResponse response = new DealResponse(400, "Bad request", "Deal with the same unique ID already exists: " + dealRequest.getUniqueId(), null);
                return response;

            }

            // Save the deal entity to the database
            Deal savedDeal = dealRepository.save(deal);

            // Convert the saved deal entity to DealResponse
            DealResponse response = new DealResponse(0, null, "Successful", convertToDto(savedDeal));
            log.info("Deal response: {} " + response);
            return response;

        } catch (Exception e) {
            log.error("Deal Error: {} ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while saving the deal.Kindly contact the administrator ");
        }
    }

    private Deal convertToEntity(DealRequest dealRequest) {
        Deal deal = new Deal();
        deal.setUniqueId(dealRequest.getUniqueId());
        deal.setFromCurrencyIsoCode(dealRequest.getFromCurrencyIsoCode());
        deal.setToCurrencyIsoCode(dealRequest.getToCurrencyIsoCode());
        deal.setDealTimestamp(dealRequest.getDealTimestamp());
        deal.setDealAmount(dealRequest.getDealAmount());
        return deal;
    }

    private DealDTO convertToDto(Deal deal) {
        DealDTO dto = new DealDTO();
        dto.setId(deal.getId());
        dto.setUniqueId(deal.getUniqueId());
        dto.setFromCurrencyIsoCode(deal.getFromCurrencyIsoCode());
        dto.setToCurrencyIsoCode(deal.getToCurrencyIsoCode());
        dto.setDealTimestamp(deal.getDealTimestamp());
        dto.setDealAmount(deal.getDealAmount());
        return dto;
    }


}
