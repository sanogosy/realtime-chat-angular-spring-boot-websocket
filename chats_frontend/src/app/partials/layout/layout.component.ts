import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageComponent } from '../../pages/message/message.component';

@Component({
  selector: 'app-layout',
  imports: [MessageComponent],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {

    constructor(private router: Router) {
        //
    }

    logout() {
        localStorage.removeItem('loginSuccess');
        localStorage.removeItem('token');
        // window.location.href = '/login';
        this.router.navigate(['/login']);
    }

}
