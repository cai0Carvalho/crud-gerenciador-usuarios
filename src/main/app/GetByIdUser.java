package main.app;

import java.util.Scanner;

import main.java.dao.UserDao;
import main.java.model.Users;

public class GetByIdUser {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o ID do usuário:");
        int userId = sc.nextInt();

        UserDao userDao = new UserDao();
        Users users = userDao.getUserById(userId);

        if(users != null){
            System.out.println("Id: "+ users.getId());
            System.out.println("Nome de usuário: "+ users.getUsername());
            System.out.println("Email: "+ users.getEmail());
        } else {
            System.out.println("Usuário não encontrado");
        }
        sc.close();
    }
}
