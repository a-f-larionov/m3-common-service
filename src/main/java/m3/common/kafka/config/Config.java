package m3.common.kafka.config;

import lombok.extern.slf4j.Slf4j;
import m3.common.commons.ErrorCodes;
import m3.common.dto.rq.ErrorRsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Slf4j
@Configuration
public class Config implements KafkaListenerConfigurer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        registrar.setValidator(this.validator);
    }

    @Bean
    public KafkaListenerErrorHandler validationErrorHandler() {
        log.info("INFO!");
        return (m, e) -> {
            String errorMsg = null;
            log.info("HANDLE THE VALIDATION ERROR!!!!!!!!!!");
            //kafkaTemplate.send("topic-client", new HttpExceptionError(ErrorCodes.VALIDATE_ERROR));
            log.info(e.getMessage());

            if (!(e.getCause() instanceof MethodArgumentNotValidException)) {
                errorMsg += "Не известная ошибка!";
            } else {

                var firstError = ((MethodArgumentNotValidException) e.getCause()).getBindingResult()
                        .getAllErrors()
                        .get(0);
//                if (firstError instanceof SpringValidatorAdapter) {
//                    //((SpringValidatorAdapter)firstError).getConstraintsForClass()
                errorMsg += " " + firstError.getDefaultMessage();
            }

            var rs = new ErrorRsDto(ErrorCodes.VALIDATE_ERROR, errorMsg);
            kafkaTemplate.send("topic-client", rs);

            return rs;
        };
    }

}