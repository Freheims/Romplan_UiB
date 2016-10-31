package com.pensive.android.romplanuib.util;

/**
 * Created by EddiStat on 05.06.2016.
 */
public class StringCleaner {

    /**
     * Removes the building code from the building name
     * @param fullBuildingName The building name with building code and name
     * @return The building name without the building code
     */
    public String createBuildingName(String fullBuildingName) {
        String buildingName = fullBuildingName.replaceAll("(^[^:]+:.)", "");
        buildingName = buildingName.substring(1);
        return buildingName;
    }

    /**
     * Extracts the building code from the building name
     * @param fullBuildingName The building name with building name and code
     * @return The building code
     */
    public String createBuildingCode(String fullBuildingName) {
        String buildingCode = fullBuildingName.replaceAll("(:.*)", "");
        buildingCode = buildingCode.substring(1);
        return buildingCode;
    }

}
