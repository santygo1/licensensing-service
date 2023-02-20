package ru.danilspirin.license.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.danilspirin.license.model.License;

import java.util.Locale;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messages;

    public License getLicense(String licenseId, String organizationId){
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");

        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale){
        String responseMessage = null;
        if (license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(
                    messages.getMessage("license.create.message", null, locale),
                    license);

        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId, Locale locale){
        String responseMessage = null;
        if (license != null){
            license.setOrganizationId(organizationId);

            responseMessage = String.format(
                    // Также мы можем вызвать getMessage с параметром null, вместо locale.
                    // В этом случае будет выбрана дефолтная локаль, которая была прописана в файле конфигурации
                    messages.getMessage("license.update.message", null, locale),
                    license);
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId, Locale locale){
        String responseMessage = null;
        responseMessage = String.format(
                messages.getMessage("license.delete.message", null, locale),
                licenseId,
                organizationId
        );
        return responseMessage;
    }
}
