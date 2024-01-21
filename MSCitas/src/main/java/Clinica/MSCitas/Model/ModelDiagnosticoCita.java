package Clinica.MSCitas.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "diagnosticocita")
public class ModelDiagnosticoCita {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosticoid")
    private int diagnosticoid;

    @ManyToOne
    @JoinColumn(name = "citasid", referencedColumnName ="citasid")
    private ModelCitas citas;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pacienteid")
    private int pacienteid;

    public int getDiagnosticoid() {
        return diagnosticoid;
    }

    public void setDiagnosticoid(int diagnosticoid) {
        this.diagnosticoid = diagnosticoid;
    }

    public ModelCitas getCitas() {
        return citas;
    }

    public void setCitas(ModelCitas citas) {
        this.citas = citas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(int pacienteid) {
        this.pacienteid = pacienteid;
    }

    

    
}
