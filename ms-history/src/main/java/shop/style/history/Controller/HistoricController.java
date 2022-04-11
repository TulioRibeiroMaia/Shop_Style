package shop.style.history.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.style.history.DTO.HistoricDTO;
import shop.style.history.Service.Impl.HistoricServiceImpl;

@RestController
@RequestMapping("/v1/historic/user/")
public class HistoricController {

    @Autowired
    private HistoricServiceImpl historicServiceImpl;

    @GetMapping(value = "/{userId}")
    public HistoricDTO findHistoricByUser(@PathVariable Long userId) {
        return historicServiceImpl.findHistoricByUser(userId);
    }
}
