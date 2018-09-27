import { HttpClient } from '@angular/common/http';
import { map, tap , catchError} from 'rxjs/operators';
import { Observable, of } from 'rxjs';   
import { HttpClientModule } from '@angular/common/http'; 
import { ReflectiveInjector , Inject, ViewChild} from '@angular/core';  
import {ErrorResponse} from "../../utils/error/error.response.component";

declare function setUpErrorModal( response : ErrorResponse )

export class RESTService<T> {
 
  protected p_RESTClient: HttpClient;
  private  Reflect: any;
    
  constructor(protected serviceURL : string ) 
  { 
      let injector = ReflectiveInjector.resolveAndCreate(this.getAnnotations(HttpClientModule)[0].providers);
  
      this.p_RESTClient =  injector.get(HttpClient);
  }
    
  /** REST Object from Server */
    
  protected getRESTObject (): Observable<T> { 
   
    return this.p_RESTClient.get<T >(this.serviceURL).pipe(
          // _ => this.log(`fetched hero id=1`)
        tap<T>(res => console.log(res)),
        catchError(this.handleError<T>(`getHero id=1`))
     );
 
  }
 
  protected postRESTObjectNonParsed<U> (postParameter: U ){ 
         
    return this.p_RESTClient.post(this.serviceURL, postParameter, {responseType : 'blob'});
  
  } 

  protected postRESTObject<U> (postParameter: U){ 
         
    return this.p_RESTClient.post<T>(this.serviceURL, postParameter ).pipe(
         
        tap(_ => this.log(`fetched hero id=1`)),
        catchError(this.handleError<T>(`getHero id=1`))
     );
  } 

 
  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  protected handleError<T> (operation = 'operation', result?: T) {
     
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      
      // we retrieve the error Response from the server 
      let errorResponse = ErrorResponse.buildErrorResponse(error.error) as ErrorResponse

  
      setUpErrorModal(errorResponse)
      // TODO: better job of transforming error for user consumption 

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

   /** Log a HeroService message with the MessageService */
   protected log(message: string) {
    
    console.log("message " + message);
    //setUpProgressModal(); 
  }

 getAnnotations(typeOrFunc: any): any[]|null {
  // Prefer the direct API.
  if ((<any>typeOrFunc).annotations) {
    let annotations = (<any>typeOrFunc).annotations;
    if (typeof annotations === 'function' && annotations.annotations) {
      annotations = annotations.annotations;
    }
    return annotations;
  }

  // API of tsickle for lowering decorators to properties on the class.
  if ((<any>typeOrFunc).decorators) {
    return this.convertTsickleDecoratorIntoMetadata((<any>typeOrFunc).decorators);
  }

  // API for metadata created by invoking the decorators.
 
  return null;
}

convertTsickleDecoratorIntoMetadata(decoratorInvocations: any[]): any[] {
  if (!decoratorInvocations) {
    return [];
  }
  return decoratorInvocations.map(decoratorInvocation => {
    const decoratorType = decoratorInvocation.type;
    const annotationCls = decoratorType.annotationCls;
    const annotationArgs = decoratorInvocation.args ? decoratorInvocation.args : [];
    return new annotationCls(...annotationArgs);
  });

}


}
