package shop.style.checkout.Service;

import shop.style.checkout.DTO.Form.PaymentFormDTO;
import shop.style.checkout.DTO.PaymentDTO;

import java.util.List;

public interface PaymentService {

    PaymentDTO savePayment(PaymentFormDTO body);

    List<PaymentDTO> findAll();

    PaymentDTO findById(Long id);

    PaymentDTO updatePayment(Long id, PaymentFormDTO body);

    void deletePayment(Long id);

}
