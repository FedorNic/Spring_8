package controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import services.ServiceBeanTwo;

@Slf4j
@Controller
@AllArgsConstructor
public class BeanTwoController {
    ServiceBeanTwo beanTwo;

    @EventListener(ApplicationReadyEvent.class)
    public void getData() {
        beanTwo.getStringBean(1000);
        beanTwo.getNameClassBean(1000);
        try {
            beanTwo.getMessageException();
        } catch (Exception e) {
            log.error(e.getClass().getName() + " " + e.getMessage());
        }

    }
}