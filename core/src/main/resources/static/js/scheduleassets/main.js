(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "../frontendutilities/src/environments/environment.ts":
/*!************************************************************!*\
  !*** ../frontendutilities/src/environments/environment.ts ***!
  \************************************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
var environment = {
    production: false,
    API_URL: 'api',
};


/***/ }),

/***/ "../frontendutilities/src/pipes/coursedegreepipe.ts":
/*!**********************************************************!*\
  !*** ../frontendutilities/src/pipes/coursedegreepipe.ts ***!
  \**********************************************************/
/*! exports provided: UniqueCourseDegreeFilterPipe */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UniqueCourseDegreeFilterPipe", function() { return UniqueCourseDegreeFilterPipe; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _uniquepipe__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./uniquepipe */ "../frontendutilities/src/pipes/uniquepipe.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var UniqueCourseDegreeFilterPipe = /** @class */ (function (_super) {
    __extends(UniqueCourseDegreeFilterPipe, _super);
    function UniqueCourseDegreeFilterPipe() {
        var _this = _super.call(this) || this;
        _this.itemAttribute = function (item) {
            var coursename = item.courseDegree;
            return coursename;
        };
        return _this;
    }
    UniqueCourseDegreeFilterPipe = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Pipe"])({ name: 'coursedegrees' }),
        __metadata("design:paramtypes", [])
    ], UniqueCourseDegreeFilterPipe);
    return UniqueCourseDegreeFilterPipe;
}(_uniquepipe__WEBPACK_IMPORTED_MODULE_1__["UniqueFilterPipe"]));



/***/ }),

/***/ "../frontendutilities/src/pipes/coursenamepipe.ts":
/*!********************************************************!*\
  !*** ../frontendutilities/src/pipes/coursenamepipe.ts ***!
  \********************************************************/
/*! exports provided: UniqueCourseNameFilterPipe */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UniqueCourseNameFilterPipe", function() { return UniqueCourseNameFilterPipe; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _uniquepipe__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./uniquepipe */ "../frontendutilities/src/pipes/uniquepipe.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var UniqueCourseNameFilterPipe = /** @class */ (function (_super) {
    __extends(UniqueCourseNameFilterPipe, _super);
    function UniqueCourseNameFilterPipe() {
        var _this = _super.call(this) || this;
        console.log("XXXXXXXXXXXX");
        _this.itemAttribute = function (item) {
            var coursename = item.courseName;
            return coursename;
        };
        return _this;
    }
    UniqueCourseNameFilterPipe = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Pipe"])({ name: 'coursenames' }),
        __metadata("design:paramtypes", [])
    ], UniqueCourseNameFilterPipe);
    return UniqueCourseNameFilterPipe;
}(_uniquepipe__WEBPACK_IMPORTED_MODULE_1__["UniqueFilterPipe"]));



/***/ }),

/***/ "../frontendutilities/src/pipes/coursetermpipe.ts":
/*!********************************************************!*\
  !*** ../frontendutilities/src/pipes/coursetermpipe.ts ***!
  \********************************************************/
/*! exports provided: UniqueCourseTermFilterPipe */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UniqueCourseTermFilterPipe", function() { return UniqueCourseTermFilterPipe; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _uniquepipe__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./uniquepipe */ "../frontendutilities/src/pipes/uniquepipe.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var UniqueCourseTermFilterPipe = /** @class */ (function (_super) {
    __extends(UniqueCourseTermFilterPipe, _super);
    function UniqueCourseTermFilterPipe() {
        var _this = _super.call(this) || this;
        _this.itemAttribute = function (item) {
            var coursename = item.courseTerm;
            return coursename;
        };
        return _this;
    }
    UniqueCourseTermFilterPipe = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Pipe"])({ name: 'courseterms' }),
        __metadata("design:paramtypes", [])
    ], UniqueCourseTermFilterPipe);
    return UniqueCourseTermFilterPipe;
}(_uniquepipe__WEBPACK_IMPORTED_MODULE_1__["UniqueFilterPipe"]));



/***/ }),

/***/ "../frontendutilities/src/pipes/parallelcoursenamefilterpipe.ts":
/*!**********************************************************************!*\
  !*** ../frontendutilities/src/pipes/parallelcoursenamefilterpipe.ts ***!
  \**********************************************************************/
/*! exports provided: ParallelCourseNameFilterPipe */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ParallelCourseNameFilterPipe", function() { return ParallelCourseNameFilterPipe; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var ParallelCourseNameFilterPipe = /** @class */ (function () {
    function ParallelCourseNameFilterPipe() {
        this.itemAttribute = function (item) {
            var coursename = item.courseName;
            return coursename;
        };
    }
    ParallelCourseNameFilterPipe.prototype.transform = function (items, args) {
        var _this = this;
        var parallelArray = [];
        items.forEach(function (item) {
            if ((_this.itemAttribute(item) !== args) && (parallelArray.indexOf(_this.itemAttribute(item)) == -1)) {
                parallelArray.push(_this.itemAttribute(item));
            }
        });
        return parallelArray;
    };
    ParallelCourseNameFilterPipe = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        }),
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Pipe"])({ name: 'parallelCourse' })
    ], ParallelCourseNameFilterPipe);
    return ParallelCourseNameFilterPipe;
}());



/***/ }),

/***/ "../frontendutilities/src/pipes/uniquepipe.ts":
/*!****************************************************!*\
  !*** ../frontendutilities/src/pipes/uniquepipe.ts ***!
  \****************************************************/
