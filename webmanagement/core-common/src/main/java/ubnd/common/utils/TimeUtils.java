package ubnd.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
    /**
     * Get current date time format timestamp
     *
     * @return Timestamp
     */
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Convert timeMillis to Date
     *
     * @param timeMillis String timeMillis
     * @return Date
     */
    static Date convertTime(String timeMillis) {
        long currentDateTime = Long.parseLong(timeMillis);
        return new Date(currentDateTime);
    }

    /**
     * Convert the epoch time to TimeStamp
     *
     * @param timestampInString timestamp as string
     * @return date as timestamp
     */
    public static Timestamp getTimestamp(String timestampInString) {
        Date date = new Date(Long.parseLong(timestampInString));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        return Timestamp.valueOf(formatted);
    }

    public static Timestamp getTimestamps(String timestampInString) {
        Date date = new Date(Long.parseLong(timestampInString));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        return Timestamp.valueOf(formatted);
    }



    public static String convertStringDateToTimeMillis(String s){
        Date date = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            date = simpleDateFormat.parse(s);

        } catch (ParseException e) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            try {
                date = simpleDateFormat.parse(s);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        }
        return String.valueOf(date.getTime());
    }
}
