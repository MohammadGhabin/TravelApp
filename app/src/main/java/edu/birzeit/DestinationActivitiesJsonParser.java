package edu.birzeit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DestinationActivitiesJsonParser {
    public static List<DestinationActivity> getObjectFromJson(String json) {
        List<DestinationActivity> destinationActivities;
        try {
            JSONArray jsonArray = new JSONArray(json);
            destinationActivities = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                DestinationActivity destinationActivity = new DestinationActivity();
                destinationActivity.setCity(jsonObject.getString("city"));
                destinationActivity.setCountry(jsonObject.getString("country"));
                destinationActivity.setContinent(jsonObject.getString("continent"));
                destinationActivity.setLongitude(jsonObject.getDouble("longitude"));
                destinationActivity.setLatitude(jsonObject.getDouble("latitude"));
                destinationActivity.setCost(jsonObject.getDouble("cost"));
                destinationActivity.setImg(jsonObject.getString("img"));
                destinationActivity.setDescription(jsonObject.getString("description"));
                destinationActivities.add(destinationActivity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return destinationActivities;
    }
}
