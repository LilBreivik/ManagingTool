import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { LectureUploadAssetsManagerApplicationModule  } from 'assetsmanager/lecture.scheduleuploadassetsmanager.app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(LectureUploadAssetsManagerApplicationModule  )
  .catch(err => console.log(err));
