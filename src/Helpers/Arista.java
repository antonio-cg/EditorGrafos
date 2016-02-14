package Helpers;

/**
  Modelo de una arista
 * @author antonio Caro
 */
public class Arista {
    
    private Nodo inicia;
    private Nodo termina;
    private String nombreNodoInicio;
    private String nombreNodoFin;

    public String getNombreNodoInicio() {
        return nombreNodoInicio;
    }

    public void setNombreNodoInicio(String nombreNodoInicio) {
        this.nombreNodoInicio = nombreNodoInicio;
    }

    public String getNombreNodoFin() {
        return nombreNodoFin;
    }

    public void setNombreNodoFin(String nombreNodoFin) {
        this.nombreNodoFin = nombreNodoFin;
    }
    public Nodo getInicia() {
        return inicia;
    }

    public void setInicia(Nodo inicia) {
        this.inicia = inicia;
    }

    public Nodo getTermina() {
        return termina;
    }

    public void setTermina(Nodo termina) {
        this.termina = termina;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(double heuristica) {
        this.heuristica = heuristica;
    }
    private double peso = 0;
    private double heuristica = 0;
    
    public Arista(Nodo inicia, Nodo termina)
    {
        this.inicia = inicia;
        this.termina = termina;
        this.nombreNodoFin = termina.getNombre();
        this.nombreNodoInicio = inicia.getNombre();
    }
    
    public Arista(Nodo inicia, Nodo termina, double peso)
    {
        this.inicia = inicia;
        this.termina  = termina;
        this.peso = peso;
        this.nombreNodoFin = termina.getNombre();
        this.nombreNodoInicio = inicia.getNombre();
    }
    
    public Arista(Nodo inicia, Nodo termina, double peso, double heuristica)
    {
    
        this.inicia = inicia;
        this.termina = termina;
        this.peso = peso;
        this.heuristica = heuristica;
        this.nombreNodoFin = termina.getNombre();
        this.nombreNodoInicio = inicia.getNombre();
    }
    
    public String imprimeRelacion()
    {
        return inicia.getNombre() + ":" + termina.getNombre();
    }
    
    public Arista obtenerContraparte()
    {
        return new Arista(termina,inicia,peso,heuristica);
    }
    
    public Nodo getFin()
    {
        return termina;
    }
    
    public Nodo getInicio()
    {
        return inicia;
    
    }
    

}
