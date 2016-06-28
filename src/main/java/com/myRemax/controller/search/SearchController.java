package com.myRemax.controller.search;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.myRemax.hibernate_model.AssetsEntity;
import com.myRemax.service.AssetManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myRemax.util.HibernateUtil;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private AssetManager assetManager;

	@RequestMapping(value = "/searchAssetByAddress", method={RequestMethod.GET}, produces= { "application/json; charset=UTF-8" })
	@ResponseBody
	@JsonView(AssetsEntity.class)
//	@PreAuthorize("hasRole('USER')")
	public String searchAssetByAddress(@RequestParam("Street") String street, HttpServletResponse response)
			throws UnsupportedEncodingException {

		List <AssetsEntity> aResult = null;
		System.out.println(street);
		String json = "";

		try {
			aResult = assetManager.getAssetByFullAddress("באר שבע", street,"");
			for(int i=0; i<aResult.size(); i++)
				System.out.println("***"+aResult.get(i).getStreet() +"***");
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(aResult);

		} catch (Exception e) {
			System.out.println("There was a problem while retreiving asset!");
			System.out.println(e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/searchAssetsByParams", method={RequestMethod.POST}, produces= { "application/json; charset=UTF-8" })
	@ResponseBody
	@JsonView(AssetsEntity.class)
	public String serchAssetsByParams(@RequestParam("City") String city,
									  @RequestParam("Type") String type, @RequestParam("Agent") String agent,
									  @RequestParam("Floor") String floor, @RequestParam("FromPrice") String fromPrice,
									  @RequestParam("ToPrice") String toPrice, @RequestParam("Neighborhood") String neighborhood,
									  @RequestParam("Rooms") String rooms, @RequestParam("Mamad") String mamad,
									  @RequestParam("AirCon") String airCon, @RequestParam("Elevator") String elevator,
									  @RequestParam("Square") String square,  @RequestParam("Status") String status,
									  HttpServletResponse response)
	{

		System.out.println(type+", "+agent+", "+floor+", "+fromPrice+", "+toPrice+", "+
				neighborhood+", "+rooms+", "+mamad+", "+airCon+", "+elevator + city);



		Session session = null;
		List<AssetsEntity> l_asset = null;
		String json = "";

		try {
			l_asset = assetManager.getAssetByParams("באר שבע", type, agent, floor, fromPrice, toPrice, neighborhood, rooms, mamad, airCon, elevator, square, status);
			for(int i=0; i<l_asset.size(); i++)
				System.out.println("***"+l_asset.get(i).getStreet() +"***");
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(l_asset);

		} catch (Exception e) {
			System.out.println("There was a problem while retreiving asset!");
			System.out.println(e.getMessage());
		}
		return json;
		
	}
}

