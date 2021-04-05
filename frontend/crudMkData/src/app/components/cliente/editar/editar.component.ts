import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from '../../../model/cliente.model';
import { ClienteService } from '../../../services/cliente.service';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css']
})
export class EditarComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;

  cliente = new Cliente(null,'','','','','',null,null,null,[null]);
  message:{};
  classCss: {};

  radios = [
    {
      label: 'Ativo',
      value: true,
      name: 'rdAtivo'
    },
    {
      label: 'Inativo',
      value: false,
      name: 'rdInativo'
    }
  ];

  tipos = [
    {
      label: 'Pessoa Física',
      value: 1,
      name: 'sltFisica'
    },
    {
      label: 'Pessoa Jurídica',
      value: 2,
      name: 'sltJuridica'
    }
  ]
  constructor(
    private clienteService: ClienteService,
    private activeRoute: ActivatedRoute,
    private route: Router
  ) { }

  ngOnInit() {
    let id:number = this.activeRoute.snapshot.params.id;
    if(id != undefined){
      this.findById(id);
    }
  }

  findById(id:number){
    this.clienteService.detailId(id).subscribe(
      data => {
        this.cliente = data;
        console.log(this.cliente);
      }, err => {
      this.showMessage({
        type: 'error',
        text: err.error.message
      });
    });
  }

  

  onUpdate(): void{
    const id = this.activeRoute.snapshot.params.id;
    this.clienteService.update(id, this.cliente).subscribe(
      data => {
        this.showMessage({
          type: 'success',
          text: `Cliente ${this.cliente.nome} atualizazo com sucesso!`
        });
        this.route.navigate(['/lista']);
      }, err => {
      this.showMessage({
        type: 'error',
        text: err.error.message
      });
    });
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
