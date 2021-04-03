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

  delete(id:number){
    this.message = {};
    this.clienteService.delete(id).subscribe(
      data => {
        this.showMessage({
          type: 'success',
          text: data.success.message
        });
        this.router.navigate(['/lista']);
      },
      err => {
        this.showMessage({
          type: 'error',
          text: err.error.message
        });
      }
    );   
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
