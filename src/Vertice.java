
import java.util.LinkedList;
import java.util.List;

public class Vertice 
{
    // ATRIBUTOS DO VERTICE
    private final List<Vertice> verticesAdjacentes = new LinkedList<>();
    private int cor;
    private final int id;
    
    //METODO CONSTRUTOR: GUARDA O ID E SETA ANTECESSOR 0
    public Vertice(int id)
    {
        this.cor = 0;
        this.id = id;
    }
    
    // RETORNA A COR DO VERTICE
    public int getCor()
    {
        return this.cor;
    }
    
    // SETA A COR DO VERTICE
    public void setCor(int cor)
    {
        this.cor = cor;
    }
    
    
    // RETORNA O ID DO VERTICE
    public int getId()
    {
        return this.id;
    }
    
    // ADICIONA VERTICE NA LISTA DE ADJACENCIAS
    public void adicionarVerticeAdjacente(Vertice v)
    {
        if(!this.verticesAdjacentes.contains(v) && v!=this)
        {
            this.verticesAdjacentes.add(v);
        }
    }
    
    // RETORNA A LISTA DE VERTICES ADJACENTES
    public List<Vertice> getVerticesAdjacentes()
    {
        return this.verticesAdjacentes;
    }
}