/*! exports provided: UniqueFilterPipe */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UniqueFilterPipe", function() { return UniqueFilterPipe; });
/*
    Utility Class
    to remove double values from
    datasources
*/
var UniqueFilterPipe = /** @class */ (function () {
    function UniqueFilterPipe() {
        this.itemAttribute = function (item) { };
    }
    UniqueFilterPipe.prototype.transform = function (items, args) {
        var _this = this;
        var uniqueArray = [];
        if (!args) {
            items.forEach(function (item) {
                console.log(args + " " + (!args));
                console.log(JSON.stringify(_this.itemAttribute(item)));
                console.log(uniqueArray.indexOf(_this.itemAttribute(item)));
                console.log("------>  " + JSON.stringify(item));
                if (uniqueArray.indexOf(_this.itemAttribute(item)) == -1) {
                    uniqueArray.push(_this.itemAttribute(item));
                    console.log(uniqueArray[uniqueArray.length - 1]);
                }
            });
            return uniqueArray;
        }
    };
    return UniqueFilterPipe;
}());



/***/ }),

/***/ "../frontendutilities/src/services/REST/coursesschedule.service.ts":
/*!*************************************************************************!*\
  !*** ../frontendutilities/src/services/REST/coursesschedule.service.ts ***!
  \*************************************************************************/
/*! exports provided: CoursesScheduleService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CoursesScheduleService", function() { return CoursesScheduleService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _rest_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./rest.service */ "../frontendutilities/src/services/REST/rest.service.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


/**
 * Service class for handling
 * the Course Schedule POJO
 */
var CoursesScheduleService = /** @class */ (function (_super) {
    __extends(CoursesScheduleService, _super);
    function CoursesScheduleService() {
        return _super.call(this, "/Synthesize/GeneralCourseSchedule") || this;
    }
    /** GET hero by id. Will 404 if id not found */
    CoursesScheduleService.prototype.getCourseSchedule = function () {
        return this.getRESTObject();
    };
    CoursesScheduleService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [])
    ], CoursesScheduleService);
    return CoursesScheduleService;
}(_rest_service__WEBPACK_IMPORTED_MODULE_1__["RESTService"]));



/***/ }),

/***/ "../frontendutilities/src/services/REST/rest.service.ts":
/*!**************************************************************!*\
  !*** ../frontendutilities/src/services/REST/rest.service.ts ***!
  \**************************************************************/
/*! exports provided: RESTService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RESTService", function() { return RESTService; });
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs/operators */ "../frontendutilities/node_modules/rxjs/_esm5/operators/index.js");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "../frontendutilities/node_modules/rxjs/_esm5/index.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");





var RESTService = /** @class */ (function () {
    function RESTService(serviceURL) {
        this.serviceURL = serviceURL;
        var injector = _angular_core__WEBPACK_IMPORTED_MODULE_3__["ReflectiveInjector"].resolveAndCreate(this.getAnnotations(_angular_common_http__WEBPACK_IMPORTED_MODULE_0__["HttpClientModule"])[0].providers);
        this.m_RESTClient = injector.get(_angular_common_http__WEBPACK_IMPORTED_MODULE_0__["HttpClient"]);
    }
    /** REST Object from Server */
    RESTService.prototype.getRESTObject = function () {
        var _this = this;
        return this.m_RESTClient.get(this.serviceURL).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["tap"])(function (_) { return _this.log("fetched hero id=1"); }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["catchError"])(this.handleError("getHero id=1")));
    };
    RESTService.prototype.postRESTObjectNonParsed = function (postParameter) {
        return this.m_RESTClient.post(this.serviceURL, postParameter, { responseType: 'blob' });
    };
    RESTService.prototype.postRESTObject = function (postParameter) {
        var _this = this;
        return this.m_RESTClient.post(this.serviceURL, postParameter).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["tap"])(function (_) { return _this.log("fetched hero id=1"); }), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["catchError"])(this.handleError("getHero id=1")));
    };
    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    RESTService.prototype.handleError = function (operation, result) {
        var _this = this;
        if (operation === void 0) { operation = 'operation'; }
        return function (error) {
            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead
            // TODO: better job of transforming error for user consumption
            _this.log(operation + " failed: " + error.message);
            // Let the app keep running by returning an empty result.
            return Object(rxjs__WEBPACK_IMPORTED_MODULE_2__["of"])(result);
        };
    };
    /** Log a HeroService message with the MessageService */
    RESTService.prototype.log = function (message) {
        console.log("message " + message);
    };
    RESTService.prototype.getAnnotations = function (typeOrFunc) {
        // Prefer the direct API.
        if (typeOrFunc.annotations) {
            var annotations = typeOrFunc.annotations;
            if (typeof annotations === 'function' && annotations.annotations) {
                annotations = annotations.annotations;
            }
            return annotations;
        }
        // API of tsickle for lowering decorators to properties on the class.
        if (typeOrFunc.decorators) {
            return this.convertTsickleDecoratorIntoMetadata(typeOrFunc.decorators);
        }
        // API for metadata created by invoking the decorators.
        return null;
    };
    RESTService.prototype.convertTsickleDecoratorIntoMetadata = function (decoratorInvocations) {
        if (!decoratorInvocations) {
            return [];
        }
        return decoratorInvocations.map(function (decoratorInvocation) {
            var decoratorType = decoratorInvocation.type;
            var annotationCls = decoratorType.annotationCls;
            var annotationArgs = decoratorInvocation.args ? decoratorInvocation.args : [];
            return new (annotationCls.bind.apply(annotationCls, [void 0].concat(annotationArgs)))();
        });
    };
    return RESTService;
}());



