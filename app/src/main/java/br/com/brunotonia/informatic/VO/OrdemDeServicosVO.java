package br.com.brunotonia.informatic.VO;

import android.content.Context;

import br.com.brunotonia.informatic.BO.ClientesBO;

/**
 * Created by bruno on 10/06/16.
 */
public class OrdemDeServicosVO {

    private Long id;
    private Long cliente;
    private Long situacao;

    public OrdemDeServicosVO(Long cliente, Long situacao) {
        this.cliente = cliente;
        this.situacao = situacao;
    }

    public OrdemDeServicosVO(Long id, Long cliente, Long situacao) {
        this.id = id;
        this.cliente = cliente;
        this.situacao = situacao;
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

    public Long getSituacao() {
        return situacao;
    }

    public void setSituacao(Long situacao) {
        this.situacao = situacao;
    }

}
