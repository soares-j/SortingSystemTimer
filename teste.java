import java.util.*;
import java.util.function.Consumer;

public class teste {

    // Gera uma lista embaralhada de nomes aleatórios (caso médio)
    // Complexidade: O(n) para gerar e embaralhar
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

    // Gera lista ordenada (melhor caso)
    // Complexidade: O(n)
    public static List<String> generateOrderedSequential(int quantidade) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            list.add("a" + i);
        }
        return list;
    }

    // Gera lista reversa (pior caso)
    // Complexidade: O(n)
    public static List<String> generateReverseSequential(int quantidade) {
        List<String> list = new ArrayList<>();
        for (int i = quantidade; i >= 1; i--) {
            list.add("a" + i);
        }
        return list;
    }

    // Bogo Sort (comentado)
    // Complexidade: O((n+1)!) no pior caso (extremamente ineficiente)
    // public static void bogoSort(List<String> list) {
    //     Random rand = new Random();
    //     while (!isSorted(list)) {
    //         Collections.shuffle(list, rand);
    //     }
    // }

    // Selection Sort
    // Complexidade: O(n^2) para todos os casos
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

    // Verifica se a lista está ordenada
    // Complexidade: O(n)
    public static boolean isSorted(List<String> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    // Cronometra e executa o algoritmo de ordenação
    // Complexidade: depende do algoritmo passado
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
        System.out.println("Ordenado: " + isSorted(list));
        // System.out.println("Lista ordenada: " + list);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de elementos: ");
        int quantidade = scanner.nextInt();

        // Melhor caso: lista ordenada
        List<String> orderedList = generateOrderedSequential(quantidade);
        runAndTimeSort("Selection Sort (Melhor caso)", orderedList, teste::selectionSort);

        // Caso médio: lista embaralhada
        List<String> shuffledList = generateShuffledNames(quantidade);
        runAndTimeSort("Selection Sort (Caso médio)", shuffledList, teste::selectionSort);

        // Pior caso: lista reversa
        List<String> reverseList = generateReverseSequential(quantidade);
        runAndTimeSort("Selection Sort (Pior caso)", reverseList, teste::selectionSort);

        // Bogo Sort (caso queira testar, descomente)
        // runAndTimeSort("Bogo Sort", shuffledList, teste::bogoSort);

        scanner.close();
    }
}