import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { CLIENTE_API } from './cliente.api'
import { Cliente } from '../model/cliente.model';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ClienteService {

  constructor(private http: HttpClient) {}

  findAll(): Observable<Cliente[]>{
    return this.http.get<Cliente[]>(`${CLIENTE_API}lista`);
  }
    
  public detailId(id:number): Observable<Cliente>{
    return this.http.get<Cliente>(`${CLIENTE_API}detailId/${id}`);
  }
    
  public detailName(nome:string): Observable<Cliente[]>{
    return this.http.get<Cliente[]>(`${CLIENTE_API}detailNome/${nome}`);
  }

  public detailAtivo(ativo:boolean): Observable<Cliente[]>{
    return this.http.get<Cliente[]>(`${CLIENTE_API}detailAtivo/${ativo}`);
  }

  public create(cliente:Cliente): Observable<any>{
    return this.http.post<any>(`${CLIENTE_API}create`,cliente);
  }

  public update(id:number, cliente:Cliente): Observable<any>{
    return this.http.put<any>(`${CLIENTE_API}update/${id}`, cliente);
  }

  delete(id:number): Observable<any>{
    return this.http.delete<any>(`${CLIENTE_API}delete/${id}`);
  }
  
}
