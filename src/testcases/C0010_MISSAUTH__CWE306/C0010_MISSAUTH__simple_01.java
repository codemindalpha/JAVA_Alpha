package testcases.C0010_MISSAUTH__CWE306;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import testcases.C0012_WRONGPERM__CWE732.MemberModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class C0010_MISSAUTH__simple_01 {

    Service service = new Service();

    @RequestMapping(value = "/modify.do", method = RequestMethod.POST)
    public ModelAndView bad(@ModelAttribute("MemberModel") MemberModel memberModel,
                                            BindingResult result, HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String userId = (String) session.getAttribute("userId");
        String passwd = request.getParameter("oldUserPw");

        /* FLAW : CWE-306 */
        if (service.modifyMember(memberModel)) {
            mav.setViewName("redirect:/board/list.do");
            session.setAttribute("userName", memberModel.getUserName());
            return mav;
        } else {
            mav.addObject("errCode", 2);
            mav.setViewName("/board/member_modify");
            return mav;
        }
    }

    @RequestMapping(value = "/modify.do", method = RequestMethod.POST)
    public ModelAndView good(@ModelAttribute("MemberModel") MemberModel memberModel, BindingResult result, HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String userId = (String) session.getAttribute("userId");
        String passwd = request.getParameter("oldUserPw");
        String requestUser = memberModel.getUserId();

        /* FIX */
        if (userId != null && requestUser != null && !userId.equals(requestUser)) {
            mav.addObject("errCode", 1);
            mav.addObject("member", memberModel);
            mav.setViewName("/board/member_modify");
            return mav;
        }

        if (service.modifyMember(memberModel)) {
            mav.setViewName("redirect:/board/list.do");
            session.setAttribute("userName", memberModel.getUserName());
            return mav;
        } else {
            mav.addObject("errCode", 2);
            mav.setViewName("/board/member_modify");
            return mav;
        }
    }
}
