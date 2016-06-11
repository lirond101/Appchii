package com.myRemax.controller.asset;

import com.fasterxml.jackson.annotation.JsonView;
import com.myRemax.hibernate_model.AssetsEntity;
import com.myRemax.hibernate_model.UsersEntity;
import com.myRemax.service.AssetManager;
import com.myRemax.service.AssetManagerImpl;
import com.myRemax.service.UserManager;
import com.myRemax.service.UserManagerImpl;
import com.myRemax.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by liron_d on 17/03/2016.
 */

    @RestController
    @RequestMapping(value = "/asset")
    public class AssetController {

    @Autowired
    private AssetManager assetManager;

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/addAsset", method = {RequestMethod.POST})
    @JsonView(AssetsEntity.class)
    public void addAsset(@RequestParam("City") String city, @RequestParam("Street") String street,
                         @RequestParam("Type") String type, @RequestParam("Num_Address") String num_Address,
                         @RequestParam("Agent") String agent, @RequestParam("Floor") String floor,
                         @RequestParam("Price") String price, @RequestParam("Neighborhood") String neighborhood,
                         @RequestParam("Rooms") String rooms, @RequestParam("Mamad") String mamad,
                         @RequestParam("AirCon") String airCon, @RequestParam("Elevator") String elevator,
                         @RequestParam("Square") String square,  @RequestParam("Status") String status,
                         @RequestParam("Customer_Name") String cust_Name, @RequestParam("Customer_Tel") String cust_Tel,
                         @RequestParam("NumOfFloors") String numOfFloors, @RequestParam("Details") String details,
                         HttpServletResponse response) {

        System.out.println(city + ", " + street + ", " + num_Address + ", " + type + ", " + agent + ", " + floor + ", " + numOfFloors + ", " + price + ", " +
                neighborhood + ", " + rooms + ", " + mamad + ", " + airCon + ", " + elevator + ", " + square + ", " + status + ", " + cust_Name +
                ", " + cust_Tel + ", " + details);

        boolean isType = false, isAgent = false, isFloor = false, isNumOfFloors = false, isPrice = false, isNeighborhood = false,
                isRooms = false, isMamad = false, isAirConditioner = false, isElevator = false, isSquare = false, isStatus = false,
                isCust_Name = false, isCust_Tel = false, isDetails = false;
        if (type != "")
            isType = true;
        if (agent != "")
            isAgent = true;
        if (floor != "")
            isFloor = true;
        if (numOfFloors != "")
            isNumOfFloors = true;
        if (price != "")
            isPrice = true;
        if (neighborhood != "")
            isNeighborhood = true;
        if (rooms != "")
            isRooms = true;
        if (mamad != "")
            isMamad = true;
        if (airCon != "")
            isAirConditioner = true;
        if (elevator != "")
            isElevator = true;
        if (square != "")
            isSquare = true;
        if (status != "")
            isStatus = true;
        if (cust_Name != "")
            isCust_Name = true;
        if (cust_Tel != "")
            isCust_Tel = true;
        if (details != "")
            isDetails = true;

        //Session session = null;
        AssetsEntity assetsEntityToAdd = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            assetsEntityToAdd = new AssetsEntity();

            assetsEntityToAdd.setCity(city);
            assetsEntityToAdd.setStreet(street);
            assetsEntityToAdd.setNum_Address(num_Address);

            if (isType)
                assetsEntityToAdd.setType(type);

            if (isAgent)
                assetsEntityToAdd.setUsersByAgent(userManager.getUser(agent));

            if (isFloor)
                assetsEntityToAdd.setFloor(Float.parseFloat(floor));

            if (isNumOfFloors)
                assetsEntityToAdd.setFloor(Float.parseFloat(numOfFloors));

            if (isPrice)
                assetsEntityToAdd.setPrice(Integer.parseInt(price));

            if (isNeighborhood)
                assetsEntityToAdd.setNeighborhood(neighborhood);

            if (isRooms)
                assetsEntityToAdd.setRooms(Float.parseFloat(rooms));

            if (isMamad)
                assetsEntityToAdd.setMamad(Byte.parseByte("1"));

            if (isAirConditioner)
                assetsEntityToAdd.setAirConditioner(Byte.parseByte("1"));

            if (isElevator)
                assetsEntityToAdd.setElevator(Byte.parseByte("1"));

            if(isSquare)
                assetsEntityToAdd.setSquare(Float.parseFloat(square));

            if(isStatus)
                assetsEntityToAdd.setStatus(status);

            if(isCust_Name)
                assetsEntityToAdd.setCust_Name(cust_Name);

            if(isCust_Tel)
                assetsEntityToAdd.setCust_Tel(cust_Tel);

            if(isDetails)
                assetsEntityToAdd.setDetails(details);

            assetManager.insertAsset(assetsEntityToAdd);

        } catch (Exception e) {
            System.out.println("There was a problem while adding an asset!");
            System.out.println(e.getMessage());
            httpStatus = HttpStatus.CONFLICT;
            response.setHeader("error_Message", e.getMessage());
        }
        response.setStatus(httpStatus.value());
    }

    @RequestMapping(value = "/editAsset", method = {RequestMethod.POST})
    @JsonView(AssetsEntity.class)
    public void editAsset(@RequestParam("assetID") String assetID, @RequestParam("City") String city,
                          @RequestParam("Street") String street, @RequestParam("Num_Address") String num_Address,
                          @RequestParam("Type") String type, @RequestParam("Agent") String agent,
                          @RequestParam("Floor") String floor, @RequestParam("NumOfFloors") String numOfFloors,
                          @RequestParam("Price") String price, @RequestParam("Neighborhood") String neighborhood,
                          @RequestParam("Rooms") String rooms, @RequestParam("Mamad") String mamad,
                          @RequestParam("AirCon") String airCon, @RequestParam("Elevator") String elevator,
                          @RequestParam("Square") String square,  @RequestParam("Status") String status,
                          @RequestParam("Customer_Name") String cust_Name, @RequestParam("Customer_Tel") String cust_Tel,
                          @RequestParam("Details") String details, HttpServletResponse response) {

        System.out.println(assetID + ", " + city + ", " + street + ", " + num_Address + ", " + type + ", " + agent + ", " + floor +
                ", " + numOfFloors + ", " + price + ", " + neighborhood + ", " + rooms + ", " + mamad + ", " + airCon + ", " + elevator +
                ", " + square + ", " + status + ", " + cust_Name + ", " + cust_Tel + ", " + details);

        boolean isCity = false, isStreet = false, isNum_Address = false, isType = false, isAgent = false, isFloor = false, isNumOfFloors = false, isPrice = false, isNeighborhood = false,
                isRooms = false, isMamad = false, isAirConditioner = false, isElevator = false, isSquare = false, isStatus = false,
                isCust_Name = false, isCust_Tel = false, isDetails = false;

        if (type != "")
            isType = true;
        if (agent != "")
            isAgent = true;
        if (floor != "")
            isFloor = true;
        if (numOfFloors != "")
            isNumOfFloors = true;
        if (price != "")
            isPrice = true;
        if (neighborhood != "")
            isNeighborhood = true;
        if (rooms != "")
            isRooms = true;
        if (mamad != "")
            isMamad = true;
        if (airCon != "")
            isAirConditioner = true;
        if (elevator != "")
            isElevator = true;
        if (square != "")
            isSquare = true;
        if (status != "")
            isStatus = true;
        if (cust_Name != "")
            isCust_Name = true;
        if (cust_Tel != "")
            isCust_Tel = true;
        if (details != "")
            isDetails = true;

        AssetsEntity assetsEntityToEdit = null;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            assetsEntityToEdit = assetManager.getAsset(Integer.parseInt(assetID));
            assetsEntityToEdit.setCity(city);

            if (isCity)
                assetsEntityToEdit.setCity(city);

            if (isStreet)
                assetsEntityToEdit.setStreet(street);

            if (isNum_Address)
                assetsEntityToEdit.setNum_Address(num_Address);

            if (isType)
                assetsEntityToEdit.setType(type);

            if (isAgent)
                assetsEntityToEdit.setUsersByAgent(userManager.getUser(agent));

            if (isFloor)
                assetsEntityToEdit.setFloor(Float.parseFloat(floor));

            if (isNumOfFloors)
                assetsEntityToEdit.setNumOfFloors(Float.parseFloat(numOfFloors));

            if (isPrice)
                assetsEntityToEdit.setPrice(Integer.parseInt(price));

            if (isNeighborhood)
                assetsEntityToEdit.setNeighborhood(neighborhood);

            if (isRooms)
                assetsEntityToEdit.setRooms(Float.parseFloat(rooms));

            if (isMamad)
                assetsEntityToEdit.setMamad(Byte.parseByte("1"));

            if (isAirConditioner)
                assetsEntityToEdit.setAirConditioner(Byte.parseByte("1"));

            if (isElevator)
                assetsEntityToEdit.setElevator(Byte.parseByte("1"));

            if (isSquare)
                assetsEntityToEdit.setSquare(Float.parseFloat(square));

            if (isStatus)
                assetsEntityToEdit.setStatus(status);

            if (isCust_Name)
                assetsEntityToEdit.setCust_Name(cust_Name);

            if (isCust_Tel)
                assetsEntityToEdit.setCust_Tel(cust_Tel);

            if (isDetails)
                assetsEntityToEdit.setDetails(details);

            assetManager.insertAsset(assetsEntityToEdit);

        } catch (Exception e) {
            System.out.println("There was a problem while adding an asset!");
            System.out.println(e.getMessage());
            httpStatus = HttpStatus.CONFLICT;
            response.setHeader("error_Message", e.getMessage());

            response.setStatus(httpStatus.value());
        }
    }
}

