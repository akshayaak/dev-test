package com.test.api.connector.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOG = LoggerFactory.getLogger(GoEuroApiConnectorImpl.class);

    public JSONArray connectAndFetchResponse(String parameter) throws UnexpectedApplicationException {
        LOG.info("connect with the go euro api and fetch the response");

        if (StringUtils.isBlank(parameter)) {
            throw new UnexpectedApplicationException("the input parameter should be provided while calling the goeuro api");
        }

        String output = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {
            HttpGet getRequest = new HttpGet(ApiConnectorConstants.GO_EURO_API_URL.concat(parameter));

            HttpResponse response = httpClient.execute(getRequest);

            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
                throw new UnexpectedApplicationException("HTTP error code:-" + response.getStatusLine().getStatusCode() + "not a succesful request");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            output = br.readLine();
        } catch (ClientProtocolException e) {
            LOG.error(e.getMessage());
            throw new UnexpectedApplicationException(e.getMessage() + "http protocol error");
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new UnexpectedApplicationException(e.getMessage() + "unable to read resspone recieved from http request sent to go euro remote API.");
        }

        if (StringUtils.isBlank(output)) {
            throw new UnexpectedApplicationException("no response received from the GOEuro API. Request processing will be aborted");
        }
        return new JSONArray(output);
    }

}
