package m3.users.config.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.Configurator;
import ch.qos.logback.core.spi.ContextAwareBase;
import org.slf4j.Logger;

//@ch.qos.logback.(value = ConfiguratorRank.FALLBACK)
public class LogbackConfiguration extends ContextAwareBase implements Configurator {

    public   LogbackConfiguration(){
        System.out.println("LogbackConfiguration appender");
    }
    @Override
    public ExecutionStatus configure(LoggerContext loggerContext) {

        System.out.println("configre appender");
        var appender = new KafkaAppender();

        appender.setContext(loggerContext);
        appender.setName("kafka-appender");
        appender.start();

        var rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.addAppender(appender);



        System.out.println(rootLogger.getName());

        /**
         *    CustomAppender appender = new CustomAppender();
         *
         *         appender.setContext(context);
         *         appender.setName("custom");
         *         appender.start();
         *
         *
         *         var rootLogger = context.getLogger(Logger.ROOT_LOGGER_NAME);
         *         rootLogger.addAppender(appender);
         */

        return ExecutionStatus.NEUTRAL;
    }
}