/***/ }),

/***/ "../frontendutilities/src/services/entities/Parameter/courserequestparameter.ts":
/*!**************************************************************************************!*\
  !*** ../frontendutilities/src/services/entities/Parameter/courserequestparameter.ts ***!
  \**************************************************************************************/
/*! exports provided: CourseRequestParameter */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CourseRequestParameter", function() { return CourseRequestParameter; });
var CourseRequestParameter = /** @class */ (function () {
    function CourseRequestParameter() {
    }
    return CourseRequestParameter;
}());



/***/ }),

/***/ "../frontendutilities/src/services/frontendutilities.app.module.ts":
/*!*************************************************************************!*\
  !*** ../frontendutilities/src/services/frontendutilities.app.module.ts ***!
  \*************************************************************************/
/*! exports provided: FrontendUtilitiesApplicationModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FrontendUtilitiesApplicationModule", function() { return FrontendUtilitiesApplicationModule; });
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _pipes_coursenamepipe__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../pipes/coursenamepipe */ "../frontendutilities/src/pipes/coursenamepipe.ts");
/* harmony import */ var _pipes_coursedegreepipe__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../pipes/coursedegreepipe */ "../frontendutilities/src/pipes/coursedegreepipe.ts");
/* harmony import */ var _pipes_coursetermpipe__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../pipes/coursetermpipe */ "../frontendutilities/src/pipes/coursetermpipe.ts");
/* harmony import */ var _pipes_parallelcoursenamefilterpipe__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../pipes/parallelcoursenamefilterpipe */ "../frontendutilities/src/pipes/parallelcoursenamefilterpipe.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};







var FrontendUtilitiesApplicationModule = /** @class */ (function () {
    function FrontendUtilitiesApplicationModule() {
    }
    FrontendUtilitiesApplicationModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_0__["HttpClientModule"]
            ],
            declarations: [
                _pipes_coursenamepipe__WEBPACK_IMPORTED_MODULE_3__["UniqueCourseNameFilterPipe"],
                _pipes_coursedegreepipe__WEBPACK_IMPORTED_MODULE_4__["UniqueCourseDegreeFilterPipe"],
                _pipes_coursetermpipe__WEBPACK_IMPORTED_MODULE_5__["UniqueCourseTermFilterPipe"],
                _pipes_parallelcoursenamefilterpipe__WEBPACK_IMPORTED_MODULE_6__["ParallelCourseNameFilterPipe"]
            ],
            exports: [
                _pipes_coursenamepipe__WEBPACK_IMPORTED_MODULE_3__["UniqueCourseNameFilterPipe"],
                _pipes_coursedegreepipe__WEBPACK_IMPORTED_MODULE_4__["UniqueCourseDegreeFilterPipe"],
                _pipes_coursetermpipe__WEBPACK_IMPORTED_MODULE_5__["UniqueCourseTermFilterPipe"],
                _pipes_parallelcoursenamefilterpipe__WEBPACK_IMPORTED_MODULE_6__["ParallelCourseNameFilterPipe"]
            ]
        })
    ], FrontendUtilitiesApplicationModule);
    return FrontendUtilitiesApplicationModule;
}());



/***/ }),

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/assetsmanager/assetsmanager.component.ts":
/*!******************************************************!*\
  !*** ./src/assetsmanager/assetsmanager.component.ts ***!
  \******************************************************/
