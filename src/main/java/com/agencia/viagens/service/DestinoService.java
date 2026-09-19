package com.agencia.viagens.service;

import com.agencia.viagens.model.Avaliacao;
import com.agencia.viagens.model.Destino;
import com.agencia.viagens.repository.DestinoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinoService {

    private final DestinoRepository destinoRepository;

    public DestinoService(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    public List<Destino> listarTodos() {
        return destinoRepository.findAll();
    }

    public Destino buscarPorId(Long id) {
        return destinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado com id: " + id));
    }

    public List<Destino> buscar(String nome, String localizacao) {
        if (nome != null && !nome.isBlank()) {
            return destinoRepository.findByNomeContainingIgnoreCase(nome);
        }

        if (localizacao != null && !localizacao.isBlank()) {
            List<Destino> porLocalizacao =
                    destinoRepository.findByLocalizacaoContainingIgnoreCase(localizacao);

            if (!porLocalizacao.isEmpty()) {
                return porLocalizacao;
            }

            return destinoRepository.findByPaisContainingIgnoreCase(localizacao);
        }

        return destinoRepository.findAll();
    }

    public Destino criar(Destino destino) {
        destino.setId(null);

        if (destino.getAvaliacoes() == null) {
            destino.setAvaliacoes(new ArrayList<>());
        }

        destino.setMediaAvaliacoes(null);
        return destinoRepository.save(destino);
    }

    public Destino atualizar(Long id, Destino dados) {
        Destino destino = buscarPorId(id);

        destino.setNome(dados.getNome());
        destino.setPais(dados.getPais());
        destino.setLocalizacao(dados.getLocalizacao());
        destino.setDescricao(dados.getDescricao());
        destino.setPreco(dados.getPreco());

        return destinoRepository.save(destino);
    }

    public void excluir(Long id) {
        Destino destino = buscarPorId(id);
        destinoRepository.delete(destino);
    }

    public Destino adicionarAvaliacao(Long id, Avaliacao avaliacao) {
        if (avaliacao.getNota() == null || avaliacao.getNota() < 0 || avaliacao.getNota() > 5) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 5.");
        }

        Destino destino = buscarPorId(id);

        avaliacao.setId(null);
        avaliacao.setDestino(destino);

        destino.getAvaliacoes().add(avaliacao);

        double media = destino.getAvaliacoes()
                .stream()
                .mapToDouble(Avaliacao::getNota)
                .average()
                .orElse(0.0);

        destino.setMediaAvaliacoes(media);

        return destinoRepository.save(destino);
    }
}
