import java.util.*;
import java.io.PrintWriter;

public class Laboratorio {

	private Set<Exame> exames;
	private Set<Cliente> clientes;
	private Set<SolicitacaoExame> solicitacoes;
	private Map<Exame, ArrayList<Cliente>> clientesPorExame; // Map: Interface que obriga classes a manipularem os dados em pares chave-valor (semelhante a um dicionario) --> Implementação mais moderna de um dicionario.

	/*------------------------------------------------------------------------------------------*/
	// Estruturas que implementam a interface Map:
	// HashMap: armazena objetos em uma tabela hash --> fator de comparação: hashCode();
	// TreeMap: armazena objetos em uma Árvore --> fator de comparação: Comparable ou Comparator;
	// LinkedHashMap: armazena uma lista de objetos na ordem em que foram inseridos, combinando bom desempenho de hash com a preservação da ordem de inserção.
	/*------------------------------------------------------------------------------------------*/

	public Laboratorio() {
		this.exames = new TreeSet<Exame>(); // TreeSet --> Árvore (não usa o hashCode()) --> implementa a interface Comparator;
		this.clientes = new TreeSet<Cliente>(); // TreeSet --> Árvore (não usa o hashCode()) --> implementa a interface Comparator;
		this.clientesPorExame = new HashMap<>(); // HashMap --> utiliza o hashCode();
	}

	public void adicionarExame(Exame e) {
		this.exames.add(e);
	}

	public void adicionarCliente(Cliente c) {
		this.clientes.add(c);
	}

	public Cliente buscarCliente(String cpf) {
		for(Cliente cliente : this.clientes) {
			if(cliente.getCPF().contains(cpf)) {
				return cliente;
			}
		}
		return null;
	}

	public Exame buscarExame(String codigo) {
		return null;
	}

	public void buscarClientesPorExame(Exame exame) {
		this.listarClientesPorExame(this.exames);
		System.out.println("\nBuscando clientes que realizaram o exame: " + exame.getNome() + "\n");
		if(this.clientesPorExame.containsKey(exame)) {
			ArrayList<Cliente> resultado = this.clientesPorExame.get(exame);
			if (!resultado.isEmpty()) {
				for(Cliente cliente : resultado) {
					System.out.println("- " + cliente.getNome());
				}
			} else {
				System.out.println("Nenhum cliente realizou este exame ainda.");
			}
		}
	}

	// auxiliar
	private void listarClientesPorExame(Set<Exame> exames2) {
		for(Exame exame : exames2) {
			ArrayList<Cliente> clientesPorExame = new ArrayList<>();
			for(Cliente cliente : this.clientes) {
				for(SolicitacaoExame solicitacao : cliente.getSolicitacoes()) {
					if(solicitacao.getExames().contains(exame)) {
						clientesPorExame.add(cliente);
					}
				}
			}
			this.clientesPorExame.put(exame, clientesPorExame);
		}
	}

	public void buscarClientesPorNome(String nome) {
		System.out.println("\nBuscando clientes com o nome: " + nome + "\n");
		for(Cliente cliente : clientes) {
			if(cliente.getNome().contains(nome)) {
				System.out.println("- " + cliente.getNome() + " | Média de Valor de suas solicitações: " + cliente.calcularMediaValor());
			}
		}
	}

	public Map<Exame, ArrayList<Cliente>> getClientesPorExame() {
		return this.clientesPorExame;
	}

	public void salvarDadosCliente(Cliente cliente, String nomeArq) {
		try (PrintWriter saida = new PrintWriter(nomeArq)) {
			saida.println("- Cliente CPF=" + cliente.getCPF() + ", nome=" + cliente.getNome());
			for(SolicitacaoExame solicitacao : cliente.getSolicitacoes()) {
				saida.println("\t * Solicitação de Exame " + solicitacao.getNroSolicitacao() + ", ano=" + solicitacao.getAno() + ", mes=" + solicitacao.getMes() + ", CRM=" + solicitacao.getCRM());
				for(Exame exame : solicitacao.getExames()) {
					saida.println("\t\t - Exame de " + exame.getNome() + " | R$ " + exame.getPreco());
				}
			}

		} catch (Exception e) {
			System.out.println("Falha de acesso ao arquivo!");
			e.printStackTrace();
		}
	}
}
