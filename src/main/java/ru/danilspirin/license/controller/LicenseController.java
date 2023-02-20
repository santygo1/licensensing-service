package ru.danilspirin.license.controller;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseEntity<String> createLicense(
            @PathVariable String organizationId,
            @RequestBody License license){ // Аннотация @RequestBody отображает тело запроса в объект License

        return ResponseEntity.ok(licenseService.createLicense(license, organizationId));
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(
            @PathVariable String organizationId,
            @RequestBody License license){

        return ResponseEntity.ok(licenseService.updateLicense(license, organizationId));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId){

        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
    }

}
