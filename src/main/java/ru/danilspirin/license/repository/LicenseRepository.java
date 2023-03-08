package ru.danilspirin.license.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilspirin.license.model.License;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License, String> {

    List<License> findByOrganizationId(String organizationId);
    Optional<License> findByOrOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
