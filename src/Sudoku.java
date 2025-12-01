
import java.util.Scanner;

public class Sudoku 
{
    public static void main(String[] args) 
    {
        // CAPTURA O TAMANHO DO SUDOKU (N)
        Scanner lerEntradas = new Scanner(System.in);
        System.out.println("Entre com um valor inteiro para n:");
        int n = lerEntradas.nextInt();
        
        // DEFINE VARIAVEIS PARA GUARDAR OS TEMPOS DE EXECUCAO
        Cronometro tempoCriacaoGrafo = new Cronometro();
        Cronometro tempoResolucao = new Cronometro();
            
        // CRIA O GRAFO QUE REPRESENTA O SUDOKU
        tempoCriacaoGrafo.start();
        Grafo grafo = new Grafo(n);
        tempoCriacaoGrafo.stop();
        
        // INICIALIZA O GRAFO COM VALORES INICIAIS DO SUDOKU (ANEXO NA ESPECIFICAÇÃO DO TRABALHO)
        grafo.inicializarSudoku(n);
        
        // APLICA O ALGORITMO DE RESOLUÇÃO (HEURÍSTICA)
        tempoResolucao.start();
        int violacoes = grafo.aplicarHeuristicaMaisVizinhosEncontradosComFilaDeEespera();
        tempoResolucao.stop();
        
        // SALVA EM ARQUIVO A SOLUÇÃO DO SUDOKU
        grafo.imprimirEmArquivo();
        
        // IMPRIME EM TELA O TEMPO DE EXECUÇÃO DO ALGORITMO E A QUANTIDADE DE VIOLAÇÕES
        System.out.println("Tempo de criação do grafo: "+tempoCriacaoGrafo);
        System.out.println("Tempo de resolução: "+tempoResolucao);
        System.out.println("Quantidade de violações: "+violacoes); 
    }
}
