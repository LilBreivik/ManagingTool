import { TestBed, async } from '@angular/core/testing';
import {  CourseScheduleManagerApplicationModule } from './courseschedulemanager.app.module';

describe('CourseScheduleManagerApplicationModule', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        CourseScheduleManagerApplicationModule 
      ],
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent( CourseScheduleManagerApplicationModule );
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'schedulemanager'`, async(() => {
    const fixture = TestBed.createComponent( CourseScheduleManagerApplicationModule );
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('schedulemanager');
  }));
  it('should render title in a h1 tag', async(() => {
    const fixture = TestBed.createComponent( CourseScheduleManagerApplicationModule );
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Welcome to schedulemanager!');
  }));
});
