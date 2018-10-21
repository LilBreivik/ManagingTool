import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import {PasswordChangeManagerApplicationModule } from '../src/app/passwordchangemanager.app.module';
//'../passwordchangemanager/src/app/passwordchangemanager.app.module';
import { environment } from "@frontendutilities/src/environments/environment";
 
if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(PasswordChangeManagerApplicationModule )
  .catch(err => console.log(err));
 