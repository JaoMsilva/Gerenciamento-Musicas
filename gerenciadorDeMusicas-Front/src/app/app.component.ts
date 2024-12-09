import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import { Musica } from './Musica';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  verMusica: boolean = false;
  musicas: Musica[] = [];
  musicaSelecionada: Musica | null = null;
  musicaForm: Musica = {
    id: undefined,
    nome: '',
    cantor: '',
    dataLancamento: '',
    album: '',
    duracao: '',
  };
  isEdicao: boolean = false;

  constructor(private appService: AppService) {}

  ngOnInit() {
    this.carregarMusicas();
  }

  carregarMusicas() {
    this.appService.buscarTodosMusicas().subscribe((musicas) => {
      this.musicas = musicas;
    });
  }

  editarMusica(id: number) {
    this.appService.buscarMusicaPorId(id).subscribe((musica) => {
      this.musicaForm = { ...musica };
      this.isEdicao = true;
    });
  }

  excluirMusica(id: number) {
    this.appService.excluirMusica(id).subscribe(() => {
      this.carregarMusicas();
      this.resetForm();
    });
  }

  salvarMusica() {
    if (this.isEdicao && this.musicaForm.id !== undefined) {
      this.appService
        .atualizarMusica(this.musicaForm.id, this.musicaForm)
        .subscribe((musica) => {
          this.carregarMusicas();
          this.resetForm();
        });
    } else {
      this.appService.salvarMusica(this.musicaForm).subscribe((musica) => {
        this.carregarMusicas();
        this.resetForm();
      });
    }
  }

  limparForm() {
    this.resetForm();
  }

  private resetForm() {
    this.musicaForm = {
      id: undefined,
      nome: '',
      cantor: '',
      dataLancamento: '',
      album: '',
      duracao: '',
    };
  }

  cancelar() {
    this.isEdicao = false;
    this.resetForm();
  }

  transformDate(data: string): string{
    const partes = data.split('-');
    return `${partes[2]}/${partes[1]}/${partes[0]}`;
  }
}
