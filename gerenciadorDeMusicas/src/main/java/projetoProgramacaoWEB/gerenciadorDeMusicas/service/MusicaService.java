package projetoProgramacaoWEB.gerenciadorDeMusicas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetoProgramacaoWEB.gerenciadorDeMusicas.model.MusicaEntity;
import projetoProgramacaoWEB.gerenciadorDeMusicas.repository.MusicaRepository;
import projetoProgramacaoWEB.gerenciadorDeMusicas.vo.MusicaVo;

@Service
public class MusicaService {
	@Autowired
	private MusicaRepository musicaRepository;
	
	public List<MusicaVo> findAll(){
		return MusicaVo.convertToList(musicaRepository.findAll());
	}
	
	public MusicaVo findById(Integer id) {
		Optional<MusicaEntity> musica = musicaRepository.findById(id);
		MusicaVo musicaVo = new MusicaVo();
		if(musica.isPresent()) {
            MusicaEntity musicaEntity = musica.get();
            musicaVo.setId(musicaEntity.getId());
	        musicaVo.setNome(musicaEntity.getNome());
	        musicaVo.setCantor(musicaEntity.getCantor());
	        musicaVo.setDataLancamento(musicaEntity.getDataLancamento());
	        musicaVo.setAlbum(musicaEntity.getAlbum());
	        musicaVo.setDuracao(musicaEntity.getDuracao());
		}
		return musicaVo;
	}
	
	public boolean deleteById(Integer id) {
		if(musicaRepository.findById(id).isPresent()) {
			musicaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public MusicaVo saveMusica(MusicaVo musica) {
		MusicaEntity novaMusica = MusicaVo.convertToEntity(musica);
		MusicaVo musicaVo =	MusicaVo.convertToVO(musicaRepository.save(novaMusica));
		return musicaVo;
	}
	
	public MusicaVo updateMusica(Integer id, MusicaVo musica) {
	    Optional<MusicaEntity> musicaExistente = musicaRepository.findById(id);
	    MusicaVo musicaVo = new MusicaVo();

	    if (musicaExistente.isPresent()) {
	        MusicaEntity musicaEntity = musicaExistente.get();
	        musicaEntity.setNome(musica.getNome());
	        musicaEntity.setCantor(musica.getCantor());
	        musicaEntity.setDataLancamento(musica.getDataLancamento());
	        musicaEntity.setAlbum(musica.getAlbum());
	        musicaEntity.setDuracao(musica.getDuracao());

	        musicaRepository.save(musicaEntity);

	        musicaVo = MusicaVo.convertToVO(musicaEntity);
	    }

	    return musicaVo;
	}
	
}
