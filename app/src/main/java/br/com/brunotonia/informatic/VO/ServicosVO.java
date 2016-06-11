package br.com.brunotonia.informatic.VO;

/**
 * Created by bruno on 10/06/16.
 */
public class ServicosVO {

    private Long id;
    private String servico;
    private Float valor;
    private String descricao;

    public ServicosVO(String servico, Float valor, String descricao) {
        this.servico = servico;
        this.valor = valor;
        this.descricao = descricao;
    }

    public ServicosVO(Long id, String servico, Float valor, String descricao) {
        this.id = id;
        this.servico = servico;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return servico + "(R$ " + valor + ")";
    }
}
