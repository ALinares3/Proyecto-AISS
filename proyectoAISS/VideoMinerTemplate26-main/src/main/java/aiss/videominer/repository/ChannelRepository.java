package aiss.videominer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aiss.videominer.model.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long>{
    
    // Lo he cambiado a Long porque segun los videos hace falta un long para el id, pero no se si es correcto o si deberia ser un String
}
