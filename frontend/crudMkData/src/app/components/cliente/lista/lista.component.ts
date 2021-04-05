import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DialogService } from '../../../dialog.service';
import { Cliente } from '../../../model/cliente.model';
import { ClienteService } from '../../../services/cliente.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  clientes: Cliente[] = [];  
  cpf_cnpj:string;
  message:{};
  classCss:{};
  nome: string = null;
  ativo: boolean;

  constructor(
    private clienteService: ClienteService,
    private dialogService: DialogService,
    private router: Router
  ) { }

  ngOnInit() {
    this.findAllClientes();
  }

  findAllClientes(): void {
    this.clienteService.findAll().subscribe(
      data => {
        this.clientes = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  edit(id:string){
    this.router.navigate(['/edit',id]);
  }

  filter(){
    if(this.nome != null){
      this.clienteService.detailName(this.nome).subscribe(
        data => {
          this.clientes = data;
        },
        err => {
          this.showMessage({
            type: 'error',
            text: err.error.message
          });
        }
      );
    }

    if(this.ativo != null){
      this.clienteService.detailAtivo(this.ativo).subscribe(
        data => {
          this.clientes = data;
        },
        err => {
          this.showMessage({
            type: 'error',
            text: err.error.message
          });
        }
      );
    }
  }

  cleanFilter(){
    this.nome = null;
    this.ativo = null;
    this.findAllClientes();
  }

  detail(id:number){
    this.router.navigate(['/detail',id]);
  }

  delete(id:number){
    this.dialogService.confirm('Confirma a exclusão do cliente selecionado')
      .then((candelete:boolean) => {
          if(candelete){
            this.message = {};
            this.clienteService.delete(id).subscribe(
              data => {
                this.showMessage({
                  type: 'success',
                  text: `Cliente excluído com sucesso!`
                });
                this.findAllClientes();
            } , err => {
              this.showMessage({
                type: 'error',
                text: err['error']['errors'][0]
              });
            });
          }
      });
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
