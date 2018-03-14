package com.ufcg.si1.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.Produto;
import com.ufcg.si1.model.VendaItem;
import com.ufcg.si1.model.DTO.LoteDTO;
import com.ufcg.si1.repositories.LotesRepository;
import com.ufcg.si1.repositories.ProdutosRepository;
import com.ufcg.si1.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loteService")
public class LoteServiceImpl implements LoteService {

	@Autowired
	private LotesRepository lotesRepository;
	@Autowired
	private ProdutosRepository produtosRepository;

	@Override
	public Lote saveLote(Lote lote) {
		Lote savedLote = lotesRepository.save(lote);

		return savedLote;
	}

	@Override
	public Lote findById(long id) {
		Lote lote = this.lotesRepository.getLoteById(id);
		return lote;
	}

	/**
	 * Faz o update do lote, lote é recebido como parâmetro já com o novo produto
	 * como atributo
	 *
	 * @param lote
	 *            - lote a ser atualizado
	 */
	@Override
	public void updateProduto(Lote lote) {
		this.lotesRepository.save(lote);
	}

	@Override
	public void deleteLoteById(long id) {
		Lote lote = this.findById(id);
		this.lotesRepository.delete(lote);
	}

	@Override
	public List<Lote> findAllLotes() {
		Iterable<Lote> lotes = this.lotesRepository.findAll();
		List<Lote> lotesList = Util.toList(lotes);

		return lotesList;
	}

	@Override
	public int size() {
		List<Lote> lotes = this.findAllLotes();
		int size = lotes.size();

		return size;
	}

	@Override
	public Iterator<Lote> getIterator() {
		Iterable<Lote> lotes = this.findAllLotes();
		Iterator<Lote> iterator = lotes.iterator();

		return iterator;
	}

	//TODO nao ta criando lotes com o mesmo produto 
	@Override
	public Lote criarLote(long produtoId, LoteDTO loteDTO) {
		Lote loteParaSalvar = null;
		Lote loteSalvo = null;
		Produto produto = this.produtosRepository.findOne(produtoId);

		if (produto != null) {
			int numeroDeItens = loteDTO.getNumeroDeItens();
			String validade = loteDTO.getDataDeValidade();
			loteParaSalvar = new Lote(produtoId, produto, numeroDeItens, validade);
			produto.setDisponibilidade(true);
			loteSalvo = this.lotesRepository.save(loteParaSalvar);
		}
		
		return loteSalvo;
	}

	//TODO: teste. e melhorar ta feio
	@Override
	public void atualizaQuantProduto(List<VendaItem> produtosVendidos) {
		for (VendaItem vendaItem : produtosVendidos) {
			
			Produto produto = vendaItem.getProduto();
			int quantidade = vendaItem.getQuantidade();
			List<Lote> lotesDoProduto = this.lotesDoProduto(produto); //TODO: para exluir do mais perto de vencer so ordenar essa list pela data ade validade
			int cont = 0;
			
			while(quantidade > 0 && lotesDoProduto.size() < cont) {
				Lote lote = lotesDoProduto.get(cont);
				int quantidadeDoLote = lote.getNumeroDeItens();
				
				if(quantidadeDoLote >= quantidade) {
					lote.setNumeroDeItens(quantidadeDoLote - quantidade);
					break;
				}else {
					lote.setNumeroDeItens(0);
					cont++;
					quantidade = quantidade - quantidadeDoLote;
				}
			}
			this.salvaLotesAlterados(lotesDoProduto);
		}
		
	}
	
	private void salvaLotesAlterados(List<Lote> lotesDoProduto) {
		for (Lote lote : lotesDoProduto) {
			this.lotesRepository.save(lote);
		}
		
	}

	private List<Lote> lotesDoProduto(Produto produto){
		List<Lote> lotes = this.findAllLotes();
		List<Lote> lotesDoProduto = new LinkedList<Lote>();
		
		for (Lote lote : lotes) {
			
			if(lote.getProduto().equals(produto)) {
				lotesDoProduto.add(lote);
			}
			
		}
		
		return lotesDoProduto;
	}
	

	// retorna a quantidade de produtos em todos os lote 
	@Override
	public int quantProduto(long produtoId) {
		List<Lote> lotes = this.findAllLotes();
		int quantidade = 0;

		for (Lote lote : lotes) {
			
			if (lote.getProduto().getId() == produtoId && lote.estaNaValidade()) {
				quantidade += lote.getNumeroDeItens();
			}
			
		}

		return quantidade;
	}

	@Override
	public int quantLotes() {
		return this.findAllLotes().size();
	}

	@Override
	public List<String> getValidades() {
		List<String> validade = new LinkedList<String>();
		List<Lote> lotes = this.findAllLotes();

		for (Lote lote : lotes) {
			validade.add(lote.getDataDeValidade());
		}

		return validade;
	}

	@Override
	public void incrementaQuantProdutos(List<VendaItem> produtosVendidos) {
		List<Lote> lotes = this.findAllLotes();

		for (VendaItem vendaItem : produtosVendidos) {
			Produto produto = vendaItem.getProduto();
			int quantidade = vendaItem.getQuantidade();

			for (Lote lote : lotes) {
				
				if (lote.getProduto().equals(produto)) {
					lote.setNumeroDeItens(lote.getNumeroDeItens() + quantidade);
				}
				
			}

		}

	}

	@Override
	public List<Produto> listaProdutosBaixaQuant() {
		List<Produto> produtosComBaixaQaunt = new LinkedList<>();
		List<Produto> produtos = Util.toList(this.produtosRepository.findAll());

		for (Produto produto : produtos) {
			int quantidadeProduto = this.quantProduto(produto.getId());
			
			if(quantidadeProduto <= 15) {
				produtosComBaixaQaunt.add(produto);
			}
		
		}
		
		return produtosComBaixaQaunt;

	}

	@Override
	public List<Produto> listaProximoDeVencer() {
		List<Produto> produtosProximoDeVencer = new LinkedList<>();
		List<Produto> produtos = Util.toList(this.produtosRepository.findAll());
		
		for (Produto produto : produtos) {
			boolean produtoProximoDeVencer = this.temLoteProximoDeVencer(produto);
			
			if(produtoProximoDeVencer) {
				produtosProximoDeVencer.add(produto);
			}
			
		}
		
		return produtosProximoDeVencer;
	}

	private boolean temLoteProximoDeVencer(Produto produto) {
		List<Lote> lotes = this.findAllLotes();
		
		for (Lote lote : lotes) {
			
			if(lote.getProduto().equals(produto) && lote.pertoDeVencer()) {
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	public List<Produto> listaDisponiveis() {
		Iterable<Produto> produtosIterable = this.produtosRepository.findAll();
		List<Produto> produtos = Util.toList(produtosIterable);
		List<Produto> produtosDisponiveis = new LinkedList<Produto>();
		
		for (Produto produto : produtos) {
			
			if(this.quantProduto(produto.getId()) > 0) {
				produtosDisponiveis.add(produto);
			}
	
		}
		
		return produtosDisponiveis;
	}

	@Override
	public List<Produto> listaIndisponiveis() {
		Iterable<Produto> produtosIterable = this.produtosRepository.findAll();
		List<Produto> produtos = Util.toList(produtosIterable);
		List<Produto> produtosIndisponiveis = new LinkedList<Produto>();
		
		for (Produto produto : produtos) {
			
			if(this.quantProduto(produto.getId()) == 0) {
				produtosIndisponiveis.add(produto);
			}
	
		}
		
		return produtosIndisponiveis;
	}
	
}
