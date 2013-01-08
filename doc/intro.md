# Introduction to kiosk

KioskSolution
=============

This document describes the **KioskSolution** web application. This application will be used by the shops in a mall who are interested in displaying information about their store on a kiosk. As mentioned before, it is a web application, requiring no installation anywhere.

Every kiosk has a web browser, and this web browser is a host for KioskSolution. The browser on the kiosk has no close button, no toolbar and even no place for typing a URL.

Features
--------

 - The admin access to the KioskSolution must be secured using a login page.
 - Once the user successfully logs in, then he should be taken to the Main Page
 - A shop owner should be able to register some of his shops for using the KioskSolution
 - A shop owner should be able to register advertisements to be displayed with different pages of the application

### UI

There should be some margin on all four sides of the screen, for displaying the advertisements, and only the remaining screen estate should be used for the KioskSolution application.

For the PoC (proof of concept), show a button in the KioskSolution application. When a user clicks the button, display one advertisement in the top and bottom margin of the application.

Also, show a textbox in the KioskSolution application and display an advertisement in the left and right margins based on the number entered in the text box.

### Development Stack

Clojure (for server side) and ClojureScript (for client side, JS part) will be used for developing the fully featured KioskSolution applicaton. MongoDB will be used for persistence.

Following libraries from the Clojure / ClojureScript ecosystem will be used -

 - [Monger](http://clojuremongodb.info/) - a Clojure MongoDB client.
 - [Compojure](https://github.com/weavejester/compojure) - a small routing library for Ring.
 - [Enlive](https://github.com/cgrand/enlive) - a selector-based (à la CSS) templating and transformation system for Clojure.
 - [Friend](https://github.com/cemerick/friend) - an extensible authentication and authorization library for Clojure Ring web applications and services.
 - [Enfocus](https://github.com/ckirkendall/enfocus) - a DOM manipulation and templating library for ClojureScript inspired by Enlive.
