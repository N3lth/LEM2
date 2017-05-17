import java.util.*;

/**
 * Created by Mateusz Grankowski on 2017-05-01.
 */
public class Main {
    public static void main(String[] args) {

        // 1.
        String[][] system = {
                {"2", "6", "1", "2", "3", "1"},
                {"1", "1", "1", "3", "2", "1"},
                {"2", "1", "1", "2", "3", "1"},
                {"4", "1", "3", "1", "2", "1"},
                {"3", "5", "2", "1", "3", "2"},
                {"3", "1", "3", "1", "1", "2"},
                {"1", "1", "1", "3", "1", "2"}
        };
        List<Regula> niesprzeczneReguly = new LinkedList<>();
        // 2.
        List<String> decyzje = new LinkedList<>();
        for (String[] wiersz : system) {
            if (!decyzje.contains(wiersz[wiersz.length - 1])) {
                decyzje.add(wiersz[wiersz.length - 1]);
            }
        }
        // 3.
        for (String koncept : decyzje) {
            // 4. [lista 1]
            List<String[]> obiektyBezReguly = new LinkedList<>();

            for (String[] obiektSystemu : system) {
                if (obiektSystemu[obiektSystemu.length - 1].equals(koncept))
                    obiektyBezReguly.add(obiektSystemu);

                // 5. [lista 1]
                while (obiektyBezReguly.size() > 0) {
                    // 5. a) [lista 2] <- [lista 1]
                    List<String[]> listaObiektowDlaKtorychSzukamyReguly = new LinkedList<>();
                    listaObiektowDlaKtorychSzukamyReguly.addAll(obiektyBezReguly);
                    // 5. b)
                    List<Integer> atrybutyWylaczoneZPrzeszukiwania = new LinkedList<>();
                    // 5. c)
                    Regula r = new Regula(koncept);
                    // 5. d)
                    while (listaObiektowDlaKtorychSzukamyReguly.size() > 0) {
                        // 5. d) a)
                        AbstractMap.SimpleEntry<Integer, String> deskryptor = NajczestszyDeskryptor.Znajdz(listaObiektowDlaKtorychSzukamyReguly, atrybutyWylaczoneZPrzeszukiwania);
                        // 5. d) b)
                        r.deskryptoryReguly.put(deskryptor.getKey(), deskryptor.getValue());
                        // 5. d) c)
                        List<String[]> obiektyDoUsunieciaNaRzeczZawezeniaZbioruPrzeszukiwan = new LinkedList<>();
                        for (String[] obiekt : listaObiektowDlaKtorychSzukamyReguly) {
                            if (!obiekt[deskryptor.getKey()].equals(deskryptor.getValue())) {
                                obiektyDoUsunieciaNaRzeczZawezeniaZbioruPrzeszukiwan.add(obiekt);
                            }
                        }
                        // 5. d) d)
                        listaObiektowDlaKtorychSzukamyReguly.removeAll(obiektyDoUsunieciaNaRzeczZawezeniaZbioruPrzeszukiwan);
                        // 5. d) e)
                        if (!r.CzyJestSprzeczna(system)) {
                            // 5. d) e) a)
                            if(!r.czyTakaRegulaJuzJest(niesprzeczneReguly)) {
                                niesprzeczneReguly.add(r);
                                r.setSupport(1);
                            }
                            obiektyBezReguly.removeAll(listaObiektowDlaKtorychSzukamyReguly);
                            break;
                            // 5. d) e) b)
                        } else {
                            atrybutyWylaczoneZPrzeszukiwania.add(deskryptor.getKey());
                        }

                    }
                }
            }
        }
        System.out.println("\n\n\nNiesprzeczne reguly LEM2: ");
        for (Regula regula : niesprzeczneReguly) {
            for (Map.Entry<Integer, String> entry : regula.deskryptoryReguly.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                System.out.print("( a" + (key + 1) + " = " + value + " )");
            }
            System.out.print(" => ( d = " + regula.decyzja + " )[" + regula.getSupport() + "]\n");
        }
    }

}
