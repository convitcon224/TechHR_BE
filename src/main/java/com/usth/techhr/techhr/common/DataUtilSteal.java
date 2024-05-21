package com.usth.techhr.techhr.common;//package com.ngsc.webservices.ngscrestfulwebservices.common;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import com.google.common.base.CharMatcher;
//import com.unikom.trainingapi.constant.VPSRoles;
//import org.apache.logging.log4j.util.Strings;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.sql.Clob;
//import java.sql.SQLException;
//import java.text.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.function.Supplier;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//public class DataUtilSteal {
//    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
//    public static final String DATE_FORMAT = "dd/MM/yyyy";
//    public static final String DATE_FORMAT_1 = "yyyy-MM-dd";
//    // Email Regex java
//    private static final String EMAIL_REGEX = "^[\\w\\+]+([\\.-]?\\w+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,3})+$";
//
//    // static Pattern object, since pattern is fixed
//    private static Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
//
//    private static final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();
//
//    private static final Logger logger = LoggerFactory.getLogger(DataUtilSteal.class);
//
//    public static boolean nonEmpty(String text) {
//        return !nullOrEmpty(text);
//    }
//
//    public static boolean nonEmpty(Collection collection) {
//        return !nullOrEmpty(collection);
//    }
//
//    public static boolean notNullOrEmpty(String text) {
//        return !nullOrEmpty(text);
//    }
//
//    public static boolean notNullOrEmpty(Collection collection) {
//        return !nullOrEmpty(collection);
//    }
//
//    public static boolean nullOrEmpty(Collection objects) {
//        return objects == null || objects.isEmpty();
//    }
//
//    public static boolean nullOrEmpty(Map<?, ?> map) {
//        return map == null || map.isEmpty();
//    }
//
//    public static boolean notNull(Object object) {
//        return !nullObject(object);
//    }
//
//    public static boolean nullObject(Object object) {
//        return object == null;
//    }
//
//    public static boolean nullOrZero(Long value) {
//        return (value == null || value.equals(0L));
//    }
//
//    public static boolean nullOrZero(String value) {
//        return value == null || "0".equals(value);
//    }
//
//    public static boolean nullOrEmpty(String value) {
//        return value == null || "".equalsIgnoreCase(value);
//    }
//
//    public static boolean nullOrZero(Integer value) {
//        return (value == null || value.equals(0));
//    }
//
//    public static boolean isNullOrZero(Long value) {
//        return (value == null || value.equals(0L));
//    }
//
//    public static boolean equalsObj(Object obj1, Object obj2) {
//        if (obj1 == null || obj2 == null) return false;
//        return obj1.equals(obj2);
//    }
//
//    public static Integer parseToInt(Object value, Integer defaultVal) {
//        try {
//            if (value == null || isNullOrEmpty(parseToString(value))) {
//                return defaultVal;
//            }
//
//            return Integer.parseInt(value.toString());
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return defaultVal;
//        }
//    }
//
//    public static Integer parseToInt(String value) {
//        return parseToInt(value, null);
//    }
//
//    public static Integer parseToInt(Object value) {
//        return parseToInt(parseToString(value), null);
//    }
//
//    public static Long parseToLong(Object value, Long defaultVal) {
//        try {
//            if (value == null) {
//                return defaultVal;
//            }
//            String str = parseToString(value);
//            if (nullOrEmpty(str)) {
//                return defaultVal;
//            }
//            return Long.parseLong(str);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return defaultVal;
//        }
//    }
//
//    public static Date parseToDate(Object value) {
//        if (value == null) {
//            return null;
//        }
//        return (Date) value;
//    }
//
//    public static Double safeToDouble(Object value) {
//        return value == null ? 0D : parseToDouble(value, 0.0);
//    }
//
//    public static Double parseToDouble(Object value) {
//        return parseToDouble(value, null);
//    }
//
//    public static Double parseToDouble(Object value, Double defaultVal) {
//        try {
//            if (value == null) {
//                return defaultVal;
//            }
//            String str = parseToString(value);
//            if (nullOrEmpty(str)) {
//                return defaultVal;
//            }
//            return Double.parseDouble(str);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return defaultVal;
//        }
//    }
//
//    public static Float parseToFloat(Object value) {
//        return parseToFloat(value, null);
//    }
//
//    public static Float parseToFloat(Object value, Float defaultVal) {
//        try {
//            if (value == null) {
//                return defaultVal;
//            }
//            String str = parseToString(value);
//            if (nullOrEmpty(str)) {
//                return defaultVal;
//            }
//            return Float.parseFloat(str);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return defaultVal;
//        }
//    }
//
//    public static Long parseToLong(Object value) {
//        return parseToLong(value, null);
//    }
//
//    public static String parseToString(Object value, String defaultVal) {
//        try {
//            if (value == null) {
//                return defaultVal;
//            }
//            return value.toString();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return defaultVal;
//        }
//    }
//
//    public static String parseToString(Object value) {
//        return parseToString(value, "");
//    }
//
//    public static String parseToStringNull(Object value) {
//        return parseToString(value, null);
//    }
//
//    public static boolean matchByPattern(String value, String regex) {
//        if (nullOrEmpty(regex) || nullOrEmpty(value)) {
//            return false;
//        }
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(value);
//        return matcher.matches();
//    }
//
//    public static void throwIfFailed(boolean test, String message) throws Exception {
//        if (!test) throw new Exception(message);
//    }
//
//    public static <X extends Throwable> void throwIfFailed(boolean test, Supplier<? extends X> exceptionSupplier) throws X {
//        if (!test) throw exceptionSupplier.get();
//    }
//
//    public static <X extends Throwable> void throwIf(boolean test, Supplier<? extends X> exceptionSupplier) throws X {
//        if (test) throw exceptionSupplier.get();
//    }
//
////    public static boolean nullOrEmpty(CharSequence cs) {
////        int strLen;
////        if (cs == null || (strLen = cs.length()) == 0) {
////            return true;
////        }
////        for (int i = 0; i < strLen; i++) {
////            if (!Character.isWhitespace(cs.charAt(i))) {
////                return false;
////            }
////        }
////        return true;
////    }
//
//    public static boolean isNullOrEmpty(CharSequence cs) {
//        return nullOrEmpty(cs);
//    }
//
//    public static boolean isNullOrEmpty(final Collection<?> collection) {
//        return collection == null || collection.isEmpty();
//    }
//
//    public static <T> T deepCloneObject(T source) {
//        try {
//            if (source == null) {
//                return null;
//            }
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ObjectOutputStream out = new ObjectOutputStream(bos);
//            out.writeObject(source);
//            out.flush();
//            out.close();
//
//            ObjectInputStream in = new ObjectInputStream(
//                    new ByteArrayInputStream(bos.toByteArray()));
//            T dto = (T) in.readObject();
//            in.close();
//            return dto;
//        } catch (IOException | ClassNotFoundException e) {
//            logger.error(e.getMessage(), e);
//            return null;
//        }
//    }
//
//    public static <T> T cloneObject(T source) {
//        if (source == null) {
//            return null;
//        } else {
//            Class clzz = source.getClass();
//            Object target = null;
//
//            try {
//                target = clzz.newInstance();
//            } catch (InstantiationException var4) {
//            } catch (IllegalAccessException var5) {
//            }
//
//            BeanUtils.copyProperties(source, target);
//            return (T) target;
//        }
//    }
//
//    private static boolean safeEqualString(String str1, String str2, boolean ignoreCase, boolean trimspace) {
//        if (str1 == null || str2 == null) {
//            return false;
//        }
//
//        if (trimspace) {
//            str1 = str1.trim();
//            str2 = str2.trim();
//        }
//
//        if (ignoreCase) {
//            return str1.equalsIgnoreCase(str2);
//        } else {
//            return str1.equals(str2);
//        }
//    }
//
//    public static boolean safeEqualIgnoreCaseString(String str1, String str2) {
//        return safeEqualString(str1, str2, true, true);
//    }
//
//    public static boolean safeEqualString(String str1, String str2) {
//        return safeEqualString(str1, str2, false, true);
//    }
//
//    public static boolean safeEqualIgnoreCaseWithoutTrimSpaceString(String str1, String str2) {
//        return safeEqualString(str1, str2, true, false);
//    }
//
//    public static boolean safeEqualWithoutTrimSpaceString(String str1, String str2) {
//        return safeEqualString(str1, str2, false, false);
//    }
//
//
//    public static List<String> split(String separate, String object) {
//        return Optional.ofNullable(object)
//                .map(x -> x.split(separate))
//                .map(Arrays::asList)
//                .orElseGet(ArrayList::new);
//    }
//
//    public static String firstNonEmpty(String... strings) {
//        for (String string : strings) {
//            if (!isNullOrEmpty(string)) {
//                return string;
//            }
//        }
//        return "";
//    }
//
//    public static String concatString(String specialCharacters, String... strings) {
//        return String.join(specialCharacters, strings);
//    }
//
//    public static <T> T defaultIfNull(final T object, final T defaultValue) {
//        return object != null ? object : defaultValue;
//    }
//
//    /**
//     * Tra ve doi tuong default neu object la null, neu khong thi tra object
//     *
//     * @param object
//     * @param defaultValueSupplier
//     * @param <T>
//     * @return
//     */
//    public static <T> T defaultIfNull(final T object, final Supplier<T> defaultValueSupplier) {
//        return object != null ? object : defaultValueSupplier.get();
//    }
//
//    public static boolean safeEqual(Object obj1, Object obj2) {
//        return ((obj1 != null) && (obj2 != null) && obj2.toString().equals(obj1.toString()));
//    }
//
//
//    public static Character safeToCharacter(Object value) {
//        return safeToCharacter(value, '0');
//    }
//
//    public static Character safeToCharacter(Object value, Character defaulValue) {
//        if (value == null) return defaulValue;
//        return String.valueOf(value).charAt(0);
//    }
//
//    public static String removeStartingZeroes(String number) {
//        if (DataUtilSteal.nullOrEmpty(number)) {
//            return "";
//        }
//        return CharMatcher.anyOf("0").trimLeadingFrom(number);
//    }
//
//    public static String plusZeroByLength(int length) {
//        String strZero = "";
//        for (int i = 0; i < length; i++) {
//            strZero += "0";
//        }
//        return strZero;
//    }
//
//
//    /**
//     * NamLX : Convert tien thanh chuoi string
//     *
//     * @param
//     * @return
//     */
//
//    public static String toUpperCharacters(String inputString, int startIndex, int numberOfCharacter) {
//        if (startIndex < 0) {
//            startIndex = 0;
//        }
//        int endIndex = startIndex + numberOfCharacter;
//        if (endIndex > inputString.length()) {
//            endIndex = inputString.length() - 1;
//        }
//        String returnString = "";
//        String toUpperString = inputString.substring(startIndex, endIndex);
//        toUpperString = toUpperString.toUpperCase();
//        returnString = inputString.substring(0, startIndex) + toUpperString + inputString.substring(endIndex);
//        return returnString;
//    }
//
//
//    public static long parseStringToLong(String strValue) {
//        long result = 0;
//        try {
//            NumberFormat numberFormat = NumberFormat.getInstance();
//            result = numberFormat.parse(strValue.trim()).longValue();
//        } catch (ParseException e) {
//            logger.error(e.getMessage(), e);
//            throw new BusinessException(e.getMessage());
//        }
//        return result;
//    }
//
//    public static String readFromInputStream(InputStream inputStream)
//            throws IOException {
//        StringBuilder resultStringBuilder = new StringBuilder();
//        try (BufferedReader br
//                     = new BufferedReader(new InputStreamReader(inputStream))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                resultStringBuilder.append(line).append("\n");
//            }
//        }
//        return resultStringBuilder.toString();
//    }
//
//    public static boolean isNull(Long value) {
//        return (value == null);
//    }
//
//    public static boolean isNull(Double value) {
//        return (value == null);
//    }
//
//    public static boolean isNull(Integer value) {
//        return (value == null);
//    }
//
//    public static String stringToBool(String value) {
//        String boolString = null;
//        if ("true".equalsIgnoreCase(value) || ("1").equalsIgnoreCase(value)) {
//            boolString = "1";
//        } else if ("false".equalsIgnoreCase(value) || ("0").equalsIgnoreCase(value)) {
//            boolString = "0";
//        }
//        return boolString;
//    }
//
//    public static String getReceiptNo(String actionCode) {
//        if (notNull(actionCode) && actionCode.contains("_")) {
//            int length = actionCode.length();
//            int start = actionCode.lastIndexOf("_");
//            int end = start + 6;
//            end = (end > length) ? length : end;
//            return actionCode.substring(start, end);
//        }
//        return "";
//    }
//
//
//    //==========================================
//
//
////    public static boolean notNullOrEmpty(String text) {
////        return !nullOrEmpty(text);
////    }
//
//    public static boolean nullOrEmpty(CharSequence cs) {
//        int strLen;
//        if (cs == null || (strLen = cs.length()) == 0) {
//            return true;
//        }
//        for (int i = 0; i < strLen; i++) {
//            if (!Character.isWhitespace(cs.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static <T> List<T> jsonToList(String json, Class<T> classOutput) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        TypeFactory typeFactory = objectMapper.getTypeFactory();
//        return objectMapper.readValue(json, typeFactory.constructCollectionType(List.class, classOutput));
//    }
//
//    public static String objectToJson(Object object) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        if (object == null) {
//            return null;
//        }
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        return mapper.writeValueAsString(object);
//    }
//
//    public static <T> T jsonToObject(String json, Class<T> classOutput) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        if (json == null || json.isEmpty()) {
//            return null;
//        }
//        return mapper.readValue(json, classOutput);
//    }
//
//    public static LocalDateTime convertStringToLocalDateTime(String value) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
//        LocalDateTime formatDateTime = LocalDateTime.parse(value, formatter);
//        return formatDateTime;
//    }
//
//    public static LocalDate convertStringToLocalDate(String value) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_1);
//        LocalDate formatDate = LocalDate.parse(value, formatter);
//        return formatDate;
//    }
//
//    public static String localDatetoString(LocalDate localDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_1);
//        return localDate.format(formatter);
//    }
//
//    public static String localDateTimeToString(LocalDateTime value) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
//        return value.format(formatter);
//    }
//
//    public static Date localDatetoDate(LocalDate localDate) {
//        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//    }
//
//    public static LocalDate toLocalDate(Date input) {
//        return input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//    }
//
//
//    public static LocalDateTime toLocalDateTime(Date input) {
//        if (input == null) return null;
//        try {
//            return input.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
//            return null;
//        }
//    }
//
//    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
//        return dateToConvert.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//    }
//
//    public static Double isZeroIfNull(Double number) {
//        if (number == null) {
//            return 0D;
//        } else {
//            return number;
//        }
//    }
//
//
//    public static void fillValueToCell(Row row, int cellIdx, CellStyle cellStyle, String value) {
//        Cell cell = row.createCell(cellIdx);
//        cell.setCellValue(value);
//        cell.setCellStyle(cellStyle);
//    }
//
//    public static void fillValueToCell(Row row, int cellIdx, CellStyle cellStyle, Long value) {
//        Cell cell = row.createCell(cellIdx);
//        cell.setCellValue(value);
//        cell.setCellStyle(cellStyle);
//    }
//
//    public static void fillValueToCell(Row row, int cellIdx, CellStyle cellStyle, Double value) {
//        Cell cell = row.createCell(cellIdx);
//        cell.setCellValue(value);
//        cell.setCellStyle(cellStyle);
//    }
//
//    public static void fillValueToCell(Row row, int cellIdx, CellStyle cellStyle, Integer value) {
//        Cell cell = row.createCell(cellIdx);
//        cell.setCellValue(value);
//        cell.setCellStyle(cellStyle);
//    }
//
//    public static String trim(String val) {
//        if (!isNullOrEmpty(val)) {
//            return val.trim();
//        }
//
//        return val;
//    }
//
//    public static boolean isNumeric(String data) {
//        boolean numeric = true;
//
//        try {
//            Double.parseDouble(data.trim());
//        } catch (NumberFormatException e) {
//            numeric = false;
//        }
//
//        return numeric;
//    }
//
//    public static ByteArrayResource wbToByte(Workbook wb) throws IOException {
//        if (wb == null) {
//            return null;
//        }
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        try {
//            wb.write(outputStream);
//        } finally {
//            outputStream.close();
//        }
//        byte[] reasultByte = outputStream.toByteArray();
//        ByteArrayResource temp = new ByteArrayResource(reasultByte);
//        return temp;
//    }
//
//
//    public static boolean validatePreImportExcel(MultipartFile file, String[] extension) {
//        if (extension == null || extension.length <= 0) {
//            return true;
//        }
//
//        String fileName = file.getOriginalFilename();
//
//        for (String ext : extension) {
//            if (DataUtilSteal.isNullOrEmpty(ext)) {
//                continue;
//            }
//            if (fileName.toLowerCase().endsWith(ext.toLowerCase())) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    public static String lPad(String inputString, int length) {
//        return String.format("%1$" + length + "s", inputString).replace(' ', '0');
//    }
//
//    public static String rPad(String inputString, int length) {
//        return String.format("%1$-" + length + "s", inputString).replace(' ', '0');
//    }
//
//    public static Double round(Double value, int places) {
//        try {
//            if (value == null) {
//                return value;
//            }
//            if (places < 0) throw new IllegalArgumentException();
//
//            BigDecimal bd = new BigDecimal(Double.toString(value));
//            bd = bd.setScale(places, RoundingMode.HALF_UP);
//            return bd.doubleValue();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return value;
//        }
//    }
//
//    public static HttpStatus getHttpStatus(String code) {
//        HttpStatus status;
//        switch (code) {
//            case "200":
//                status = HttpStatus.OK;
//                break;
//            case "201":
//                status = HttpStatus.CREATED;
//                break;
//            case "400":
//                status = HttpStatus.BAD_REQUEST;
//                break;
//            case "403":
//                status = HttpStatus.FORBIDDEN;
//                break;
//            case "404":
//                status = HttpStatus.NOT_FOUND;
//                break;
//            case "409":
//                status = HttpStatus.CONFLICT;
//                break;
//            case "500":
//                status = HttpStatus.INTERNAL_SERVER_ERROR;
//                break;
//            default:
//                status = HttpStatus.INTERNAL_SERVER_ERROR;
//                break;
//        }
//
//        return status;
//    }
//
//    public static double nvl(Double value, double defaultVal) {
//        if (value == null) {
//            return defaultVal;
//        }
//
//        return value;
//    }
//
//    public static double nvl(Long value, long defaultVal) {
//        if (value == null) {
//            return defaultVal;
//        }
//
//        return value;
//    }
//
//    public static boolean isValidEmail(String email) {
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
//                "[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//                "A-Z]{2,7}$";
//
//        Pattern pat = Pattern.compile(emailRegex);
//        if (email == null)
//            return false;
//        return pat.matcher(email).matches();
//    }
//
//
//    public static HttpHeaders buildPdfHeader(String fileName) throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        return headers;
//    }
//
//    public static String formatNumberAmount(Double number) {
//        String amountFormat = "0";
//        String pattern = "###,###.####";
//        DecimalFormat decimalFormat = new DecimalFormat(pattern);
//        if (number != null) {
//            amountFormat = decimalFormat.format(number);
//        }
//        return amountFormat;
//    }
//
//    /**
//     * @param object
//     * @return
//     * @author noipd
//     * @since 2020-11-12
//     */
//    public static boolean checkTypeInteger(String object) {
//        try {
//            Integer.parseInt(object);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * @param obj1
//     * @return
//     * @author noipd
//     * @since 2021-02-28
//     */
//    public static BigDecimal safeToBigDecimal(Object obj1) {
//        BigDecimal result = BigDecimal.ZERO;
//        if (obj1 == null) {
//            return result;
//        }
//        try {
//            result = new BigDecimal(obj1.toString());
//        } catch (Exception ignored) {
//            logger.error(ignored.getMessage());
//        }
//
//        return result;
//    }
//
//    public static String strimString(String input) {
//        if (input == null) return "";
//        if (Strings.isNotEmpty(input)) {
//            return input.trim();
//        }
//        return input;
//    }
//
//    public static Date convertStringToDate(String dateStr, String pattern) {
//        Date date;
//        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
//        try {
//            date = dateFormat.parse(dateStr);
//        } catch (ParseException e) {
//            return null;
//        }
//        return date;
//    }
//
//    public static boolean isEmail(String email) {
//
//        if (email == null) {
//            return false;
//        }
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    public static boolean isPhoneNumber(String phoneNumber) {
//        return phoneNumber.matches("^(84|0)([0-9]{9,14})");
//    }
//
//    public static Integer convertCustomerTypeToInt(String customerType) {
//        switch (customerType) {
//            case "B2B":
//                return 0;
//            default:
//                return 1;
//        }
//    }
//
//    public static Integer convertBusinessToInt(String IndustryType) {
//        switch (IndustryType) {
//            case "ME":
//                return 1;
//            case "SS":
//                return 2;
//            case "ICT":
//                return 3;
//            case "NLMT":
//                return 4;
//            case "CNTT":
//                return 5;
//            case "Home Service":
//                return 6;
//            case "Solar Service":
//                return 7;
//            case "IT Service":
//                return 8;
//            case "Tower/FiberCare":
//                return 9;
//            case "XDDD B2C":
//                return 10;
//            case "XDDD B2B":
//                return 11;
//            case "Viễn thông":
//                return 12;
//            case "BTS":
//                return 13;
//            case "Cáp quang":
//                return 14;
//            case "DAS - CĐBR":
//                return 15;
//            case "Cống bể":
//                return 16;
//            case "Điện mặt trời":
//                return 17;
//            case "Nhà trạm cho thuê":
//                return 18;
//            default:
//                return null;
//        }
//    }
//
//    public static Integer convertProductionTypeToInt(String productionType) {
//        switch (productionType) {
//            case "Điều hòa":
//                return 1;
//            case "Máy giặt":
//                return 2;
//            case "Máy lọc nước":
//                return 3;
//            case "Bình nước nóng":
//                return 4;
//            case "Tivi":
//                return 5;
//            case "Tủ lạnh":
//                return 6;
//            case "Ghế massage":
//                return 7;
//            case "Robot hút bụi":
//                return 8;
//            case "Khóa":
//                return 9;
//            case "Đèn năng lượng mặt trời":
//                return 10;
//            case "Đèn chiếu sáng":
//                return 11;
//            case "Chuông cửa":
//                return 12;
//            case "Công tắc cảm ứng":
//                return 13;
//            case "NLMT":
//                return 14;
//            case "Bảo dưỡng":
//                return 15;
//            case "Sửa chữa":
//                return 16;
//            case "Trọn gói":
//                return 17;
//            case "Khác":
//                return 0;
//            default:
//                return null;
//        }
//    }
//
//    public static Integer convertLeadSourceToInt(String source) {
//        if (source == null) return 14;
//        switch (source) {
//            case "Hotline":
//                return 1;
//            case "Website":
//                return 2;
//            case "Facebook":
//                return 3;
//            case "Zalo":
//                return 4;
//            case "Email":
//                return 5;
//            case "Trực tiếp":
//                return 6;
//            case "Sự kiện":
//                return 7;
//            case "Sms":
//                return 8;
//            case "Phát thanh":
//                return 9;
//            case "Truyền hình":
//                return 10;
//            case "Google":
//                return 11;
//            case "Tài liệu bán hàng":
//                return 12;
//            case "Khách hàng là nhân viên":
//                return 13;
//            case "Khác":
//                return 14;
//            default:
//                return null;
//        }
//    }
//
//    public static String convertLeadValueToSource(Integer source) {
//        if (source == null) return "Khác";
//        switch (source) {
//            case 1:
//                return "Hotline";
//            case 2:
//                return "Website";
//            case 3:
//                return "Facebook";
//            case 4:
//                return "Zalo";
//            case 5:
//                return "Email";
//            case 6:
//                return "Trực tiếp";
//            case 7:
//                return "Sự kiện";
//            case 8:
//                return "SMS";
//            case 9:
//                return "Phát thanh";
//            case 10:
//                return "Truyền hình";
//            case 11:
//                return "Google";
//            case 12:
//                return "Tài liệu bán hàng";
//            case 13:
//                return "Khách hàng là nhân viên";
//            case 14:
//                return "Khác";
//            default:
//                return "";
//        }
//    }
//
//    public static String convertLeadPotentialStatus(Integer status) {
//        switch (status) {
//            case 1:
//                return "Chưa phân bổ";
//            case 2:
//                return "Tiếp nhận";
//            case 3:
//                return "Sai số";
//            case 4:
//                return "Không nghe máy";
//            case 5:
//                return "Không có nhu cầu";
//            case 6:
//                return "Chưa có nhu cầu";
//            case 7:
//                return "Có nhu cầu";
//            default:
//                return "";
//        }
//    }
//
//
//    public static String callApiPost(RestTemplate restTemplate, String url, HttpEntity<String> request) throws Exception {
//        logger.info("------body-------"+request.getBody());
//        ResponseEntity<String> personResultAsJsonStr = restTemplate.postForEntity(url, request, String.class);
//        logger.info("-------------"+personResultAsJsonStr.getBody());
//        JsonNode root = objectMapper.convertValue(personResultAsJsonStr.getBody(), JsonNode.class);
//        JsonNode jsonRoot = objectMapper.readTree(root.asText());
//        return jsonRoot.toString();
//    }
//
//    public static String convertDateToString(java.util.Date date, String format) {
//        String strDate;
//        if (date == null) {
//            return "";
//        }
//        strDate = new SimpleDateFormat(format).format(date);
//        return strDate;
//    }
//
//    public static String convertApproachMethodToString(Integer value) {
//        switch (value) {
//            case 1:
//                return "voso";
//            case 2:
//                return "app";
//            case 3:
//                return "viettelaio.com";
//            case 4:
//                return "Điện thoại";
//            case 5:
//                return "Facebook";
//            case 6:
//                return "Google";
//            case 7:
//                return "vtracking";
//            default:
//                return "";
//        }
//    }
//
//    public static int parseStringToInt(String strValue) {
//        int result = 0;
//        try {
//            NumberFormat numberFormat = NumberFormat.getInstance();
//            result = numberFormat.parse(strValue.trim()).intValue();
//        } catch (ParseException e) {
//            logger.error(e.getMessage(), e);
//        }
//        return result;
//    }
//
//    public static String convertTicketStatusToString(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(1, "Chưa thực hiện");
//        objMap.put(2, "Đang tiến hành");
//        objMap.put(3, "Hoàn thành");
//        objMap.put(4, "Đóng");
//        objMap.put(5, "Theo dõi");
//        return objMap.get(value);
//    }
//
//    public static String convertOverallReasonToString(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(0, "Khác");
//        objMap.put(1, "Chất lượng sản phẩm");
//        objMap.put(2, "Khách hàng");
//        objMap.put(3, "Sản phẩm lỗi");
//        objMap.put(4, "Nhân viên Viettel");
//        objMap.put(5, "Giám sát không đảm bảo chuyên môn");
//        objMap.put(6, "Chất lượng nhà thầu phụ kém");
//        objMap.put(7, "Ý thức nhân sự thực thi kém");
//        objMap.put(8, "Nhân sự không được đào tạo");
//        objMap.put(9, "BGĐ của chi nhánh không chỉ đạo sâu sát");
//        objMap.put(10, "Các phòng ban không phối hợp");
//        objMap.put(11, "Sự cố bị tạo trùng");
//        objMap.put(12, "Điều phối sai luồng xử lý");
//        objMap.put(13, "Quy trình giám sát đang có lỗ hổng");
//        objMap.put(14, "Quy trình chồng chéo, không thể thực thi");
//        objMap.put(15, "Chi nhánh đang xử lý nhưng chưa xong");
//        objMap.put(16, "Khách hàng chưa sắp xếp thời gian");
//        objMap.put(17, "Lý do khác");
//
//        return objMap.get(value);
//    }
//
//    public static String convertHappyCallCallStatusToString(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(1, "Nghe máy");
//        objMap.put(2, "Không nghe máy");
//        objMap.put(3, "Chuyển bảo hành");
//        objMap.put(4, "Không HappyCall");
//        return objMap.get(value);
//    }
//
//    public static String convertHappyCallCallResultToString(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(1, "Hài lòng");
//        objMap.put(2, "Không hài lòng");
//        objMap.put(3, "Bình thường");
//        return objMap.get(value);
//    }
//
//    public static String convertHappyCallCauseOverallToString(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(0, "Khác");
//        objMap.put(1, "Thiết bị");
//        objMap.put(2, "Nghiệp vụ nhân viên");
//        objMap.put(3, "Khách hàng");
//        objMap.put(4, "Quy trình - Chính sách");
//        objMap.put(5, "Hệ thống");
//        return objMap.get(value);
//    }
//
//    public static String convertExplanationStatusToString(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(0, "");
//        return objMap.get(value);
//    }
//
//    public static String convertTicketProgressAndClosingStatusToString(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(1, "Trong hạn");
//        objMap.put(2, "Quá hạn");
//        objMap.put(3, "Sắp quá hạn");
//        return objMap.get(value);
//    }
//
//    public static String convertResponsibleDepartmentToString(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(0, "Khác");
//        objMap.put(1, "CNKT");
//        objMap.put(2, "NVKT");
//        objMap.put(3, "TT CNTT");
//        objMap.put(4, "P.CSKH");
//        objMap.put(5, "TT Kinh Doanh");
//        objMap.put(6, "Phòng ban khác");
//        return objMap.get(value);
//    }
//
//    public static String convertCostConfirmationToString(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(0, "Tính phí");
//        objMap.put(1, "Không tính phí");
//        return objMap.get(value);
//    }
//
//    public static String callCenterCallReasonToChannel(Integer value) {
//        Map<Integer, String> objMap = new HashMap<Integer, String>();
//        objMap.put(10,"facebook messenger");
//        objMap.put(12,"zalo");
//        objMap.put(13,"Email");
//        objMap.put(9,"facebook comment");
//        objMap.put(6,"Voice mail");
//        objMap.put(5,"live chat");
//        return objMap.get(value);
//    }
//
//    public static Double truncateDoubleSetPrecision(Double value, Integer scale) {
//        return BigDecimal.valueOf(value)
//                .setScale(scale, RoundingMode.HALF_UP)
//                .doubleValue();
//    }
//
//
//    public static String clobToString(Clob clob) throws SQLException {
//        if (Objects.isNull(clob)) {
//            return "";
//        }
//        return clob.getSubString(1, (int) clob.length());
//    }
//
//    public static String splitCode(String data){
//        String result = "";
//        List<String> list = Arrays.asList(data.split("-")).stream().map(String::toString).collect(Collectors.toList());
//        if (list.size() > 0){
//            result = list.get(0).trim();
//        }
//        return result;
//    }
//
//    public static String encrypt(Object value) {
//        try {
//            if (value == null) {
//                return "";
//            }
//            return VPSRoles.ENCRYPT;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return "";
//        }
//    }
//
//    public static String stripAccents(String s) {
//        s = Normalizer.normalize(s, Normalizer.Form.NFD);
//        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
//        s = s.replaceAll("Đ", "D");
//        s = s.replaceAll("đ", "d");
//        return s;
//    }
//
//
//    public static String formatCurrency(Double value){
//        if (Objects.nonNull(value)){
//            NumberFormat formatter = NumberFormat.getCurrencyInstance();
//            String moneyString = formatter.format(value);
//            if (moneyString.endsWith(".00")) {
//                int centsIndex = moneyString.lastIndexOf(".00");
//                if (centsIndex != -1) {
//                    moneyString = moneyString.substring(1, centsIndex);
//                }
//            }
//
//            return moneyString;
//        }
//        return "";
//    }
//
//    public static String decimalFormatCurrency(Double value){
//        if (Objects.nonNull(value)){
//            String pattern = "###,###";
//            DecimalFormat myFormatter = new DecimalFormat(pattern);
//            String output = myFormatter.format(value);
//            return output;
//        }
//        return "";
//    }
//
//    public static String getExtensionFile(String fileName, String typeFile){
//        String extension = "";
//        if (fileName.contains(".")){
//            extension = fileName.substring(fileName.lastIndexOf(".")).replace(".", "");
//        }
//
//        fileName = fileName.toLowerCase();
//        if (fileName.contains(".jpg") || fileName.contains(".gif") || fileName.contains(".jpeg")
//                || fileName.contains(".png") || fileName.contains(".bmp") || fileName.contains(".tiff")
//                || fileName.contains(".apng") || fileName.contains(".avif") || fileName.contains(".pjpeg")
//                || fileName.contains(".svg") || fileName.contains(".jfif") || fileName.contains(".heic")){
//            typeFile = "image/"+extension;
//        } else if (fileName.contains(".mp4") || fileName.contains(".mov")
//                || fileName.contains(".webm") || fileName.contains(".ogg")){
//            typeFile = "video/"+extension;
//        }
//        return typeFile;
//    }
//
//    public static File replaceFileIsExists(String path, String fileName){
//        String pathFile = path+"/"+fileName;
//        File file = new File(pathFile);
//        if (file.exists()){
//            int iDot = file.getAbsolutePath().lastIndexOf(".");
//            file = new File(UUID.randomUUID()+file.getAbsolutePath().substring(iDot));
//        }
//        return file;
//    }
//
//    public static String getValueDate(){
//        LocalDateTime now = LocalDateTime.now();
//        int year = now.getYear();
//        int month = now.getMonthValue();
//        int day = now.getDayOfMonth();
//        int hour = now.getHour();
//        int minute = now.getMinute();
//        int second = now.getSecond();
//        String time = year+""+String.format("%02d", month)+""+String.format("%02d", day)+""+String.format("%02d", hour)+""+String.format("%02d", minute)+""+String.format("%02d", second);
//        return time;
//    }
//
//    public static String resolveKeySearch(String keyword) {
//        if (Objects.isNull(keyword)) {
//            return null;
//        }
//
//        if (keyword.trim().length() == 0) {
//            return null;
//        }
//        return "%" + keyword
//                .replaceAll("&", "&&")
//                .replaceAll("%", "&%")
//                .replaceAll("_", "&_")
//                .trim() + "%";
//    }
//
//    public static String resolveKeySearch(Object keyword) {
//        if (Objects.isNull(keyword)) return null;
//        return DataUtilSteal.resolveKeySearch(keyword.toString());
//    }
//
//    public static String validateKeySearch(String str) {
//        return str.replaceAll("&", "&&").replaceAll("%", "&%").replaceAll("_", "&_");
//    }
//
//    public static String getSafeFileName(String fileName) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < fileName.length(); i++) {
//            char c = fileName.charAt(i);
//            if (c != '/' && c != '\\' && c != 0) {
//                sb.append(c);
//            }
//        }
//        return sb.toString();
//    }
//
//
//
//
//}
//
//
