public abstract class Konto implements OperacjeBankowe {
    protected String numerKonta;
    protected int pin;
    protected double saldo;
    protected String wlasciciel;
    protected double zaduzenie;

    public Konto(String numerKonta, int pin, double saldo, String wlasciciel) {
        this.numerKonta = numerKonta;
        this.pin = pin;
        this.saldo = saldo;
        this.wlasciciel = wlasciciel;
    }

    public String getNumerKonta() {
        return numerKonta;
    }

    public boolean sprawdzPin(int pin) {
        return this.pin == pin;
    }

    @Override
    public void zmienPin(int nowyPin) {
        if (nowyPin >= 1000 && nowyPin <= 9999) { // prosty warunek, że PIN ma 4 cyfry
            this.pin = nowyPin;
            System.out.println("PIN został pomyślnie zmieniony!");
        } else {
            System.out.println("PIN musi mieć dokładnie 4 cyfry!");
        }
    }

    @Override
    public void wplata(double kwota) {
        saldo += kwota;
        System.out.println("Wpłacono: " + kwota + " PLN");
    }

    @Override
    public void wyplata(double kwota) {
        if (kwota <= saldo) {
            saldo -= kwota;
            System.out.println("Wypłacono: " + kwota + " PLN");
        } else {
            System.out.println("Brak wystarczających środków!");
        }
    }

    @Override
    public void pozyczkaWez(double kwota) {
        if (zaduzenie < saldo * 20) {
            saldo += kwota;
            zaduzenie += kwota + kwota * 0.05;
            System.out.println("Zapożyczono: " + kwota + " PLN");
        } else {
            System.out.println("Brak wystarczających środków!");
        }
    }

    @Override
    public void pozyczkaSplac(double kwota) {
        if (kwota < saldo) {
            saldo -= kwota;
            zaduzenie -= kwota;
            System.out.println("Spłacono: " + kwota + " PLN");
        } else {
            System.out.println("Brak wystarczających środków!");
        }
    }

    @Override
    public void pokazSaldo() {
        System.out.println("Saldo: " + saldo + " PLN" + "\nZadłużenie: " + zaduzenie + " PLN");
    }

    public String getWlasciciel() {
        return wlasciciel;
    }
}
