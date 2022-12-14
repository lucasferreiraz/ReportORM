package br.com.reportorm;

import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.reportorm.database.DB;
import br.com.reportorm.entities.*;
import br.com.reportorm.service.*;

public class CrudTest {
    
    public static void insertions(){

        Laboratorio laboratorio = new Laboratorio(null, "laboratorio", "123456789", "123456789", "123456789", "Ana Lima");
        new LaboratorioService().insert(laboratorio);

        Contato contato = new Contato(null, "988380012", laboratorio);
        new ContatoService().insert(contato);

        Endereco endereco = new Endereco(null, "Paulo Afonso", "41", "Baixos", "Jereissati II", "61920690", "Maracanaú", laboratorio);
        new EnderecoService().insert(endereco);

        SiglaFormacao siglaFormacao = new SiglaFormacao(null, "Dr(a)");
        new SiglaFormacaoService().insert(siglaFormacao);
        
        ResponsavelTecnico responsavelTecnico = new ResponsavelTecnico(null, "Maria do Socorro", "CME", "Pediatra", siglaFormacao);
        new ResponsavelTecnicoService().insert(responsavelTecnico);

        Paciente paciente = null;
        try {
            paciente = new Paciente(null, "Enzo de Oliveira", new SimpleDateFormat("dd/MM/yyyy").parse("16/07/2014"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        new PacienteService().insert(paciente);

        Medico medico = new Medico(null, "123456789", "Alexandre de Morais");
        new MedicoService().insert(medico);

        Especialidade especialidade = new Especialidade(null, "Infectologia", "Doutor");
        new EspecialidadeService().insert(especialidade);

        MedicoHasEspecialidade medicoHasEspecialidade = new MedicoHasEspecialidade(null, medico, especialidade);
        new MedicoHasEspecialidadeService().insert(medicoHasEspecialidade);
        
        ResponsavelTecnicoHasLaboratorio responsavelTecnicoHasLaboratorio = new ResponsavelTecnicoHasLaboratorio(null, responsavelTecnico, laboratorio);
        new ResponsavelTecnicoHasLaboratorioService().insert(responsavelTecnicoHasLaboratorio);
        
        ConsultaMedica consultaMedica = new ConsultaMedica(null, new Date(), "5056", medico, paciente);
        new ConsultaMedicaService().insert(consultaMedica);

        TipoExame tipoExame = new TipoExame(null, "Exame Avaliativo", "Rotina");
        new TipoExameService().insert(tipoExame);

        HabilitacaoExame habilitacaoExame = new HabilitacaoExame(null, "Pendente", 130L, laboratorio, tipoExame);
        new HabilitacaoExameService().insert(habilitacaoExame);

        MaterialExame materialExame = new MaterialExame(null, "Estetoscópio", "Usado");
        new MaterialExameService().insert(materialExame);

        Exame exame = new Exame(null, "Exame", "Padrão", tipoExame, materialExame);
        new ExameService().insert(exame);

        SolicitacaoExame solicitacaoExame = new SolicitacaoExame(null, "1234", new Date(), consultaMedica, habilitacaoExame, exame);
        new SolicitacaoExameService().insert(solicitacaoExame);

        UnidadeMedida unidadeMedida = new UnidadeMedida(null, "mg");
        new UnidadeMedidaService().insert(unidadeMedida);

        ComposicaoExame composicaoExame = new ComposicaoExame(null, "Composto", unidadeMedida);
        new ComposicaoExameService().insert(composicaoExame);

        ValorReferenciaComposicaoExame valorReferenciaComposicaoExame = new ValorReferenciaComposicaoExame(null, "0", "40", "10",  "50", unidadeMedida);
        new ValorReferenciaComposicaoExameService().insert(valorReferenciaComposicaoExame);

        Composicao composicao = new Composicao(null, exame, composicaoExame, valorReferenciaComposicaoExame);
        new ComposicaoService().insert(composicao);

        Laudo laudo = new Laudo(null, "123456789", new Date(), "123456789", solicitacaoExame);
        new LaudoService().insert(laudo);

        ResultadoExame resultadoExame = new ResultadoExame(null, new Date(), "130.00", composicao , laudo);
        new ResultadoExameService().insert(resultadoExame);

    }

    //In testing! Do not use.
    public static void reset(){

        String[] tables1 = {
            "responsavel_tecnico_has_laboratorio", "medico_has_especialidade"
        };
        resettor(tables1);

        String[] tables2 = {
            "resultado_exame", "laudo", "solicitacao_exame",  "habilitacao_exame", "consulta_medica", "composicao", "exame", "valor_referencia_composicao_exame", "composicao_exame", "responsavel_tecnico", "endereco", "contato"
        };
        resettor(tables2);

        String[] tables3 = {
            "unidade_medida" ,  "exame", "material_exame", "tipo_exame",    "especialidade", "medico", "paciente",  "sigla_formacao",   "laboratorio"
        };
        resettor(tables3);
        
    }

    private static void resettor(String[] tables) {
        for (String table : tables) {

            String delete = "DELETE FROM " + table + ";";
            String resetseq = null;

            try (PreparedStatement statement = DB.getConnection().prepareStatement(delete)) {
                statement.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (!isManyToMany(table)) {
                resetseq = "ALTER SEQUENCE " + table + "_id_seq RESTART;";

                try (PreparedStatement statement = DB.getConnection().prepareStatement(resetseq)) {
                    statement.executeUpdate();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private static boolean isManyToMany(String table){
        switch (table) {
            case "responsavel_tecnico_has_laboratorio":
                return true;
            case "medico_has_especialidade":
                return true;
            default:
                return false;
        }
    }

}
