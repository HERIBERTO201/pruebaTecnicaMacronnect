import { DetalleVentaModel } from "./detalle-venta-model";

export interface VentaModel {
    
  id?: number;

  fecha?: string;

  total: number;

  folio: string;

  clienteId: number;

  estado: string;

  detalles: DetalleVentaModel[];
}
