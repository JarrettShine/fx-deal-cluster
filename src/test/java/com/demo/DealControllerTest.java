package com.demo;

//import com.demo.controller.DealController;
//import com.demo.dto.DealDTO;
//import com.demo.dto.DealRequest;
//import com.demo.dto.DealResponse;
//import com.demo.service.DealService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.LocalDateTime;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
public class DealControllerTest {

//    @Mock
//    private DealService dealService;
//
//    @InjectMocks
//    private DealController dealController;
//
//    @Before
//    public void setup() {
//    }
//
//    @Test
//    public void testCreateDeal_Success() throws JsonProcessingException {
//        // Mock input
//        DealRequest dealRequest = new DealRequest();
//        dealRequest.setUniqueId("ABC123");
//        dealRequest.setFromCurrencyIsoCode("USD");
//        dealRequest.setToCurrencyIsoCode("EUR");
//        dealRequest.setDealTimestamp(LocalDateTime.of(2024, 5, 10, 12, 30));
//        dealRequest.setDealAmount(1000.00);
//
//        // Mock service behavior
//        when(dealService.saveDeal(any(DealRequest.class))).thenReturn(
//                new DealResponse(0, null, "Successful",
//                        new DealDTO(1L, "ABC123", "USD", "EUR",
//                                LocalDateTime.of(2024, 5, 10, 12, 30), 1000.00))
//        );
//
//        // Call the controller method
//        ResponseEntity<DealResponse> responseEntity = dealController.createDeal(dealRequest);
//
//        // Verify the response
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        DealResponse responseDTO = responseEntity.getBody();
//        assertEquals(0, responseDTO.getStatus());
//        assertEquals("Successful", responseDTO.getMessage());
//        assertEquals("ABC123", responseDTO.getData().getUniqueId());
//        assertEquals("USD", responseDTO.getData().getFromCurrencyIsoCode());
//        assertEquals("EUR", responseDTO.getData().getToCurrencyIsoCode());
//        assertEquals(LocalDateTime.of(2024, 5, 10, 12, 30), responseDTO.getData().getDealTimestamp());
//        assertEquals(1000.00, responseDTO.getData().getDealAmount(), 0);
//    }
//
//    @Test
//    public void testCreateDeal_Conflict() throws JsonProcessingException {
//        // Mock input
//        DealRequest dealRequest = new DealRequest();
//        dealRequest.setUniqueId("ABC123");
//        dealRequest.setFromCurrencyIsoCode("USD");
//        dealRequest.setToCurrencyIsoCode("EUR");
//        dealRequest.setDealTimestamp(LocalDateTime.of(2024, 5, 10, 12, 30));
//        dealRequest.setDealAmount(1000.00);
//
//        // Mock service behavior
//        when(dealService.saveDeal(any(DealRequest.class))).thenThrow(
//                new ResponseStatusException(HttpStatus.CONFLICT, "Deal with the same unique ID already exists: ABC123")
//        );
//
//        // Call the controller method
//        ResponseEntity<DealResponse> responseEntity = dealController.createDeal(dealRequest);
//
//        // Verify the response
//        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
//        DealResponse responseDTO = responseEntity.getBody();
//        assertEquals(409, responseDTO.getStatus());
//        assertEquals("Conflict", responseDTO.getError());
//        assertEquals("Deal with the same unique ID already exists: ABC123", responseDTO.getMessage());
//    }
}

