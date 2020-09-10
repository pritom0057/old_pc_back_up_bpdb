
package com.sarwar.bpdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Identify {

    @SerializedName("OPN_KWH_SR_RDNG")
    @Expose
    private Double oPNKWHSRRDNG;
    @SerializedName("CONS_KWH_SR")
    @Expose
    private Double cONSKWHSR;
    @SerializedName("CURRENT_BILL")
    @Expose
    private Double cURRENTBILL;
    @SerializedName("ARREAR_BILL")
    @Expose
    private Double aRREARBILL;
    @SerializedName("TOTAL_BILL")
    @Expose
    private Double tOTALBILL;
    @SerializedName("PAID_AMT")
    @Expose
    private Object pAIDAMT;
    @SerializedName("RECEIPT_DATE")
    @Expose
    private Object rECEIPTDATE;
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
    @SerializedName("TARIFF")
    @Expose
    private String tARIFF;
    @SerializedName("BILL_CYCLE_CODE")
    @Expose
    private String bILLCYCLECODE;
    @SerializedName("METER_COND_KWH")
    @Expose
    private String mETERCONDKWH;
    @SerializedName("CD")
    @Expose
    private String cD;
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

    public Double getCONSKWHSR() {
        return cONSKWHSR;
    }

    public void setCONSKWHSR(Double cONSKWHSR) {
        this.cONSKWHSR = cONSKWHSR;
    }

    public Double getCURRENTBILL() {
        return cURRENTBILL;
    }

    public void setCURRENTBILL(Double cURRENTBILL) {
        this.cURRENTBILL = cURRENTBILL;
    }

    public Double getARREARBILL() {
        return aRREARBILL;
    }

    public void setARREARBILL(Double aRREARBILL) {
        this.aRREARBILL = aRREARBILL;
    }

    public Double getTOTALBILL() {
        return tOTALBILL;
    }

    public void setTOTALBILL(Double tOTALBILL) {
        this.tOTALBILL = tOTALBILL;
    }

    public Object getPAIDAMT() {
        return pAIDAMT;
    }

    public void setPAIDAMT(Object pAIDAMT) {
        this.pAIDAMT = pAIDAMT;
    }

    public Object getRECEIPTDATE() {
        return rECEIPTDATE;
    }

    public void setRECEIPTDATE(Object rECEIPTDATE) {
        this.rECEIPTDATE = rECEIPTDATE;
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

    public String getCD() {
        return cD;
    }

    public void setCD(String cD) {
        this.cD = cD;
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
