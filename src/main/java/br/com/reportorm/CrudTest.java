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

        Endereco endereco = new Endereco(null, "Paulo Afonso", "41", "Baixos", "Jereissati II", "61920690", "Maracanaú", 1);
        new EnderecoService().insert(endereco);

        SiglaFormacao siglaFormacao = new SiglaFormacao(null, "Dr(a)");
        new SiglaFormacaoService().insert(siglaFormacao);
        
        ResponsavelTecnico responsavelTecnico = new ResponsavelTecnico(null, "Maria do Socorro", "CME", "Pediatra", 1);
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

        MedicoHasEspecialidade medicoHasEspecialidade = new MedicoHasEspecialidade(null, 1, 1);
        new MedicoHasEspecialidadeService().insert(medicoHasEspecialidade);
        
        ResponsavelTecnicoHasLaboratorio responsavelTecnicoHasLaboratorio = new ResponsavelTecnicoHasLaboratorio(null, 1, 1);
        new ResponsavelTecnicoHasLaboratorioService().insert(responsavelTecnicoHasLaboratorio);
        
        ConsultaMedica consultaMedica = new ConsultaMedica(null, new Date(), "5056", 1, 1);
        new ConsultaMedicaService().insert(consultaMedica);

        TipoExame tipoExame = new TipoExame(null, "Exame Avaliativo", "Rotina");
        new TipoExameService().insert(tipoExame);

        HabilitacaoExame habilitacaoExame = new HabilitacaoExame(null, "Pendente", 130L, 1, 1);
        new HabilitacaoExameService().insert(habilitacaoExame);

        MaterialExame materialExame = new MaterialExame(null, "Estetoscópio", "Usado");
        new MaterialExameService().insert(materialExame);

        Exame exame = new Exame(null, "Exame", "Padrão", 1, 1);
        new ExameService().insert(exame);

        SolicitacaoExame solicitacaoExame = new SolicitacaoExame(null, "1234", new Date(), 1, 1, 1);
        new SolicitacaoExameService().insert(solicitacaoExame);

        UnidadeMedida unidadeMedida = new UnidadeMedida(null, "mg");
        new UnidadeMedidaService().insert(unidadeMedida);

        ComposicaoExame composicaoExame = new ComposicaoExame(null, "Composto", unidadeMedida);
        new ComposicaoExameService().insert(composicaoExame);

        ValorReferenciaComposicaoExame valorReferenciaComposicaoExame = new ValorReferenciaComposicaoExame(null, "0", "40", "10",  "50", 1);
        new ValorReferenciaComposicaoExameService().insert(valorReferenciaComposicaoExame);

        Composicao composicao = new Composicao(null, 1, 1, 1);
        new ComposicaoService().insert(composicao);

        Laudo laudo = new Laudo(null, "123456789", new Date(), "123456789", 1);
        new LaudoService().insert(laudo);

        ResultadoExame resultadoExame = new ResultadoExame(null, new Date(), "130.00", 1 , 1);
        new ResultadoExameService().insert(resultadoExame);

    }

    //In testing! Do not use.
    public static void reset(){

        String[] tables = {
            "resultado_exame", "laudo", "composicao", "valor_referencia_composicao_exame", "composicao_exame", "unidade_medida" , "solicitacao_exame", "exame", "material_exame", "habilitacao_exame", "tipo_exame", "consulta_medica", "responsavel_tecnico_has_laboratorio", "medico_has_especialidade", "especialidade", "medico", "paciente", "responsavel_tecnico", "sigla_formacao", "endereco", "contato", "laboratorio"
        };

        for(String table : tables){

            String delete = "DELETE FROM " + table +";";
            String resetseq = "ALTER SEQUENCE " + table +"_id_seq RESTART;";

            try (PreparedStatement statement = DB.getConnection().prepareStatement(delete)){
                statement.executeUpdate();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            try (PreparedStatement statement = DB.getConnection().prepareStatement(resetseq)) {
                statement.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
