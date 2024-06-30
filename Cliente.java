import java.util.TreeSet;
import java.util.Set;

public class Cliente implements Comparable<Cliente> {
	private String cpf;
	private String nome;
	private Set<SolicitacaoExame> solicitacoes;

	public Cliente(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome.trim();
		this.solicitacoes = new TreeSet<SolicitacaoExame>();
	}

	public String getCPF() {
		return this.cpf;
	}

	public String getNome() {
		return this.nome;
	}

	public void adicionarSolicitacao(SolicitacaoExame s) {
		this.solicitacoes.add(s);
	}

	public Set<SolicitacaoExame> getSolicitacoes() {
		return this.solicitacoes;
	}

	public Double calcularMediaValor() {
		Double totalPreco = 0.0;
		Double totalExames = 0.0;
		for(SolicitacaoExame solicitacao : this.solicitacoes) {
			totalExames += solicitacao.getExames().size();
			totalPreco += solicitacao.calcularValorTotal();
		}

		return totalPreco / totalExames;
	}

	@Override
	public int compareTo(Cliente outroCliente) {
		return this.nome.compareTo(outroCliente.nome);
	}
}
