package by.tarlikovski.freelance.control.tag;

import javax.servlet.jsp.tagext.TagSupport;

public class Locale extends TagSupport {
    public static String getLocale(final String attribute,
                                   final String cookie) {
        String locale;
        switch (attribute) {
            case "ru_RU":
            case "en_US":
                locale = attribute;
                break;
            default:
                locale = cookie;
                break;
        }
        return locale;
    }
}
