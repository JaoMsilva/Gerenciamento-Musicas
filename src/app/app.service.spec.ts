import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { Musica } from './Musica';
import { AppService } from './app.service';

describe('AppService', () => {
  let service: AppService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AppService]
    });
    service = TestBed.inject(AppService);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });

  describe('salvarMusica', () => {
    it('makes expected calls', () => {
      const httpTestingController = TestBed.inject(HttpTestingController);
      const musicaStub: Musica = <any>{};
      service.salvarMusica(musicaStub).subscribe(res => {
        expect(res).toEqual(musicaStub);
      });
      const req = httpTestingController.expectOne('/api/musica');
      expect(req.request.method).toEqual('POST');
      req.flush(musicaStub);
      httpTestingController.verify();
    });
  });

  describe('buscarTodosMusicas', () => {
    it('makes expected calls', () => {
      const httpTestingController = TestBed.inject(HttpTestingController);
      service.buscarTodosMusicas().subscribe(res => {
        expect(res).toEqual([]);
      });
      const req = httpTestingController.expectOne('/api/musica');
      expect(req.request.method).toEqual('GET');
      req.flush([]);
      httpTestingController.verify();
    });
  });
});