/*! exports provided: AssetsManager */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AssetsManager", function() { return AssetsManager; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var file_saver__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! file-saver */ "./node_modules/file-saver/FileSaver.js");
/* harmony import */ var file_saver__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(file_saver__WEBPACK_IMPORTED_MODULE_1__);
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var AssetsManager = /** @class */ (function () {
    function AssetsManager(m_assetsStockService, m_deleteFileService, m_downloadFileService, m_coursescheduleService, m_uploadFileService, m_requestParameter, m_uploadFileRequestParameter) {
        this.m_assetsStockService = m_assetsStockService;
        this.m_deleteFileService = m_deleteFileService;
        this.m_downloadFileService = m_downloadFileService;
        this.m_coursescheduleService = m_coursescheduleService;
        this.m_uploadFileService = m_uploadFileService;
        this.m_requestParameter = m_requestParameter;
        this.m_uploadFileRequestParameter = m_uploadFileRequestParameter;
        this.assetsThere = false;
    }
    AssetsManager.prototype.ngOnInit = function () {
        var _this = this;
        this.m_coursescheduleService.getCourseSchedule().subscribe(function (courseSchedule) {
            _this.coursesSchedule = [];
            courseSchedule.coursesSchedulePOJO.forEach(function (course) {
                _this.coursesSchedule.push(course);
            });
            // we have to init the default values manually due to the missing capability of the 
            // selected tag in angular
            _this.selectedCourseNameValue = _this.coursesSchedule[0].courseName;
            _this.selectedCourseDegreeValue = _this.coursesSchedule[0].courseDegree;
            _this.selectedCourseTermValue = _this.coursesSchedule[0].courseTerm;
            _this.readAssetsInformation();
        });
    };
    AssetsManager.prototype.readAssetsInformation = function () {
        var _this = this;
        this.m_requestParameter.courseName = this.selectedCourseNameValue;
        this.m_requestParameter.courseDegree = this.selectedCourseDegreeValue;
        this.m_requestParameter.courseTerm = this.selectedCourseTermValue;
        this.m_assetsStockService.requestFileAssetsStock(this.m_requestParameter).subscribe(function (assetsStock) {
            _this.assetsThere = assetsStock.presentFlag;
        });
    };
    AssetsManager.prototype.deleteAsset = function () {
        var _this = this;
        this.m_requestParameter.courseName = this.selectedCourseNameValue;
        this.m_requestParameter.courseDegree = this.selectedCourseDegreeValue;
        this.m_requestParameter.courseTerm = this.selectedCourseTermValue;
        // handle delete answer 
        this.m_deleteFileService.deleteFile(this.m_requestParameter).subscribe(function (deleteResponse) {
            _this.readAssetsInformation();
        });
    };
    AssetsManager.prototype.downloadAsset = function () {
        var _this = this;
        this.m_requestParameter.courseName = this.selectedCourseNameValue;
        this.m_requestParameter.courseDegree = this.selectedCourseDegreeValue;
        this.m_requestParameter.courseTerm = this.selectedCourseTermValue;
        // handle delete answer 
        this.m_downloadFileService.downloadFile(this.m_requestParameter).subscribe(function (response) {
            var filename = 'report.xml';
            file_saver__WEBPACK_IMPORTED_MODULE_1__["saveAs"](response, filename);
            _this.readAssetsInformation();
        });
    };
    AssetsManager.prototype.uploadAsset = function (test) {
        var _this = this;
        var reader = uploadAsset2(test.target);
        reader.onload = function (e) {
            _this.m_uploadFileRequestParameter.courseName = _this.selectedCourseNameValue;
            _this.m_uploadFileRequestParameter.courseDegree = _this.selectedCourseDegreeValue;
            _this.m_uploadFileRequestParameter.courseTerm = _this.selectedCourseTermValue;
            // alert(typeof reader.result)
            _this.m_uploadFileRequestParameter.scheduleFile = reader.result.toString();
            alert("---> " + JSON.stringify(_this.m_uploadFileRequestParameter));
            _this.m_uploadFileService.uploadFile(_this.m_uploadFileRequestParameter).subscribe(function (response) {
                alert(response);
                _this.readAssetsInformation();
            });
        };
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Array)
    ], AssetsManager.prototype, "coursesSchedule", void 0);
    return AssetsManager;
}());



/***/ }),

/***/ "./src/assetsmanager/courseschedule.assetsmanager.component.ts":
/*!*********************************************************************!*\
  !*** ./src/assetsmanager/courseschedule.assetsmanager.component.ts ***!
  \*********************************************************************/
/*! exports provided: CourseScheduleAssetsManager */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CourseScheduleAssetsManager", function() { return CourseScheduleAssetsManager; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _assetsmanager_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./assetsmanager.component */ "./src/assetsmanager/assetsmanager.component.ts");
/* harmony import */ var _services_files_assets_courseschedule_assets_stock_files_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/files/assets/courseschedule.assets.stock.files.service */ "./src/services/files/assets/courseschedule.assets.stock.files.service.ts");
/* harmony import */ var entities_parameter_assets_request_stock_assets_course_stock_request_parameter__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! entities/parameter/assets/request/stock/assets.course.stock.request.parameter */ "./src/entities/parameter/assets/request/stock/assets.course.stock.request.parameter.ts");
/* harmony import */ var _frontendutilities_src_services_REST_coursesschedule_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @frontendutilities/src/services/REST/coursesschedule.service */ "../frontendutilities/src/services/REST/coursesschedule.service.ts");
/* harmony import */ var _services_files_delete_courseschedule_delete_files_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../services/files/delete/courseschedule.delete.files.service */ "./src/services/files/delete/courseschedule.delete.files.service.ts");
/* harmony import */ var services_files_download_courseschedule_download_files_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! services/files/download/courseschedule.download.files.service */ "./src/services/files/download/courseschedule.download.files.service.ts");
/* harmony import */ var entities_parameter_assets_request_upload_assets_upload_request_parameter__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! entities/parameter/assets/request/upload/assets.upload.request.parameter */ "./src/entities/parameter/assets/request/upload/assets.upload.request.parameter.ts");
/* harmony import */ var services_files_upload_courseschedule_upload_files_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! services/files/upload/courseschedule.upload.files.service */ "./src/services/files/upload/courseschedule.upload.files.service.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};









