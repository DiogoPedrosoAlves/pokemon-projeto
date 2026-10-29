package br.com.fiap.bo;

import br.com.fiap.dao.PokemonDAO;
import br.com.fiap.to.PokemonTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class PokemonBO {

    private PokemonDAO pokemonDAO = new PokemonDAO();

    public ArrayList<PokemonTO> findAll() {
        return pokemonDAO.findAll();
    }

    public PokemonTO findByCodigo(Long codigo) {
        return pokemonDAO.findByCodigo(codigo);
    }

    public PokemonTO save(PokemonTO pokemon) {
        if (!dataValida(pokemon)) {
            return null;
        }
        return pokemonDAO.save(pokemon);
    }

    public PokemonTO update(PokemonTO pokemon) {
        if (!dataValida(pokemon)) {
            return null;
        }
        return pokemonDAO.update(pokemon);
    }

    public boolean delete(Long codigo) {
        return pokemonDAO.delete(codigo);
    }

    // regra de negócio: a data da captura deve ser uma data no passado
    private boolean dataValida(PokemonTO pokemon) {
        return pokemon.getDataDaCaptura() != null
                && pokemon.getDataDaCaptura().isBefore(LocalDate.now());
    }
}
