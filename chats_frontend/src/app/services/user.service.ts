import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../interface/user';
import { Observable } from 'rxjs';
import { AuthenticationRequest } from '../interface/authentication-request';
import { apiUrlRegister, apiUrlLogin, apiUrlUserByEmail } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {
      //
  }

  register(user: User): Observable<any> {
      return this.httpClient.post(apiUrlRegister, user);
  }

  login(authenticationRequest: AuthenticationRequest): Observable<any> {
      return this.httpClient.post(apiUrlLogin, authenticationRequest);
  }

  // Fetch user data by email (request param)
  getCurrentUserDataByEmail(email: string): Observable<any> {
      
      return this.httpClient.get(apiUrlUserByEmail, {
        params: { email: email.toString() }
      });

  }

}
