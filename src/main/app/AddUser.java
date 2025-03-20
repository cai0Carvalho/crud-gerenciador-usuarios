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

        new UserDao().addUser(user);
    }
}