package com.duzieblo.ewdserver.dao;

import com.duzieblo.ewdserver.db.DbHelper;
import com.duzieblo.ewdserver.model.People;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Damian Uziębło
 */
public class PeopleDao {
    
    private static final String QUERY_PEOPE_BY_CITY 
            = "SELECT ewd_pesel, ewd_name, ewd_surname, ewd_city FROM ewidencja_ludnosci WHERE lower(ewd_city) LIKE lower(?)";
    private static final String COLUMN_PESEL = "ewd_pesel";
    private static final String COLUMN_NAME = "ewd_name";
    private static final String COLUMN_SURNAME = "ewd_surname";
    private static final String COLUMN_CITY = "ewd_city";
    
    private DbHelper db;
    private Connection connect;

    public PeopleDao(DbHelper db) {
        this.db = db;
    }
    
    public List<People> findPeopleByCity(String city) throws SQLException {
        openConnection();
        if (connect == null) {
            throw new SQLException("Not connected to database!");
        }
        
        ArrayList<People> peoples = new ArrayList<>();
        PreparedStatement ps = connect.prepareStatement(QUERY_PEOPE_BY_CITY);
        ps.setString(1, city);
        ResultSet records = ps.executeQuery();
        while (records.next()) {
            People people = new People(records.getString(COLUMN_PESEL), 
                    records.getString(COLUMN_NAME),
                    records.getString(COLUMN_SURNAME),
                    records.getString(COLUMN_CITY));
            addPeople(peoples, people);
        }
        records.close();
        ps.close();
        closeConnection();
        return peoples;
    }
    
    private void openConnection() throws SQLException {
        try {
            connect = db.openConnection();
        } catch (ClassNotFoundException e) {
            System.out.println(e); //TODO use logger!
        }
    }
    
    private void closeConnection() throws SQLException {
        if (connect != null) {
            connect.close();
        }
    }
    
    private void addPeople(ArrayList<People> peoples, People people) {
        peoples.add(people);
    }
}
