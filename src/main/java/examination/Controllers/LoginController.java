package examination.Controllers;

import examination.QuestionService.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private RegistrationService registrationService;

	@RequestMapping(value = {"/", "/root.html",  "/welcome.html" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Password Encoder");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView("login");
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}

		return model;

	}

    @RequestMapping(value = "/registration.html", method = RequestMethod.GET)
    public ModelAndView registerPage(@RequestParam(value = "error", required = false) String error                              ) {

        ModelAndView model = new ModelAndView("registration");
        if (error != null) {
            model.addObject("error", "username already exist" + error);
        }

        return model;

    }


    @RequestMapping(value = "/registration.html", method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "password", required = false) String password) {

        try {
            registrationService.register(username, password);
        } catch (Exception ex) {
            createRedirectModelAndView("/registration.html?error=already.exist");
        }

        return createRedirectModelAndView("/login.html");

    }


    @RequestMapping(value = "/user/change_password.html", method = RequestMethod.GET)
    public ModelAndView changePasswordPage(@RequestParam(value = "error", required = false) String error                              ) {

        ModelAndView model = new ModelAndView("changePassword");
        if (error != null) {
            model.addObject("error", "wrong password" + error);
        }

        return model;

    }


    @RequestMapping(value = "/user/change_password.html", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam(value = "oldPassword", required = false) String oldPassword,
                                     @RequestParam(value = "newPassword", required = false) String newPassword,
                                     HttpServletRequest request) {

        String username = getCurrentUserName();

        try {
            registrationService.changePassword(username, oldPassword, newPassword);
            request.logout();
        } catch (Exception ex) {
            createRedirectModelAndView("/user/change_password.html?error=" + ex.toString());
        }

        return createRedirectModelAndView("/login.html");

    }

}