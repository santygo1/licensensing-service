package ru.danilspirin.license.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.danilspirin.license.conf.ServiceConfig;
import ru.danilspirin.license.repository.LicenseRepository;
import ru.danilspirin.license.model.License;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messages;
    private final LicenseRepository licenseRepository;
    private final ServiceConfig config;

    public License getLicense(String licenseId, String organizationId){
        Optional<License> license = licenseRepository
                .findByOrOrganizationIdAndLicenseId(licenseId,organizationId);
        return license
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(messages.getMessage("license.search.error.message", null, null),
                                licenseId, organizationId)))
                .withComment(config.getProperty());
    }

    public License createLicense(License license){
        license.setOrganizationId(UUID.randomUUID().toString());
        licenseRepository.save(license);

        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license){
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId, Locale locale){
        String responseMessage =
                String.format(messages.getMessage("license.delete.message", null,locale),
                        licenseId);
        licenseRepository.deleteById(licenseId);

        return responseMessage;
    }
}
