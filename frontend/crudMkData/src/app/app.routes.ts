import { ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DetalheComponent } from "./components/cliente/detalhe/detalhe.component";
import { EditarComponent } from "./components/cliente/editar/editar.component";
import { ListaComponent } from "./components/cliente/lista/lista.component";
import { NovoComponent } from "./components/cliente/novo/novo.component";
import { HomeComponent } from "./components/home/home.component";

export const ROUTES: Routes = [
    {path: '', component: HomeComponent},
    {path: 'lista', component: ListaComponent},
    {path: 'novo', component: NovoComponent},
    {path: 'detail/:idCliente', component: DetalheComponent},
    {path: 'edit/:idCliente', component: EditarComponent}
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);