package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.Payment;
import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import guru.springframework.msscssm.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder().amount(new BigDecimal("12.99")).build();
    }

    @Transactional
    @Test
    void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());

        Payment preAuthorizedPayment = paymentRepository.getOne(savedPayment.getId());

        System.out.println(sm.getState());
        System.out.println(sm.getState().getId());
        System.out.println(preAuthorizedPayment);
    }

    @Transactional
    @Test
    void testAuth() {
        Payment savedPayment = paymentService.newPayment(payment);
        savedPayment.setState(PaymentState.PRE_AUTH);

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.authorizePayment(savedPayment.getId());

        Payment authorizedPayment = paymentRepository.getOne(savedPayment.getId());

        System.out.println(sm.getState());
        System.out.println(sm.getState().getId());
        System.out.println(authorizedPayment);
    }
}