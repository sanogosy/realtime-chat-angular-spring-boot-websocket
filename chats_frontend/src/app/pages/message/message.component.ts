import { Component, OnInit } from '@angular/core';
import { TabsComponent } from '../../partials/tabs/tabs.component';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../interface/user';
import { UserMessage } from '../../interface/user-message';
import { MessageService } from '../../services/message.service';
import { Router } from '@angular/router';
import { UserDto } from '../../interface/user-dto';
import { AppMessageComposeComponent } from '../../partials/app-message-compose/app-message-compose.component';
import { MessageRequest } from '../../interface/message-request';

@Component({
  selector: 'app-message',
  imports: [TabsComponent, AppMessageComposeComponent, FormsModule, DatePipe, NgIf],
  templateUrl: './message.component.html',
  styleUrl: './message.component.css'
})
export class MessageComponent implements OnInit {

    currentTab: string = 'Messages recu';
    currentUser: User = {};
    messages: UserMessage[] = [];
    users: UserDto[] = [];
    sendmessage: boolean = false;
    receiverId: number = 0;
    useremail: string = '';
    messageToSend: string = '';

    messageRequest: MessageRequest = {};

    constructor(
        private router: Router,
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
                    this.getAllUsers();
                }

            },
            (error) => {
                console.error('Failed to fetch current user data by email : ', error);
                this.logout();
            });
        }
        else {
            localStorage.removeItem('email');
            localStorage.removeItem('token');
            localStorage.removeItem('loginSuccess');
            this.router.navigate(['/login']);
        }
    }

    onTabClick(tab: string): void {
        console.log('Selected tab:', tab);
        this.sendmessage = false;
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

    getAllUsers(): void {
        this.userService.getAllUsers().subscribe((res: any) => {
            console.log('All users : ', res);
            this.users = res;
        },
        (error) => {
            console.error('Failed to fetch all users : ', error);
        });

    }

    composeMessage(user: UserDto): void {
        console.log('On click compose message to user : ', user);
        this.sendmessage = true;
        this.receiverId = user.id!;
        this.useremail = user.email!;
    }

    sendContent(content: string): void {
        console.log('Message content to send : ', content);
        this.messageToSend = content;

        if(this.useremail !== '' && this.messageToSend !== '') {
            this.messageRequest = {
                senderId: this.currentUser.id!,
                receiverId: this.receiverId,
                content: this.messageToSend
            };

            this.messageService.sendMessage(this.messageRequest).subscribe((res: any) => {
                console.log('Message sent successfully : ', res);
            },
            (error) => {
                console.error('Failed to send message : ', error);
            });
        }
        else {
            console.error('User email or message is mandatory.');
        }
    }

    logout() {
        localStorage.removeItem('loginSuccess');
        localStorage.removeItem('token');
        // window.location.href = '/login';
        this.router.navigate(['/login']);
    }

}
