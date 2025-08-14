import java.util.*;
import java.util.function.Consumer;

public class Sorting {

    // Gera uma lista embaralhada de nomes aleatórios
    public static List<String> generateShuffledNames(int quantidade) {
        List<String> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < quantidade; i++) {
            StringBuilder nome = new StringBuilder();
            for (int j = 0; j < 5; j++) { // nomes de 5 letras
                char letra = (char) ('a' + rand.nextInt(26));
                nome.append(letra);
            }
            list.add(nome.toString());
        }
        Collections.shuffle(list);
        return list;
    }

    // Sistema de lista sequencial (a1, a2, ..., aN)
    public static List<String> generateShuffledSequential(int quantidade) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            list.add("a" + i);
        }
        Collections.shuffle(list);
        return list;
    }

    // Bogo Sort
    // Algoritmo ineficiente que embaralha a lista até que ela esteja ordenada.
    // Comente se quer desativar o bogo sort
    // public static void bogoSort(List<String> list) {
    //     Random rand = new Random();
    //     while (!isSorted(list)) {
    //         Collections.shuffle(list, rand);
    //     }
    // }

    // Bubble Sort
    // Compara elementos adjacentes e os troca se estiverem fora de ordem.
    public static void bubbleSort(List<String> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }

    // Selection Sort
    // Seleciona o menor elemento e o coloca na posição correta, repetindo para cada posição.
    public static void selectionSort(List<String> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).compareTo(list.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            Collections.swap(list, i, minIdx);
        }
    }

    // Merge Sort
    // Divide a lista em partes menores, ordena e depois combina (merge) as partes ordenadas.
    public static void mergeSort(List<String> list) {
        if (list.size() <= 1) return;
        int mid = list.size() / 2;
        List<String> left = new ArrayList<>(list.subList(0, mid));
        List<String> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    private static void merge(List<String> list, List<String> left, List<String> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }

    // Verifica se a lista está ordenada
    public static boolean isSorted(List<String> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    // Cronometra e executa o algoritmo de ordenação
    public static void runAndTimeSort(String sortName, List<String> original, Consumer<List<String>> sortMethod) {
        List<String> list = new ArrayList<>(original); // copia para não alterar a original
        long startTicks = System.nanoTime();
        sortMethod.accept(list);
        long endTicks = System.nanoTime();
        long elapsedNanoseconds = endTicks - startTicks;
        long elapsedTicks = elapsedNanoseconds / 100;
        double elapsedSeconds = elapsedNanoseconds / 1_000_000_000.0;

        System.out.println("\n--- " + sortName + " ---");
        System.out.println("Tempo de execução em nanosegundos: " + elapsedNanoseconds);
        System.out.println("Tempo de execução em ticks: " + elapsedTicks);
        System.out.printf("Tempo de execução em segundos: %.2f%n", elapsedSeconds);
        System.out.println("Ordenado: " + isSorted(list)); // escreve se a lista está ordenada (true ou false)
        // System.out.println("Lista ordenada: " + list); // escreve a lista ordenada
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecione o tipo de lista:");
        System.out.println("1 - Nomes aleatórios");
        System.out.println("2 - Sequencial (a1, a2, ... aN)");
        int tipo = scanner.nextInt();

        System.out.print("Digite a quantidade de elementos: ");

        int quantidade = scanner.nextInt();

        System.out.println("\nExecutando tarefa...");
        List<String> shuffledList;
        if (tipo == 1) {
            shuffledList = generateShuffledNames(quantidade);
        } else {
            shuffledList = generateShuffledSequential(quantidade);
        }

        // Bogo Sort (pode demorar muito!)
        // runAndTimeSort("Bogo Sort", shuffledList, Sorting::bogoSort);  // comente se quer desativar o bogo sort

        // Bubble Sort
        runAndTimeSort("Bubble Sort", shuffledList, Sorting::bubbleSort);

        // Selection Sort
        runAndTimeSort("Selection Sort", shuffledList, Sorting::selectionSort);

        // Merge Sort
        runAndTimeSort("Merge Sort", shuffledList, Sorting::mergeSort);

        scanner.close();
    }
}
