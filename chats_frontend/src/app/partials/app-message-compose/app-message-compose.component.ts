import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-app-message-compose',
  imports: [FormsModule],
  templateUrl: './app-message-compose.component.html',
  styleUrl: './app-message-compose.component.css'
})
export class AppMessageComposeComponent {

    @Input() email: string = '';
    // @Input() message: string = '';

    @Output() messageContent = new EventEmitter<string>();

    constructor() {
        //
    }

    onClickSend(value: string): void {
        console.log('Clicked compose message : ', value);
        this.messageContent.emit(value);
    }

}
