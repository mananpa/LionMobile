package ug.co.lion.lionmobile;

import android.content.Context;

/**
 * Created by Eriq on 11/5/2015.
 */
public class MotorCompInsuranceQuoteItem {

    Long sumInsured;
    Long basicPremium;
    Long trainingLevy;
    Long stickerFees;
    Long stampDuty;
    Long VAT;
    Long totalPremium;
    Context context;
    ConversionClass mCC;
    Double rateMotorPrivate = 0.04;
    Double rateMotorCommercialOwnBusiness = 0.05;
    Double rateMotorCommercialGeneral = 0.06;
    Double trainingLevyRate = 0.005;
    String stickerFeesString = "6000";
    String stampDutyString = "35000";
    Double vatRate = 0.18;
    Long vatableAmount;



    public void setUpAll(String valueOfVehicle, Context c){
        context = c;
        mCC = new ConversionClass(context);


        sumInsured = mCC.valueConverter(valueOfVehicle);

        basicPremium = mCC.getLongFromAmountAndRate(sumInsured, rateMotorPrivate);
        trainingLevy = mCC.getLongFromAmountAndRate(basicPremium, trainingLevyRate);
        stickerFees = mCC.valueConverter(stickerFeesString);

        vatableAmount = mCC.add(basicPremium, trainingLevy, stickerFees);

        VAT = mCC.getLongFromAmountAndRate(vatableAmount, vatRate);

        stampDuty = mCC.valueConverter(stampDutyString);

        totalPremium = mCC.add(basicPremium, trainingLevy, stickerFees, stampDuty, VAT);



    };


    public String getSumInsured() {
        return mCC.valueConverter(this.sumInsured);
    }

    public String getBasicPremium() {
        return mCC.valueConverter(this.basicPremium);
    }

    public String getTrainingLevy() {
        return mCC.valueConverter(this.trainingLevy);
    }

    public String getVAT() {
        return mCC.valueConverter(this.VAT);
    }

    public String getTotalPremium() {
        return mCC.valueConverter(this.totalPremium);
    }

    public String getStampDuty() {
        return mCC.valueConverter(this.stampDuty);
    }

    public String getStickerFees(){return mCC.valueConverter(this.stickerFees);
    }



}
