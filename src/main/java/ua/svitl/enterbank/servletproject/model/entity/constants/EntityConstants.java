package ua.svitl.enterbank.servletproject.model.entity.constants;

public class EntityConstants {
    private EntityConstants() { }

    public static final String FORMAT_DATE_TIME = "dd-MM-yyyy HH:mm:ss";

    public static final int BANK_MFO = 320_130;

    // defaults for entity entries
    public static final String DEFAULT_BANK_ACCOUNT_NUMBER = "26320130000000";
    public static final String DEFAULT_CREDIT_CARD_NAME = "MasterCard";
    public static final String DEFAULT_BANK_ACCOUNT_TYPE = "Regular";
    public static final String DEFAULT_CURRENCY = "₴"; // U+20B4
    public static final String DEFAULT_COUNTRY_CODE = "+380";
}
