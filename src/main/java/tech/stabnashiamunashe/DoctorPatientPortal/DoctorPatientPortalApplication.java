package tech.stabnashiamunashe.DoctorPatientPortal;

import tech.stabnashiamunashe.DoctorPatientPortal.Security.Configs.RSAKeyProperties;
import tech.stabnashiamunashe.DoctorPatientPortal.Payments.PayPal.PayPalProperties;
import tech.stabnashiamunashe.DoctorPatientPortal.Payments.Paynow.Configs.PaynowProperties;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.AzureBlobProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({RSAKeyProperties.class, AzureBlobProperties.class, PayPalProperties.class, PaynowProperties.class})
@SpringBootApplication
public class DoctorPatientPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorPatientPortalApplication.class, args);
	}

}
