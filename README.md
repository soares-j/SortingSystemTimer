# Sorting System Timer

**WORK IN PROGRESS** – Este projeto está desenvolvendo um sistema de ordenação no terminal que mede e exibe o tempo de execução do código em nanosegundos, ticks (1 tick = 100 nanosegundos) e segundos (com duas casas decimais).

## Como funciona

O programa executa uma tarefa repetitiva e utiliza o cronômetro interno do Java (`System.nanoTime()`) para calcular o tempo gasto. Os resultados são exibidos no terminal em três formatos:

- **Nanosegundos**
- **Ticks** (1 tick = 100 nanosegundos)
- **Segundos** (com precisão de duas casas decimais)

## Como executar

1. Compile o arquivo:

   ```
   javac sorting.java
   ```

2. Execute o programa:

   ```
   java sorting
   ```

## Exemplo de saída

```
Executando tarefa...
Executando tarefa...
...
Tempo de execução em nanosegundos: 123456789
Tempo de execução em ticks: 1234567
Tempo de execução em segundos: 0.12
```

## Sobre

Este projeto serve como base para medir o tempo de execução de algoritmos de ordenação e outras tarefas no terminal, facilitando a análise de desempenho.
