package Controller;

import Model.Candidato;
import Model.CandidatoDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class CandidatoController {

    public void salvar(String numero,String nome,String partido)throws SQLException{
        Candidato candidato = new Candidato();
        candidato.setNumero(numero);
        candidato.setNome(nome);
        candidato.setPartido(partido);


        new CandidatoDAO().salvar(candidato);
    }

    public void alterar(Long id,String numero,String nome,String partido)throws SQLException{

        Candidato candidato = new Candidato();
        candidato.setId(id);
        candidato.setNumero(numero);
        candidato.setNome(nome);
        candidato.setPartido(partido);


        new CandidatoDAO().alterar(candidato);
    }

    public List listarCandidatos(){
        CandidatoDAO dao = new CandidatoDAO();
        try {
            return dao.findCandidato();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Candiatos"
                    + e.getLocalizedMessage());

        }
        return null;

    }
    public Candidato buscaMotoristaPorNumero(String numero) throws SQLException {
        CandidatoDAO dao = new CandidatoDAO();
        return dao.findByNumero(numero);
    }

}
