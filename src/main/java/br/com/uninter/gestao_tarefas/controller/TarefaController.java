package br.com.uninter.gestao_tarefas.controller;

import br.com.uninter.gestao_tarefas.model.Tarefa;
import br.com.uninter.gestao_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    /**
     * Endpoint para criar uma nova tarefa.
     * 
     * @param tarefa Objeto Tarefa recebido no corpo da requisição
     * @return A tarefa criada com o ID gerado pelo banco de dados
     */
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
    }

    /**
     * Endpoint para listar todas as tarefas cadastradas.
     * 
     * @return Lista de todas as tarefas
     */
    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return ResponseEntity.ok(tarefas);
    }

    /**
     * Endpoint para consultar uma tarefa específica por ID.
     * 
     * @param id ID da tarefa a ser consultada
     * @return A tarefa encontrada ou status 404 se não encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> consultarTarefaPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if (tarefa.isPresent()) {
            return ResponseEntity.ok(tarefa.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint para atualizar uma tarefa existente.
     * 
     * @param id               ID da tarefa a ser atualizada
     * @param tarefaAtualizada Objeto com os novos dados da tarefa
     * @return A tarefa atualizada ou status 404 se não encontrada
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(
            @PathVariable Long id,
            @RequestBody Tarefa tarefaAtualizada) {

        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);

        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();

            if (tarefaAtualizada.getNome() != null) {
                tarefa.setNome(tarefaAtualizada.getNome());
            }
            if (tarefaAtualizada.getDataEntrega() != null) {
                tarefa.setDataEntrega(tarefaAtualizada.getDataEntrega());
            }
            if (tarefaAtualizada.getResponsavel() != null) {
                tarefa.setResponsavel(tarefaAtualizada.getResponsavel());
            }

            Tarefa tarefaSalva = tarefaRepository.save(tarefa);
            return ResponseEntity.ok(tarefaSalva);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint para remover uma tarefa.
     * 
     * @param id ID da tarefa a ser removida
     * @return Status 204 (No Content) se removida com sucesso, ou 404 se não
     *         encontrada
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if (tarefa.isPresent()) {
            tarefaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
