package aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectRunnable {
    @Pointcut("within(@aspects.Time *)")
    public void beansAnnotatedWith() {

    }

    @Pointcut("@annotation(aspects.Time)")
    public void methodsAnnotatedWith() {

    }

    @Pointcut("@annotation(aspects.RecoverException)")
    public void methodsExceptionWith() {

    }

    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object getStringAspect(ProceedingJoinPoint joinPoint) {
        Long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Ошибка выполнения замера времени: {} {}", e.getClass().getName(), e.getMessage());
        }
        Long end = System.currentTimeMillis();
        log.warn(
                "{} - {} # {} секунд",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                (end - start) / 1000
        );
        return result;
    }

    @Around("methodsExceptionWith()")
    public Object getStringAspectException(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RecoverException annotation = signature.getMethod().getAnnotation(RecoverException.class);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            for (int i = 0; i < annotation.noRecoverFor().length; i++) {
                if (annotation.noRecoverFor()[i].isAssignableFrom(e.getClass())) {
                    throw e;
                }
            }
            log.warn("Ошибка аспекта " + e.getClass().getName() + " " + e.getMessage());
        }
        return result;
    }
}