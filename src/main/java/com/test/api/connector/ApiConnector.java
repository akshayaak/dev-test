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

    /**
     * connects and fetch response
     * 
     * @param parameter
     * @returns {@link JSONArray}
     * @throws UnexpectedApplicationException
     */
    public JSONArray connectAndFetchResponse(String parameter) throws UnexpectedApplicationException;

}
