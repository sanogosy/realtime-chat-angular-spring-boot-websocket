import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { LayoutComponent } from './partials/layout/layout.component';
import { RegisterComponent } from './auth/register/register.component';
import { MessageComponent } from './pages/message/message.component';
import { authGuard } from './guard/auth.guard';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'verified',
        component: LayoutComponent,
        canActivate: [authGuard],
        children: [
            {
                path: 'messages',
                component: MessageComponent
            }
        ]
    }
];
