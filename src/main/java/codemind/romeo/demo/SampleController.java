package codemind.romeo.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SampleController {
    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @Value("${welcome.message2:test}")
    private String message2 = "Hello World";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model, @RequestParam("id") String id) {
        model.put("message", this.message);
        model.put("message2", this.message2);
        model.put("id", id);
//        return "welcome";
        return "CWE80_XSS__Thymeleaf_01";
    }
}