package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Date;
import javax.persistence.*;


@Entity
public class HourPeriod extends PanacheEntity{

    public Date startHour;
    public Date endHour;
    
    @OneToOne(mappedBy = "hourPeriod")
    @JoinColumn
    public CalendarPeriod hourPeriod;


    public HourPeriod() {
    }

    public HourPeriod( Date startHour, Date endHour) {

        this.startHour = startHour;
        this.endHour = endHour;
    }

}
