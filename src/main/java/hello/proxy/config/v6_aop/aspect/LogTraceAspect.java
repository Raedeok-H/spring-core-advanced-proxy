package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class LogTraceAspect {
    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    /**
     * pointcut, advice를 지정해서 advisor를 만든 것이다.
     */
    @Around("execution(* hello.proxy.app..*(..))") // 포인트컷 표현식을 넣는다. 표현식은 AspectJ 표현식을 사용한다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        //ProceedingJoinPoint joinPoint : 어드바이스에서 살펴본 MethodInvocation invocation 과 유사한 기능
        TraceStatus status = null;
        try {
            // log.info("target={}", joinPoint.getTarget()); //실제 호출 대상
            // log.info("getArgs={}", joinPoint.getArgs()); //전달인자
            // log.info("getSignature={}", joinPoint.getSignature()); //join point 시그니처
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            // 로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

}
