package com.hospital.medcontrol.gerenciadores;

import java.util.ArrayList;
import com.hospital.medcontrol.model.Pagamento;
import com.hospital.medcontrol.enums.FormaPagamento;
import java.io.Serializable;

public class GerenciadorPagamentos implements Serializable {
    private static final long serialVersionUID = 1L;

        private ArrayList<Pagamento> pagamentos = new ArrayList<>();
        private int contadorId = 1;

        public void registrarPagamento(double valor, FormaPagamento forma) {
            Pagamento pagamento = new Pagamento(contadorId++, valor, forma);
            pagamentos.add(pagamento);
        }

        public void listarPagamentos() {
            for (Pagamento p : pagamentos) {
                System.out.println(p);
            }
        }
    }