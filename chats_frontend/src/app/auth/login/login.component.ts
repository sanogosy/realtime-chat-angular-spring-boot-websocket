import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthenticationRequest } from '../../interface/authentication-request';
import { UserService } from '../../services/user.service';
import { NgClass, NgIf } from '@angular/common';
import { FormsModule, NgForm, NgModel } from '@angular/forms';
import { AuthenticationResponse } from '../../interface/authentication-response';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink, NgIf, NgClass],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

    authenticationRequest: AuthenticationRequest = {};
    authenticationResponse: AuthenticationResponse = {};
    
    constructor(private router: Router,private userService: UserService) {
        //
    }

    ngOnInit(): void {
        // throw new Error('Method not implemented.');
        if(localStorage.getItem('loginSuccess') !== null){
            console.log('loginSuccess');
            this.router.navigate(['/verified/messages']);
        }
        else {
            console.log('not login');
        }
    }

    login(): void {
      console.log(this.authenticationRequest);

      this.userService.login(this.authenticationRequest).subscribe((res: any) => {
          this.authenticationResponse = res;
          console.log('res', this.authenticationResponse);
          localStorage.setItem('loginSuccess', 'true');
          localStorage.setItem('email', this.authenticationResponse.email!);
          localStorage.setItem('token', this.authenticationResponse.token!);

          // localStorage.setItem('userFirstName', res.data.firstname);
          // localStorage.setItem('userLastName', res.data.lastname);
          
          this.router.navigate(['/verified/messages']);
          // this.router.navigateByUrl('messages');
      },
      (error) => {
          console.error('Registration failed:', error);
      });
  }

}
