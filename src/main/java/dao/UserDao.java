package main.java.dao;

import main.java.model.Users;
import main.java.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.*;

public class UserDao {
    private final Logger logger = Logger.getLogger(UserDao.class.getName());

    // Metódo para adicionar um usuário ao banco de dados
    public void addUser(Users users) {
        // Obtém a conexão uma única vez e verifica se ela está válida
        try (Connection connection = ConnectionUtil.getConnection()) {
            if (connection == null || connection.isClosed()) {
                System.out.println("Conexão inválida ou fechada.");
                return; // Não executa a inserção se a conexão estiver fechada
            }

        // SQL para inserir um novo usuário apenas se não existir outro com o mesmo username ou email
        String sql = "INSERT INTO users (username, email) " +
                     "SELECT ?, ? " + 
                     "WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = ? OR email = ?)";

        // Define os parâmetros da query
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getEmail());
            preparedStatement.setString(3, users.getUsername()); // Parâmetro para a subconsulta
            preparedStatement.setString(4, users.getEmail());  // Parâmetro para a subconsulta

            // Executa a query e obtém o número de linhas afetadas
            int rowsInserted = preparedStatement.executeUpdate();

            // Se houver linhas inseridas (usuário único), obtém o ID gerado
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        users.setId(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Usuário: " + users.getUsername() + " e email: " + users.getEmail() + " inseridos com sucesso!");
            } else {
                System.out.println("Usuário: " + users.getUsername() + " ou email: " + users.getEmail() + " já cadastrados no banco de dados!");
            }
        }
    } catch (SQLException e) {
        // Em caso de erro SQL, registra o erro no log
        logger.log(Level.SEVERE, "Erro ao tentar adicionar usuário no banco de dados", e);
    }
}
    // Método para buscar o usuário por ID
    public Users getUserById(int id){
        Users user = null;
        String sql = "SELECT id, username, email FROM users WHERE id = ?";

        try (Connection connection = ConnectionUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
            }else{
                System.out.println("Nenhum usuário encontrado com o ID: " + id);
            };

        }catch(SQLException e){
            logger.log(Level.SEVERE, "Erro ao buscar o usuário por Id", e);
        }
        return user;
    }

    // Método para atualizar um usuário pelo ID
    public void updateUser(Users user){
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";

        try(Connection connection = ConnectionUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getId());

            int rowsUpdate = preparedStatement.executeUpdate();

            if(rowsUpdate > 0){
                StringBuilder message = new StringBuilder("User (ID: " + user.getId() + ") atualizado no banco de dados. ");

                // Verifica se o username foi alterado
                if (user.getUsername() != null) {
                    message.append("Username alterado para: ").append(user.getUsername()).append(". ");
                }

                // Verifica se o email foi alterado
                if (user.getEmail() != null) {
                    message.append("Email alterado para: ").append(user.getEmail());
                }

                // Caso nenhum dado tenha sido alterado
                if (user.getUsername() == null && user.getEmail() == null) {
                    message.append("Nenhum dado foi alterado.");
                }

                System.out.println(message.toString());

            }else{
                logger.log(Level.SEVERE, "Falha ao atualizar o usuário (ID: " + user.getId() +")." );
            }
        }catch(SQLException e){
            logger.log(Level.SEVERE, "Falha ao tentar atualizar usuário no banco de dados", e);
        }
    }

    // Método para deletar um usuário pelo ID
    public void deleteUser(int userId){
        String selectSql =  "SELECT id, username, email FROM users WHERE id = ?";
        String deleteSql = "DELETE FROM users WHERE id = ?";

        try(Connection connection = ConnectionUtil.getConnection()){
            // Obtém os dados do usuário antes de excluí-lo
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, userId);
            ResultSet resultSet = selectStatement.executeQuery();

            Users deletedUser = null;
            if(resultSet.next()){
                deletedUser = new Users();
                deletedUser.setId(resultSet.getInt("id"));
                deletedUser.setUsername(resultSet.getString("username"));
                deletedUser.setEmail(resultSet.getString("email"));
            }

            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, userId);
            int rowsDeleted = deleteStatement.executeUpdate();

            if (rowsDeleted <= 0 || deletedUser == null) {
                logger.warning("Nenhum usuário encontrado com esse ID.");
            }
    
        } catch(SQLException e){
            logger.log(Level.SEVERE, "Erro ao tentar excluir usuário do banco de dados", e);
        }
    }

    //metódo para consultar todos os usários
    public List<Users> getAllUsers(){
        List<Users> userList = new ArrayList<>();
        String sql = "SELECT id, username, email FROM users";

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    Users user = new Users();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    userList.add(user);
                }
        } catch(SQLException e){
            logger.log(Level.SEVERE, "Erro ao obter todos os usuários", e);
        }
        return userList;
    }

}
