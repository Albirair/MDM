package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class CalendarPeriod extends PanacheEntity{

    public String status;
    public String day;
    public String timeZone;
    @OneToOne
    @JoinColumn(name = "hourPeriod")
    public HourPeriod hourPeriod;

    public CalendarPeriod() {
    }

    public CalendarPeriod( String status, String day, String timeZone, HourPeriod hourPeriod) {

        this.status = status;
        this.day = day;
        this.timeZone = timeZone;
        this.hourPeriod = hourPeriod;
    }
}
