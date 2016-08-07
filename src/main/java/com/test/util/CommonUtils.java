package com.test.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.api.exception.UnexpectedApplicationException;

/**
 * THe util class comprising of helper/utility methods that can be re-used across applications.-
 * 
 * @author Akshay
 *
 */
public class CommonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * parses json array to csv file
     * 
     * @param valueAsArray
     * @throws UnexpectedApplicationException
     * @throws URISyntaxException
     */
    public static void convertJsonArrayToCsv(JSONArray valueAsArray) throws UnexpectedApplicationException {
        LOG.info("begin converting json array into csv");

        if (null == valueAsArray || valueAsArray.length() == 0) {
            throw new UnexpectedApplicationException("json array doesnt contain any value");
        }

        try {
            // create a unique alphanumeric value that can be appended each time to the file name
            String uniqueVal = RandomStringUtils.randomAlphanumeric(10);
            // creates a new temporary file
            File file = File.createTempFile("apiResponse" + uniqueVal, ".csv");
            LOG.info("csv file created at path:- " + file.getAbsolutePath() + " and with name:- " + file.getName());
            // restructure Json paramters
            structureJsonParameters(valueAsArray);
            String csv = CDL.toString(valueAsArray);
            // write the data of Json array to CSV
            FileUtils.writeStringToFile(file, csv);
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage());
            throw new UnexpectedApplicationException("cannot write the response to CSV file" + ioe.getMessage());
        }
    }

    /**
     * iterates through json array contents and removes/modifies unwanted files from the array before writing them into
     * the CSV file.
     * 
     * @param valueAsArray
     * @throws JSONException
     */
    private static void structureJsonParameters(JSONArray valueAsArray) throws JSONException {
        for (int i = 0; i < valueAsArray.length(); i++) {
            JSONObject geo_position = valueAsArray.getJSONObject(i).getJSONObject("geo_position");
            // restructure the jsonarray
            valueAsArray.getJSONObject(i).put("latitude", geo_position.get("latitude"));
            valueAsArray.getJSONObject(i).put("longitude", geo_position.get("longitude"));
            // remove unwanted values from the json array
            valueAsArray.getJSONObject(i).remove("geo_position");
            valueAsArray.getJSONObject(i).remove("key");
            valueAsArray.getJSONObject(i).remove("iata_airport_code");
            valueAsArray.getJSONObject(i).remove("fullName");
            valueAsArray.getJSONObject(i).remove("distance");
            valueAsArray.getJSONObject(i).remove("inEurope");
            valueAsArray.getJSONObject(i).remove("locationId");
            valueAsArray.getJSONObject(i).remove("coreCountry");
            valueAsArray.getJSONObject(i).remove("alternativeNames");
            valueAsArray.getJSONObject(i).remove("distance");
            valueAsArray.getJSONObject(i).remove("country");
            valueAsArray.getJSONObject(i).remove("countryId");
            valueAsArray.getJSONObject(i).remove("countryCode");
            valueAsArray.getJSONObject(i).remove("names");
        }
    }
}
