package main.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import main.java.dao.UserDao;
import main.java.model.Users;

public class DeleteUser {
    public static void main(String[] args){
        Scanner scan  = new Scanner(System.in);

        UserDao userDao = new UserDao();
        List<Users> usersList = userDao.getAllUsers();

        // Verifica se a lista de usuários está vazia
        if(usersList.isEmpty()){
            System.out.println("Não há usuários para excluir. Encerrando o programa");
            scan.close();
            return;
        }

        System.out.println("Lista de usuários disponíveis para exclusão: ");

        // Exibe a lista de usuários com seus respectivos IDs
        for (Users users : usersList){
            System.out.println("ID " + users.getId() + 
            "| username: " + users.getUsername() + 
            "| email: " + users.getEmail());
        }

        // Loop para solicitar O ID do usuario ate que um ID válido seja fornecido
        int userId;
        Users users = null;

        do {
            System.out.print("Digite o ID do usuário que deseja excluir (ou digite -1 para sair)");
            try {
                userId = scan.nextInt();
                scan.nextLine();

                // Sai do programa se o usuário digitar -1
                if(userId == -1){
                    System.out.println("Operaão cancelada pelo usuário.");
                    break;
                }
                users = userDao.getUserById(userId);

                //exclui o usuário se ele existir
                if(users != null){
                    userDao.deleteUser(userId);
                    System.out.println("Usuário deletado com sucesso.");
                    break;
                } else {
                    System.out.println("Usuário não encontrado. Tente novamente.");
                }
            }catch(InputMismatchException e){
                System.out.println("ID inválido, insira um número inteiro.");
                scan.nextLine();
                userId = -1; // Define userId como -1 para continuar o loop
            }
        } while(users == null);
        scan.close();
    }
}
