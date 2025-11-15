import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppMessageComposeComponent } from './app-message-compose.component';

describe('AppMessageComposeComponent', () => {
  let component: AppMessageComposeComponent;
  let fixture: ComponentFixture<AppMessageComposeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppMessageComposeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppMessageComposeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
