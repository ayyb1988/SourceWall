package net.nashlegend.sourcewall.model;

/**
 * Created by NashLegend on 2015/2/2 0002
 */
public class ReminderNoticeNum {

    private int notice_num = 0;//通知数量
    private int reminder_num = 0;//站内信数量，或许还有其他？

    public int getReminder_num() {
        return reminder_num;
    }

    public void setReminder_num(int reminder_num) {
        this.reminder_num = reminder_num;
    }

    public int getNotice_num() {
        return notice_num;
    }

    public void setNotice_num(int notice_num) {
        this.notice_num = notice_num;
    }

}
