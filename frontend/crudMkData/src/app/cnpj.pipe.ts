import { Pipe, PipeTransform } from '@angular/core';
import { maskBr } from 'js-brasil';

@Pipe({
  name: 'cnpj'
})
export class CnpjPipe implements PipeTransform {

  transform(cnpjValue: any): string {
    return maskBr.cnpj(cnpjValue);
  }

}
