package ru.danilspirin.license.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Аннотация сообщает, что это REST служба, и она автоматически сериализует/десериализует запросы в JSON
@RequestMapping("v1/organization/{organizationId}/license")
public class LicenseController {


}
