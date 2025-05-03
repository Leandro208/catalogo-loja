package com.catalogoweb.CatalogoLoja.service;

import java.util.List;
import java.util.Optional;

import com.catalogoweb.CatalogoLoja.repository.BaseRepository;

public abstract class BaseService<E, ID> {
	
	protected abstract BaseRepository<E,ID> getRepository();
	
	public List<E> listarTodos() {
        return getRepository().findAll();
    }

    public Optional<E> buscarPorId(ID id) {
        return getRepository().findById(id);
    }

    public E salvar(E entidade) {
        return getRepository().save(entidade);
    }

    public Optional<E> atualizar(ID id, E entidade) {
        if (getRepository().existsById(id)) {
            return Optional.of(getRepository().save(entidade));
        }
        return Optional.empty();
    }

    public boolean deletar(ID id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
            return true;
        }
        return false;
    }

	
}
