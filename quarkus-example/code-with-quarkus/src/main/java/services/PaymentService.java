package services;


import data.order.Order;
import data.order.OrderRepository;
import data.order.OrderStatus;
import data.payment.Payment;
import data.payment.PaymentRepository;
import data.payment.PaymentStatus;
import dto.PaymentDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@Transactional
public class PaymentService {
    @Inject
    PaymentRepository paymentRepository;
    @Inject
    OrderRepository orderRepository;
    public List<PaymentDto> findByPriceRange(Double max) {
        return this.paymentRepository
                .findAllByAmountBetween(BigDecimal.ZERO, BigDecimal.valueOf(max))
                .stream().map(payment -> mapToDto(payment,
                        findOrderByPaymentId(payment.getId()).getId()))
                .collect(Collectors.toList());
    }
    public List<PaymentDto> findAll() {
        return this.paymentRepository.findAll().stream()
                .map(payment -> findById(payment.getId())).collect(Collectors.toList());
    }
    public PaymentDto findById(Long id) {
        log.debug("Request to get Payment : {}", id);
        Order order = findOrderByPaymentId(id);
        return this.paymentRepository.findById(id)
                .map(payment -> mapToDto(payment, order.getId())).orElse(null);
    }
    public PaymentDto create(PaymentDto paymentDto) {
        log.debug("Request to create Payment : {}", paymentDto);
        Order order = this.orderRepository.findById(paymentDto.getOrderId())
                .orElseThrow(() ->
                        new IllegalStateException("The Order does not exist!"));
        order.setStatus(OrderStatus.PAID);
        Payment payment = this.paymentRepository.saveAndFlush(new Payment(
                paymentDto.getPaypalPaymentId(),
                PaymentStatus.valueOf(paymentDto.getStatus()),
                order.getPrice()
        ));
        this.orderRepository.saveAndFlush(order);
        return mapToDto(payment, order.getId());
    }
    private Order findOrderByPaymentId(Long id) {
        return this.orderRepository.findByPaymentId(id).orElseThrow(() ->
                new IllegalStateException("No Order exists for the Payment ID " + id));
    }
    public void delete(Long id) {
        log.debug("Request to delete Payment : {}", id);
        this.paymentRepository.deleteById(id);
    }
    public static PaymentDto mapToDto(Payment payment, Long orderId) {
        if (payment != null) {
            return new PaymentDto(
                    payment.getId(),
                    payment.getPaypalPaymentId(),
                    payment.getStatus().name(),
                    orderId);
        }
        return null;
    }
}
