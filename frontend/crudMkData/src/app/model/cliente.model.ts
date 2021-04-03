export class Cliente{
    constructor(
        public idCliente: number,
        public nome: string,
        public cpf: string,
        public rg: string,
        public cnpj: string,
        public ie: string,
        public tipo: number,
        public dataCriacao: Date,
        public ativo: boolean,
        public telefones: string[]
    ){}
}