public class KontoOszczednosciowe extends Konto {
    public KontoOszczednosciowe(String numerKonta, int pin, double saldo, String wlasciciel) {
        super(numerKonta, pin, saldo, wlasciciel);
    }

    public void naliczOdsetki(double procent) {
        double odsetki = saldo * (procent / 100);
        saldo += odsetki;
        System.out.println("Naliczono odsetki: " + odsetki + " PLN");
    }
}
