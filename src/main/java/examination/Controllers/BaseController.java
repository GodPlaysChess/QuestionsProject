package examination.Controllers;

import examination.QuestionService.UserDetailsServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * author: a.savanovich
 * Date: 28.09.14
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseController {
    protected ModelAndView createRedirectModelAndView(String url) {
        return new ModelAndView(new RedirectView(url));
    }

    protected long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsServiceImpl.CustomUser customUser = (UserDetailsServiceImpl.CustomUser)authentication.getPrincipal();
        return customUser.getUserId();
    }

    protected String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsServiceImpl.CustomUser customUser = (UserDetailsServiceImpl.CustomUser)authentication.getPrincipal();
        return customUser.getUsername();
    }
}