var CourseScheduleAssetsManager = /** @class */ (function (_super) {
    __extends(CourseScheduleAssetsManager, _super);
    function CourseScheduleAssetsManager(coursesScheduleService, courseScheduleAssetsStockService, courseScheduleFileDeleteService, courseDownloadCourseScheduleFileService, courseUploadScheduleFileService, courseAssetsFileRequestParameter, courseAssetsFileUploadRequestParameter) {
        var _this = _super.call(this, courseScheduleAssetsStockService, courseScheduleFileDeleteService, courseDownloadCourseScheduleFileService, coursesScheduleService, courseUploadScheduleFileService, courseAssetsFileRequestParameter, courseAssetsFileUploadRequestParameter) || this;
        _this.coursesScheduleService = coursesScheduleService;
        _this.courseScheduleAssetsStockService = courseScheduleAssetsStockService;
        _this.courseScheduleFileDeleteService = courseScheduleFileDeleteService;
        _this.courseDownloadCourseScheduleFileService = courseDownloadCourseScheduleFileService;
        _this.courseUploadScheduleFileService = courseUploadScheduleFileService;
        _this.courseAssetsFileRequestParameter = courseAssetsFileRequestParameter;
        _this.courseAssetsFileUploadRequestParameter = courseAssetsFileUploadRequestParameter;
        return _this;
    }
    CourseScheduleAssetsManager = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'scheduleassetsmanager-root',
            template: __webpack_require__(/*! ./courseschedulemanager.component.html */ "./src/assetsmanager/courseschedulemanager.component.html"),
            providers: [_services_files_assets_courseschedule_assets_stock_files_service__WEBPACK_IMPORTED_MODULE_2__["CourseScheduleAssetsStockService"],
                _frontendutilities_src_services_REST_coursesschedule_service__WEBPACK_IMPORTED_MODULE_4__["CoursesScheduleService"],
                entities_parameter_assets_request_stock_assets_course_stock_request_parameter__WEBPACK_IMPORTED_MODULE_3__["CourseAssetsFileRequestParameter"],
                _services_files_delete_courseschedule_delete_files_service__WEBPACK_IMPORTED_MODULE_5__["DeleteCourseScheduleFileService"],
                services_files_download_courseschedule_download_files_service__WEBPACK_IMPORTED_MODULE_6__["DownloadCourseScheduleFileService"],
                services_files_upload_courseschedule_upload_files_service__WEBPACK_IMPORTED_MODULE_8__["UploadCourseScheduleFileService"],
                entities_parameter_assets_request_upload_assets_upload_request_parameter__WEBPACK_IMPORTED_MODULE_7__["UploadAssetsFileRequestParameter"]]
        }),
        __metadata("design:paramtypes", [_frontendutilities_src_services_REST_coursesschedule_service__WEBPACK_IMPORTED_MODULE_4__["CoursesScheduleService"],
            _services_files_assets_courseschedule_assets_stock_files_service__WEBPACK_IMPORTED_MODULE_2__["CourseScheduleAssetsStockService"],
            _services_files_delete_courseschedule_delete_files_service__WEBPACK_IMPORTED_MODULE_5__["DeleteCourseScheduleFileService"],
            services_files_download_courseschedule_download_files_service__WEBPACK_IMPORTED_MODULE_6__["DownloadCourseScheduleFileService"],
            services_files_upload_courseschedule_upload_files_service__WEBPACK_IMPORTED_MODULE_8__["UploadCourseScheduleFileService"],
            entities_parameter_assets_request_stock_assets_course_stock_request_parameter__WEBPACK_IMPORTED_MODULE_3__["CourseAssetsFileRequestParameter"],
            entities_parameter_assets_request_upload_assets_upload_request_parameter__WEBPACK_IMPORTED_MODULE_7__["UploadAssetsFileRequestParameter"]])
    ], CourseScheduleAssetsManager);
    return CourseScheduleAssetsManager;
}(_assetsmanager_component__WEBPACK_IMPORTED_MODULE_1__["AssetsManager"]));



/***/ }),

/***/ "./src/assetsmanager/courseschedulemanager.component.html":
/*!****************************************************************!*\
  !*** ./src/assetsmanager/courseschedulemanager.component.html ***!
  \****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<h1>This is the schedleuploadsassetsmanager</h1>\n\n<style>\n    .my-drop-zone { border: dotted 3px lightgray; }\n    /* Default class applied to drop zones on over */\n    .invalid-drag { border: dotted 3px red; }\n    .valid-drag { border: dotted 3px green; }\n    html, body { height: 100%; }\n    .previewIcon{\n      width:100px;height:100px;\n      background-size:cover;\n      background-repeat:no-repeat;\n    }\n   \n    .inline-block{\n      display:inline-block;\n      margin:.2em;\n    }\n  </style>\n\n<div id=\"scheduleManager\">\n    <p>This is the scheduleManager </p>\n \n        <div *ngIf=\"coursesSchedule\">\n\n           <select id=\"selectedCourseName\" (change)=\"readAssetsInformation()\" [(ngModel)]=\"selectedCourseNameValue\" >\n                  <option *ngFor=\"let course of coursesSchedule | coursenames\"  > {{ course  }} </option>      \n            </select>\n\n            <select id=\"selectedCourseDegree\" (change)=\"readAssetsInformation()\" [(ngModel)]=\"selectedCourseDegreeValue\">\n                    <option *ngFor=\"let course of coursesSchedule | coursedegrees\" > {{ course  }} </option>      \n            </select>\n\n            <select id=\"selectedCourseTerm\" (change)=\"readAssetsInformation()\" [(ngModel)]=\"selectedCourseTermValue\">\n                    <option *ngFor=\"let course of coursesSchedule | courseterms\" > {{ course  }} </option>      \n            </select>\n           \n            <div *ngIf=\"assetsThere\">\n                <button type=\"button\" (click)=\"deleteAsset()\"> {{selectedCourseNameValue + ' ' + selectedCourseDegreeValue + ' ' + selectedCourseTermValue}} löschen </button>\n                <button type=\"button\" (click)=\"downloadAsset()\"> {{selectedCourseNameValue + ' ' + selectedCourseDegreeValue + ' ' + selectedCourseTermValue}} herunterladen </button>    \n            </div>\n            <div *ngIf=\"!assetsThere\">\n             \n                 \n              <input type=\"file\" (change)=\"uploadAsset($event)\" placeholder=\"Upload file\" accept=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/xml\">\n                       \n               \n            </div>\n       \n        </div>\n</div>\n\n\n"

