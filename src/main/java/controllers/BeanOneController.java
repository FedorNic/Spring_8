package controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import services.ServiceBeanOne;

@Slf4j
@Controller
@AllArgsConstructor
public class BeanOneController {
    ServiceBeanOne beanOne;

    @EventListener(ApplicationReadyEvent.class)
    public void getData() {
        beanOne.getStringBean(1000);
        beanOne.getNameClassBean(1000);
        try {
            beanOne.getMessageIllegalArgumentException();
        } catch (Throwable e) {
            log.error(e.getClass().getName() + " " + e.getMessage());
        }
        try {
            beanOne.getMessageNullPointerException();
        } catch (Throwable e) {
            log.error(e.getClass().getName() + " " + e.getMessage());
        }

    }
}