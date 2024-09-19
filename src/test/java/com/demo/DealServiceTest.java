package com.demo;

//import com.demo.dto.DealRequest;
//import com.demo.dto.DealResponse;
//import com.demo.entity.Deal;
//import com.demo.repository.DealRepository;
//import com.demo.service.DealService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.server.ResponseStatusException;
//import java.time.LocalDateTime;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
public class DealServiceTest {
//
//    @Mock
//    private DealRepository dealRepository;
//
//    @InjectMocks
//    private DealService dealService;
//
//    @Before
//    public void setUp() {
//
//    }
//
//    @Test
//    public void testSaveDeal_Success() {
//        // Mock input
//        DealRequest dealRequest = new DealRequest();
//        dealRequest.setUniqueId("ABC123");
//        dealRequest.setFromCurrencyIsoCode("USD");
//        dealRequest.setToCurrencyIsoCode("EUR");
//        dealRequest.setDealTimestamp(LocalDateTime.of(2024, 5, 10, 12, 30));
//        dealRequest.setDealAmount(1000.00);
//
//        // Mock repository behavior
//        when(dealRepository.existsByUniqueId("ABC123")).thenReturn(false);
//        when(dealRepository.save(any(Deal.class))).thenReturn(new Deal(1L, "ABC123", "USD", "EUR",
//                LocalDateTime.of(2024, 5, 10, 12, 30), 1000.00));
//
//        // Call service method
//        DealResponse responseDTO = dealService.saveDeal(dealRequest);
//
//        // Verify response
//        assertEquals(0, responseDTO.getStatus());
//        assertEquals("Successful", responseDTO.getMessage());
//        assertEquals("ABC123", responseDTO.getData().getUniqueId());
//    }
//
//    @Test(expected = ResponseStatusException.class)
//    public void testSaveDeal_Conflict() {
//        // Mock input
//        DealRequest dealRequest = new DealRequest();
//        dealRequest.setUniqueId("ABC123");
//        dealRequest.setFromCurrencyIsoCode("USD");
//        dealRequest.setToCurrencyIsoCode("EUR");
//        dealRequest.setDealTimestamp(LocalDateTime.of(2024, 5, 10, 12, 30));
//        dealRequest.setDealAmount(1000.00);
//
//        // Mock repository behavior
//        when(dealRepository.existsByUniqueId("ABC123")).thenReturn(true);
//
//        // Call service method
//        dealService.saveDeal(dealRequest);
//    }
}
