package am.main.common.validation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmed.motair on 11/15/2017.
 */
public class RegExp {
    public static final String REAL_NAME = "^[A-Za-z\\-\\.',]+$";
    public static final String CONTENT_NAME = "^[A-Za-z0-9\\-\\.', ]+$";
    public static final String LOOKUP = "^[A-Za-z]+$";
    public static final String USERNAME = "^[A-Za-z][A-Za-z0-9\\.\\-_]+$";
    public static final String PASSWORD = "^[A-Za-z0-9\\.\\-_@]+$";
    public static final String EMAIL = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final String URL = "^((http[s]?|ftp):\\/)?\\/?([^:\\/\\s]+)((\\/\\w+)*\\/)([\\w\\-\\.]+[^#?\\s]+)(.*)?(#[\\w\\-]+)?$";
    public static final String YOUTUBE_VIDEO = "^(https?\\:\\/\\/)?(www\\.youtube\\.com|youtu\\.?be)\\/.+$";
    public static final String YOUTUBE_PLAYLIST = "^http(s)?:\\/\\/(?:www\\.)?youtube\\.com\\/(playlist\\?|watch\\?v=[a-zA-Z0-9_\\-]+&)list=[a-zA-Z0-9_\\-]+$";

    public static final String ORDER_BY = "^(Asc|Desc)$";

    public static final Map<String, String> MESSAGES = Collections.unmodifiableMap(
       new HashMap<String, String>(){{
           put(REAL_NAME, "chars, hyphen, comma, period, and Apostrophe");
           put(CONTENT_NAME, "chars, numbers, hyphen, comma, period, space and Apostrophe");
           put(LOOKUP, "chars");
           put(USERNAME, "chars, numbers, period, hyphen, and Underscore");
           put(PASSWORD, "chars, numbers, period, hyphen, Ampersand, and Underscore");
           put(EMAIL, "valid Emails");
           put(URL, "valid URLs");
           put(ORDER_BY, "Asc or Desc");
           put(YOUTUBE_VIDEO, "valid YouTube URLs");
           put(YOUTUBE_PLAYLIST, "valid YouTube List URLs");
       }}
    );
}
