package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor { // CGLIB의 MethodInterceptor 와 이름이 같으므로 패키지 이름에 주의
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("timeProxy 실행");
        long startTime = System.currentTimeMillis();

//        Object result = method.invoke(target, args);
        Object result = invocation.proceed(); // target은 invocation에 다 포함되어있다.
                                              // ProxyFactory를 생성할 때, target정보를 이미 넘겨버림.

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
