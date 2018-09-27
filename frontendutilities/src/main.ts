import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { FrontendUtilitiesApplicationModule  } from '../src/services/frontendutilities.app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
} 

platformBrowserDynamic().bootstrapModule(FrontendUtilitiesApplicationModule )
  .catch(err => console.error(err));

