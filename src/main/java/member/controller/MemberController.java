package member.controller;

import lombok.extern.slf4j.Slf4j;
import member.model.Member;
import member.model.MemberRole;
import member.service.MemberService;
import member.support.MemberValidator;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberValidator memberValidator;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new Member());

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") @Validated Member userForm, BindingResult bindingResult) {
        memberValidator.validate(userForm, bindingResult);
        if(bindingResult.hasErrors()) {
            log.debug("valid error");
            return "signup";
        }
        userForm.setRole(MemberRole.USER);
        memberService.save(userForm);
        log.debug("userInfo" + userForm.toString());
        log.debug("email" + userForm.getEmail() + "|" + userForm.getPassword());

        return "redicrect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model, String error, String logout) {
        if(error != null)
            model.addAttribute("error", "아이디 또는 패스워드가 잘못 되었습니다.");

        if(logout != null)
            model.addAttribute("message", "로그아웃 되었습니다.");

        return new ModelAndView("login");
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping("/welcom")
    public String welcome() {
        return "welcome";
    }
}
