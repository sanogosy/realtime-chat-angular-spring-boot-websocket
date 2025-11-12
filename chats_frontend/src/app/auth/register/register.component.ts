import { Component, OnInit } from '@angular/core';
import { Route, Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../interface/user';
import { FormsModule } from '@angular/forms';
import { NgClass, NgIf } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink, NgIf, NgClass],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {

  user: User = {};

  constructor(
    private router: Router, 
    private userService: UserService
  ) { 
      //
  }

  ngOnInit(): void {
      //
  }

  register(): void {
      console.log(this.user);
      this.userService.register(this.user).subscribe(() => {
          this.router.navigate(['/login']);
      },
      (error) => {
          console.error('Registration failed:', error);
      });
  }

}
