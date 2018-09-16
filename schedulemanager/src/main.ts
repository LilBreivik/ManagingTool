import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { CourseScheduleManagerApplicationModule } from "./courseschedulemanager/courseschedulemanager.app.module";
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(CourseScheduleManagerApplicationModule)
  .catch(err => console.log(err));
