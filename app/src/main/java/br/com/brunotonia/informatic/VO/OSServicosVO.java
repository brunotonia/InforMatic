package br.com.brunotonia.informatic.VO;

import br.com.brunotonia.informatic.BO.ClientesBO;

/**
 * Created by bruno on 10/06/16.
 */
public class OSServicosVO {

    private Long id;
    private Long cliente;
    private Long servico;
    private Float valor;

    public OSServicosVO(Long cliente, Long servico, Float valor) {
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
    }

    public OSServicosVO(Long id, Long cliente, Long servico, Float valor) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public Long getServico() {
        return servico;
    }

    public void setServico(Long servico) {
        this.servico = servico;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