/***/ }),

/***/ "./src/assetsmanager/scheduleuploadassetsmanager.app.module.ts":
/*!*********************************************************************!*\
  !*** ./src/assetsmanager/scheduleuploadassetsmanager.app.module.ts ***!
  \*********************************************************************/
/*! exports provided: ScheduleUploadAssetsManagerApplicationModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ScheduleUploadAssetsManagerApplicationModule", function() { return ScheduleUploadAssetsManagerApplicationModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _frontendutilities_src_services_frontendutilities_app_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @frontendutilities/src/services/frontendutilities.app.module */ "../frontendutilities/src/services/frontendutilities.app.module.ts");
/* harmony import */ var _courseschedule_assetsmanager_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./courseschedule.assetsmanager.component */ "./src/assetsmanager/courseschedule.assetsmanager.component.ts");
/* harmony import */ var angular_file__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! angular-file */ "./node_modules/angular-file/index.js");
/* harmony import */ var angular_file__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(angular_file__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var _upload_upload_assetsmanager_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../upload/upload.assetsmanager.component */ "./src/upload/upload.assetsmanager.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};







var ScheduleUploadAssetsManagerApplicationModule = /** @class */ (function () {
    function ScheduleUploadAssetsManagerApplicationModule() {
    }
    ScheduleUploadAssetsManagerApplicationModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _courseschedule_assetsmanager_component__WEBPACK_IMPORTED_MODULE_4__["CourseScheduleAssetsManager"],
                _upload_upload_assetsmanager_component__WEBPACK_IMPORTED_MODULE_6__["UploadAssetsManager"]
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                angular_file__WEBPACK_IMPORTED_MODULE_5__["ngfModule"],
                _frontendutilities_src_services_frontendutilities_app_module__WEBPACK_IMPORTED_MODULE_3__["FrontendUtilitiesApplicationModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormsModule"]
            ],
            bootstrap: [_courseschedule_assetsmanager_component__WEBPACK_IMPORTED_MODULE_4__["CourseScheduleAssetsManager"]]
        })
    ], ScheduleUploadAssetsManagerApplicationModule);
    return ScheduleUploadAssetsManagerApplicationModule;
}());



/***/ }),

/***/ "./src/entities/parameter/assets/request/stock/assets.course.stock.request.parameter.ts":
/*!**********************************************************************************************!*\
  !*** ./src/entities/parameter/assets/request/stock/assets.course.stock.request.parameter.ts ***!
  \**********************************************************************************************/
/*! exports provided: CourseAssetsFileRequestParameter */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CourseAssetsFileRequestParameter", function() { return CourseAssetsFileRequestParameter; });
/* harmony import */ var _assets_stock_request_parameter__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./assets.stock.request.parameter */ "./src/entities/parameter/assets/request/stock/assets.stock.request.parameter.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var CourseAssetsFileRequestParameter = /** @class */ (function (_super) {
    __extends(CourseAssetsFileRequestParameter, _super);
    function CourseAssetsFileRequestParameter() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return CourseAssetsFileRequestParameter;
}(_assets_stock_request_parameter__WEBPACK_IMPORTED_MODULE_0__["AssetsStockRequestParameter"]));



/***/ }),

/***/ "./src/entities/parameter/assets/request/stock/assets.stock.request.parameter.ts":
/*!***************************************************************************************!*\
  !*** ./src/entities/parameter/assets/request/stock/assets.stock.request.parameter.ts ***!
  \***************************************************************************************/
/*! exports provided: AssetsStockRequestParameter */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AssetsStockRequestParameter", function() { return AssetsStockRequestParameter; });
/* harmony import */ var _frontendutilities_src_services_entities_Parameter_courserequestparameter__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @frontendutilities/src/services/entities/Parameter/courserequestparameter */ "../frontendutilities/src/services/entities/Parameter/courserequestparameter.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

/**
 * This class defines
 * the class, that describes a single request
 * to an seets file (Cours eor Lecture Asset)
 */
var AssetsStockRequestParameter = /** @class */ (function (_super) {
    __extends(AssetsStockRequestParameter, _super);
    function AssetsStockRequestParameter() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return AssetsStockRequestParameter;
}(_frontendutilities_src_services_entities_Parameter_courserequestparameter__WEBPACK_IMPORTED_MODULE_0__["CourseRequestParameter"]));



/***/ }),

/***/ "./src/entities/parameter/assets/request/upload/assets.upload.request.parameter.ts":
/*!*****************************************************************************************!*\
  !*** ./src/entities/parameter/assets/request/upload/assets.upload.request.parameter.ts ***!
  \*****************************************************************************************/
/*! exports provided: UploadAssetsFileRequestParameter */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UploadAssetsFileRequestParameter", function() { return UploadAssetsFileRequestParameter; });
/* harmony import */ var _frontendutilities_src_services_entities_Parameter_courserequestparameter__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @frontendutilities/src/services/entities/Parameter/courserequestparameter */ "../frontendutilities/src/services/entities/Parameter/courserequestparameter.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

