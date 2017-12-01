package am.main.common.validation;

/**
 * Created by ahmed.motair on 11/15/2017.
 */
public class RegExp {
    public static final String REAL_NAME = "^[A-Za-z\\-\\.',]+$";
    public static final String CONTENT_NAME = "^[A-Za-z0-9\\-\\.', ]+$";
    public static final String LOOKUP_CHAR = "^[A-Za-z]+$";
    public static final String USERNAME = "^[A-Za-z][A-Za-z0-9\\.\\-_]+$";
    public static final String PASSWORD = "^[A-Za-z0-9\\.\\-_@]+$";
    public static final String POSITIVE_NUM = "^[0-9]+$";
    public static final String EMAIL = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final String URL = "^((http[s]?|ftp):\\/)?\\/?([^:\\/\\s]+)((\\/\\w+)*\\/)([\\w\\-\\.]+[^#?\\s]+)(.*)?(#[\\w\\-]+)?$";
    public static final String YOUTUBE_VIDEO = "^(https?\\:\\/\\/)?(www\\.youtube\\.com|youtu\\.?be)\\/.+$";
    public static final String YOUTUBE_PLAYLIST = "^http(s)?:\\/\\/(?:www\\.)?youtube\\.com\\/(playlist\\?|watch\\?v=[a-zA-Z0-9_\\-]+&)list=[a-zA-Z0-9_\\-]+$";
}
