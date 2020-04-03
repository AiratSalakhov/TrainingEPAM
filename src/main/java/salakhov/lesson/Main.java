package salakhov.lesson;

import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private final static org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Collection<Uuid> uuids = Stream.generate(Uuid::new)
                .limit(10000)
                .collect(Collectors.toList());
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("logs/output.txt"));
            for (Uuid uuid : uuids) {
                bufferedWriter.write(uuid.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            log.error("Write error! {}", e.getMessage());
        }
        try {
            int count = (int) Files.lines(Paths.get("logs/output.txt"))
                    .map((s) -> s.replaceAll("[-ABCDEFabcdef]", ""))
                    .map((s) -> {
                        int sum = 0;
                        for (int i = 0; i < s.length(); i++) {
                            sum += Integer.parseInt(String.valueOf(s.charAt(i)));
                        }
                        return sum;
                    })
                    .filter(n -> n > 100)
                    .count();
            log.info("count = {}", count);
        } catch (IOException e) {
            log.error("Read error! {}", e.getMessage());
        }
        ZoneId zoneId = ZoneId.of("Pacific/Pitcairn");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        zonedDateTime = zonedDateTime.withZoneSameInstant(zoneId);
        String stringDateTime = formatter.format(zonedDateTime);
        String n = "00".concat(stringDateTime.split("-")[0]);
        n = n.substring(n.length() - 4);
        String m = n.substring(2);
        n = n.substring(0, 2);
        log.info("N={} M={}", n, m);
        zonedDateTime = zonedDateTime.plusMonths(Integer.parseInt(n));
        zonedDateTime = zonedDateTime.plusDays(Integer.parseInt(m));
        log.info("End of the world time is {}", formatter.format(zonedDateTime));
        try {
            Collection<Sausage> sausages = Files.lines(Paths.get("src/main/resources/File.txt"))
                    .map((s) -> new String(Base64.getDecoder().decode(s)))
                    .map((s) -> {
                        String[] strings = s.split(", ");
                        return new Sausage(
                                strings[0].split("=")[1].replace("'", ""),
                                Integer.parseInt(strings[1].split("=")[1]),
                                Integer.parseInt(strings[2].split("=")[1]));
                    })
                    .collect(Collectors.toList());
            log.info(sausages.toString());
        } catch (IOException e) {
            log.error("Read error! {}", e.getMessage());
        }
    }
}
