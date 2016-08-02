package com.myRemax.controller.login;

import java.util.List;

import com.myRemax.hibernate_model.AssetsEntity;
import com.myRemax.service.AssetManager;
import com.myRemax.service.UserManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonView;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private AssetManager assetManager;

	public LoginController(){}

//	@RequestMapping(value = "/validate_User", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	@JsonView(AssetsEntity.class)
//	public String validateUser(@RequestParam("username") String username,
//							   @RequestParam("password") String password, HttpServletResponse response)
//			throws IOException, SQLException {
//
//		System.out.println(username + " " + password);
//		HttpStatus httpStatus = HttpStatus.CONFLICT;
//		String res = "something wrong!";
//
//		try {
//			UsersEntity usersEntity = userManager.getUser(username);
//			String pass = usersEntity.getPassword();
//			if (pass.equals(password))
//			{
//				System.out.println("user is valid!");
//				httpStatus = HttpStatus.OK;
//				res = getAssetsByAgent(usersEntity.getUserid());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			//HibernateUtil.closeSession();
//		}
//		response.setStatus(httpStatus.value());
//		return res;
//	}


//	@CrossOrigin(origins = "http://localhost:9000")
	@RequestMapping(value = "/getAssetsByAgent", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
	@ResponseBody
	@PreAuthorize("hasRole('USER')")
	@JsonView(AssetsEntity.class)
	public ResponseEntity getAssetsByAgent(@RequestParam("Username") String username/*, HttpServletResponse response*/) {

		System.out.println(username);
		List<AssetsEntity> l_asset = null;
		String json = "";
		//HttpStatus httpStatus = HttpStatus.OK;

		try {
			l_asset = assetManager.getAssetsByAgent(username);
			if (!l_asset.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				json = objectMapper.writeValueAsString(l_asset);
			}

		} catch (Exception e) {
			System.out.println("There was a problem while retreiving assets!");
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body("retrieving assets failed");
		}
		return ResponseEntity.ok(json);
	}
}

