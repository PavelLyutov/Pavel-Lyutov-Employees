package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StringToDateUtil {
    private static final String[] formats = {
            "yyyy-MM-dd","yyyy:MM:dd","yyyyMMdd","dd/MM/yyyy","dd-MMM-yyyy" };
    private List<SimpleDateFormat> sdfList;

    public StringToDateUtil() throws Exception {
        sdfList=new ArrayList<>();
        for (int i = 0; i < formats.length; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat(formats[i]);
            sdfList.add(sdf);
        }
    }

    public Date stringToDate(String str) throws Exception {
        if (str == null)
            return null;
        Date date = null;
        // parse the input String with list of SimpleDateFormats we have
        for (SimpleDateFormat sdf : sdfList) {
            try {
                date = sdf.parse(str);
            } catch (ParseException pe) {
                // do nothing, we need to try other format
            }
            // check if parsed successfully?
            if (date != null)
                break;
        }
        // return date if parsed successfully or else throw exception
        return  date;

    }
}
