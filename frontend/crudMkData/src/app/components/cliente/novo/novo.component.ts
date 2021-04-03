import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Cliente } from '../../../model/cliente.model';
import { ClienteService } from '../../../services/cliente.service';

@Component({
  selector: 'app-novo',
  templateUrl: './novo.component.html',
  styleUrls: ['./novo.component.css']
})
export class NovoComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  idCliente = 0;
  nome='';
  cpf='';
  rg='';
  cnpj='';
  ie='';
  tipo = 0;
  dataCriacao= null;
  ativo=false;
  telefones=[];
  message : {};
  classCss : {}; 

  constructor(
    private clienteService: ClienteService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  onCreate(): void{
    this.message={};
    const cliente = new Cliente(this.idCliente, this.nome, this.cpf, this.rg, this.cnpj, this.ie, this.tipo, this.dataCriacao, this.ativo, this.telefones);
    this.clienteService.create(cliente).subscribe(
      data => {
        this.showMessage({
          type: 'success',
          text: `Cliente ${cliente.nome} cadastrado com sucesso!`
        });
        this.router.navigate(['/lista']);
      },
      err => {
        this.showMessage({
          type: 'error',
          text: err.error.message
          
        });
        this.router.navigate(['/novo']);
      }
    );
  }

  getFormGroupClass(isInvalid: boolean, isDirty:boolean): {} {
    return {
      'form-group': true,
      'has-error' : isInvalid  && isDirty,
      'has-success' : !isInvalid  && isDirty
    };
  }

  private showMessage(message: {type: string, text: string}): void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string): void {
    this.classCss = {
      'alert': true
    }
    this.classCss['alert-'+type] =  true;
  }

}
