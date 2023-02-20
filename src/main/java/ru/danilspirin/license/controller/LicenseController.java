package ru.danilspirin.license.controller;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.danilspirin.license.model.License;
import ru.danilspirin.license.service.LicenseService;

@RestController  // Аннотация сообщает, что это REST служба, и она автоматически сериализует/десериализует запросы в JSON
@RequestMapping("v1/organization/{organizationId}/license")
@RequiredArgsConstructor
public class LicenseController {

    private @Nonnull LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId){

        // Получаем из сервиса лицензию, пока что создается статично
        License license = licenseService.getLicense(licenseId, organizationId);

        // Так как сервис всегда возвращает лицензию, возвращаем код 200 с телом лицензии.
        return ResponseEntity.ok(license);
    }



}
