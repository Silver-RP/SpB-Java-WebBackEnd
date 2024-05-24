package demo.devspringboot.WebBackEnd.common.service;

import demo.devspringboot.WebBackEnd.common.exception.WBEBussinessException;
import demo.devspringboot.WebBackEnd.common.model.BaseEntity;
import demo.devspringboot.WebBackEnd.common.util.WebBackEndMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenericService <T extends BaseEntity, D, I>{

    JpaRepository<T, I>getRepository();
    WebBackEndMapper getMapper();

    default List<D> findAll(Class<D> clazzDTO){
        return getRepository().findAll().stream().map((model)->
                getMapper().map(model, clazzDTO )
        ).toList();
    }

    default D findById(I id, Class<D> clazzDTO){
        T model = getRepository().findById(id)
                .orElseThrow(()-> new WBEBussinessException("Product with id not exist."));
        return getMapper().map(model, clazzDTO);
    }

    default D save(D dtoForSave, Class<T> clazzModel, Class<D> clazzDTO){
        T model = getRepository().save(getMapper().map(dtoForSave, clazzModel));
        return  getMapper().map(model, clazzDTO);
    }

    default void delete(I id){
        T entity = getRepository().findById(id)
                .orElseThrow(()-> new WBEBussinessException("Entity to delete not found."));
        getRepository().delete(entity);

    }

    default T update(T model){
        return getRepository().save(model);
    }

}
