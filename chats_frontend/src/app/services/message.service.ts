import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiUrlGetUserMessages, apiUrlSendMessage } from '../constants';
import { Observable } from 'rxjs';
import { MessageRequest } from '../interface/message-request';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

    constructor(private httpClient: HttpClient) {
        //
    }

    // Implement API call to fetch messages for the current user (pathvariable)
    getMessagesById(userId: number) : Observable<any> {
        return this.httpClient.get(apiUrlGetUserMessages + '/' + parseInt(userId.toString()));
    }

    sendMessage(messageRequest: MessageRequest): Observable<any> {
        return this.httpClient.post(apiUrlSendMessage, messageRequest);
    }

}
