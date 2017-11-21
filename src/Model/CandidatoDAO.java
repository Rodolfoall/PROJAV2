package Model;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO extends GenericDAO {

    public void salvar (Candidato candidato)throws SQLException{

        String insert = "INSERT INTO candidato(numero,nome,partido)VALUES(?,?,?)";
        save(insert,candidato.getNumero(),candidato.getNome(),candidato.getPartido());
    }

    public void alterar(Candidato candidato)throws SQLException{

        String update = "UPDATE candidato"+
                "SET numero = ?, nome = ?, partido = ?"+"WHERE id=?";
        update(update,candidato.getId(),candidato.getNumero(),candidato.getNome()
                ,candidato.getPartido());
    }

    public List findCandidato()throws SQLException{

        List candidato = new ArrayList();

        String select = "SELECT * FROM candidato";


        PreparedStatement preparedStatement = getConnection().prepareStatement(select);


        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Candidato candidato1 = new Candidato();
            candidato1.setId(resultSet.getLong("id"));
            candidato1.setNumero(resultSet.getString("numero"));
            candidato1.setNome(resultSet.getString("nome"));
            candidato1.setPartido(resultSet.getString("partido"));
            candidato.add(candidato1);

        }
        resultSet.close();
        preparedStatement.close();

        return candidato;

    }

    public Candidato findByNumero (String numero) throws SQLException{
        String select = "SELECT * FROM candidato WHERE numero = ?";
        Candidato candidato = null;
        PreparedStatement preparedStatement =getConnection().prepareStatement(select);

        preparedStatement.setString(1,numero);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            candidato = new Candidato();
            candidato.setId(resultSet.getLong("id"));
            candidato.setNumero(resultSet.getString("Numero"));
            candidato.setNome(resultSet.getString("Nome"));
            candidato.setPartido(resultSet.getString("Partido"));

        }

        resultSet.close();
        preparedStatement.close();
        return candidato;
    }

}
