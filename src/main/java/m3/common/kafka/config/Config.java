package m3.common.kafka.config;

import lombok.extern.slf4j.Slf4j;
import m3.common.commons.ErrorCodes;
import m3.common.dto.rq.UserIdRqDto;
import m3.common.dto.rs.ErrorRsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
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
        return (genericMsg, exception) -> {
            String errorMsg = null;
            var payload = genericMsg.getPayload();

            errorMsg = extractErrorMessage(exception, errorMsg);
            errorMsg += payload.toString();

            if (payload instanceof UserIdRqDto && ((UserIdRqDto) payload).getUserId() != null) {
                var rs = new ErrorRsDto(
                        ErrorCodes.VALIDATE_ERROR,
                        errorMsg,
                        ((UserIdRqDto) payload).getUserId(),
                        payload.getClass().getName());
                kafkaTemplate.send("topic-client", rs);
            }

            throw exception;
        };
    }

    private static String extractErrorMessage(ListenerExecutionFailedException e, String errorMsg) {
        if (!(e.getCause() instanceof MethodArgumentNotValidException)) {
           return "Не известная ошибка!";
        } else {

            var firstError = ((MethodArgumentNotValidException) e.getCause()).getBindingResult()
                    .getAllErrors()
                    .get(0);
            return firstError.getDefaultMessage();
        }
    }

}