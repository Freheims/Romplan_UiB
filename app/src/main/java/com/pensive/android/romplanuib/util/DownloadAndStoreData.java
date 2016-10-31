package com.pensive.android.romplanuib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pensive.android.romplanuib.io.BuildingCodeParser;
import com.pensive.android.romplanuib.io.BuildingParser;
import com.pensive.android.romplanuib.io.RoomParser;
import com.pensive.android.romplanuib.models.CalActivity;
import com.pensive.android.romplanuib.models.UIBbuilding;
import com.pensive.android.romplanuib.models.UIBroom;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EddiStat on 24.05.2016.
 *
 * Skulle faen meg ha kommentert denne når jeg skreiv den
 */
public class DownloadAndStoreData {
    List<UIBbuilding> allBuildings;
    List<UIBroom> allRooms = new ArrayList<>();
    List<WeekViewEvent> weekViewEvents;



    public DownloadAndStoreData(){

        //Empty for now

    }

    public void setStoreWeekEventsTempDataInArray(List<WeekViewEvent> weekViewEvents){
        this.weekViewEvents = weekViewEvents;
    }

    public List<WeekViewEvent> getStoreWeekEventsTempDataInArray(){
            return weekViewEvents;
    }


    public void setStoreDataAllBuildings(Context context, List<UIBbuilding> buildingList) {
        ArrayList<UIBbuilding> buildingArrayList = new ArrayList<>(buildingList); //Convert to ArrayList because Gson.toJson demands it.
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(buildingArrayList);
        editor.putString("all_buildings", json);
        editor.apply();
    }

    public List<UIBbuilding> getStoredDataAllBuildings(Context context){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("all_buildings", null);
        Type type = new TypeToken<List<UIBbuilding>>() {}.getType();
        List<UIBbuilding> arrayList = gson.fromJson(json, type);

        return arrayList;
    }


    public void setStoreDataAllRooms(Context context, List<UIBroom> roomList) {
        ArrayList<UIBroom> roomArrayList = new ArrayList<>(roomList);//Convert to ArrayList because Gson.toJson demands it.
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(roomArrayList);
        editor.putString("all_rooms", json);
        editor.apply();
    }

    public List<UIBroom> getStoredDataAllRooms(Context context){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("all_rooms", null);
        Type type = new TypeToken<List<UIBroom>>() {}.getType();
        List<UIBroom> arrayList = gson.fromJson(json, type);

        return arrayList;
    }

    public List<UIBbuilding> getAllBuildings(){
        List<UIBbuilding> allBuildingsWithoutRooms;
        List<UIBbuilding> allBuildingsWithRooms = new ArrayList<>();
        List<UIBroom> buildingRooms;
        UIBbuilding buildingToBeAdded;
        try {
            BuildingParser buildingParser = new BuildingParser(
                    "http://rom.app.uib.no/ukesoversikt/?entry=byggrom");

            allBuildingsWithoutRooms = buildingParser.getBuildings();

            for (int i = 0; i < allBuildingsWithoutRooms.size(); i++ ){

                RoomParser roomParser = new RoomParser(
                        BuildingCodeParser.getBuildingURL(allBuildingsWithoutRooms.get(i).getName()), allBuildingsWithoutRooms.get(i).getName());
                buildingRooms = roomParser.getRooms();
                buildingToBeAdded = new UIBbuilding(allBuildingsWithoutRooms.get(i).getName(), buildingRooms);

                allBuildingsWithRooms.add(buildingToBeAdded);

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("?????");
        }

        return allBuildingsWithRooms;
    }

    public List<UIBroom> getAllRoomsInUni(){
        List<UIBroom> allRoomsInBuilding;
        allBuildings = getAllBuildings();

        for (UIBbuilding building : getAllBuildings() ){

            try {
                RoomParser roomParser = new RoomParser(
                        BuildingCodeParser.getBuildingURL(building.getName()), building.getName());
                allRoomsInBuilding = roomParser.getRooms();

                for (UIBroom room : allRoomsInBuilding){
                    allRooms.add(room);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return allRooms;
    }

    public Boolean isDataIsStored(Context context){
        if(getStoredDataAllRooms(context) == null && getStoredDataAllBuildings(context) == null){
            System.out.println("isDataStored returns false");
            return false;
        } else {
            System.out.println("isDataStored returns true");
            return true;
        }
    }
}
