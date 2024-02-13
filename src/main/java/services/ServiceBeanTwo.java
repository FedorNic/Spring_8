package services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import aspects.Time;

@Service
@AllArgsConstructor
@Time
public class ServiceBeanTwo {

    public void getStringBean(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getNameClassBean(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMessageException() throws Exception {
        int res = 5 + 10;
        Thread.sleep(2000);

        getError();

        System.out.println(res);
    }

    private void getError() throws Exception {
        throw new Exception("error");
    }
}