package com.usth.techhr.techhr.common;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

public class DataUtil {
    public static boolean nullOrEmpty(String value) {
        return value == null || "".equalsIgnoreCase(value);
    }

    public static boolean nullOrEmpty(Collection objects) {
        return objects == null || objects.isEmpty();
    }

    public static boolean nullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean nullOrEmpty(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(CharSequence cs) {
        return nullOrEmpty(cs);
    }

    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(Long value) {
        return value == null;
    }

    public static Integer parseToInt(Object value, Integer defaultVal) {
        try {
            if (value == null || isNullOrEmpty(parseToString(value))) {
                return defaultVal;
            }

            return Integer.parseInt(value.toString());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Integer parseToInt(String value) {
        return parseToInt(value, null);
    }

    public static Integer parseToInt(Object value) {
        return parseToInt(parseToString(value), null);
    }

    public static String parseToString(Object value, String defaultVal) {
        try {
            if (value == null) {
                return defaultVal;
            }
            return value.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String parseToString(Object value) {
        return parseToString(value, "");
    }

    public static String convertDateToString(java.util.Date date, String format) {
        String strDate;
        if (date == null) {
            return "";
        }
        strDate = new SimpleDateFormat(format).format(date);
        return strDate;
    }

    public static Long parseToLong(Object value, Long defaultVal) {
        try {
            if (value == null) {
                return defaultVal;
            }
            String str = parseToString(value);
            if (nullOrEmpty(str)) {
                return defaultVal;
            }
            return Long.parseLong(str);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Long parseToLong(Object value) {
        return parseToLong(value, null);
    }
}
