import java.util.ArrayList;

public class BazaKont {
    private ArrayList<Konto> konta = new ArrayList<>();
    public BazaKont() {
        konta.add(new KontoOsobiste("1111", 1234, 1000.0, "Mateusz Frątczak"));
        konta.add(new KontoFirmowe("2222", 4321, 5000.0, "TAU"));
        konta.add(new KontoOszczednosciowe("3333", 9999, 3000.0, "Jakub Szymański"));
    }

    public Konto zaloguj(String numer, int pin) {
        for (Konto k : konta) {
            if (k.getNumerKonta().equals(numer) && k.sprawdzPin(pin)) {
                return k;
            }
        }
        return null;
    }

    public Konto sprawdzanie(String numer) {
        for (Konto k : konta) {
            if (k.getNumerKonta().equals(numer)) {
                return k;
            }
        }
        return null;
    }

    public void dodajKonto(Konto konto) {
        konta.add(konto);
        System.out.println("Nowe konto zostało dodane do bazy!");
    }
}
