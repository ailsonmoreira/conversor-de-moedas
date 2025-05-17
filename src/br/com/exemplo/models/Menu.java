package br.com.exemplo.models;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    //Instancia um scanner e uma conexao com a api

    private final Scanner input;
    ApiConnect connect;


    public Menu(){
        this.input = new Scanner(System.in);
        this.connect = new ApiConnect();
    }


    //Opcoes para realizar as operacoes
    public enum OperacaoConversao{
        MULTIPLICAR,
        DIVIDIR
    }

    //Metodo para chamar o menu

    public void exibir(){

        while(true) {

            try {
                Moeda moeda = connect.buscarTaxa();

                System.out.println("""
                        ****************************************
                        ------Conversor de moedas------
                        
                        1) Real  --> Dólar
                        2) Dólar --> Real
                        3) Iene  --> Dólar
                        4) Dólar --> Iene
                        5) Euro  --> Dólar
                        6) Dólar --> Euro
                        7) Sair
                        \s""");

                System.out.println("Escolha uma opção: ");
                int opc = Integer.parseInt(input.nextLine());


                //break para finalizar a aplicacao fora do switch case para evitar que a entrada seja chamada novamente
                if (opc == 7) {
                    System.out.println("Finalizando...");
                    break;
                }


                //Chama o metodo de solicitacao de entrada
                double valorAConverter = lerValor(input);

                switch (opc) {
                    case 1:
                        exibeConversao("BRL", "USD", valorAConverter, moeda, OperacaoConversao.DIVIDIR);
                        break;
                    case 2:
                        exibeConversao("USD", "BRL", valorAConverter, moeda, OperacaoConversao.MULTIPLICAR);
                        break;
                    case 3:
                        exibeConversao("JPY", "USD", valorAConverter, moeda, OperacaoConversao.DIVIDIR);
                        break;
                    case 4:
                        exibeConversao("USD", "JPY", valorAConverter, moeda, OperacaoConversao.MULTIPLICAR);
                        break;
                    case 5:
                        exibeConversao("EUR", "USD", valorAConverter, moeda, OperacaoConversao.DIVIDIR);
                        break;
                    case 6:
                        exibeConversao("USD", "EUR", valorAConverter, moeda, OperacaoConversao.MULTIPLICAR);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        continue;
                }
                input.nextLine();

            } catch (NumberFormatException | IOException| InterruptedException e) {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
            }

        }
    }

    //Faz os cálculos de acordo com a operação necessária e exibe o resultado
    private void exibeConversao(String origemMoeda, String destinoMoeda, double valorAConverter, Moeda moeda, OperacaoConversao operacao){

        double origem = moeda.conversion_rates().get(origemMoeda);
        double destino = moeda.conversion_rates().get(destinoMoeda);

        if(operacao == OperacaoConversao.MULTIPLICAR){
            destino = destino * valorAConverter;
        } else if (operacao == OperacaoConversao.DIVIDIR){
            destino = valorAConverter / origem;
        } else{
            System.out.println("Operação inválida");
            return;
        }

        //Exibe o valor da conversão
        System.out.printf("%.2f %s = %.2f %s na cotação atual\n", valorAConverter, origemMoeda, destino, destinoMoeda);
    }


    //Solicita a entrada do usuario
    private double lerValor(Scanner input){
        double valorAConverter;

        //verifica se foi inserido um numero
        while (true) {
            System.out.print("Quanto deseja converter? ");
            try {
                valorAConverter = input.nextDouble(); // Tenta ler o valor como double
                break; // Se a leitura for bem-sucedida, sai do loop
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
                input.nextLine(); // Limpa o buffer para tentar novamente
            }
        }

        return valorAConverter;
    }

}
