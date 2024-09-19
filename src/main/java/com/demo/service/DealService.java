package com.demo.service;

import com.demo.dto.DealRequest;
import com.demo.dto.DealResponse;

public interface DealService {

    DealResponse saveDeal(DealRequest dealRequest);
}
