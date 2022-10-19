package models.location;

// import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.ModelBase;
import java.util.Date;
import javax.persistence.*;

@Entity
public class GeographicSite extends ModelBase {

    public String name;
    public String href;
    public String code;
    public String GBID;
    public String ESID;
    public Date on_air_date;
    public String description;
    public String status;
    public Date baseType;
    public String type;
    public String schemaLocation;
    // primary key:
    @OneToOne(mappedBy = "GeoSite_SiteRole")
    public Site_Role GeoSite_SiteRole;
    // Forign key:
    @OneToOne
    @JoinColumn(name = "GeoAdd_GeSite")
    public GeographicAddress GeoAdd_GeSite;
    @OneToOne
    @JoinColumn(name = "SiteRel_GeoSite")
    public SiteRelationship SiteRel_GeoSite;
    @OneToOne
    @JoinColumn(name = "CalPeriod_GeoSite")
    public CalendarPeriod CalPeriod_GeoSite;
    @OneToOne
    @JoinColumn(name = "GeoLocat_GeoSite")
    public GeographicLocation GeoLocat_GeoSite;
    // @OneToOne
    // @JoinColumn(name = "RelatParty_GeoSite")
    // public RelatedPartyRef RelatParty_GeoSite;

    public GeographicSite() {
    }

    public GeographicSite(String name, String href, String code, String GBID, String ESID, Date on_air_date,
            String description, String status, Date baseType, String type, String schemaLocation,
            GeographicAddress GeoAdd_GeSite, SiteRelationship SiteRel_GeoSite, CalendarPeriod CalPeriod_GeoSite,
            GeographicLocation GeoLocat_GeoSite) {

        this.name = name;
        this.href = href;
        this.code = code;
        this.GBID = GBID;
        this.ESID = ESID;
        this.on_air_date = on_air_date;
        this.description = description;
        this.status = status;
        this.baseType = baseType;
        this.type = type;
        this.schemaLocation = schemaLocation;
        this.GeoAdd_GeSite = GeoAdd_GeSite;
        this.SiteRel_GeoSite = SiteRel_GeoSite;
        this.CalPeriod_GeoSite = CalPeriod_GeoSite;
        this.GeoLocat_GeoSite = GeoLocat_GeoSite;
    }
}