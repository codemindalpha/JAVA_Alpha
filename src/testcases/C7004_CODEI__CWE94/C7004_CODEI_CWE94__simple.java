package testcases.C7004_CODEI__CWE94;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Controller
public class C7004_CODEI_CWE94__simple {
    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    public String bad(@RequestParam("src") String src) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();;
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        // 외부 입력값인 src를 javascript eval 함수로 실행하고 있다.
        /* FLAW: CWE-94 */
        String retValue = (String)scriptEngine.eval(src);
        return retValue;
    }

    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    public String good(@RequestParam("src") String src) throws ScriptException {
        // 정규식을 이용하여 특수문자 입력 시 예외를 발생시킨다.
        if (src.matches("[\\w]*") == false) {
            throw new IllegalArgumentException();
        }

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();;
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        String retValue = (String)scriptEngine.eval(src);
        return retValue;
    }
}
