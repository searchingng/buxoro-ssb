package uz.everbest.buxorossb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.everbest.buxorossb.entity.audit.DateAudit;
import uz.everbest.buxorossb.entity.enums.DoctorStatus;
import uz.everbest.buxorossb.entity.enums.Organisation;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "organisation_id")
    private Long organisationId;

    @Column(name = "region_id")
    private Long regionId;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = true, insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "organisation_id", insertable = false, updatable = false)
    private Organisation organisation;

    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private Region region;

    @Enumerated(EnumType.STRING)
    private DoctorStatus status;

}
