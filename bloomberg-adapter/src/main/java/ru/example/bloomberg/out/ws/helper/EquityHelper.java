package ru.example.bloomberg.out.ws.helper;

import ru.example.bloomberg.model.instrument.Instrument;
import ru.example.bloomberg.model.instrument.InstrumentType;
import ru.example.bloomberg.model.instrument.Rating;
import ru.example.bloomberg.model.instrument.equity.Equity;
import ru.example.bloomberg.model.instrument.equity.EquityStatic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static ru.example.bloomberg.model.Constant.InstrumentFields.ID_ISIN;
import static ru.example.bloomberg.model.Constant.InstrumentFields.*;
import static ru.example.bloomberg.model.Constant.QuoteFields.*;

public class EquityHelper {

    private EquityHelper() {
    }

    /**
     * @should set correct id
     * @should return correct result
     */
    public static Equity createEquity(Map<String, String> fieldValues) {
        Instrument instrument = InstrumentHelper.createInstrument(fieldValues, InstrumentType.EQUITY);
        if (instrument == null) {
            return null;
        }
        Rating rating = RatingHelper.getRating(fieldValues);
        EquityStatic equityStatic = getEquityStatic(fieldValues);

        return new Equity(instrument, equityStatic, rating);
    }

    private static EquityStatic getEquityStatic(Map<String, String> fieldValues) {

        String isin = FieldHelper.getStringFieldValue(fieldValues, ID_ISIN);
        String name = FieldHelper.getStringFieldValue(fieldValues, NAME);
        String currency = FieldHelper.getStringFieldValue(fieldValues, CRNCY);
        String industrySector = FieldHelper.getStringFieldValue(fieldValues, INDUSTRY_SECTOR);
        LocalDate settleDt = FieldHelper.getLocalDateFieldValue(fieldValues, SETTLE_DT);

        BigDecimal marketCapitalization = FieldHelper.getBigDecimalFieldValue(fieldValues, CUR_MKT_CAP);
        Long issuerId = FieldHelper.getLongFieldValue(fieldValues, ID_BB_COMPANY);
        String securityName = FieldHelper.getStringFieldValue(fieldValues, SECURITY_NAME);
        BigDecimal zSpreadAsk = FieldHelper.getBigDecimalFieldValue(fieldValues, Z_SPRD_ASK);
        BigDecimal outstandingAmount = FieldHelper.getBigDecimalFieldValue(fieldValues, AMT_OUTSTANDING);
        String industryGroup = FieldHelper.getStringFieldValue(fieldValues, INDUSTRY_GROUP);
        String dayCountConvention = FieldHelper.getStringFieldValue(fieldValues, DAY_CNT_DES);
        String shortDescription = FieldHelper.getStringFieldValue(fieldValues, SECURITY_SHORT_DES);
        Boolean ruLombardList = FieldHelper.getBooleanFromYNString(fieldValues, RUSSIAN_CB_LOMBARD);
        String issuerCountry = FieldHelper.getStringFieldValue(fieldValues, COUNTRY);
        String dividendCurrency = FieldHelper.getStringFieldValue(fieldValues, DVD_CRNCY);
        BigDecimal dividendShare12M = FieldHelper.getBigDecimalFieldValue(fieldValues, DVD_SH_12M);
        LocalDate dividendExDate = FieldHelper.getLocalDateFieldValue(fieldValues, DVD_EX_DT);
        LocalDate dividendRecordDate = FieldHelper.getLocalDateFieldValue(fieldValues, DVD_RECORD_DT);
        BigDecimal dividendShareLast = FieldHelper.getBigDecimalFieldValue(fieldValues, DVD_SH_LAST);
        BigDecimal dividendCashGrossNext = FieldHelper.getBigDecimalFieldValue(fieldValues, EQY_DVD_CASH_GROSS_NEXT);
        LocalDate dividendCashRecordDateNext = FieldHelper.getLocalDateFieldValue(fieldValues, EQY_DVD_CASH_RECORD_DT_NEXT);
        String dividendCurrencyNext = FieldHelper.getStringFieldValue(fieldValues, EQY_DVD_CASH_CRNCY_NEXT);
        LocalDate dividendStockRecordDateNext = FieldHelper.getLocalDateFieldValue(fieldValues, EQY_DVD_STK_RECORD_DT_NEXT);
        String dividendStockTypeNext = FieldHelper.getStringFieldValue(fieldValues, EQY_DVD_STK_TYP_NEXT);
        String dividendStockAdjustFactor = FieldHelper.getStringFieldValue(fieldValues, EQY_DVD_STK_ADJ_FCT_NEXT);
        LocalDate dividendSplitRecordDate = FieldHelper.getLocalDateFieldValue(fieldValues, EQY_DVD_SPL_RECORD_DT_NEXT);
        String dividendSplitAdjustFactor = FieldHelper.getStringFieldValue(fieldValues, EQY_DVD_SPL_ADJ_FCT_NEXT);
        Boolean dividendExFlag = FieldHelper.getBooleanFromYNString(fieldValues, EQY_DVD_EX_FLAG);
        Boolean dividendStockFlag = FieldHelper.getBooleanFromYNString(fieldValues, EQY_DVD_STK_EX_FLAG);
        Boolean stockSplitFlag = FieldHelper.getBooleanFromYNString(fieldValues, EQY_DVD_SPL_EX_FLAG);


        return EquityStatic.builder()
                .isin(isin)
                .issuer(name)
                .currency(currency)
                .industrySector(industrySector)
                .settlementDate(settleDt)
                .marketCapitalization(marketCapitalization)
                .issuerId(issuerId)
                .securityName(securityName)
                .zSpreadAsk(zSpreadAsk)
                .outstandingAmount(outstandingAmount)
                .industryGroup(industryGroup)
                .dayCountConvention(dayCountConvention)
                .shortDescription(shortDescription)
                .ruLombardList(ruLombardList)
                .issuerCountry(issuerCountry)
                .dividendCurrency(dividendCurrency)
                .dividendShare12M(dividendShare12M)
                .dividendExDate(dividendExDate)
                .dividendRecordDate(dividendRecordDate)
                .dividendShareLast(dividendShareLast)
                .dividendCashGrossNext(dividendCashGrossNext)
                .dividendCashRecordDateNext(dividendCashRecordDateNext)
                .dividendCurrencyNext(dividendCurrencyNext)
                .dividendStockRecordDateNext(dividendStockRecordDateNext)
                .dividendStockTypeNext(dividendStockTypeNext)
                .dividendStockAdjustFactor(dividendStockAdjustFactor)
                .dividendSplitRecordDate(dividendSplitRecordDate)
                .dividendSplitAdjustFactor(dividendSplitAdjustFactor)
                .dividendExFlag(dividendExFlag)
                .dividendStockFlag(dividendStockFlag)
                .stockSplitFlag(stockSplitFlag)
                .build();
    }
}