import java.util.*;

public class MyApp {
  public static void main(String[] args) {
    
    // O laboratório e os exames que oferece
    Laboratorio lab = new Laboratorio();
    
    Exame exame1 = new Exame("HEMOG", "Hemograma", 10.0);
    Exame exame2 = new Exame("TGO", "Transaminase Oxalacética ", 3.99);
    Exame exame3 = new Exame("TGP", "Transaminase Glutâmico Pirúvica", 4.25);
    Exame exame4 = new Exame("TSH", "Hormônio Tireoestimulante ", 19.72);

    // [A fazer] Você deve adicionar mais 3 exames ao laboratório

    Exame exame5 = new Exame("GLIC", "Glicemia", 5.5);
    Exame exame6 = new Exame("COLE", "Colesterol Total", 6.75);
    Exame exame7 = new Exame("TRIG", "Triglicerídeos", 7.30);
    Exame exame8 = new Exame("TEST", "Test", 7.40);

    lab.adicionarExame(exame1);
    lab.adicionarExame(exame2);
    lab.adicionarExame(exame3);
    lab.adicionarExame(exame4);
    lab.adicionarExame(exame5);
    lab.adicionarExame(exame6);
    lab.adicionarExame(exame7);
    lab.adicionarExame(exame8);

    // Simulando cliente 1 e suas solicitacoes de exame
    Cliente cliente1 = new Cliente("1948141", "Maria Silva");

    SolicitacaoExame solicitacao1 = new SolicitacaoExame(2023, 12, 19401);
    solicitacao1.adicionarExame(exame1);
    solicitacao1.adicionarExame(exame2);

    SolicitacaoExame solicitacao2 = new SolicitacaoExame(2024, 4, 20511);
    solicitacao2.adicionarExame(exame3);
    solicitacao2.adicionarExame(exame4);

    cliente1.adicionarSolicitacao(solicitacao1);
    cliente1.adicionarSolicitacao(solicitacao2);

    // [A fazer] Você deve criar pelo menos mais 2 clientes e suas solicitacoes de exame
    // Um deles deve ter sobrenome "Silva" e o outro não.

    Cliente cliente2 = new Cliente("1948142", "João Silva");
    Cliente cliente3 = new Cliente("1948143", "Ana Souza");

    SolicitacaoExame solicitacao3 = new SolicitacaoExame(2023, 8, 20678);
    solicitacao3.adicionarExame(exame5);
    solicitacao3.adicionarExame(exame6);

    SolicitacaoExame solicitacao4 = new SolicitacaoExame(2022, 6, 19405);
    solicitacao4.adicionarExame(exame7);
    solicitacao4.adicionarExame(exame8);

    cliente2.adicionarSolicitacao(solicitacao3);
    cliente3.adicionarSolicitacao(solicitacao4);

    lab.adicionarCliente(cliente1);
    lab.adicionarCliente(cliente2);
    lab.adicionarCliente(cliente3);

    // // Buscando dados
    Cliente c = lab.buscarCliente("1948141");
    System.out.println(c.getNome());

    // [A fazer] Você deve buscar clientes que possuem "Silva" no nome
    // e imprimir o nome do cliente e a média de valor das suas solicitações.

    lab.buscarClientesPorNome("Silva");
    lab.buscarClientesPorExame(exame7);

    // [A fazer] Você deve buscar clientes que NÃO realizaram o exame "TSH".
    Set<String> clientesImpressos = new HashSet<>();
    for(Exame chave : lab.getClientesPorExame().keySet()) {
      if(!chave.getCodigo().equals("TSH") && !lab.getClientesPorExame().get(chave).isEmpty()) {
        for(Cliente cliente : lab.getClientesPorExame().get(chave)) {
          clientesImpressos.add(cliente.getNome());
        }
      }
    }

    System.out.println("\nBuscando clientes que não realizaram o exame TSH:\n");
    for(String nome : clientesImpressos) {
      System.out.println(nome);
    }

    lab.salvarDadosCliente(cliente1, "cliente1.txt");
  }
}
