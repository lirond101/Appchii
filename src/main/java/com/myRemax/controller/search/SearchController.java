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

import com.google.gson.Gson;
import com.myRemax.util.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/search")
public class SearchController {

	@Autowired
	private AssetManager assetManager;

	@RequestMapping(value = "/searchAssetByAddress", method={RequestMethod.GET}, produces= { "application/json; charset=UTF-8" })
	@ResponseBody
	@JsonView(AssetsEntity.class)
	@PreAuthorize("hasRole('USER')")
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

	@RequestMapping(value = "/searchAssetsByParams", method={RequestMethod.POST},consumes = "application/json", produces= { "application/json; charset=UTF-8" })
	@ResponseBody
	@JsonView(AssetsEntity.class)
	public String serchAssetsByParams(@RequestParam("Type") String type, @RequestParam("Agent") String agent,
									  @RequestParam("FromFloor") String floor, @RequestParam("FromPrice") String fromPrice,
									  @RequestParam("ToPrice") String toPrice, @RequestParam("Neighborhood") String neighborhood,
									  @RequestParam("Rooms") String rooms, @RequestParam("Mamad") String mamad,
									  @RequestParam("AirCon") String airCon, @RequestParam("Elevator") String elevator,
									  @RequestParam("City") String city, HttpServletResponse response)
	{

		System.out.println(type+", "+agent+", "+floor+", "+fromPrice+", "+toPrice+", "+
				neighborhood+", "+rooms+", "+mamad+", "+airCon+", "+elevator + city);

		boolean isType=false, isAgent=false, isFloor=false, isFromPrice=false, isToPrice=false,
				isNeighborhood=false, isRooms=false, isMamad=false, isAirConditioner=false, isElevator = false;
		boolean isAndHasUsed = false;
		if(type!="")
			isType=true;
		if(agent!="")
			isAgent=true;
		if(floor!="")
			isFloor=true;
		if(fromPrice!="")
			isFromPrice=true;
		if(toPrice!="")
			isToPrice=true;
		if(neighborhood!="")
			isNeighborhood=true;
		if(rooms!="")
			isRooms=true;
		if(mamad!="")
			isMamad=true;
		if(airCon!="")
			isAirConditioner=true;
		if(elevator!="")
			isElevator=true;

		Session session = null;
		List<AssetsEntity> l_asset = null;
		String json = "";

		try {
			session = HibernateUtil.currentSession();
			String queryToBuild = "from AssetsEntity where city=:city";
			if (isType) {
				if(!isAndHasUsed) {
					queryToBuild += " Type=" + type;
					isAndHasUsed = true;
				}
				else queryToBuild += " AND Type="+type;
			}

			if (isAgent) {
				if(!isAndHasUsed) {
					queryToBuild += " Agent=" + agent;
					isAndHasUsed = true;
				}
				else queryToBuild += " AND Agent="+agent;
			}

			if (isFloor) {
				if(!isAndHasUsed) {
					queryToBuild += " Floor=" + floor;
					isAndHasUsed = true;
				}
				else queryToBuild += " AND Floor="+floor;
			}

			if (isFromPrice && isToPrice) {
				if(!isAndHasUsed) {
					queryToBuild += " Price BETWEEN " + fromPrice + " AND " + toPrice;
					isAndHasUsed = true;
				}
				else queryToBuild += " AND Price BETWEEN " + fromPrice + " AND " + toPrice;
			}

			if (isNeighborhood) {
				if(!isAndHasUsed) {
					queryToBuild += " Neighborhood=" + neighborhood;
					isAndHasUsed = true;
				}
				else queryToBuild += " AND Neighborhood=" + neighborhood;
			}

			if (isRooms) {
				if(!isAndHasUsed) {
					queryToBuild += " Rooms=" + rooms;
					isAndHasUsed = true;
				}
				else queryToBuild += " AND Rooms=" + rooms;
			}

			if (isMamad) {
				if(!isAndHasUsed) {
					queryToBuild += " Mamad=" + mamad;
					isAndHasUsed = true;
				}
				else queryToBuild += " AND Mamad=" + mamad;
			}

			if (isAirConditioner) {
				if(!isAndHasUsed) {
					queryToBuild += " Air_Conditioner=" + airCon;
					isAndHasUsed = true;
				}
				else queryToBuild += " AND Air_Conditioner=" + airCon;
			}

			if (isElevator) {
				if(!isAndHasUsed) {
					queryToBuild += " Elevator=" + elevator;
					isAndHasUsed = true;
				}
				else queryToBuild += " AND Elevator=" + elevator;
			}

			if(queryToBuild == "from AssetsEntity where") {
				queryToBuild = queryToBuild.substring(0, 17);
				System.out.println(queryToBuild);
			}

			System.out.println(queryToBuild);
			Query HQL_Query = session.createQuery(queryToBuild);
			HQL_Query.setParameter("city", city);

			l_asset = HQL_Query.list();

			if(!l_asset.isEmpty())
			{
				for(int i=0; i<l_asset.size(); i++)
					System.out.println(l_asset.get(i).getAssetid());
				ObjectMapper objectMapper = new ObjectMapper();
				json = objectMapper.writeValueAsString(l_asset);

			}

		} catch (Exception e) {
			System.out.println("There was a problem while retrieving assets!");
			System.out.println(e.getMessage());
		} 

		finally
		{
			HibernateUtil.closeSession();
		}		
		return json;
		
	}
}

