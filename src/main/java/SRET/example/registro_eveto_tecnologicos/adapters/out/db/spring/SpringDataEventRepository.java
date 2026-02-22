package SRET.example.registro_eveto_tecnologicos.adapters.out.db.spring;

import SRET.example.registro_eveto_tecnologicos.adapters.out.db.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpringDataEventRepository extends JpaRepository<EventEntity, UUID> {
    @Query("""
            SELECT e 
            FROM EventEntity e
            WHERE (:nombre IS NULL OR e.nombre = :nombre)
            AND(:organizador IS NULL OR e.organizador = :organizador)
            AND(:fecha IS NULL OR e.fecha = :fecha)
            AND(:ubicacion IS NULL OR e.ubicacion = :ubicacion)""")
    List<EventEntity> search(
            @Param("nombre") String nombre,
            @Param("organizador") String organizador,
            @Param("fecha") LocalDateTime fecha,
            @Param("ubicacion")String ubicacion
    );
}
