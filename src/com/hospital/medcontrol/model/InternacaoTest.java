package com.hospital.medcontrol.model;

import com.hospital.medcontrol.enums.TipoLeito;
import org.junit.Test;
import org.junit.Assert;
import java.time.LocalDate;

public class InternacaoTest {

    @Test
    public void testCalcularValorFinalEnfermariaComCoparticipacao() {

        Internacao internacao = new Internacao(1, "11122233344", "123456/PB",
                TipoLeito.ENFERMARIA, "101A", LocalDate.now().minusDays(5), 0.15);

        internacao.registrarAlta(LocalDate.now());

        Assert.assertEquals(240.0, internacao.calcularValorFinal(), 0.01);
    }

    @Test
    public void testCalcularValorFinalUtiParticular() {

        Internacao internacao = new Internacao(2, "99988877766", "654321/SP",
                TipoLeito.UTI, "UTI-01", LocalDate.now().minusDays(2), 1.0);

        internacao.registrarAlta(LocalDate.now());

        Assert.assertEquals(5000.0, internacao.calcularValorFinal(), 0.01);
    }
}