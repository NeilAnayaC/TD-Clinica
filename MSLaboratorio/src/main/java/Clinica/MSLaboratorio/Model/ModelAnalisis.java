package Clinica.MSLaboratorio.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "analisis")
public class ModelAnalisis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analisisid")
    private int analisisid;

    @Column(name = "diagnosticoid")
    private int diagnosticoid;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "pacienteid")
    private int pacienteid;

    @OneToMany(mappedBy = "analisis", cascade = CascadeType.ALL)
    private List<ModelLaboratorio> laboratorios;

    public int getAnalisisid() {
        return analisisid;
    }

    public void setAnalisisid(int analisisid) {
        this.analisisid = analisisid;
    }

    public int getDiagnosticoid() {
        return diagnosticoid;
    }

    public void setDiagnosticoid(int diagnosticoid) {
        this.diagnosticoid = diagnosticoid;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }


    public List<ModelLaboratorio> getLaboratorios() {
        return laboratorios;
    }

    public void setLaboratorios(List<ModelLaboratorio> laboratorios) {
        this.laboratorios = laboratorios;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(int pacienteid) {
        this.pacienteid = pacienteid;
    }

    
}
