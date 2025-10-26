public interface OperacjeBankowe {
    void wplata(double kwota);
    void wyplata(double kwota);
    void pozyczkaWez(double kwota);
    void pozyczkaSplac(double kwota);
    void pokazSaldo();
    void zmienPin(int nowyPin);
}
