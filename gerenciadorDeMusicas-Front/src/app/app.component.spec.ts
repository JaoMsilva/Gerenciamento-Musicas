import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { AppService } from './app.service';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(() => {
    const appServiceStub = () => ({
      buscarTodosMusicas: () => ({ subscribe: f => f({}) }),
      buscarMusicaPorId: id => ({ subscribe: f => f({}) }),
      excluirMusica: id => ({ subscribe: f => f({}) }),
      atualizarMusica: (id, musicaForm1) => ({ subscribe: f => f({}) }),
      salvarMusica: musicaForm => ({ subscribe: f => f({}) })
    });
    TestBed.configureTestingModule({
      imports: [FormsModule],
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [AppComponent],
      providers: [{ provide: AppService, useFactory: appServiceStub }]
    });
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`verMusica has default value`, () => {
    expect(component.verMusica).toEqual(false);
  });

  it(`musicas has default value`, () => {
    expect(component.musicas).toEqual([]);
  });

  it(`isEdicao has default value`, () => {
    expect(component.isEdicao).toEqual(false);
  });

  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      spyOn(component, 'carregarMusicas').and.callThrough();
      component.ngOnInit();
      expect(component.carregarMusicas).toHaveBeenCalled();
    });
  });

  describe('carregarMusicas', () => {
    it('makes expected calls', () => {
      const appServiceStub: AppService = fixture.debugElement.injector.get(
        AppService
      );
      spyOn(appServiceStub, 'buscarTodosMusicas').and.callThrough();
      component.carregarMusicas();
      expect(appServiceStub.buscarTodosMusicas).toHaveBeenCalled();
    });
  });

  describe('salvarMusica', () => {
    it('makes expected calls', () => {
      const appServiceStub: AppService = fixture.debugElement.injector.get(
        AppService
      );
      spyOn(component, 'carregarMusicas').and.callThrough();
      spyOn(appServiceStub, 'atualizarMusica').and.callThrough();
      spyOn(appServiceStub, 'salvarMusica').and.callThrough();
      component.salvarMusica();
      expect(component.carregarMusicas).toHaveBeenCalled();
      expect(appServiceStub.atualizarMusica).toHaveBeenCalled();
      expect(appServiceStub.salvarMusica).toHaveBeenCalled();
    });
  });
});
