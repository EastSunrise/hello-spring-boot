package cn.wsg.springboot.config;

import cn.wsg.springboot.common.StringToEnumConverterFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configurations for MVC.
 *
 * @author Kingen
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
            .registerModule(new JavaTimeModule()
                .addSerializer(new LocalDateTimeSerializer(FORMATTER)));
    }

    /**
     * Registers customized converters.
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactory());
        Jsr310Converters.getConvertersToRegister().forEach(registry::addConverter);
        registry.addConverter(new StringToLocalDateTimeConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowCredentials(true)
            // todo set origins
            .allowedOrigins("**")
            .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name());
    }

    private static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

        @Override
        public LocalDateTime convert(String source) {
            return LocalDateTime.parse(source, FORMATTER);
        }
    }
}
