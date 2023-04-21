import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int globalId;
    public static List<Toy> toys;
    public static List<String> prizeToy;

    public Main() {
    }

    public static void main(String[] args) {
        init();
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        prizeToy = new ArrayList<>();
        while (k != 4) {
            System.out.println(
                    "Выберите 1 для добавления игрушки, \n 2 - для розыгрыша, \n 3 - для получения, \n 4 - для выхода из программы\n");
            k = scanner.nextInt();
            switch (k) {
                case (1): {
                    addToys();
                    break;
                }
                case (2): {
                    playToy();
                    printPlayToy();
                    break;
                }
                case (3): {
                    System.out.println(prizeToy);
                    if (!prizeToy.isEmpty())
                        getToy();
                    else
                        System.out.println("Выигрышных игрушек нет");
                    break;
                }
                case (4): {
                    break;
                }
                default:
                    System.out.println("Введено некоректное значение, попробуйте снова");
            }
            // printToys();
        }
    }

    private static void printPlayToy() {
        System.out.println("Список всех выигрышей: ");
        for (int i = 0; i < prizeToy.size(); i++) {
            System.out.println("   " + prizeToy.get(i));
        }
        System.out.println();
        System.out.println("___________________________________");
    }

    private static void getToy() {
        File file = new File("winToys.txt");

        try {
            if (!file.exists()) {
                System.out.println("Создался файл: winToys.txt");
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(prizeToy.get(0) + "\n");
            prizeToy.remove(0);
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать в файл", e);
        }
        System.out.println("Copy successfully");
    }

    private static void playToy() {
        Random r = new Random();
        double randomValue = 0 + (canTotalWeight() - 0) * r.nextDouble();
        double temp = toys.get(0).getWeight();
        if (randomValue <= temp) {
            System.out.println("Вы проиграли ");
            return;
        }
        for (int i = 1; i < toys.size(); i++) {
            if (toys.get(i).getAmount() > 0)
                temp += toys.get(i).getWeight();
            if (randomValue <= temp) {
                prizeToy.add(toys.get(i).getName());
                toys.get(i).setAmount(toys.get(i).getAmount() - 1);
                System.out.println("Вы выиграли: ");
                System.out.println(toys.get(i).getName());
                System.out.println();
                return;
            }
        }
    }

    private static double canTotalWeight() {
        double totalWeight = toys.get(0).getWeight();
        for (int i = 1; i < toys.size(); i++)
            if (toys.get(i).getAmount() > 0)
                totalWeight += toys.get(i).getWeight();
        return totalWeight;
    }

    private static void init() {
        toys = new ArrayList();
        globalId = 0;
        toys.add(new Toy(globalId++, null, 1, 10)); // вероятность того, что при розыгрыше игрушка не выпадет
        toys.add(new Toy(globalId++, "Duck", 10, 5));
        toys.add(new Toy(globalId++, "Shark", 3, 15));
        toys.add(new Toy(globalId++, "TeddyBear", 6, 18));
    }

    private static void addToys() {
        System.out.println("Введите название игрушки");
        Scanner sc = new Scanner(System.in);
        String nameToys = sc.nextLine();
        while (nameToys.isEmpty()) {
            System.out.println("Введены некоректные данные. Введите название игрушки снова: ");
            nameToys = sc.nextLine();
        }
        System.out.println("Введите количество игрушек");
        int count = sc.nextInt();
        while (count < 0) {
            System.out.println("Количество введено некорректно, попробуйте ввести снова: ");
            count = sc.nextInt();
        }
        System.out.println("Введите вероятность");
        double weight = sc.nextDouble();
        while (weight < 0) {
            System.out.println("Вес (вероятность) введены некорректно. Введите вероятность снова: ");
            weight = sc.nextDouble();
        }
        toys.add(new Toy(globalId++, nameToys, count, weight));
    }

    private static void printToys() {
        for (int i = 0; i < toys.size(); i++) {
            System.out.println(toys.get(i).getInfo());
        }
        System.out.println("___________________________________");
    }
}