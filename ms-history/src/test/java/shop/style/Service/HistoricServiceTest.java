package shop.style.Service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.style.Service.HistoricServive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HistoricServiceTest {

    @Autowired
    private HistoricServive historicServive;

//    @Test
//    @DisplayName("Deve retornar o historicp pelo ID informado")
//    void shouldListHistoricByID() {
//        HistoricDTO historicDTO = this.historicServive.findHistoricByUser(1L);
//
//        assertNotNull(historicDTO);
//        assertEquals(1L, historicDTO.getUser());
//    }
}
