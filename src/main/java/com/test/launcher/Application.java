package com.test.launcher;

import java.net.URISyntaxException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.BasicConfigurator;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.api.connector.ApiConnector;
import com.test.api.connector.factory.APIConnectorFactory;
import com.test.api.exception.UnexpectedApplicationException;
import com.test.constant.ApiConnectorConstants;
import com.test.util.CommonUtils;

/**
 * The class serving as the entry point of the application which further delegates call to the required classes.
 * 
 * @author Akshay
 *
 */
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    /**
     * entry point of the application
     * 
     * @param args
     * @throws UnexpectedApplicationException
     * @throws URISyntaxException
     */
    public static void main(String[] parameters) throws UnexpectedApplicationException {
        // quick solution for setting up the logging for the application without the creation of log4j.xml
        BasicConfigurator.configure();
        LOG.info("request is getting processed....");
        // if no arguments i.e input parameters exist, throw application level exception.
        if (ArrayUtils.isEmpty(parameters)) {
            throw new UnexpectedApplicationException("unable to process, input paramter should be provided for the application to run");
        }
        // if more than one arguments are passed
        if (ArrayUtils.getLength(parameters) > 1) {
            throw new UnexpectedApplicationException("only one input paramter is supported.");
        }

        APIConnectorFactory connectorFactory = new APIConnectorFactory();
        ApiConnector apiConnectionFactory = connectorFactory.getAPIConnector(ApiConnectorConstants.GO_EURO);
        JSONArray response = apiConnectionFactory.connectAndFetchResponse(parameters[0]);
        // write the json array to CSV
        CommonUtils.convertJsonArrayToCsv(response);
        LOG.info("request processing completed....");

    }

}
