package com.test.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.test.api.exception.UnexpectedApplicationException;

/**
 * THe util class comprising of helper/utility methods that can be re-used across applications.-
 * 
 * @author akshay
 *
 */
public class CommonUtils {

    /**
     * parses json array to csv file
     * 
     * @param valueAsArray
     * @throws UnexpectedApplicationException
     */
    public static void convertJsonArrayToCsv(JSONArray valueAsArray) throws UnexpectedApplicationException {
        try {
            File file = new File("temp" + File.separator + "apiResponse.csv");
            if (!file.exists()) {
                file.createNewFile();
            } else {

            }

            structureJsonParameterToCSV(valueAsArray);

            String csv = CDL.toString(valueAsArray);
            FileUtils.writeStringToFile(file, csv);
        } catch (IOException e) {
            throw new UnexpectedApplicationException("cannot write the response to CSV file" + e.getMessage());
        }
    }

    /**
     * iterates through json array contents and removes/modifies unwanted files from the array before writing them into
     * the CSV file.
     * 
     * @param valueAsArray
     * @throws JSONException
     */
    private static void structureJsonParameterToCSV(JSONArray valueAsArray) throws JSONException {
        for (int i = 0; i < valueAsArray.length(); i++) {
            JSONObject geo_position = valueAsArray.getJSONObject(i).getJSONObject("geo_position");
            valueAsArray.getJSONObject(i).put("latitude", geo_position.get("latitude"));
            valueAsArray.getJSONObject(i).put("longitude", geo_position.get("longitude"));
            valueAsArray.getJSONObject(i).remove("geo_position");
            valueAsArray.getJSONObject(i).remove("key");
            valueAsArray.getJSONObject(i).remove("iata_airport_code");
            valueAsArray.getJSONObject(i).remove("iata_airport_code");
            valueAsArray.getJSONObject(i).remove("fullName");
            valueAsArray.getJSONObject(i).remove("country");
        }
    }
}
