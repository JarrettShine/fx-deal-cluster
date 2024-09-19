package com.demo.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  private final String apiTitle;
  private final String apiDescription;
  private final String apiTermsOfService;
  private final String apiLicense;
  private final String apiLicenseUrl;
  private final String apiContactName;
  private final String apiContactUrl;
  private final String apiContactEmail;
  private final String appVersion;

  public SwaggerConfig(
          @Value("${api.common.title}") String apiTitle,
          @Value("${springdoc.version}") String appVersion,
          @Value("${api.common.license}") String apiLicense,
          @Value("${api.common.licenseUrl}") String apiLicenseUrl,
          @Value("${api.common.contact.url}") String apiContactUrl,
          @Value("${api.common.description}") String apiDescription,
          @Value("${api.common.contact.name}") String apiContactName,
          @Value("${api.common.contact.email}") String apiContactEmail,
          @Value("${api.common.termsOfService}") String apiTermsOfService
  ) {
    this.apiTitle = apiTitle;
    this.appVersion = appVersion;
    this.apiLicense = apiLicense;
    this.apiLicenseUrl = apiLicenseUrl;
    this.apiContactUrl = apiContactUrl;
    this.apiDescription = apiDescription;
    this.apiContactName = apiContactName;
    this.apiContactEmail = apiContactEmail;
    this.apiTermsOfService = apiTermsOfService;
  }

  @Bean
  public OpenAPI getOpenApiDocumentation() {
    return new OpenAPI()
            .info(new Info().title(apiTitle)
                    .description(apiDescription)
                    .version(appVersion)
                    .contact(new Contact()
                            .name(apiContactName)
                            .url(apiContactUrl)
                            .email(apiContactEmail)
                    )
                    .termsOfService(apiTermsOfService)
                    .license(new License()
                            .name(apiLicense)
                            .url(apiLicenseUrl)
                    )
            );
  }
}
