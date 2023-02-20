package ru.danilspirin.license.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

// Наследование от RepresantationModel дает возможность добавлять ссылки на класс модели License
@Getter @Setter @ToString @FieldDefaults(level = AccessLevel.PRIVATE)
public class License extends RepresentationModel<License> {
    int id;
    String licenseId;
    String description;
    String organizationId;
    String productName;
    String licenseType;
}
