package models.party;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class LanguageAbility extends PanacheEntity {
    public boolean isFavoriteLanguage;
    public String languageCode;
    public String languageName;
    public String listeningProficiency;
    public String readingProficiency;
    public String speakingProficiency;
    public String writingProficiency;
    public Date validFrom;
    public Date validUntil;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "individual")
    public Individual individual;
}
