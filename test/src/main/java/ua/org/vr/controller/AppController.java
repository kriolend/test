package ua.org.vr.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.org.vr.model.User;
import ua.org.vr.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class AppController {
	private static final Logger logger = Logger.getLogger(AppController.class);

	@Autowired
	UserService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}

		//logs exception
		logger.error("This is Error message", new Exception("Testing"));

		List<User> users = service.findAllUsers();
		model.addAttribute("users", users);
		return "allusers";
	}

	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isUserSsnUnique(user.getId(), user.getSsn())){
			FieldError ssnError =new FieldError("user","ssn",messageSource.getMessage("non.unique.ssn", new String[]{user.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}
		
		service.saveUser(user);

		model.addAttribute("success", "User " + user.getName() + " registered successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-{ssn}-user" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssn, ModelMap model) {
		User user = service.findUserBySsn(ssn);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}

	/*
	 * This method will provide the search user by ssn.
	 */
	@RequestMapping(value = {"/search/"}, method = RequestMethod.GET)
	public String findUser(@RequestParam("ssn") String ssn, ModelMap model) {
		model.addAttribute("user", this.service.findUserBySsn(ssn));
		return "search";
	}

	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{ssn}-user" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isUserSsnUnique(user.getId(), user.getSsn())){
			FieldError ssnError =new FieldError("user","ssn",messageSource.getMessage("non.unique.ssn", new String[]{user.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}

		service.updateUser(user);

		model.addAttribute("success", "User " + user.getName()	+ " updated successfully");
		return "success";
	}

	/*
	 * This method will delete an user by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{ssn}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssn) {
		service.deleteUserBySsn(ssn);
		return "redirect:/list";
	}

}
