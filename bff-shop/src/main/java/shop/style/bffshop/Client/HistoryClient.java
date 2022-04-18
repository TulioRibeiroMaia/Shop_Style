package shop.style.bffshop.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.style.bffshop.DTO.HistoricDTO;

@FeignClient("history")
public interface HistoryClient {

    @GetMapping("/v1/historic/user/{userId}")
    HistoricDTO findHistoricByUser(@PathVariable Long userId);
}
