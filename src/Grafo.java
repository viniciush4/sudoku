
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Grafo 
{
    // MATRIZ (NXN) DE VÉRTICES
    private final Vertice matriz[][];
    
    // CRIA UM GRAFO DO TIPO MALHA (N X N)
    public Grafo (int n)
    {
        int contadorIndice = 0;
        
        // INICIALIZA A MATRIZ
        this.matriz = new Vertice[n*n][n*n];
        
        // PREENCHE A MATRIZ COM VÉRTICES
        for(int i=0; i<n*n; i++)
        {
            for(int j=0; j<n*n; j++)
            {
                this.matriz[i][j] = new Vertice(contadorIndice);
                contadorIndice++;
            }
        }
        
        // ADICIONA RELACIONAMENTOS (ARESTAS) ENTRE OS VERTICES
        for(int i=0; i<n*n; i++)
        {
            for(int j=0; j<n*n; j++)
            {
                for(int k=0; k<n*n; k++)
                {
                    // ADJACENTES NA MESMA LINHA
                    if(k!=i)
                    {
                        matriz[i][j].adicionarVerticeAdjacente(matriz[k][j]);
                    }
                    
                    // ADJACENTES NA MESMA COLUNA
                    if(k!=j)
                    {
                        matriz[i][j].adicionarVerticeAdjacente(matriz[i][k]);
                    }
                }
                
                int quadrantei = i/n;
                int quadrantej = j/n;
                
                // ADJACENTES NO MESMO QUADRANTE
                for(int k=quadrantei*n; k<quadrantei*n + n; k++)
                {
                    for(int m=quadrantej*n; m<quadrantej*n + n; m++)
                    {
                        matriz[i][j].adicionarVerticeAdjacente(matriz[k][m]);
                    }
                }
            }
        }
    }
    
    // IMPRIME O SUDOKU EM ARQUIVO
    public void imprimirEmArquivo()
    {
        int n = this.matriz.length;
        
        try (PrintStream escreverSudoku = new PrintStream("sudoku ("+(int)Math.sqrt(n)+").txt")) 
        {
            escreverSudoku.println();
            escreverSudoku.println("##################################################");
            escreverSudoku.println("################# Solução Sudoku #################");
            escreverSudoku.println("##################################################");
            escreverSudoku.println();
            
            for(int i=0; i<n; i++)
            {
                if(i % Math.sqrt(n) == 0 && i!=0)
                {
                    escreverSudoku.println();
                    for(int k=0; k<n+(int)Math.sqrt(n)-1; k++)
                    {
                        escreverSudoku.print("__\t");
                    }
                    escreverSudoku.println();
                }
                escreverSudoku.println();
                
                for(int j=0; j<n; j++)
                {
                    if(j % Math.sqrt(n) == 0 && j!=0)
                    {
                        escreverSudoku.print("|\t");
                    }
                    escreverSudoku.print(this.matriz[i][j].getCor()+"\t");
                }
            }
            
            escreverSudoku.close();

        }
        catch(IOException e)
        {
            System.err.println("Erro ao criar arquivo de saída");
            System.exit(0);
        }
    }
    
    // RETORNA A QUANTIDADE DE VERTICES PREENCHIDOS (COLORIDOS) DO SUDOKU
    private int getVerticesPreenchidos()
    {
        int n = this.matriz.length;
        int verticesPreenchidos = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(matriz[i][j].getCor()!=0)
                {
                    verticesPreenchidos++;
                }
            }
        }
        return verticesPreenchidos;
    }
    
    // RETORNA A QUANTIDADE DE VIZINHOS NÃO PREENCHIDOS (NÃO COLORIDOS) DE UM DADO VERTICE
    private int getQuantidadeVizinhosPreenchidosVertice(Vertice vertice)
    {
        int vizinhosPreenchidos = 0;
        for(Vertice v : vertice.getVerticesAdjacentes())
        {
           if(v.getCor()!=0)
           {
               vizinhosPreenchidos++;
           }
        }
        return vizinhosPreenchidos;
    }
    
    // RETORNA TRUE CASO NÃO EXISTA VIZINHO COM A COR ESPECIFICADA
    private static boolean verificarPossibilidadeCor(List<Vertice> listaAdjacencias, int cor)
    {
        // PERCORRE A LISTA DE ADJACENCIAS DO VERTICE QUE ESTÁ NA POSIÇÃO (I,J)
        for(Vertice v : listaAdjacencias)
        {
            if(v.getCor() == cor)
            {
                return false;
            }
        }
        return true;
    }
    
    // INICIALIZA UM SUDOKU COM AS INSTANCIAS FORNECIDAS NA ESPECIFICAÇÃO DO TRABALHO
    public void inicializarSudoku(int n)
    {
        if(n == 2)
        {
            this.matriz[1][0].setCor(4);
        }
        if(n == 3)
        {
            this.matriz[8][2].setCor(7);
            this.matriz[4][3].setCor(1);
            this.matriz[0][5].setCor(7);
            this.matriz[1][6].setCor(5);
            this.matriz[3][6].setCor(4);
            this.matriz[8][6].setCor(9);
            this.matriz[4][7].setCor(5);
            this.matriz[8][7].setCor(1);
        }
        if(n == 4)
        {
            this.matriz[2][0].setCor(1);
            this.matriz[7][0].setCor(12);
            this.matriz[11][0].setCor(14);
            this.matriz[7][1].setCor(5);
            this.matriz[0][4].setCor(16);
            this.matriz[11][4].setCor(4);
            this.matriz[13][4].setCor(15);
            this.matriz[1][5].setCor(15);
            this.matriz[7][6].setCor(7);
            this.matriz[12][6].setCor(5);
            this.matriz[4][7].setCor(12);
            this.matriz[0][9].setCor(4);
            this.matriz[2][9].setCor(15);
            this.matriz[7][9].setCor(3);
            this.matriz[0][10].setCor(8);
            this.matriz[0][12].setCor(13);
            this.matriz[7][13].setCor(10);
            this.matriz[9][13].setCor(1);
            this.matriz[13][13].setCor(8);
            this.matriz[15][13].setCor(6);
            this.matriz[2][14].setCor(12);
            this.matriz[5][15].setCor(8);
            this.matriz[6][15].setCor(12);
            this.matriz[10][15].setCor(14);
        }
        if(n == 5)
        {
            this.matriz[13][0].setCor(21);
            this.matriz[20][0].setCor(24);
            this.matriz[20][1].setCor(10);
            this.matriz[9][2].setCor(15);
            this.matriz[11][2].setCor(9);
            this.matriz[16][2].setCor(20);
            this.matriz[17][2].setCor(11);
            this.matriz[20][3].setCor(22);
            this.matriz[22][3].setCor(11);
            this.matriz[1][4].setCor(20);
            this.matriz[4][4].setCor(7);
            this.matriz[9][4].setCor(8);
            this.matriz[11][5].setCor(19);
            this.matriz[24][5].setCor(25);
            this.matriz[1][6].setCor(24);
            this.matriz[5][6].setCor(15);
            this.matriz[17][6].setCor(13);
            this.matriz[8][7].setCor(4);
            this.matriz[9][8].setCor(12);
            this.matriz[6][9].setCor(19);
            this.matriz[9][9].setCor(21);
            this.matriz[22][9].setCor(9);
            this.matriz[8][10].setCor(20);
            this.matriz[12][10].setCor(19);
            this.matriz[1][11].setCor(3);
            this.matriz[16][11].setCor(7);
            this.matriz[17][11].setCor(19);
            this.matriz[23][11].setCor(14);
            this.matriz[7][12].setCor(24);
            this.matriz[10][12].setCor(25);
            this.matriz[15][13].setCor(25);
            this.matriz[2][14].setCor(22);
            this.matriz[12][14].setCor(10);
            this.matriz[24][14].setCor(6);
            this.matriz[5][15].setCor(6);
            this.matriz[2][16].setCor(3);
            this.matriz[4][16].setCor(17);
            this.matriz[16][17].setCor(1);
            this.matriz[19][17].setCor(20);
            this.matriz[2][18].setCor(7);
            this.matriz[18][18].setCor(16);
            this.matriz[1][19].setCor(1);
            this.matriz[2][19].setCor(2);
            this.matriz[11][19].setCor(21);
            this.matriz[2][20].setCor(25);
            this.matriz[6][20].setCor(6);
            this.matriz[19][20].setCor(10);
            this.matriz[1][21].setCor(18);
            this.matriz[21][21].setCor(23);
            this.matriz[0][22].setCor(13);
            this.matriz[2][22].setCor(21);
            this.matriz[17][22].setCor(1);
            this.matriz[18][22].setCor(2);
            this.matriz[8][23].setCor(8);
            this.matriz[12][23].setCor(12);
            this.matriz[19][23].setCor(16);
            this.matriz[0][24].setCor(9);
            this.matriz[16][24].setCor(18);
        }
    }
    
    // HEURISTICA GULOSA SIMPLES
    public int aplicarHeuristicaGulosaSimples()
    {
        int n = this.matriz.length;
        List<Integer> listaCores = new LinkedList<>();
        int violacoes = 0;
        
        // CRIA VETOR DE CORES
        for(int i=0; i<n; i++)
        {
            listaCores.add(i+1);
        }
        
        // PARA CADA VERTICE
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                // SE O VERTICE NÃO POSSUI COR AINDA
                if(this.matriz[i][j].getCor() == 0)
                {
                    int quantidadeVizinhosCoresDiferentes = 0;
                
                    // PARA CADA COR
                    for(Integer cor : listaCores)
                    {
                        // SE NÃO EXISTE VIZINHO COM ESSA COR
                        if(verificarPossibilidadeCor(matriz[i][j].getVerticesAdjacentes(), cor))
                        {
                            // SETA A COR DO VERTICE
                            this.matriz[i][j].setCor(cor);
                            break;
                        }
                        quantidadeVizinhosCoresDiferentes++;
                    }
                    
                    // SE TODAS AS CORES JÁ ESTÃO SENDO USADAS PELOS VIZINHOS DO VERTICE
                    if(quantidadeVizinhosCoresDiferentes == listaCores.size())
                    {
                        // SETA ALGUMA COR ALEATÓRIA PARA O VERTICE
                        Random random = new Random();
                        this.matriz[i][j].setCor(listaCores.get(random.nextInt(listaCores.size())));

                        // CONTABILIZA VIOLACAO
                        violacoes++;
                    }
                }
            }
        }        
        return violacoes;
    }

    // ALGORITMO MAIS VIZINHOS ENCONTRADOS
    public int aplicarHeuristicaMaisVizinhosEncontrados()
    {
        int n = this.matriz.length;
        List<Integer> listaCores = new LinkedList<>();
        int violacoes = 0;

        // QUANTIDADE DE VERTICES PREENCHIDOS NA MATRIZ
        int verticesPreenchidos = this.getVerticesPreenchidos();
                
        // CRIA VETOR DE CORES
        for(int i=0; i<n; i++)
        {
            listaCores.add(i+1);
        }
        
        // ENQUANTO TODOS OS VERTICES NÃO FOREM COLORIDOS
        while(verticesPreenchidos < n*n)
        {
            // VERTICE COM MAIS VIZINHOS PREENCHIDOS
            Vertice verticeMaisVizinhosPreenchidos = null;
            int quantidadeVizinhosPreenchidosVertice = 0;
            
            // RODA A MATRIZ UMA VEZ E ENCONTRA O VERTICE COM 
            // MAIOR NUMERO DE VIZINHOS PREENCHIDOS
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    if(this.matriz[i][j].getCor()==0)
                    {
                        int quantidadeVizinhosPreenchidos = getQuantidadeVizinhosPreenchidosVertice(this.matriz[i][j]);
                    
                        if(verticeMaisVizinhosPreenchidos == null || (quantidadeVizinhosPreenchidos > quantidadeVizinhosPreenchidosVertice))
                        {
                            verticeMaisVizinhosPreenchidos = this.matriz[i][j];
                            quantidadeVizinhosPreenchidosVertice = quantidadeVizinhosPreenchidos;
                        }
                    }
                }
            }
                        
            // SE O VERTICE NÃO POSSUI COR AINDA
            if(verticeMaisVizinhosPreenchidos.getCor() == 0)
            {
                int quantidadeVizinhosCoresDiferentes = 0;

                // PARA CADA COR
                for(Integer cor : listaCores)
                {
                    // SE NÃO EXISTE VIZINHO COM ESSA COR
                    if(verificarPossibilidadeCor(verticeMaisVizinhosPreenchidos.getVerticesAdjacentes(), cor))
                    {
                        // SETA A COR DO VERTICE
                        verticeMaisVizinhosPreenchidos.setCor(cor);
                        verticesPreenchidos++;
                        break;
                    }
                    quantidadeVizinhosCoresDiferentes++;
                }

                // SE TODAS AS CORES JÁ ESTÃO SENDO USADAS
                if(quantidadeVizinhosCoresDiferentes == listaCores.size())
                {
                    // SETA ALGUMA COR ALEATÓRIA PARA O VERTICE
                    Random random = new Random();
                    verticeMaisVizinhosPreenchidos.setCor(listaCores.get(random.nextInt(listaCores.size())));
                    verticesPreenchidos++;

                    // CONTABILIZA VIOLACAO
                    violacoes++;
                }
            }
 
        }  
        return violacoes;
    }
    
    // ALGORITMO MAIS VIZINHOS ENCONTRADOS COM FILA DE ESPERA
    public int aplicarHeuristicaMaisVizinhosEncontradosComFilaDeEespera()
    {
        int n = this.matriz.length;
        List<Integer> listaCores = new LinkedList<>();
        int violacoes = 0;
        Queue<Vertice> filaEspera = new LinkedList<>();

        // QUANTIDADE DE VERTICES INICIALMENTE PREENCHIDOS NA MATRIZ
        int verticesPreenchidos = this.getVerticesPreenchidos();
                
        // CRIA VETOR DE CORES
        for(int i=0; i<n; i++)
        {
            listaCores.add(i+1);
        }
        
        // ENQUANTO HOUVER VÉRTICES SEM COR
        while(verticesPreenchidos+filaEspera.size() < n*n)
        {
            // VERTICE COM MAIS VIZINHOS PREENCHIDOS
            Vertice verticeMaisVizinhosPreenchidos = null;
            int quantidadeVizinhosPreenchidosVertice = 0;
            
            // RODA A MATRIZ UMA VEZ E ENCONTRA O VERTICE SEM
            // COR COM MAIOR NUMERO DE VIZINHOS PREENCHIDOS
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    // SE FOR UM VERTICE SEM COR E NÃO ESTIVER NA LISTA DE ESPERA
                    if(this.matriz[i][j].getCor()==0 && !filaEspera.contains(this.matriz[i][j]))
                    {
                        // GUARDA A QUANTIDADE DE VIZINHOS PREENCHIDOS DO VERTICE
                        int quantidadeVizinhosPreenchidos = getQuantidadeVizinhosPreenchidosVertice(this.matriz[i][j]);
                        
                        // SE A QUANTIDADE DE VIZINHOS PREENCHIDOS DESTE VERTICE FOR MAIOR QUE A DO VERTICE ANTERIORMENTE ENCONTRADO
                        if(verticeMaisVizinhosPreenchidos == null || (quantidadeVizinhosPreenchidos > quantidadeVizinhosPreenchidosVertice))
                        {
                            // ATUALIZA O VERTICE COM MAIS VIZINHOS PREENCHIDOS
                            verticeMaisVizinhosPreenchidos = this.matriz[i][j];
                            quantidadeVizinhosPreenchidosVertice = quantidadeVizinhosPreenchidos;
                        }
                    }
                }
            }
            
            // SE O VERTICE NÃO POSSUI COR AINDA
            if(verticeMaisVizinhosPreenchidos.getCor() == 0)
            {
                int quantidadeVizinhosCoresDiferentes = 0;

                // PARA CADA COR
                for(Integer cor : listaCores)
                {
                    // SE NÃO EXISTE VIZINHO COM ESSA COR
                    if(verificarPossibilidadeCor(verticeMaisVizinhosPreenchidos.getVerticesAdjacentes(), cor))
                    {
                        // SETA A COR DO VERTICE
                        verticeMaisVizinhosPreenchidos.setCor(cor);
                        verticesPreenchidos++;
                        break;
                    }
                    quantidadeVizinhosCoresDiferentes++;
                }

                // SE TODAS AS CORES JÁ ESTÃO SENDO USADAS
                if(quantidadeVizinhosCoresDiferentes == listaCores.size())
                {
                    // COLOCA O VERTICE NA LISTA DE ESPERA
                    filaEspera.add(verticeMaisVizinhosPreenchidos);
                }
            }
        }
        
        // PREENCHE TODOS OS VERTICES QUE ESTÃO NA LISTA DE ESPERA
        for(Vertice v : filaEspera)
        {
            Random random = new Random();
            v.setCor(listaCores.get(random.nextInt(listaCores.size())));
            violacoes++;
        }
             
        return violacoes;
    }
}
