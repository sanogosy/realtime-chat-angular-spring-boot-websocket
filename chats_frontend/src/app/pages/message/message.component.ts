import { Component, OnInit } from '@angular/core';
import { TabsComponent } from '../../partials/tabs/tabs.component';
import { NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../interface/user';
import { UserMessage } from '../../interface/user-message';
import { MessageService } from '../../services/message.service';

@Component({
  selector: 'app-message',
  imports: [TabsComponent, FormsModule],
  templateUrl: './message.component.html',
  styleUrl: './message.component.css'
})
export class MessageComponent implements OnInit {

    currentTab: string = 'Messages recu';
    currentUser: User = {};
    messages: UserMessage[] = [];

    constructor(
        private userService: UserService,
        private messageService: MessageService
    ) {
        //
    }

    ngOnInit(): void {
        if(localStorage.getItem('email') !== null) {
            console.log('User email:', localStorage.getItem('email'));

            this.userService.getCurrentUserDataByEmail(localStorage.getItem('email')!).subscribe((res: any) => {
                console.log('Current user data : ',  res);

                this.currentUser = {
                    id: res.id,
                    firstname: res.firstName,
                    lastname: res.lastName,
                    email: res.email
                };

                if(this.currentUser.id !== undefined) {
                    this.getUserMessages(this.currentUser.id);
                }

            },
            (error) => {
                console.error('Failed to fetch current user data by email : ', error);
            });
        }
    }

    onTabClick(tab: string): void {
        console.log('Selected tab:', tab);
        this.currentTab = tab;
    }

    getUserMessages(userId: number): void {
        this.messageService.getMessagesById(userId).subscribe((res: any) => {
            console.log('User messages : ', res);
            this.messages = res.data;
        },
        (error) => {
            console.error('Failed to fetch user messages : ', error);
        });
    }

}
