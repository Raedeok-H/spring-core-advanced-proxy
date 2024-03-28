package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping//스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 "컨트롤러"로 인식
@ResponseBody
public interface OrderControllerV1 {
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId); //인터페이스에서는 @RequestParam("itemId")를 명시하지 않으면 컴파일이 안될 때도 있어서 복잡하니까 넣기

    @GetMapping("/v1/no-log")
    String noLog();
}
