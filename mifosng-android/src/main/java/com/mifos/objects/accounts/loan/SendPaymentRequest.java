package com.mifos.objects.accounts.loan;
/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.mifos.api.local.MifosBaseModel;
import com.mifos.api.local.MifosDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by ashish verma on 13/03/22.
 */
@Table(database = MifosDatabase.class)
@ModelContainer
public class SendPaymentRequest extends MifosBaseModel implements Parcelable {


    @PrimaryKey
    transient long timeStamp;

    @Column
    String client_id;

    @Column
    String loan_id;

    @Column
    String emi_number;

    @Column
    Double amount;

    @Column
    String purpose_message;

    @Column
    String client_name;

    @Column
    String mobile;

    @Column
    String due_date;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEmi_number() {
        return emi_number;
    }

    public void setEmi_number(String emi_number) {
        this.emi_number = emi_number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPurpose_message() {
        return purpose_message;
    }

    public void setPurpose_message(String purpose_message) {
        this.purpose_message = purpose_message;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.loan_id);
        dest.writeString(this.client_id);
        dest.writeString(this.emi_number);
        dest.writeString(this.amount.toString());
        dest.writeString(this.purpose_message);
        dest.writeString(this.client_name);
        dest.writeString(this.mobile);
        dest.writeString(this.due_date);
    }

    public SendPaymentRequest() {
    }

    protected SendPaymentRequest(Parcel in) {
        this.client_id = in.readString();
        this.loan_id = in.readString();
        this.emi_number = in.readString();
        this.amount = in.readDouble();
        this.purpose_message = in.readString();
        this.client_name = in.readString();
        this.mobile = in.readString();
        this.due_date = in.readString();
    }

    public static final Parcelable.Creator<SendPaymentRequest> CREATOR =
            new Parcelable.Creator<SendPaymentRequest>() {
                @Override
                public SendPaymentRequest createFromParcel(Parcel source) {
                    return new SendPaymentRequest(source);
                }

                @Override
                public SendPaymentRequest[] newArray(int size) {
                    return new SendPaymentRequest[size];
                }
            };
}

