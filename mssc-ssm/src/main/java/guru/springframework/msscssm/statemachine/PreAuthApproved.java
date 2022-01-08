package guru.springframework.msscssm.statemachine;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import guru.springframework.msscssm.services.PaymentServiceImpl;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class PreAuthApproved  implements Action<PaymentState, PaymentEvent> {

    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> context) {
        System.out.println("Pre Auth Approved");
    }
}
