package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenericDAO {

    private Connection connection;

    protected  GenericDAO(){
        this.connection = ConnectionDatabase.getConnection();
    }

    protected Connection getConnection(){

        return connection;
    }

    protected void save(String insertSQL, Object...parametros)throws SQLException{
        PreparedStatement preparedStatement = getConnection().prepareStatement(insertSQL);

        for (int i = 0;i<parametros.length; i++){
            preparedStatement.setObject(i+1,parametros[i]);
        }
        preparedStatement.execute();
        preparedStatement.close();

    }

    protected void update(String updateSQL,Object id, Object...parametros)throws SQLException{

        PreparedStatement preparedStatement = getConnection().prepareStatement(updateSQL);

        for (int i = 0;i<parametros.length; i++){
            preparedStatement.setObject(i+1,parametros[i]);
        }

        preparedStatement.setObject(parametros.length+1,id);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
