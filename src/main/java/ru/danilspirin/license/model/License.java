package ru.danilspirin.license.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter @Setter @ToString @FieldDefaults(level = AccessLevel.PRIVATE)
public class License {
    int id;
    String licenseId;
    String description;
    String organizationId;
    String productName;
    String licenseType;
}
