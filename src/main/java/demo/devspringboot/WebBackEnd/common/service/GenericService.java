package demo.devspringboot.WebBackEnd.common.service;

import demo.devspringboot.WebBackEnd.common.model.BaseEntity;
import demo.devspringboot.WebBackEnd.common.util.WebBackEndMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenericService <T extends BaseEntity, D, I>{

    JpaRepository<T, I>getRepository();
    WebBackEndMapper getMapper();

    default List<D> findAll(Class<D> clazzDTO){
        return getRepository().findAll().stream().map((model)->
                getMapper().map(model, clazzDTO )
        ).toList();
    }

    default Optional<D> findById(I id, Class<D> clazzDTO){
        D dto = null;
        Optional<T> model = getRepository().findById(id);
        if (model.isPresent()){
            dto = getMapper().map(model.get(), clazzDTO);
        }
        return Optional.of(dto);
    }

    default D save(D dtoForSave, Class<T> clazzModel, Class<D> clazzDTO){
        T model = getRepository().save(getMapper().map(dtoForSave, clazzModel));
        return  getMapper().map(model, clazzDTO);
    }

    default void delete(I id){
        Optional<T> model = getRepository().findById(id);
        if(model.isPresent()){
            getRepository().delete(model.get());
        }
    }

    default T update(T model){
        return getRepository().save(model);
    }

}
