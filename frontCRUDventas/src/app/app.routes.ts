import { Routes } from '@angular/router';
import { Login } from '../app/components/login/login'
import { Clientes } from './components/clientes/clientes';
import { Index } from './components/index';
import { Ventas } from './components/ventas/ventas';
import { Productos } from './components/productos/productos';


export const routes: Routes = [

    {
        path:'',
        component:Login
    },

    {
        path:'index',
        component:Index,
        children:[
            {
                path:'productos',
                component:Productos
            },
            {
                path:'clientes',
                component:Clientes
            },
            {
                path:'ventas',
                component:Ventas
            },
            {
                path:'',
                redirectTo:'productos',
                pathMatch:'full'
            }
        ]
    }
];
