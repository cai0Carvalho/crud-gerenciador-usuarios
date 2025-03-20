package main.app;

import java.util.Scanner;
import main.java.dao.UserDao;
import main.java.model.Users;

public class AddUser {
    public static void main(String[] args){
        Users user = new Users();
        Scanner sc = new Scanner(System.in);

        System.out.println("digite o nome de usuário para inserir no ");
        user.setUsername(sc.nextLine());

        System.out.println("Digite o email do usuário para inserir no banco de dados");
        user.setEmail(sc.nextLine());

        // Adiciona o usuário ao banco de dados
        UserDao userDao = new UserDao();
        userDao.addUser(user);
        System.out.println("Usuário cadastrado no banco de dados.");


        sc.close(); // Fecha o scanner para evitar vazamento de recursos
    }
}