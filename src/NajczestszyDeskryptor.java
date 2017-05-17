import java.util.*;

/**
 * Created by Mateusz Grankowski on 2017-05-01.
 */

public class NajczestszyDeskryptor {
    public static AbstractMap.SimpleEntry<Integer, String> Znajdz(List<String[]> listaObiektow, List<Integer> listaAtrybutowWylaczonychZPrzeszukiwania) {

        // Kluczem jest mapa deskryptora (nr atrybutu i wartosc), a wartoscia ilosc jego wystapien
        Map<AbstractMap.SimpleEntry<Integer, String>, Integer> deskryptoryILiczbaIchWystapien = new LinkedHashMap<>();

        // Przerabiam na tablice, zeby mozna bylo prosciej dostac sie do kolumny.
        String[][] tablicaObiektow = new String[listaObiektow.size()][];
        tablicaObiektow = listaObiektow.toArray(tablicaObiektow);

        // Szukam najczestszego deskryptora w kazdej kolumnie
        for (int i = 0; i < tablicaObiektow[0].length - 1; i++) {
            if (listaAtrybutowWylaczonychZPrzeszukiwania.contains(i)) {
                continue;
            }

            // Pobieram kolumne
            String[] kolumna = new String[tablicaObiektow.length];
            for (int j = 0; j < tablicaObiektow.length; j++)
                kolumna[j] = tablicaObiektow[j][i];

            // Sprawdzam w mapie, czy dany deskryptor wystapil juz w kolumnie. Jesli nie, to dodaje go do mapy, zas jesli tak, to zwiekszam czestosc o 1.
            for (String deskryptor : kolumna) {
                if (!deskryptoryILiczbaIchWystapien.containsKey(new AbstractMap.SimpleEntry<>(i, deskryptor))) {
                    deskryptoryILiczbaIchWystapien.put(new AbstractMap.SimpleEntry<>(i, deskryptor), 1);
                } else {
                    // Nizej dziwny myk, ale dziala. Inkrementacja Integera domyslnie jest niemozliwa, bo jest to rodzaj immutable. Ponizszy sposob polega na nadpisaniu elementu w mapie.
                    deskryptoryILiczbaIchWystapien.put(new AbstractMap.SimpleEntry<>(i, deskryptor), deskryptoryILiczbaIchWystapien.get(new AbstractMap.SimpleEntry<>(i, deskryptor))+1);
                }
            }
        }
            // Wyszukuje najwiekszej czestosci
            Collection<Integer> liczbyWystapienDeskryptorow = deskryptoryILiczbaIchWystapien.values();
            Integer iloscNajczestszegoWystapienia = Collections.max(liczbyWystapienDeskryptorow); // < - max()

            // Znajduje pierwsza czestosc, ktora jest rowna najwiekszej i oddaje ja do wyniku
            for (Map.Entry<AbstractMap.SimpleEntry<Integer, String>, Integer> entry : deskryptoryILiczbaIchWystapien.entrySet()) {
                AbstractMap.SimpleEntry deskryptor = entry.getKey();
                Integer czestosc = entry.getValue();
                if (czestosc.equals(iloscNajczestszegoWystapienia)) {
                    AbstractMap.SimpleEntry<Integer, String> czestoscIDeskryptor = new AbstractMap.SimpleEntry<>((Integer) deskryptor.getKey(), (String)deskryptor.getValue());
                    return czestoscIDeskryptor;
                }
            }
        return null;
    }
}
