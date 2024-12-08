package projetoProgramacaoWEB.gerenciadorDeMusicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoProgramacaoWEB.gerenciadorDeMusicas.model.MusicaEntity;

public interface MusicaRepository extends JpaRepository<MusicaEntity, Integer>{

}
