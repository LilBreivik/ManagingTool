import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { ScheduleUploadAssetsManagerApplicationModule  } from './assetsmanager/course.scheduleuploadassetsmanager.app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(ScheduleUploadAssetsManagerApplicationModule  )
  .catch(err => console.log(err));