/**
 * This class defines
 * the class, that describes a single request
 * to an seets file (Cours eor Lecture Asset)
 */
var UploadAssetsFileRequestParameter = /** @class */ (function (_super) {
    __extends(UploadAssetsFileRequestParameter, _super);
    function UploadAssetsFileRequestParameter() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return UploadAssetsFileRequestParameter;
}(_frontendutilities_src_services_entities_Parameter_courserequestparameter__WEBPACK_IMPORTED_MODULE_0__["CourseRequestParameter"]));



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * In development mode, for easier debugging, you can ignore zone related error
 * stack frames such as `zone.run`/`zoneDelegate.invokeTask` by importing the
 * below file. Don't forget to comment it out in production mode
 * because it will have a performance impact when errors are thrown
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _assetsmanager_scheduleuploadassetsmanager_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./assetsmanager/scheduleuploadassetsmanager.app.module */ "./src/assetsmanager/scheduleuploadassetsmanager.app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_assetsmanager_scheduleuploadassetsmanager_app_module__WEBPACK_IMPORTED_MODULE_2__["ScheduleUploadAssetsManagerApplicationModule"])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ "./src/services/files/assets/assets.stock.files.service.ts":
/*!*****************************************************************!*\
  !*** ./src/services/files/assets/assets.stock.files.service.ts ***!
  \*****************************************************************/
/*! exports provided: AssetsStockService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AssetsStockService", function() { return AssetsStockService; });
/* harmony import */ var _frontendutilities_src_services_REST_rest_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @frontendutilities/src/services/REST/rest.service */ "../frontendutilities/src/services/REST/rest.service.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var AssetsStockService = /** @class */ (function (_super) {
    __extends(AssetsStockService, _super);
    function AssetsStockService(assetsStockURL) {
        return _super.call(this, assetsStockURL) || this;
    }
    AssetsStockService.prototype.requestFileAssetsStock = function (courseAssetsFileParameter) {
        return this.postRESTObject(courseAssetsFileParameter);
    };
    return AssetsStockService;
}(_frontendutilities_src_services_REST_rest_service__WEBPACK_IMPORTED_MODULE_0__["RESTService"]));



/***/ }),

/***/ "./src/services/files/assets/courseschedule.assets.stock.files.service.ts":
/*!********************************************************************************!*\
  !*** ./src/services/files/assets/courseschedule.assets.stock.files.service.ts ***!
  \********************************************************************************/
/*! exports provided: CourseScheduleAssetsStockService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CourseScheduleAssetsStockService", function() { return CourseScheduleAssetsStockService; });
/* harmony import */ var _assets_stock_files_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./assets.stock.files.service */ "./src/services/files/assets/assets.stock.files.service.ts");
/* harmony import */ var _frontendutilities_src_environments_environment__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @frontendutilities/src/environments/environment */ "../frontendutilities/src/environments/environment.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


var CourseScheduleAssetsStockService = /** @class */ (function (_super) {
    __extends(CourseScheduleAssetsStockService, _super);
    function CourseScheduleAssetsStockService() {
        return _super.call(this, _frontendutilities_src_environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].API_URL + "/Assets/CourseSchedule") || this;
    }
    return CourseScheduleAssetsStockService;
}(_assets_stock_files_service__WEBPACK_IMPORTED_MODULE_0__["AssetsStockService"]));



/***/ }),

/***/ "./src/services/files/delete/courseschedule.delete.files.service.ts":
/*!**************************************************************************!*\
  !*** ./src/services/files/delete/courseschedule.delete.files.service.ts ***!
  \**************************************************************************/
/*! exports provided: DeleteCourseScheduleFileService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteCourseScheduleFileService", function() { return DeleteCourseScheduleFileService; });
/* harmony import */ var _delete_files_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./delete.files.service */ "./src/services/files/delete/delete.files.service.ts");
/* harmony import */ var _frontendutilities_src_environments_environment__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @frontendutilities/src/environments/environment */ "../frontendutilities/src/environments/environment.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


var DeleteCourseScheduleFileService = /** @class */ (function (_super) {
    __extends(DeleteCourseScheduleFileService, _super);
    function DeleteCourseScheduleFileService() {
        return _super.call(this, _frontendutilities_src_environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].API_URL + "/Delete/Schedule/Course") || this;
    }
    return DeleteCourseScheduleFileService;
}(_delete_files_service__WEBPACK_IMPORTED_MODULE_0__["DeleteFileService"]));



/***/ }),

/***/ "./src/services/files/delete/delete.files.service.ts":
/*!***********************************************************!*\
  !*** ./src/services/files/delete/delete.files.service.ts ***!
  \***********************************************************/
/*! exports provided: DeleteFileService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteFileService", function() { return DeleteFileService; });
/* harmony import */ var _frontendutilities_src_services_REST_rest_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @frontendutilities/src/services/REST/rest.service */ "../frontendutilities/src/services/REST/rest.service.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var DeleteFileService = /** @class */ (function (_super) {
    __extends(DeleteFileService, _super);
    function DeleteFileService(deleteAssetsURL) {
        return _super.call(this, deleteAssetsURL) || this;
    }
    DeleteFileService.prototype.deleteFile = function (courseRequestParameter) {
        return this.postRESTObject(courseRequestParameter);
    };
    return DeleteFileService;
}(_frontendutilities_src_services_REST_rest_service__WEBPACK_IMPORTED_MODULE_0__["RESTService"]));



