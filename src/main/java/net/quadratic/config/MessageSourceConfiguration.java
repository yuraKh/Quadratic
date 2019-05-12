package net.quadratic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceConfiguration {

    public String getMessage(String id, Object[] args) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource().getMessage(id, args, currentLocale);
    }

    public String getMessage(String id) {
        return getMessage(id, null);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
