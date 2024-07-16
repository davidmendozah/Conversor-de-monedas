import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        System.out.println("*******************************************");

        List<Divisas> exchanges = new ArrayList<>();

        int exit = 0;
        while (exit != 1) {

            System.out.println("""
                    Seleccione una opcion:
                    1) Dolar =>> Peso argentino
                    2) Peso argentino =>> Dolar
                    3) Dolar =>> Real brasileño
                    4) Real brasileño =>> Dolar
                    5) Dolar =>> Peso colombiano
                    6) Peso colombiano =>> Dolar
                    7) Salir
                    """);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Elija una opcion valida: ");

            try {
                int exchangeOption = Integer.parseInt(scanner.nextLine());

                String fromCurrency;
                String toCurrency;
                switch (exchangeOption) {
                    case 1:
                        fromCurrency = "USD";
                        toCurrency = "ARS";
                        break;
                    case 2:
                        fromCurrency = "ARS";
                        toCurrency = "USD";
                        break;
                    case 3:
                        fromCurrency = "USD";
                        toCurrency = "BRL";
                        break;
                    case 4:
                        fromCurrency = "BRL";
                        toCurrency = "USD";
                        break;
                    case 5:
                        fromCurrency = "USD";
                        toCurrency = "COP";
                        break;
                    case 6:
                        fromCurrency = "COP";
                        toCurrency = "USD";
                        break;
                    case 7:
                        exit = 1;
                        fromCurrency = null;
                        toCurrency = null;
                        break;
                    default:
                        fromCurrency = null;
                        toCurrency = null;
                        break;
                }

                if (exchangeOption >=1 && exchangeOption <= 6) {
                    BuscadorDeMoneda request = new BuscadorDeMoneda();
                    Moneda exchangeRate = request.currencyExchange(fromCurrency, toCurrency);

                    System.out.println("Ingrese el valor que quiere convertir: ");
                    Double total = Double.valueOf(scanner.nextLine());

                    double newTotal = total * exchangeRate.conversion_rate();

                    System.out.println(total +
                            " [" + fromCurrency + "]" + " son: " + newTotal +
                            " [" + toCurrency + "]");

                    Divisas exchange = new Divisas(fromCurrency,toCurrency,total,newTotal);
                    exchanges.add(exchange);
                } else if (exchangeOption == 7) {
                    System.out.println("Programa terminado\n");
                } else {
                    System.out.println("La opcion seleccionada (" + exchangeOption + ") es invalida");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("La opcion seleccionada " + e.getMessage().toLowerCase() + ", es invalida");
            }
        }
    }
}