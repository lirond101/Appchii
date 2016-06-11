package com.myRemax.controller.login;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myRemax.hibernate_model.AssetsEntity;
import com.myRemax.hibernate_model.UsersEntity;
import com.myRemax.service.AssetManager;
import com.myRemax.service.UserManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.myRemax.util.HibernateUtil;


@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private AssetManager assetManager;

	@RequestMapping(value = "/search_Page", method = {RequestMethod.GET})
	public String getSearchPage() {
		return "search";
	}

	@RequestMapping(value = "/validate_User", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@JsonView(AssetsEntity.class)
	public String validateUser(@RequestParam("username") String username,
							   @RequestParam("password") String password, HttpServletResponse response)
			throws IOException, SQLException {

		System.out.println(username + " " + password);
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		String res = "something wrong!";

		try {
			UsersEntity usersEntity = userManager.getUser(username);
			String pass = usersEntity.getPassword();
			if (pass.equals(password))
			{
				System.out.println("user is valid!");
				httpStatus = HttpStatus.OK;
				res = getAssetsByAgent(usersEntity.getUserid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//HibernateUtil.closeSession();
		}
		response.setStatus(httpStatus.value());
		return res;
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String getLoginPage(ModelMap model, Principal principal) {
		System.out.println("****LoginController called****");
		String name = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Form example");
		return "hello";
	}

	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String home(ModelMap model) {

		return "home";
	}


	private String getAssetsByAgent( int agentID) {

		System.out.println(agentID);
		List<AssetsEntity> l_asset = null;
		String json = "";

		try {
			l_asset = assetManager.getAssetsByAgent(agentID);
			if (!l_asset.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				json = objectMapper.writeValueAsString(l_asset);
				System.out.println("(*******************************");
				System.out.println(json);
				System.out.println("(*******************************");
			}

		} catch (Exception e) {
			System.out.println("There was a problem while retreiving assets!");
			System.out.println(e.getMessage());
		} finally {
			//HibernateUtil.closeSession();
		}

		return json;
	}
}

