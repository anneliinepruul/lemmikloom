package org.example.demo6;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Logija {
    private static final String failiNimi = "logi.txt";

    public static void logi(String tegevus) {
        String aeg = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String rida = aeg + " - " + tegevus;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(failiNimi, true))) {
            writer.write(rida);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loKoguLogi() {
        try {
            return Files.readAllLines(Paths.get(failiNimi));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public static void puhastaLogi() {
        try {
            Files.write(Paths.get(failiNimi), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
