package projetoProgramacaoWEB.gerenciadorDeMusicas.vo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import projetoProgramacaoWEB.gerenciadorDeMusicas.model.MusicaEntity;

public class MusicaVo {
	private Integer id;
	private String nome;
	private String cantor;
	private LocalDate dataLancamento;
	private String album;
	private String duracao;

	public MusicaVo(Optional<MusicaEntity> musica) {
		this.id = musica.get().getId();
		this.nome = musica.get().getNome();
		this.cantor = musica.get().getCantor();
		this.dataLancamento = musica.get().getDataLancamento();
		this.album = musica.get().getAlbum();
		this.duracao = musica.get().getDuracao();
	}
	
	public MusicaVo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCantor() {
		return cantor;
	}

	public void setCantor(String cantor) {
		this.cantor = cantor;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String descricao) {
		this.album = descricao;
	}
	
	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	
	public static List<MusicaVo> convertToList(List<MusicaEntity> musicas) {
	    return musicas.stream()
	            .map(musica -> new MusicaVo(Optional.ofNullable(musica)))
	            .collect(Collectors.toList());
	}

	public static MusicaVo convertToVO(MusicaEntity musica) {
	    return new MusicaVo(Optional.ofNullable(musica));
	}
	
	public static MusicaEntity convertToEntity(MusicaVo musicaVo) {
        MusicaEntity musicaEntity = new MusicaEntity();
        musicaEntity.setNome(musicaVo.getNome());
        musicaEntity.setCantor(musicaVo.getCantor());
        musicaEntity.setDataLancamento(musicaVo.getDataLancamento());
        musicaEntity.setAlbum(musicaVo.getAlbum());
        musicaEntity.setDuracao(musicaVo.getDuracao());
        
        return musicaEntity;
    }
}
