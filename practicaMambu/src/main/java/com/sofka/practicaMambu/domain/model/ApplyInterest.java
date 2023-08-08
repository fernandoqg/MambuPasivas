package com.sofka.practicaMambu.domain.model;

public class ApplyInterest {

    private String interestApplicationDate;
    private boolean isPaymentHolidaysInterest;
    private String notes;
    private int paymentHolidaysInterestAmount;

    public String getInterestApplicationDate() {
        return interestApplicationDate;
    }

    public void setInterestApplicationDate(String interestApplicationDate) {
        this.interestApplicationDate = interestApplicationDate;
    }

    public boolean isPaymentHolidaysInterest() {
        return isPaymentHolidaysInterest;
    }

    public void setPaymentHolidaysInterest(boolean paymentHolidaysInterest) {
        isPaymentHolidaysInterest = paymentHolidaysInterest;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPaymentHolidaysInterestAmount() {
        return paymentHolidaysInterestAmount;
    }

    public void setPaymentHolidaysInterestAmount(int paymentHolidaysInterestAmount) {
        this.paymentHolidaysInterestAmount = paymentHolidaysInterestAmount;
    }
}
