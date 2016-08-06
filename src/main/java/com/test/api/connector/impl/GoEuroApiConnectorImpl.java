package com.test.api.connector.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.util.Strings;
import org.json.JSONArray;

import com.test.api.connector.ApiConnector;
import com.test.api.exception.UnexpectedApplicationException;
import com.test.constant.ApiConnectorConstants;

/**
 * The connector class responsible for communicating with the goeuro underlying api and fetch the response
 * 
 * @author Akshay
 *
 */
public class GoEuroApiConnectorImpl implements ApiConnector {
    private static final Log LOG = LogFactory.getLog(GoEuroApiConnectorImpl.class);

    @Override
    public JSONArray connectAndFetchResponse(String[] parameters) throws UnexpectedApplicationException {
        LOG.debug("connect with the go euro api and fetch the response");

        if (ArrayUtils.isEmpty(parameters)) {
            throw new UnexpectedApplicationException("the input parameters cannot be empty/null while calling the goeuro api");
        }
        String output = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        for (String cityName : parameters) {
            try {
                HttpGet getRequest = new HttpGet(ApiConnectorConstants.GO_EURO_API_URL.concat(cityName));
                // getRequest.addHeader("accept", "application/json");

                HttpResponse response = httpClient.execute(getRequest);

                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    throw new UnexpectedApplicationException("HTTP error code:-" + response.getStatusLine().getStatusCode() + "not a succesful request");
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
                output = br.readLine();
            } catch (ClientProtocolException e) {
                LOG.error(e.getCause());
                throw new UnexpectedApplicationException(e.getMessage() + "http protocol error");
            } catch (IOException e) {
                LOG.error(e.getCause());
                throw new UnexpectedApplicationException(e.getMessage() + "unable to read resspone recieved from http request sent to go euro remote API.");
            }

        }
        if (Strings.isBlank(output)) {
            throw new UnexpectedApplicationException("no response received from the GOEuro API. Request processing will be aborted");
        }
        return new JSONArray(output);
    }

}
