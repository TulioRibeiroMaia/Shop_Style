package shop.checkout.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.checkout.DTO.Form.PaymentFormDTO;
import shop.checkout.DTO.PaymentDTO;
import shop.checkout.Entity.Payment;
import shop.checkout.Exception.ResourceNotFoundException;
import shop.checkout.Repository.PaymentRepository;
import shop.checkout.Service.PaymentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaymentDTO savePayment(PaymentFormDTO body) {
        Payment payment = modelMapper.map(body, Payment.class);
        Payment savedPayment = this.paymentRepository.save(payment);
        return modelMapper.map(savedPayment, PaymentDTO.class);
    }

    @Override
    public List<PaymentDTO> findAll() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream().map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO findById(Long id) {
        Optional<Payment> payment = this.paymentRepository.findById(id);

        if (payment.isPresent()) {
            return modelMapper.map(payment.get(), PaymentDTO.class);
        }
        throw new ResourceNotFoundException("ID " + id);
    }

    @Override
    public PaymentDTO updatePayment(Long id, PaymentFormDTO body) {
        Optional<Payment> payment = this.paymentRepository.findById(id);

        if (payment.isPresent()) {
            Payment updatedPayment = modelMapper.map(body, Payment.class);
            updatedPayment.setId(payment.get().getId());
            this.paymentRepository.save(updatedPayment);

            return modelMapper.map(updatedPayment, PaymentDTO.class);
        }
        throw new ResourceNotFoundException("Payment not found " + id);
    }
    @Override
    public void deletePayment(Long id) {
        Optional<Payment> payment = this.paymentRepository.findById(id);

        if (!payment.isPresent()) {

            throw new ResourceNotFoundException("ID " + id);
        }
        this.paymentRepository.deleteById(payment.get().getId());
    }
}