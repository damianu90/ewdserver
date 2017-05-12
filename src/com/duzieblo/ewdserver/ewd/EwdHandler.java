package com.duzieblo.ewdserver.ewd;

import java.util.ArrayList;

/**
 *
 * @author Damian Uziębło
 */
public interface EwdHandler {
    ArrayList<ArrayList<String>> getPeoples(String login, String password, String city);
}
