package testcases.C000C_TYPEOVER__CWE190_Integer_Overflow.s01;

import org.carewebframework.api.property.PropertyUtil;

import javax.servlet.http.HttpServletRequest;

public class C000C_TYPEOVER__simple_01 {

    public void bad(HttpServletRequest request, String msg_id){
        String msg_str = "";
        String tmp = request.getParameter("slf_msg_param_num");
        if (tmp.equals("0")) {
            msg_str = PropertyUtil.getValue(msg_id);
        } else {
            // 외부입력값을 정수형으로 사용할 때 입력값의 크기를 검증하지 않고 사용
            /* FLAW : CWE-190 */
            int param_ct = Integer.parseInt(tmp);
            String[] strArr = new String[param_ct];
        }
    }

    public void good(HttpServletRequest request, String msg_id){
        String msg_str = "";
        String tmp = request.getParameter("slf_msg_param_num");
        if (tmp.equals("0")) {
            msg_str = PropertyUtil.getValue(msg_id);
        } else {
            // 외부입력값을 정수형으로 사용할 때 입력값의 크기를 검증하고 사용
            try {
                int param_ct = Integer.parseInt(tmp);
                if (param_ct < 0) {
                    throw new Exception();
                }
                String[] strArr = new String[param_ct];
            } catch(Exception e) {
                msg_str = "잘못된 입력(접근) 입니다.";
            }
        }

    }
}
