package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.FormaPagamento;
import com.hospital.medcontrol.enums.TipoLeito;
import org.junit.Test;
import org.junit.Assert;
import java.time.LocalDate;

public class PagamentoTest {

    @Test
    public void testPagamentoComDescontoPix() {
        // Cenário: Internação de 1 dia em Apartamento (850.0) particular (1.0)
        Internacao internacao = new Internacao(1, "12345678901", "111111/PB",
                TipoLeito.APARTAMENTO, "202B", LocalDate.now().minusDays(1), 1.0);
        internacao.registrarAlta(LocalDate.now());

        // Pagamento via PIX deve aplicar 10% de desconto (850 * 0.9 = 765.0)
        Pagamento pagamento = new Pagamento(1, internacao, FormaPagamento.PIX_DINHEIRO);

        // No JUnit 4, usamos Assert.assertEquals(esperado, atual, margem_de_erro)
        Assert.assertEquals(765.0, pagamento.getValorFinal(), 0.01);
    }

    @Test
    public void testPagamentoComAcrescimoParcelado() {
        // Cenário: Internação de 1 dia em Enfermaria (320.0) particular (1.0)
        Internacao internacao = new Internacao(2, "10987654321", "222222/PB",
                TipoLeito.ENFERMARIA, "101A", LocalDate.now().minusDays(1), 1.0);
        internacao.registrarAlta(LocalDate.now());

        // Pagamento Parcelado deve aplicar 8% de juros (320 * 1.08 = 345.60)
        Pagamento pagamento = new Pagamento(2, internacao, FormaPagamento.PARCELADO);

        Assert.assertEquals(345.60, pagamento.getValorFinal(), 0.01);
    }
}