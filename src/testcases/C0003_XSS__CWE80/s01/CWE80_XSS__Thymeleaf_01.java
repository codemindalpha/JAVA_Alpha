package testcases.C0003_XSS__CWE80.s01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CWE80_XSS__Thymeleaf_01 {
    @RequestMapping("/")
    public String welcome(Map<String, Object> model, @RequestParam("id") String id) {
        model.put("id", id);
        return "CWE80_XSS__Thymeleaf_01";
    }
}
