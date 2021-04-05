import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Cliente } from '../../../model/cliente.model';
import { ClienteService } from '../../../services/cliente.service';

@Component({
  selector: 'app-detalhe',
  templateUrl: './detalhe.component.html',
  styleUrls: ['./detalhe.component.css']
})
export class DetalheComponent implements OnInit {

  @ViewChild("form")
  form: NgForm;
  
  message:{};
  classCss:{};
  cliente = new Cliente(null,'','','','','',null,null,null,[]);

  constructor(
    private clienteService: ClienteService,
    private activeRoute: ActivatedRoute
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
