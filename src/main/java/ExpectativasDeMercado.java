public class ExpectativasDeMercado {

    private String Indicador;
    private String Data;
    private String DataReferencia;
    private double Media;
    private double Mediana;
    private double DesvioPadrao;
    private double Minimo;
    private double Maximo;
    private int numeroRespondentes;
    private int baseCalculo;


    public String getIndicador() {
        return Indicador;
    }

    public void setIndicador(String indicador) {
        Indicador = indicador;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getDataReferencia() {
        return DataReferencia;
    }

    public void setDataReferencia(String dataReferencia) {
        DataReferencia = dataReferencia;
    }

    public double getMedia() {
        return Media;
    }

    public void setMedia(double media) {
        Media = media;
    }

    public double getMediana() {
        return Mediana;
    }

    public void setMediana(double mediana) {
        Mediana = mediana;
    }

    public double getDesvioPadrao() {
        return DesvioPadrao;
    }

    public void setDesvioPadrao(double desvioPadrao) {
        DesvioPadrao = desvioPadrao;
    }

    public double getMinimo() {
        return Minimo;
    }

    public void setMinimo(double minimo) {
        Minimo = minimo;
    }

    public double getMaximo() {
        return Maximo;
    }

    public void setMaximo(double maximo) {
        Maximo = maximo;
    }

    public int getNumeroRespondentes() {
        return numeroRespondentes;
    }

    public void setNumeroRespondentes(int numeroRespondentes) {
        this.numeroRespondentes = numeroRespondentes;
    }

    public int getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(int baseCalculo) {
        this.baseCalculo = baseCalculo;
    }
}
