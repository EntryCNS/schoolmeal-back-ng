package kr.hs.dgsw.cns.schoolmealbacksetup.global.aop;

import kr.hs.dgsw.cns.schoolmealbacksetup.domain.user.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizedAspect {

    @Before("@annotation(Authorized)")
    public void beforeBusinessLogic(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Authentication) {
                // Authentication cast
                Authentication authentication = (Authentication) arg;
                if (!(authentication.getPrincipal() instanceof User)) {
                    // authentication 의 principal 이 User 를 참조하고 있지 않다면 예외 발생
                    throw new User.UnauthorizedException();
                }
            }
        }
    }
}
