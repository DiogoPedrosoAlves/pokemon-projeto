package br.com.fiap.resource;

import br.com.fiap.bo.PokemonBO;
import br.com.fiap.to.PokemonTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonResource {

    private PokemonBO pokemonBO = new PokemonBO();

    @GetMapping
    public ResponseEntity<List<PokemonTO>> findAll() {
        List<PokemonTO> pokemons = pokemonBO.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pokemons);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable("codigo") Long codigo) {
        PokemonTO pokemon = pokemonBO.findByCodigo(codigo);
        if (pokemon != null) {
            return ResponseEntity.status(HttpStatus.OK).body(pokemon);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokémon não encontrado!");
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PokemonTO pokemon) {
        try {
            PokemonTO resultado = pokemonBO.save(pokemon);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Dados inválidos: a data da captura deve ser no passado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao salvar pokémon");
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> update(@PathVariable("codigo") Long codigo,
                                    @RequestBody @Valid PokemonTO pokemon) {
        try {
            pokemon.setCodigo(codigo);
            PokemonTO resultado = pokemonBO.update(pokemon);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Dados inválidos: a data da captura deve ser no passado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao atualizar pokémon");
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> delete(@PathVariable("codigo") Long codigo) {
        if (pokemonBO.delete(codigo)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokémon não encontrado!");
    }
}
