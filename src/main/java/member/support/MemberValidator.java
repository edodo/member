package member.support;

import member.model.Member;
import member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MemberValidator implements Validator {
    @Autowired
    private MemberService memberService;


    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        // 계정이 너무 짧거나 길음
        if( member.getUsername().length() < 3 || member.getUsername().length() > 32 ) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        // 계정 존재
        if( memberService.findByUserName(member.getUsername()) != null ) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        // 비번이 너무 짧거나 길음
        if( member.getPassword().length() < 7 || member.getPassword().length() > 32 ) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        // 비번과 비번확인 값이 다름
        if ( !member.getPasswordConfirm().equals(member.getPassword()) ) {
            errors.rejectValue("passwordConfirm", "Diff.userFOrm.passwordConfirm");
        }
    }
}
