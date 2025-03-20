package main.app;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.dao.UserDao;
import main.java.model.Users;

public class UpdateUser {
        private static final Logger logger = Logger.getLogger(UpdateUser.class.getName());

        public static void main(String[] args){
            Scanner scan = new Scanner(System.in);

            int updateId = 0;
            boolean validInput = false;

            while(!validInput){
                try{
                    System.out.println("Digite o ID do usuário que deseja atualizar: ");
                    updateId = scan.nextInt();
                    validInput = true;
                }catch(InputMismatchException e){
                    logger.warning("Erro: ID do usuário deve ser um número inteiro. ");
                    scan.nextLine();
                }
            }

            UserDao userDao = new UserDao();
            Users users = userDao.getUserById(updateId);

            if(users != null){
                System.out.println("Usuário encontrado");
                System.out.println("ID: " + users.getId());
                System.out.println("Username: " + users.getUsername());
                System.out.println("Email: " + users.getEmail());
                System.out.println("------------------------------------ ");

                System.out.println("O que você deseja atualizar?");
                System.out.println("1 - Atualizar Username");
                System.out.println("2 - Atualizar Email");
                System.out.println("3 - Atualizar Ambos");
                System.out.println("4 - Sair");
                System.out.println("------------------------------------ ");

                int escolha = scan.nextInt();

                switch (escolha) {
                    case 1:
                        System.out.print("Digite o novo nome de usuário: ");
                        String newUsername = scan.next();
                        users.setUsername(newUsername);
                        break;
                    case 2:
                        System.out.print("Digite um novo email: ");
                        String newEmail = scan.next();
                        users.setEmail(newEmail);
                        break;
                    case 3:
                        System.out.print("Digite o novo nome de usuário: ");
                        String newUsername2 = scan.next();
                        users.setUsername(newUsername2);

                        System.out.print("Digite um novo email: ");
                        String newEmail2 = scan.next();
                        users.setEmail(newEmail2);
                        break;
                    case 4:
                        System.out.println("Saindo do programa... ");
                        return;
                    default:
                        logger.log(Level.SEVERE, "Nenhum usuário encontrado com o ID: "+ updateId);
                        return;
                }
                userDao.updateUser(users);
            } else {
                logger.log(Level.SEVERE, "Nenhum usuário encontrado com o ID: "+ updateId);
            }
            scan.close();
        }
}
