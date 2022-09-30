package uz.everbest.buxorossb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.everbest.buxorossb.entity.audit.DateAudit;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor@AllArgsConstructor
@Entity
public class Form extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String passport;

    private LocalDate startedDate;

    private LocalDate expiredDate;

    @Column(unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "doc_type_id")
    private DocType docType;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
