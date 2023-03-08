package ru.danilspirin.license.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danilspirin.license.model.License;
import ru.danilspirin.license.service.LicenseService;

import java.util.Locale;

//Вспомогательный класс предназначенный для создания ссылок на классы контроллеров
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController  // Аннотация сообщает, что это REST служба, и она автоматически сериализует/десериализует запросы в JSON
@RequestMapping("v1/organization/{organizationId}/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId){

        // Получаем из сервиса лицензию, пока что создается статично
        License license = licenseService.getLicense(licenseId, organizationId);

        // Создаем конфигурацию HATEOAS
        license.add(
                linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId()))
                        .withSelfRel(),
                linkTo(methodOn(LicenseController.class).createLicense(organizationId,license,null))
                        .withRel("createLicense"),
                linkTo(methodOn(LicenseController.class).updateLicense(organizationId, license, null))
                        .withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId()))
                        .withRel("deleteLicense")
        );

        // Так как сервис всегда возвращает лицензию, возвращаем код 200 с телом лицензии.
        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<License> createLicense(
            @PathVariable String organizationId,
            @RequestBody License license, // Аннотация @RequestBody отображает тело запроса в объект License
            @RequestHeader(value = "Accept-Language", required = false) Locale locale){

        return ResponseEntity.ok(licenseService.createLicense(license));
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(
            @PathVariable String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale){

        return ResponseEntity.ok(licenseService.updateLicense(license));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("licenseId") String licenseId,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, locale));
    }

}
