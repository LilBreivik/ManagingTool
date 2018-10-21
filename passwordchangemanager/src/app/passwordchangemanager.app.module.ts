import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';  
import { PasswordChangeManager } from './passwordchangemanager.component';
import { PasswordBar} from "./passwordbar/passwordbar.component";
import {FrontendUtilitiesApplicationModule} from "@frontendutilities/src/services/frontendutilities.app.module"; 
import { HttpClientModule } from '@angular/common/http';

@NgModule({ 
  declarations: [
    PasswordChangeManager,
    PasswordBar ,
  
  ], 
  imports: [ 
    BrowserModule, 
    FrontendUtilitiesApplicationModule,
    FormsModule 
  ], 
  bootstrap: [PasswordChangeManager] 
}) 
export class PasswordChangeManagerApplicationModule{ }
  