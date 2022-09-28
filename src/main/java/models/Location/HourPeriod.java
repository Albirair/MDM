package models.Location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Date;
import javax.persistence.*;


@Entity
public class HourPeriod extends PanacheEntity{

    public Date startHour;
    public Date endHour;
    @OneToOne(mappedBy = "hourPeriod")
    public CalendarPeriod hourPeriod;


    public HourPeriod() {
    }

    public HourPeriod( Date startHour, Date endHour) {
        
        this.startHour = startHour;
        this.endHour = endHour;
    }
    
}
