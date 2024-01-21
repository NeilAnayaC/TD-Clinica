package Clinica.MSLaboratorio.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "laboratorio")
public class ModelLaboratorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultadoid")
    private int resultadoid;

    @ManyToOne
    @JoinColumn(name = "analisisid", referencedColumnName ="analisisid")
    private ModelAnalisis analisis;

    @Column(name = "pacienteid")
    private int pacienteid;

    @Column(name = "detalleresultado")
    private String detalleresultado;

    public int getResultadoid() {
        return resultadoid;
    }

    public void setResultadoid(int resultadoid) {
        this.resultadoid = resultadoid;
    }

    public ModelAnalisis getAnalisis() {
        return analisis;
    }

    public void setAnalisis(ModelAnalisis analisis) {
        this.analisis = analisis;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(int pacienteid) {
        this.pacienteid = pacienteid;
    }

    public String getDetalleresultado() {
        return detalleresultado;
    }

    public void setDetalleresultado(String detalleresultado) {
        this.detalleresultado = detalleresultado;
    }

    
}
