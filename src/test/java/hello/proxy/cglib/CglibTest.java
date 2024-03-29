package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class CglibTest {
    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); // Enhancer : CGLIB는 Enhancer 를 사용해서 프록시를 생성한다.
        enhancer.setSuperclass(ConcreteService.class); // 상속받을 구체 클래스 지정
        enhancer.setCallback(new TimeMethodInterceptor(target)); // 프록시에 적용할 로직을 지정
        ConcreteService proxy = (ConcreteService) enhancer.create(); // 프록시 생성
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}