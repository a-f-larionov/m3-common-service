package m3.common.logging.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import lombok.Setter;

import java.io.IOException;
import java.net.URLEncoder;
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

        byte[] encodedBytes = encoder.encode(eventObject);
        String encodedMessage = new String(encodedBytes, StandardCharsets.UTF_8);
        System.out.println("TELEGRAM");
        System.out.println(encodedMessage);

        sendToTelegram(encodedMessage);
    }


    private void sendToTelegram(String encodedString) {

        var endpoint = "https://api.telegram.org/bot"
                + token
                + "/sendMessage" +
                "?chat_id=" + chatId +
                "&text=" + URLEncoder.encode(encodedString, StandardCharsets.UTF_8);

        endpoint = endpoint.substring(0, Math.min(endpoint.length(),1024));

        ProcessBuilder pb = new ProcessBuilder();
        pb.command("curl", endpoint, "--ssl-no-revoke");
        Process p;
        try {
            p = pb.start();
            p.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


//        var chatId = "-1001416055228";
////1194574646:AAEr6xf1nNEhveh-N5Drv3KOQ8I9UWrTkZw
//        var endpoint = "https://api.telegram.org/bot"
//                + token
//                + "/sendMessage" +
//                "?chat_id=" + chatId +
//                "&text=" + URLEncoder.encode(message);
//        System.out.println(endpoint);
//
////        ProcessBuilder pb = new ProcessBuilder();
////        var url = "https://api.telegram.org/bot1194574646:AAEr6xf1nNEhveh-N5Drv3KOQ8I9UWrTkZw/sendMessage?chat_id=-1001416055228&text=Starting";
////        url = "https://api.telegram.org/bot" + token + "/sendMessage?chat_id=" + chatId + "&text=" + URLEncoder.encode(message);
////        pb.command("curl",   url , "--ssl-no-revoke");
////        Process p = pb.start();
////        p.waitFor();
////        new String(p.getErrorStream().readAllBytes(), StandardCharsets.UTF_8) +
////                new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8) +
////                "-";
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        processBuilder.command("curl", endpoint, "--ssl-no-revoke");
//        try {
//
//            Process process = processBuilder.start();
//            process.waitFor();
//            1
//            process.destroy();
//            System.out.println("telesend: " + process.exitValue());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    public Encoder<ILoggingEvent> getEncoder() {
        return encoder;
    }
}
