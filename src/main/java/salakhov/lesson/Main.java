package salakhov.lesson;

import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private final static org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Collection<Uuid> uuids = Stream.generate(() -> new Uuid())
                .limit(3)
                .collect(Collectors.toList());
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("logs/output.txt"));
            for (Uuid uuid : uuids) {
                bufferedWriter.write(uuid.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            log.error("Write error! " + e);
        }
        try {
            int count = (int) Files.lines(Paths.get("logs/output.txt"))
                    .map((s) -> s.replaceAll("[-ABCDEFabcdef]", ""))
                    .map((s) -> {
                        Integer sum = 0;
                        for (int i = 0; i < s.length(); i++) {
                            sum += Integer.parseInt(String.valueOf(s.charAt(i)));
                        }
                        return sum;
                    })
                    .filter(n -> n > 100)
                    .count();
            log.info("count = " + count);
        } catch (IOException e) {
            log.error("Read error! " + e);
        }
        try {
            Collection<Sausage> sausages = Files.lines(Paths.get("src/main/resources/File.txt"))
                    .map((s) -> new String(Base64.getDecoder().decode(s)))
                    .map((s) -> {
                        String[] strings= s.split(", ");
                        return new Sausage(
                                strings[0].split("=")[1].replace("'",""),
                                (int) Integer.parseInt(strings[1].split("=")[1]),
                                (int) Integer.parseInt(strings[2].split("=")[1]));
                    })
                    .collect(Collectors.toList());
            log.info(sausages.toString());
        } catch (IOException e) {
            log.error("Read error! " + e);
        }
    }
}
