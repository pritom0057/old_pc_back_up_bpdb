
package com.sarwar.bpdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillModel {

    @SerializedName("OPN_KWH_SR_RDNG")
    @Expose
    private Double oPNKWHSRRDNG;
    @SerializedName("BOOK_NO")
    @Expose
    private String bOOKNO;
    @SerializedName("CONS_KWH_SR")
    @Expose
    private Double cONSKWHSR;
    @SerializedName("PRN_AMT")
    @Expose
    private Double pRNAMT;
    @SerializedName("CURRENT_VAT")
    @Expose
    private Double cURRENTVAT;
    @SerializedName("BILL_MONTH_TOT")
    @Expose
    private Double bILLMONTHTOT;
    @SerializedName("ADJUSTED_PRN")
    @Expose
    private Double aDJUSTEDPRN;
    @SerializedName("ADJUSTED_LPS")
    @Expose
    private Double aDJUSTEDLPS;
    @SerializedName("ADJUSTED_VAT")
    @Expose
    private Double aDJUSTEDVAT;
    @SerializedName("ARR_ADV_ADJ_PRN")
    @Expose
    private Double aRRADVADJPRN;
    @SerializedName("ARR_ADV_ADJ_LPS")
    @Expose
    private Double aRRADVADJLPS;
    @SerializedName("ARR_ADV_ADJ_VAT")
    @Expose
    private Double aRRADVADJVAT;
    @SerializedName("TOTAL_BILL")
    @Expose
    private Double tOTALBILL;
    @SerializedName("INVOICE_DATE")
    @Expose
    private String iNVOICEDATE;
    @SerializedName("INVOICE_DUE_DATE")
    @Expose
    private String iNVOICEDUEDATE;
    @SerializedName("BILL_MONTH")
    @Expose
    private String bILLMONTH;
    @SerializedName("BILL_NO")
    @Expose
    private String bILLNO;
    @SerializedName("CD")
    @Expose
    private String cD;
    @SerializedName("TARIFF")
    @Expose
    private String tARIFF;
    @SerializedName("BILL_CYCLE_CODE")
    @Expose
    private String bILLCYCLECODE;
    @SerializedName("METER_COND_KWH")
    @Expose
    private String mETERCONDKWH;
    @SerializedName("METER_NUM_KWH")
    @Expose
    private String mETERNUMKWH;
    @SerializedName("CUSTOMER_NUM")
    @Expose
    private String cUSTOMERNUM;
    @SerializedName("CONS_CHK_DIGIT")
    @Expose
    private Double cONSCHKDIGIT;
    @SerializedName("CONSUMER_NUMBER")
    @Expose
    private String cONSUMERNUMBER;
    @SerializedName("CUSTOMER_NAME")
    @Expose
    private String cUSTOMERNAME;
    @SerializedName("DESCR")
    @Expose
    private String dESCR;

    public Double getOPNKWHSRRDNG() {
        return oPNKWHSRRDNG;
    }

    public void setOPNKWHSRRDNG(Double oPNKWHSRRDNG) {
        this.oPNKWHSRRDNG = oPNKWHSRRDNG;
    }

    public String getBOOKNO() {
        return bOOKNO;
    }

    public void setBOOKNO(String bOOKNO) {
        this.bOOKNO = bOOKNO;
    }

    public Double getCONSKWHSR() {
        return cONSKWHSR;
    }

    public void setCONSKWHSR(Double cONSKWHSR) {
        this.cONSKWHSR = cONSKWHSR;
    }

    public Double getPRNAMT() {
        return pRNAMT;
    }

    public void setPRNAMT(Double pRNAMT) {
        this.pRNAMT = pRNAMT;
    }

    public Double getCURRENTVAT() {
        return cURRENTVAT;
    }

    public void setCURRENTVAT(Double cURRENTVAT) {
        this.cURRENTVAT = cURRENTVAT;
    }

    public Double getBILLMONTHTOT() {
        return bILLMONTHTOT;
    }

    public void setBILLMONTHTOT(Double bILLMONTHTOT) {
        this.bILLMONTHTOT = bILLMONTHTOT;
    }

    public Double getADJUSTEDPRN() {
        return aDJUSTEDPRN;
    }

    public void setADJUSTEDPRN(Double aDJUSTEDPRN) {
        this.aDJUSTEDPRN = aDJUSTEDPRN;
    }

    public Double getADJUSTEDLPS() {
        return aDJUSTEDLPS;
    }

    public void setADJUSTEDLPS(Double aDJUSTEDLPS) {
        this.aDJUSTEDLPS = aDJUSTEDLPS;
    }

    public Double getADJUSTEDVAT() {
        return aDJUSTEDVAT;
    }

    public void setADJUSTEDVAT(Double aDJUSTEDVAT) {
        this.aDJUSTEDVAT = aDJUSTEDVAT;
    }

    public Double getARRADVADJPRN() {
        return aRRADVADJPRN;
    }

    public void setARRADVADJPRN(Double aRRADVADJPRN) {
        this.aRRADVADJPRN = aRRADVADJPRN;
    }

    public Double getARRADVADJLPS() {
        return aRRADVADJLPS;
    }

    public void setARRADVADJLPS(Double aRRADVADJLPS) {
        this.aRRADVADJLPS = aRRADVADJLPS;
    }

    public Double getARRADVADJVAT() {
        return aRRADVADJVAT;
    }

    public void setARRADVADJVAT(Double aRRADVADJVAT) {
        this.aRRADVADJVAT = aRRADVADJVAT;
    }

    public Double getTOTALBILL() {
        return tOTALBILL;
    }

    public void setTOTALBILL(Double tOTALBILL) {
        this.tOTALBILL = tOTALBILL;
    }

    public String getINVOICEDATE() {
        return iNVOICEDATE;
    }

    public void setINVOICEDATE(String iNVOICEDATE) {
        this.iNVOICEDATE = iNVOICEDATE;
    }

    public String getINVOICEDUEDATE() {
        return iNVOICEDUEDATE;
    }

    public void setINVOICEDUEDATE(String iNVOICEDUEDATE) {
        this.iNVOICEDUEDATE = iNVOICEDUEDATE;
    }

    public String getBILLMONTH() {
        return bILLMONTH;
    }

    public void setBILLMONTH(String bILLMONTH) {
        this.bILLMONTH = bILLMONTH;
    }

    public String getBILLNO() {
        return bILLNO;
    }

    public void setBILLNO(String bILLNO) {
        this.bILLNO = bILLNO;
    }

    public String getCD() {
        return cD;
    }

    public void setCD(String cD) {
        this.cD = cD;
    }

    public String getTARIFF() {
        return tARIFF;
    }

    public void setTARIFF(String tARIFF) {
        this.tARIFF = tARIFF;
    }

    public String getBILLCYCLECODE() {
        return bILLCYCLECODE;
    }

    public void setBILLCYCLECODE(String bILLCYCLECODE) {
        this.bILLCYCLECODE = bILLCYCLECODE;
    }

    public String getMETERCONDKWH() {
        return mETERCONDKWH;
    }

    public void setMETERCONDKWH(String mETERCONDKWH) {
        this.mETERCONDKWH = mETERCONDKWH;
    }

    public String getMETERNUMKWH() {
        return mETERNUMKWH;
    }

    public void setMETERNUMKWH(String mETERNUMKWH) {
        this.mETERNUMKWH = mETERNUMKWH;
    }

    public String getCUSTOMERNUM() {
        return cUSTOMERNUM;
    }

    public void setCUSTOMERNUM(String cUSTOMERNUM) {
        this.cUSTOMERNUM = cUSTOMERNUM;
    }

    public Double getCONSCHKDIGIT() {
        return cONSCHKDIGIT;
    }

    public void setCONSCHKDIGIT(Double cONSCHKDIGIT) {
        this.cONSCHKDIGIT = cONSCHKDIGIT;
    }

    public String getCONSUMERNUMBER() {
        return cONSUMERNUMBER;
    }

    public void setCONSUMERNUMBER(String cONSUMERNUMBER) {
        this.cONSUMERNUMBER = cONSUMERNUMBER;
    }

    public String getCUSTOMERNAME() {
        return cUSTOMERNAME;
    }

    public void setCUSTOMERNAME(String cUSTOMERNAME) {
        this.cUSTOMERNAME = cUSTOMERNAME;
    }

    public String getDESCR() {
        return dESCR;
    }

    public void setDESCR(String dESCR) {
        this.dESCR = dESCR;
    }

}
