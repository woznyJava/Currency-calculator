package com.example.egzaminrest.domain;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Result {
    //org.springframework.boot.orm.jpa.EntityScan
    //"org.springframework.boot.autoconfigure.domain.EntityScan"
//"com.baeldung.demopackage
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String from;
    private String to;
    private Double value;

}
//mape zrobic i szukac komenda najwieszkej ammount i po id zwrocic najwieksza. Njaczesniej wyliczna to dodac do liczby int
//i co uzycie zrobic +1
//z godzina nwm jak to zrobic
// z kantorem pomyslec
// zrobic historie z h2 database
//zeby zapisywalo dane trwale

//zaciagam sobie z bazy key czyli from value to count
// i przy robieniu geta zeby to ogarnialo sobie bez robienia z mapy dodatkowej bazy danych

//strumieniami zrobic obczaic jak
// metoda co zwraca najczesciej uzywany element strumien
// jest dluga praca inzynierska o tym
