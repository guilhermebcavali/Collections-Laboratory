import java.util.Set;
import java.util.TreeSet;

public class SolicitacaoExame implements Comparable<SolicitacaoExame> {
	private static int NRO_SOLICITACAO = 1000;
	private int nroSolicitacao;
	private int ano;
	private int mes;
	private int crmMedico;
	private Set<Exame> exames;

	public SolicitacaoExame(int ano, int mes, int crm) {
		this.ano = ano;
		this.mes = mes;
		this.crmMedico = crm;
		this.nroSolicitacao = NRO_SOLICITACAO;
		SolicitacaoExame.NRO_SOLICITACAO++;
		this.exames = new TreeSet<Exame>();
	}

	public int getAno() {
		return this.ano;
	}

	public int getMes() {
		return this.mes;
	}

	public int getCRM() {
		return this.crmMedico;
	}

	public int getNroSolicitacao() {
		return this.nroSolicitacao;
	}

	public void adicionarExame(Exame e) {
		this.exames.add(e);
	}

	public double calcularValorTotal() {
		Double valorTotal = 0.0;
		for(Exame exame : this.exames) {
			valorTotal += exame.getPreco();
		}
		return valorTotal;
	}

	public Set<Exame> getExames() {
		return this.exames;
	}

	@Override
	public int compareTo(SolicitacaoExame outraSolicitacao) {
		if(this.nroSolicitacao < outraSolicitacao.nroSolicitacao) return -1;
		if(this.nroSolicitacao > outraSolicitacao.nroSolicitacao) return 1;
		return 0;
	}

}
