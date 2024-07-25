public class Exame implements Comparable<Exame> {
	private String codigo;
	private String nome;
	private double preco;

	public Exame(String codigo, String nome, double preco) {
		this.validarCodigo(codigo);
		this.nome = nome.trim();
		this.preco = preco;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public double getPreco() {
		return this.preco;
	}

	private void validarCodigo(String codigo) {
		if (codigo.length() >= 3 && codigo.length() <= 5) {
			this.codigo = codigo.trim().toUpperCase();
		} else {
			System.out.println("ERRO! O código do exame deve conter de 3 a 5 caracteres.");
		}
	}

	/* ----------------------------------------------------------------------------------------- */
	// Sessão de implementação do HashCode dos objetos da classe Exame. Estes métodos ditam as regras de inclusão e comparação dos objetos Exame criados no HashMap (ou em um HashSet), e são chamados no momento em que um objeto está sendo inserido na estrutura. Evitam e garantem que o HashMap/HashSet armazene 2 objetos iguais.

	// A sobrescrita destes métodos é essencial, uma vez que desejo garantir as unicidade dos objetos de acordo com os valores de seus atributos "código" e "nome". 
	
	// A IMPLEMENTAÇÃO PADRÃO de equals() verifica APENAS a igualdade de REFERÊNCIA DOS OBJETOS, não a igualdade de conteúdo dos atributos dos objetos. Isso significa que dois objetos com exatamente os mesmos dados seriam considerados diferentes a menos que sejam a mesma instância do objeto.

	// A sobrescrita garante que OBJETOS COM MESMOS DADOS sejam iguais e possuam o mesmo hashCode.
	/* ----------------------------------------------------------------------------------------- */

	// se um objeto for igual ao outro, necessariamente terão o mesmo número de hashCode.
	@Override
	public boolean equals(Object outroObjeto) {
		if (this == outroObjeto) return true; // objetos iguais --> mesmo hashCode();

		if (outroObjeto == null || getClass() != outroObjeto.getClass()) return false; // objetos com hashCode() diferentes --> objetos diferentes;

		// getClass() --> Método da classe Object que retorna o hashCode de um objeto;

		// @override: parte sobrescrevida do método original:
		Exame outroExame = (Exame) outroObjeto;

		if (!this.codigo.equals(outroExame.codigo)) return false; // atributo código diferente em cada objeto --> objetos diferentes --> hasCode() diferente;
	
		return this.nome.equals(outroExame.nome); // atributo nome igual nos objetos --> objetos iguais --> mesmo hasCode();
	}

	// como o método equals() foi sobrescrevido, necessariamente o método hashCode precisa ser sobrescrevido --> inclusao da comparacao dos atributos "codigo" e "nome";
	@Override
	public int hashCode() {
		int result = this.codigo.hashCode();
		result = 31 * result + this.nome.hashCode();
		return result;
	}
/* ----------------------------------------------------------------------------------------- */

/* Sessão que implementa o método "compareTo()" da interface "Comparable". Dita a regra de comparação e ordenação dos objetos Exame criados e inseridos em um ArrayList ou TreeSet (exige que os objetos armazenados implementem essa interface). */
	@Override
	public int compareTo(Exame outroExame) {
		return this.nome.compareTo(outroExame.nome);
	}
/* ----------------------------------------------------------------------------------------- */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome do Exame: ").append(this.getNome()).append("\n");
		sb.append("Código do Exame: ").append(this.getCodigo()).append("\n");
		sb.append("Preço do Exame: ").append(this.getPreco()).append("\n");
		return sb.toString();
	}

}
