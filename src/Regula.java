import java.util.*;

public class Regula{
    public Map<Integer, String> deskryptoryReguly = new HashMap<Integer, String>();
    public String decyzja;
    public int support;


    public Regula(String decyzja){
        this.decyzja = decyzja;
    }

    public boolean CzySpelnia(String[] obiektDecyzyjny) {

        for (Map.Entry<Integer, String> entry : deskryptoryReguly.entrySet()) { // sprawdzamy deskryptory reguly foreach
            Integer key = entry.getKey();                       // klucz deskryptora reguly
            String value = entry.getValue();                    // wartosc deskryptora reguly
            if (!value.equals(obiektDecyzyjny[key])) {          // jesli deskryptor obiektu nie jest taki sam jak reguly, to false odrazu
                return false;
            }
        }
        return true;
    }

    public boolean CzyJestSprzeczna(String[][] systemDecyzyjny) {
        for(int i = 0; i<systemDecyzyjny.length; i++) {
            if (CzySpelnia(systemDecyzyjny[i]) && !systemDecyzyjny[i][systemDecyzyjny[i].length - 1].equals(decyzja)) { // JESLI obiekt spelnia regule i JESLI decyzja SA INNE to regula jest SPRZECZNA!!
                return true; // jesli znajdzie chociaz jeden kontrprzyklad, to bedzie prawda
            }
        }
        return false;
    }


    public boolean czyTakaRegulaJuzJest(List<Regula> niesprzeczneReguly) {

        for (Regula niesprzecznaRegula : niesprzeczneReguly) {
            if (niesprzecznaRegula.deskryptoryReguly.entrySet().containsAll(deskryptoryReguly.entrySet())) { // porownuje wszystko, klucze i value
                niesprzecznaRegula.support++;
                return true;
            }
        }
        return false;
    }

    public String getDeskryptoryReguly(Integer id) {
        return deskryptoryReguly.get(id);
    }

    public void setDeskryptoryReguly(Map<Integer, String> deskryptoryReguly) {
        this.deskryptoryReguly = deskryptoryReguly;
    }

    public String getDecyzja() {
        return decyzja;
    }

    public void setDecyzja(String decyzja) {
        this.decyzja = decyzja;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }
}