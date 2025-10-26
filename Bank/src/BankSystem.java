import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BazaKont baza = new BazaKont();
        int wybor;
        int wyborPozyczka;

        do {
            System.out.println("\n=== SYSTEM BANKOWY ===");
            System.out.println("1. Zaloguj się");
            System.out.println("2. Zarejestruj konto");
            System.out.println("3. Wyjście");
            System.out.print("Wybór: ");
            wybor = sc.nextInt();
            sc.nextLine();

            switch (wybor) {
                case 1:
                    System.out.print("Podaj numer konta: ");
                    String numer = sc.nextLine();

                    System.out.print("Podaj PIN: ");
                    int pin = sc.nextInt();

                    Konto konto = baza.zaloguj(numer, pin);
                    if (konto == null) {
                        System.out.println("Niepoprawne dane logowania!");
                        break;
                    }

                    System.out.println("\nWitaj, " + konto.getWlasciciel() + "!");

                    int wyborMenu;
                    do {
                        System.out.println("\n--- MENU ---");
                        System.out.println("1. Sprawdź saldo");
                        System.out.println("2. Wpłata");
                        System.out.println("3. Wypłata");
                        System.out.println("4. Przelew");
                        if (konto instanceof KontoOszczednosciowe)
                            System.out.println("5. Nalicz odsetki");
                        System.out.println("6. Wyloguj");
                        System.out.println("7. Pożyczki");
                        System.out.println("8. Zmień PIN");

                        System.out.print("Wybór: ");
                        wyborMenu = sc.nextInt();

                        switch (wyborMenu) {
                            case 1:
                                konto.pokazSaldo();
                                break;
                            case 2:
                                System.out.print("Kwota wpłaty: ");
                                konto.wplata(sc.nextDouble());
                                break;
                            case 3:
                                System.out.print("Kwota wypłaty: ");
                                konto.wyplata(sc.nextDouble());
                                break;
                            case 4:
                                System.out.print("Podaj numer konta docelowego: ");
                                String numerKontaDocelowego = sc.next();
                                Konto kontoDocelowe = baza.sprawdzanie(numerKontaDocelowego);

                                if (kontoDocelowe == null) {
                                    System.out.println("Niepoprawny numer konta!");
                                } else if (kontoDocelowe == konto) {
                                    System.out.println("Nie możesz przelać na własne konto!");
                                } else {
                                    System.out.print("Podaj kwotę przelewu: ");
                                    double kwota = sc.nextDouble();
                                    konto.wyplata(kwota);
                                    kontoDocelowe.wplata(kwota);
                                    System.out.println("Przelew wykonany!");
                                }
                                break;
                            case 5:
                                if (konto instanceof KontoOszczednosciowe) {
                                    System.out.print("Podaj procent odsetek: ");
                                    ((KontoOszczednosciowe) konto).naliczOdsetki(sc.nextDouble());
                                } else {
                                    System.out.println("To nie jest konto oszczędnościowe!");
                                }
                                break;
                            case 6:
                                System.out.println("Wylogowano!");
                                break;
                            case 7:

                                do {
                                    System.out.println("\n--- MENU POŻYCZEK ---");
                                    System.out.println("1. Weź pożyczkę");
                                    System.out.println("2. Spłać pożyczkę");
                                    System.out.println("3. Cofnij");
                                    System.out.print("Wybór: ");
                                    wyborPozyczka = sc.nextInt();

                                    switch (wyborPozyczka) {
                                        case 1:
                                            System.out.print("Podaj kwotę pożyczki: ");
                                            konto.pozyczkaWez(sc.nextDouble());
                                            break;
                                        case 2:
                                            System.out.print("Podaj kwotę spłaty: ");
                                            konto.pozyczkaSplac(sc.nextDouble());
                                            break;
                                        case 3:
                                            System.out.println("Powrót do menu głównego.");
                                            break;
                                        default:
                                            System.out.println("Niepoprawny wybór.");
                                    }
                                } while (wyborPozyczka != 3);
                                break;
                            case 8:
                                System.out.print("Podaj obecny PIN: ");
                                int staryPin = sc.nextInt();
                                if (konto.sprawdzPin(staryPin)) {
                                    System.out.print("Podaj nowy PIN: ");
                                    int nowyPin = sc.nextInt();
                                    konto.zmienPin(nowyPin);
                                    System.out.println("PIN został zmieniony!");
                                } else {
                                    System.out.println("Błędny stary PIN!");
                                }
                                break;
                            default:
                                System.out.println("Niepoprawna opcja.");
                        }
                    } while (wyborMenu != 6);
                    break;

                case 2:
                    System.out.println("\n--- REJESTRACJA KONTA ---");
                    System.out.print("Podaj imię i nazwisko właściciela: ");
                    String wlasciciel = sc.nextLine();

                    System.out.print("Podaj numer konta: ");
                    String numerNowy = sc.nextLine();

                    System.out.print("Ustal PIN: ");
                    int pinNowy = sc.nextInt();

                    System.out.println("Podaj saldo początkowe: ");
                    double saldo = sc.nextDouble();

                    System.out.println("Podaj typ konta: ");
                    System.out.println("1. Zwykłe");
                    System.out.println("2. Firmowe");
                    System.out.println("3. Oszczędnościowe");
                    System.out.println("4. Anuluj");
                    int wyborKonto = sc.nextInt();
                    Konto noweKonto;

                    switch (wyborKonto){
                        case 1:
                            noweKonto = new KontoOsobiste(numerNowy, pinNowy, saldo, wlasciciel);
                            baza.dodajKonto(noweKonto);
                        case 2:
                            noweKonto = new KontoOszczednosciowe(numerNowy, pinNowy, saldo, wlasciciel);
                            baza.dodajKonto(noweKonto);
                        case 3:
                            noweKonto = new KontoFirmowe(numerNowy, pinNowy, saldo, wlasciciel);
                            baza.dodajKonto(noweKonto);
                        case 4:
                            System.out.println("Do zobaczenia!");
                            break;

                        default:
                            System.out.println("Niepoprawny wybór!");
                    }
            }

        } while (wybor != 3);

        sc.close();
    }
}
