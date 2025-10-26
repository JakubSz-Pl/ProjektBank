public class KontoFirmowe extends Konto {
    public KontoFirmowe(String numerKonta, int pin, double saldo, String wlasciciel) {
        super(numerKonta, pin, saldo, wlasciciel);
    }

    @Override
    public void wyplata(double kwota) {
        double prowizja = kwota * 0.02;
        double suma = kwota + prowizja;
        if (suma <= saldo) {
            saldo -= suma;
            System.out.println("Wypłacono: " + kwota + " PLN (prowizja: " + prowizja + " PLN)");
        } else {
            System.out.println("Brak środków na wypłatę z prowizją!");
        }
    }
}
