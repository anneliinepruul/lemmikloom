package org.example.demo6;

import javafx.scene.canvas.Canvas;
import java.util.List;
import java.util.Random;

public class Motiveeri {
    private static final String pilt = "/kassipildid/kass_tsitaat.png";
    private static final List<String> tsitaadid = List.of(
            "Sa pole väsinud õppimisest, vaid sellest, et unustad, miks õpid.",
            "Kui sa täna ei õpi, siis keegi teine võtab su unistuste töökoha.",
            "Väsimus pole vabandus – sa lihtsalt ei taha seda piisavalt.",
            "Kui sa praegu ei pinguta, siis oledki lihtsalt keskpärane.",
            "Iga kord, kui puhkad, annab keegi teine endast 200%.",
            "Sa kas õpid valitsema või valmistud alluma.",
            "Edu ei ole inimõigus – see on auhind tugevatele.",
            "Ah, sa saad selle selgeks. Mitte täna. Võib-olla mitte homme. Aga ainult siis, kui sa ei loobu.",
            "Ära karda eksida – kõige hullem, mis juhtuda saab, on see, et sa õpid.",
            "See tunne, et sa ei oska midagi? Kõik, kes midagi oskavad, tundsid sama.",
            "Git commit -m ‘Ma ei tea, kas see töötab, aga proovime!’",
            "Miks puhata? Unenäod ei kompileeru.",
            "Pole hullu, kui sa ei saa asjast aru – lihtsalt tee nägu, et saad, kuni semestri lõpp on käes.",
            "Kui sa tunned, et sa ei oska midagi, siis oledki täpselt IT tudeng."
    );

    public void kuva(Canvas canvas, Main main) {
        String quote = tsitaadid.get(new Random().nextInt(tsitaadid.size()));
        main.kuvaPiltJaTekstAjutiselt(pilt, quote, 20);
    }
}
