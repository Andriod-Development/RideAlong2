package com.ridealong.data;

import android.provider.BaseColumns;

/**
 * Created by Sridhar16 on 5/26/2016.
 */
public class Contract {

    public static final class PassgrTravelInfoEntry implements BaseColumns{

        public static final String TABLE_NAME = "passengr_travel_info";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_FROM_CITY = "from_city";
        public static final String COLUMN_TO_CITY = "to_city";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";

    }
}
