package com.test.api.connector.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.api.connector.ApiConnector;
import com.test.api.connector.impl.GoEuroApiConnectorImpl;
import com.test.api.exception.UnexpectedApplicationException;
import com.test.constant.ApiConnectorConstants;

/**
 * factory class providing the relevant connector classes.
 * 
 * @author Akshay
 *
 */
public class APIConnectorFactory {

    private static final Logger LOG = LoggerFactory.getLogger(APIConnectorFactory.class);

    /**
     * fetches the underlying api connector class based on the api names passed in.
     * 
     * @param apiName
     * @return {@link ApiConnector}
     * @throws UnexpectedApplicationException
     */
    public ApiConnector getAPIConnector(String apiName) throws UnexpectedApplicationException {
        LOG.info("retrieve the relevant underlying api connector");

        if (ApiConnectorConstants.GO_EURO.equalsIgnoreCase(apiName))

        {
            return new GoEuroApiConnectorImpl();

        }
        LOG.error("the requested api could not be found, exception will be thrown");
        throw new UnexpectedApplicationException("no api connector found for the given api name, current request will be aborted");

    }
}
