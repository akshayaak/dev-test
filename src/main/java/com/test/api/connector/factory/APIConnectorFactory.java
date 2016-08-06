package com.test.api.connector.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    private static final Log LOG = LogFactory.getLog(APIConnectorFactory.class);

    /**
     * fetches the underlying api connector class based on the api names passed.
     * 
     * @param apiName
     * @return {@link ApiConnector}
     * @throws UnexpectedApplicationException
     */
    public ApiConnector getAPIConnector(String apiName) throws UnexpectedApplicationException {
        LOG.debug("retrive the relevant underlying api connector");

        if (ApiConnectorConstants.GO_EURO.equalsIgnoreCase(apiName))

        {
            return new GoEuroApiConnectorImpl();

        }
        LOG.error("the requested api could not be found, exception will be thrown");
        throw new UnexpectedApplicationException("no api connector found for the given api name, current request will be aborted");

    }
}
