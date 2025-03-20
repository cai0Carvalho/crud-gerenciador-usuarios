package main.app;

import java.util.List;
import main.java.dao.UserDao;
import main.java.model.Users;

public class GettAllUsers {
    public static void main(String[] args){
        UserDao userDao = new UserDao();

        List<Users> usersList = userDao.getAllUsers();
        if(!usersList.isEmpty()){
            System.out.println("Lista de todos os usuários no banco de dados");

            for(Users users : usersList){
                System.out.println("Id: " + users.getId() +
                                    "| username: " + users.getUsername() +
                                    "| email: " + users.getEmail());
            }
        }else{
            System.out.println("Nenhum usuário encontrado na base do banco de dados");
        }
    }
}