/***/ }),

/***/ "./src/services/files/download/courseschedule.download.files.service.ts":
/*!******************************************************************************!*\
  !*** ./src/services/files/download/courseschedule.download.files.service.ts ***!
  \******************************************************************************/
/*! exports provided: DownloadCourseScheduleFileService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DownloadCourseScheduleFileService", function() { return DownloadCourseScheduleFileService; });
/* harmony import */ var _download_files_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./download.files.service */ "./src/services/files/download/download.files.service.ts");
/* harmony import */ var _frontendutilities_src_environments_environment__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @frontendutilities/src/environments/environment */ "../frontendutilities/src/environments/environment.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


var DownloadCourseScheduleFileService = /** @class */ (function (_super) {
    __extends(DownloadCourseScheduleFileService, _super);
    function DownloadCourseScheduleFileService() {
        return _super.call(this, _frontendutilities_src_environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].API_URL + "/Download/Schedule/Course") || this;
    }
    return DownloadCourseScheduleFileService;
}(_download_files_service__WEBPACK_IMPORTED_MODULE_0__["DownloadFileService"]));



/***/ }),

/***/ "./src/services/files/download/download.files.service.ts":
/*!***************************************************************!*\
  !*** ./src/services/files/download/download.files.service.ts ***!
  \***************************************************************/
/*! exports provided: DownloadFileService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DownloadFileService", function() { return DownloadFileService; });
/* harmony import */ var _frontendutilities_src_services_REST_rest_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @frontendutilities/src/services/REST/rest.service */ "../frontendutilities/src/services/REST/rest.service.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var DownloadFileService = /** @class */ (function (_super) {
    __extends(DownloadFileService, _super);
    function DownloadFileService(downloadAssetsURL) {
        return _super.call(this, downloadAssetsURL) || this;
    }
    DownloadFileService.prototype.downloadFile = function (courseRequestParameter) {
        return this.postRESTObjectNonParsed(courseRequestParameter);
    };
    return DownloadFileService;
}(_frontendutilities_src_services_REST_rest_service__WEBPACK_IMPORTED_MODULE_0__["RESTService"]));



/***/ }),

/***/ "./src/services/files/upload/courseschedule.upload.files.service.ts":
/*!**************************************************************************!*\
  !*** ./src/services/files/upload/courseschedule.upload.files.service.ts ***!
  \**************************************************************************/
/*! exports provided: UploadCourseScheduleFileService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UploadCourseScheduleFileService", function() { return UploadCourseScheduleFileService; });
/* harmony import */ var _upload_files_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./upload.files.service */ "./src/services/files/upload/upload.files.service.ts");
/* harmony import */ var _frontendutilities_src_environments_environment__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @frontendutilities/src/environments/environment */ "../frontendutilities/src/environments/environment.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();


var UploadCourseScheduleFileService = /** @class */ (function (_super) {
    __extends(UploadCourseScheduleFileService, _super);
    function UploadCourseScheduleFileService() {
        return _super.call(this, _frontendutilities_src_environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].API_URL + "/Upload/Schedule/Course") || this;
    }
    return UploadCourseScheduleFileService;
}(_upload_files_service__WEBPACK_IMPORTED_MODULE_0__["UploadFileService"]));



/***/ }),

/***/ "./src/services/files/upload/upload.files.service.ts":
/*!***********************************************************!*\
  !*** ./src/services/files/upload/upload.files.service.ts ***!
  \***********************************************************/
/*! exports provided: UploadFileService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UploadFileService", function() { return UploadFileService; });
/* harmony import */ var _frontendutilities_src_services_REST_rest_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @frontendutilities/src/services/REST/rest.service */ "../frontendutilities/src/services/REST/rest.service.ts");
var __extends = (undefined && undefined.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var UploadFileService = /** @class */ (function (_super) {
    __extends(UploadFileService, _super);
    function UploadFileService(uploadAssetsURL) {
        return _super.call(this, uploadAssetsURL) || this;
    }
    UploadFileService.prototype.uploadFile = function (courseRequestParameter) {
        return this.postRESTObject(courseRequestParameter);
    };
    return UploadFileService;
}(_frontendutilities_src_services_REST_rest_service__WEBPACK_IMPORTED_MODULE_0__["RESTService"]));



/***/ }),

/***/ "./src/upload/upload.assetsmanager.component.ts":
/*!******************************************************!*\
  !*** ./src/upload/upload.assetsmanager.component.ts ***!
  \******************************************************/
/*! exports provided: UploadAssetsManager */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UploadAssetsManager", function() { return UploadAssetsManager; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var UploadAssetsManager = /** @class */ (function () {
    function UploadAssetsManager() {
    }
    UploadAssetsManager = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'uploadassetsmanager-root',
            template: __webpack_require__(/*! ./uploadmanager.component.html */ "./src/upload/uploadmanager.component.html")
        })
    ], UploadAssetsManager);
    return UploadAssetsManager;
}());



/***/ }),

/***/ "./src/upload/uploadmanager.component.html":
/*!*************************************************!*\
  !*** ./src/upload/uploadmanager.component.html ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<script src=\"../../js/uploader.js\"></script>\n\n<input type=\"button\" type=\"file\" onChange=  \"uploadAsset2(this)\"> hochladen\n\n\n            "

/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! /Users/dustin/photon/ManagingTool/scheduleassets/src/main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map