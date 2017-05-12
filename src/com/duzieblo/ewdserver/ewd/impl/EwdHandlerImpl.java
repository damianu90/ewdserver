package com.duzieblo.ewdserver.ewd.impl;

import com.duzieblo.ewdserver.config.Config;
import com.duzieblo.ewdserver.dao.PeopleDao;
import com.duzieblo.ewdserver.db.DbHelper;
import com.duzieblo.ewdserver.ewd.EwdHandler;
import com.duzieblo.ewdserver.model.People;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Damian Uziębło
 */
public class EwdHandlerImpl implements EwdHandler{
    
    /**
     * Method authorize user in rigid manner 
     * @param login
     * @param password
     * @return boolean - return true if success authorization
     */
    private boolean isAuthorization(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        if (login.equals(Config.SERVER_LOGIN) && login.equals(Config.SERVER_PASSWORD)) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<ArrayList<String>> getPeoples(String login, String password, String city) {
        System.out.println("Start execute method getPeoples"); //TODO use logger!
        ArrayList result = new ArrayList();
        if (!isAuthorization(login, password)) {
            ArrayList error = new ArrayList();
            error.add("FAIL");
            error.add("-20999");
            error.add("Authorization failed");
            result.add(error);
            System.out.println("Methot getPeopls return FAIL"); //TODO use logger!
            return result;
        } 
        
        PeopleDao dao = new PeopleDao(new DbHelper(Config.DB_HOST, Config.DB_NAME, Config.DB_LOGIN, Config.DB_PASSWORD));
        try {
            List<People> peoples = dao.findPeopleByCity(city);
            ArrayList<String> header = new ArrayList();
            header.add("SUCCESS");
            result.add(header);
            peoples.forEach(people -> {
                ArrayList<String> peopleList = new ArrayList();
                peopleList.add(people.getPesel());
                peopleList.add(people.getName());
                peopleList.add(people.getSurname());
                peopleList.add(people.getCity());
                result.add(peopleList);
            });
            System.out.println("Methot getPeopls return SUCCESS"); //TODO use logger!
        } catch (SQLException ex) {
            ArrayList<String> error = new ArrayList<>();
            error.add("FAIL");
            error.add(String.valueOf(ex.getErrorCode()));
            error.add(ex.getMessage());
            result.add(error);
            System.out.println("Methot getPeopls return FAIL 2 " + ex.getMessage()); //TODO use logger!
        }
        return result;
    }
    
}
