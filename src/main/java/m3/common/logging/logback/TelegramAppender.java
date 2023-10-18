package m3.common.logging.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import lombok.Setter;
import m3.lib.common.helpers.TelegramSender;

import java.nio.charset.StandardCharsets;

public class TelegramAppender extends AppenderBase<ILoggingEvent> {

    @Setter
    private Encoder<ILoggingEvent> encoder;
    @Setter
    private String token;
    @Setter
    private String chatId;

    @Override
    protected void append(ILoggingEvent eventObject) {
        // Skip messages here
        if (eventObject.getMessage().equals("These configurations '{}' were supplied but are not used yet.")) {
            return;
        }
        byte[] encodedBytes = encoder.encode(eventObject);
        String encodedMessage = new String(encodedBytes, StandardCharsets.UTF_8);
        TelegramSender.getInstance().sendToTelegram(encodedMessage, token, chatId);
    }
}