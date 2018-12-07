package com.app.common.util.date;
public enum Week {  
  
    MONDAY("星期一", "Monday", "Mon.", 1),  
    TUESDAY("星期二", "Tuesday", "Tues.", 2),  
    WEDNESDAY("星期三", "Wednesday", "Wed.", 3),  
    THURSDAY("星期四", "Thursday", "Thur.", 4),  
    FRIDAY("星期五", "Friday", "Fri.", 5),  
    SATURDAY("星期六", "Saturday", "Sat.", 6),  
    SUNDAY("星期日", "Sunday", "Sun.", 7);  
      
    String name;
    String nameEn;
    String nameEnShort;
    int number;

    Week(String name, String nameEn, String nameEnShort, int number) {
        this.name = name;
        this.nameEn = nameEn;
        this.nameEnShort = nameEnShort;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameEnShort() {
        return nameEnShort;
    }

    public int getNumber() {
        return number;
    }
}