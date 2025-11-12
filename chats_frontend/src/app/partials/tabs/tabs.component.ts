import { NgClass } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-tabs',
  imports: [FormsModule, NgClass],
  templateUrl: './tabs.component.html',
  styleUrl: './tabs.component.css'
})
export class TabsComponent implements OnInit {

    @Input() tabs: string[] = [];

    @Output() selectedTab = new EventEmitter<string>();

    currentTab: string = '';

    constructor() {
        //
    }

    ngOnInit(): void {
        if(this.tabs.length > 0){
            this.currentTab = this.tabs[0];
        }
    }

    onClickTab(tab: string): void {
        console.log('Clicked tab:', tab);
        this.currentTab = tab;
        this.selectedTab.emit(tab);
    }

}
