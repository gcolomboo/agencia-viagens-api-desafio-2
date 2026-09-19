package com.agencia.viagens.controller;

import com.agencia.viagens.model.Avaliacao;
import com.agencia.viagens.model.Destino;
import com.agencia.viagens.service.DestinoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @GetMapping
    public ResponseEntity<List<Destino>> listarTodos() {
        return ResponseEntity.ok(destinoService.listarTodos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Destino>> buscar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localizacao) {
        return ResponseEntity.ok(destinoService.buscar(nome, localizacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(destinoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Destino> criar(@RequestBody Destino destino) {
        return ResponseEntity.status(HttpStatus.CREATED).body(destinoService.criar(destino));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizar(
            @PathVariable Long id,
            @RequestBody Destino destino) {
        return ResponseEntity.ok(destinoService.atualizar(id, destino));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        destinoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/avaliacoes")
    public ResponseEntity<Destino> avaliar(
            @PathVariable Long id,
            @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(destinoService.adicionarAvaliacao(id, avaliacao));
    }
}
