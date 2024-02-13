package services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import aspects.RecoverException;
import aspects.Time;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ServiceBeanOne {
    @Time
    public void getStringBean(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Time
    public void getNameClassBean(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @RecoverException(noRecoverFor = {RuntimeException.class, NoSuchElementException.class})
    public void getMessageIllegalArgumentException() {
        throw new IllegalArgumentException("Exception 503");
    }

    @RecoverException(noRecoverFor = {NullPointerException.class})
    public void getMessageNullPointerException() {
        throw new NullPointerException("Exception 404");
    }
}