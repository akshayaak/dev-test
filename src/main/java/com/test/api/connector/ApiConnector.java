package com.test.api.connector;

import org.json.JSONArray;

import com.test.api.exception.UnexpectedApplicationException;

/**
 * provides base definition for implementing connector classes.
 * 
 * @author Akshay
 *
 */
public interface ApiConnector {

    public JSONArray connectAndFetchResponse(String[] parameters) throws UnexpectedApplicationException;

}
