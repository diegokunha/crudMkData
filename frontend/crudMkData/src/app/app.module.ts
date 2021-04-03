import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MenuComponent } from './components/menu/menu.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { routes } from './app.routes';
import { ListaComponent } from './components/cliente/lista/lista.component';
import { NovoComponent } from './components/cliente/novo/novo.component';
import { ClienteService } from './services/cliente.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { DetalheComponent } from './components/cliente/detalhe/detalhe.component';
import { EditarComponent } from './components/cliente/editar/editar.component';
import { DialogService } from './dialog.service';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MenuComponent,
    FooterComponent,
    HomeComponent,
    ListaComponent,
    NovoComponent,
    DetalheComponent,
    EditarComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    routes
  ],
  providers: [
    ClienteService,
    DialogService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
