package com.test.launcher;

import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;

import com.test.api.connector.ApiConnector;
import com.test.api.connector.factory.APIConnectorFactory;
import com.test.api.exception.UnexpectedApplicationException;
import com.test.constant.ApiConnectorConstants;

/**
 * The class serving as the entry point of the application which further delegates call to the required classes.
 * 
 * @author akshay
 *
 */
public class Application {

    /**
     * entry point
     * 
     * @param args
     * @throws UnexpectedApplicationException
     */
    public static void main(String[] args) throws UnexpectedApplicationException {
        // if no arguments i.e input paramters exist, throw application level exception.
        if (ArrayUtils.isEmpty(args)) {

            throw new UnexpectedApplicationException("unable to process, input paramter should be provided for the application to run");
        }

        APIConnectorFactory connectorFactory = new APIConnectorFactory();
        ApiConnector apiConnectionFactory = connectorFactory.getAPIConnector(ApiConnectorConstants.GO_EURO);
        String[] values = new String[] { "Berlin" };
        JSONArray response = apiConnectionFactory.connectAndFetchResponse(values);

    }

}
