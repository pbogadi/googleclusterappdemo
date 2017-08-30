package com.mike.givemewingzz.mapsclusterdemo.model.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class OpeningHours extends RealmObject {

    @SerializedName("open_now")
    private boolean isOpen;

    @SerializedName("weekday_text")
    private RealmList<WeekdayText> weekdayTexts;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public RealmList<WeekdayText> getWeekdayTexts() {
        return weekdayTexts;
    }

    public void setWeekdayTexts(RealmList<WeekdayText> weekdayTexts) {
        this.weekdayTexts = weekdayTexts;
    }

}
