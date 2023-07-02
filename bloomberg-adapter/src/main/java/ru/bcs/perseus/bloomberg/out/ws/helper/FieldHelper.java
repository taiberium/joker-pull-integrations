package ru.bcs.perseus.bloomberg.out.ws.helper;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import ru.bcs.perseus.bloomberg.model.MarketSector;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import static ru.bcs.perseus.bloomberg.model.Constant.InstrumentFields.MARKET_SECTOR_DES;

@Slf4j
public class FieldHelper {

    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private FieldHelper() {
    }

    /**
     * @should return correct result
     * @should log when exception was thrown
     */
    public static LocalDate getLocalDateFieldValue(Map<String, String> fieldValues,
                                                   String fieldName) {
        String value = fieldValues.get(fieldName);
        if (isEmptyValue(value)) {
            return null;
        }

        try {
            return LocalDate.parse(value, DATE_FORMATTER);
        } catch (DateTimeParseException dtpe) {
            log.warn("Unable to parse value \"{}\" to LocalDate", value);
        }

        return null;
    }

    /**
     * @should return correct result
     * @should log when exception was thrown
     */
    public static BigDecimal getBigDecimalFieldValue(Map<String, String> fieldValues,
                                                     String fieldName) {
        String value = fieldValues.get(fieldName);
        if (isEmptyValue(value)) {
            return null;
        }

        try {
            return new BigDecimal(value);
        } catch (NumberFormatException nfe) {
            log.warn("Unable to parse value \"{}\" to BigDecimal", value);
        }

        return null;
    }

    /**
     * @should return correct result
     * @should log when exception was thrown
     */
    public static BigDecimal getBigDecimalValue(String value) {
        if (isEmptyValue(value)) {
            return null;
        }

        try {
            return new BigDecimal(value);
        } catch (NumberFormatException nfe) {
            log.warn("Unable to parse value \"{}\" to BigDecimal", value);
        }

        return null;
    }

    /**
     * @should return correct result
     * @should log when exception was thrown
     */
    public static Long getLongFieldValue(Map<String, String> fieldValues, String fieldName) {
        String value = fieldValues.get(fieldName);
        if (isEmptyValue(value)) {
            return null;
        }

        try {
            return new BigDecimal(value).longValue();
        } catch (NumberFormatException nfe) {
            log.warn("Unable to parse value \"{}\" to Long", value);
        }

        return null;
    }

    /**
     * @should return correct result
     * @should log when exception was thrown
     */
    public static Integer getIntFieldValue(Map<String, String> fieldValues, String fieldName) {
        String value = fieldValues.get(fieldName);
        if (isEmptyValue(value)) {
            return null;
        }

        try {
            return new BigDecimal(value).intValue();
        } catch (NumberFormatException nfe) {
            log.warn("Unable to parse value \"{}\" to Long", value);
        }

        return null;
    }

    /**
     * @should return correct result
     * @should log when exception was thrown
     */
    public static boolean getBooleanFromYNString(Map<String, String> fieldValues, String fieldName) {
        String value = fieldValues.get(fieldName);
        if (isEmptyValue(value)) {
            return false;
        }

        switch (value) {
            case "Y":
                return true;
            case "N":
                return false;
            default:
                log.warn("Unable to parse value \"{}\" to Boolean", value);
        }

        return false;
    }

    public static String getStringFieldValue(Map<String, String> fieldValues, String fieldName) {
        String value = fieldValues.get(fieldName);
        if (isEmptyValue(value)) {
            return null;
        }
        return value;
    }

    public static boolean isEmptyValue(String value) {
        return StringUtils.isBlank(value) || value.equalsIgnoreCase("N.A.") || value
                .equalsIgnoreCase("N.S.");
    }

    public static MarketSector getMarketSector(Map<String, String> fieldValues) {
        String marketSectorField = getStringFieldValue(fieldValues, MARKET_SECTOR_DES);
        if (marketSectorField == null) {
            return null;
        }
        MarketSector marketSector = EnumUtils
                .getEnum(MarketSector.class, marketSectorField.toUpperCase());
        if (marketSector == null) {
            return null;
        }
        return marketSector;
    }
}
