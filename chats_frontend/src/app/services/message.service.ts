import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiUrlGetUserMessages } from '../constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

    constructor(private httpClient: HttpClient) {
        //
    }

    // Implement API call to fetch messages for the current user (pathvariable)
    getMessagesById(userId: number) : Observable<any> {
        return this.httpClient.get(apiUrlGetUserMessages)
    }

}
