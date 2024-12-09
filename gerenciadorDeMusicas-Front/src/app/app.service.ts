import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Musica } from './Musica';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private apiUrl = '/api/musica';

  constructor(private http: HttpClient) { }

  buscarTodosMusicas(): Observable<Musica[]> {
    return this.http.get<Musica[]>(this.apiUrl);
  }

  buscarMusicaPorId(id: number): Observable<Musica> {
    return this.http.get<Musica>(`${this.apiUrl}/${id}`);
  }

  excluirMusica(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  salvarMusica(musica: Musica): Observable<Musica> {
    return this.http.post<Musica>(this.apiUrl, musica);
  }

  atualizarMusica(id: number, musica: Musica): Observable<Musica> {
    return this.http.put<Musica>(`${this.apiUrl}/${id}`, musica);
  }
}
