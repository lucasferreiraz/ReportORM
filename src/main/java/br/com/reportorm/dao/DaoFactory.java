package br.com.reportorm.dao;

import br.com.reportorm.dao.impl.*;
import br.com.reportorm.database.DB;

public class DaoFactory {
    
    public static ComposicaoDao createComposicaoDao(){
        return new ComposicaoDaoJDBC(DB.getConnection());
    }

    public static ComposicaoExameDao createComposicaoExameDao(){
        return new ComposicaoExameDaoJDBC(DB.getConnection());
    }

    public static ConsultaMedicaDao createConsultaMedicaDao(){
        return new ConsultaMedicaDaoJDBC(DB.getConnection());
    }

    public static ContatoDao createContatoDao(){
        return new ContatoDaoJDBC(DB.getConnection());
    }

    public static EnderecoDao createEnderecoDao(){
        return new EnderecoDaoJDBC(DB.getConnection());
    }

    public static EspecialidadeDao createEspecialidadeDao(){
        return new EspecialidadeDaoJDBC(DB.getConnection());
    }

    public static ExameDao createExameDao(){
        return new ExameDaoJDBC(DB.getConnection());
    }

    public static HabilitacaoExameDao createHabilitacaoExameDao(){
        return new HabilitacaoExameDaoJDBC(DB.getConnection());
    }

    public static LaboratorioDao createLaboratorioDao(){
        return new LaboratorioDaoJDBC(DB.getConnection());
    }

    public static LaudoDao createLaudoDao(){
        return new LaudoDaoJDBC(DB.getConnection());
    }

    public static MaterialExameDao createMaterialExameDao(){
        return new MaterialExameDaoJDBC(DB.getConnection());
    }

    public static MedicoDao createMedicoDao(){
        return new MedicoDaoJDBC(DB.getConnection());
    }

    public static MedicoHasEspecialidadeDao createMedicoHasEspecialidadeDao(){
        return new MedicoHasEspecialidadeDaoJDBC(DB.getConnection());
    }

    public static PacienteDao createPacienteDao(){
        return new PacienteDaoJDBC(DB.getConnection());
    }

    public static ResponsavelTecnicoDao createResponsavelTecnicoDao(){
        return new ResponsavelTecnicoDaoJDBC(DB.getConnection());
    }

    public static ResponsavelTecnicoHasLaboratorioDao createResponsavelTecnicoHasLaboratorioDao(){
        return new ResponsavelTecnicoHasLaboratorioDaoJDBC(DB.getConnection());
    }

    public static ResultadoExameDao createResultadoExameDao(){
        return new ResultadoExameDaoJDBC(DB.getConnection());
    }

    public static SiglaFormacaoDao createSiglaFormacaoDao(){
        return new SiglaFormacaoDaoJDBC(DB.getConnection());
    }

    public static SolicitacaoExameDao createSolicitacaoExameDao(){
        return new SolicitacaoExameDaoJDBC(DB.getConnection());
    }

    public static TipoExameDao createTipoExameDao(){
        return new TipoExameDaoJDBC(DB.getConnection());
    }

    public static UnidadeMedidaDao createUnidadeMedidaDao(){
        return new UnidadeMedidaDaoJDBC(DB.getConnection());
    }

    public static ValorReferenciaComposicaoExameDao createValorReferenciaComposicaoExameDao(){
        return new ValorReferenciaComposicaoExameDaoJDBC(DB.getConnection());
    }

}
