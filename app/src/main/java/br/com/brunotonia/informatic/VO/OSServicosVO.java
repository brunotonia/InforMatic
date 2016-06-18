package br.com.brunotonia.informatic.VO;

import android.content.Context;

import br.com.brunotonia.informatic.BO.ClientesBO;
import br.com.brunotonia.informatic.BO.ServicosBO;

/**
 * Created by bruno on 10/06/16.
 */
public class OSServicosVO {

    private Long id;
    private Long os;
    private Long servico;
    private Float valor;
    private Context context;

    public OSServicosVO(Long os, Long servico, Float valor) {
        this.os = os;
        this.servico = servico;
        this.valor = valor;
    }

    public OSServicosVO(Long id, Long os, Long servico, Float valor) {
        this.id = id;
        this.os = os;
        this.servico = servico;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOs() {
        return os;
    }

    public void setOs(Long os) {
        this.os = os;
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

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        if (context == null) {
            return "Serviço " + id;
        } else {
            ServicosBO servicosBO = ServicosBO.getInstance();
            ServicosVO servicosVO = null;
            try {
                servicosVO = servicosBO.selecionar(context, servico);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return servicosVO.getServico();
        }
    }
}
