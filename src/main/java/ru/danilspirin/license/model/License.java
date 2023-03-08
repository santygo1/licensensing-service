package ru.danilspirin.license.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

// Наследование от RepresantationModel<License> дает возможность добавлять ссылки на класс модели License
@Getter @Setter @ToString @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Table(name="license")
public class License extends RepresentationModel<License> {
    @Id
    @Column(name = "license_id", nullable = false)
    String licenseId;

    String description;

    @Column(name = "oranization_id", nullable = false)
    String organizationId;

    @Column(name = "product_name", nullable = false)
    String productName;

    @Column(name = "license_type", nullable = false)
    String licenseType;

    String comment;

    public License withComment(String comment){
        this.setComment(comment);
        return this;
    }
}
