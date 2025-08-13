public class sorting {

    public static void main(String[] args) {
        long startTicks = System.nanoTime(); // Inicia o cronômetro em nanosegundos

        // Código a ser executado
        for (int i = 0; i < 100000; i++) {
            System.out.println("Executando tarefa...");
        }

        long endTicks = System.nanoTime(); // Finaliza o cronômetro
        long elapsedNanoseconds = endTicks - startTicks; // Tempo passado em nanosegundos

        long elapsedTicks = elapsedNanoseconds / 100; // 1 tick = 100 nanosegundos
        double elapsedSeconds = elapsedNanoseconds / 1_000_000_000.0; // 1 segundo = 1.000.000.000 nanosegundos

        System.out.println("Tempo de execução em nanosegundos: " + elapsedNanoseconds);
        System.out.println("Tempo de execução em ticks: " + elapsedTicks);
        System.out.printf("Tempo de execução em segundos: %.2f%n", elapsedSeconds); // Exibe com 2 decimais
    }
}
