package ru.danilspirin.license.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver =
                new SessionLocaleResolver();

        // Устанавливаем локаль US по умолчанию
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource resourceBundleMessageSource =
                new ResourceBundleMessageSource();
        // Не генерирует ошибку, если сообщение не найдено,
        // а возвращает код сообщения вместо этого.
        // Возвратит "license.creates.message",
        // вместо ошибки "No message found under code license.creates.message ... "
        resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        //Задает базовое имя файлов с переводами сообщений на разные языки
        resourceBundleMessageSource.setBasename("messages");
        return resourceBundleMessageSource;
    }
}
